# 🏗️ Arquitetura do Sistema

## Visão Geral

O **Backend Central Controle de Fogo** segue uma arquitetura em camadas baseada no padrão **MVC (Model-View-Controller)** adaptado para APIs RESTful, utilizando o framework Spring Boot.

---

## Padrão Arquitetural

```
┌─────────────────────────────────────────────────────────────┐
│                    CAMADA DE APRESENTAÇÃO                    │
│                  (Controllers / REST API)                    │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │   Auth   │  │Occurrence│  │Battalion │  │  Patent  │   │
│  │Controller│  │Controller│  │Controller│  │Controller│   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                    CAMADA DE SEGURANÇA                       │
│              (Spring Security / JWT Filter)                  │
│  ┌────────────────────────────────────────────────────┐     │
│  │  • JWT Authentication                              │     │
│  │  • Role-based Authorization                        │     │
│  │  • CORS Configuration                              │     │
│  └────────────────────────────────────────────────────┘     │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                    CAMADA DE NEGÓCIOS                        │
│                    (Services / Business Logic)               │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │   Auth   │  │Occurrence│  │Battalion │  │  Patent  │   │
│  │ Service  │  │ Service  │  │ Service  │  │ Service  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                 CAMADA DE ACESSO A DADOS                     │
│                 (Repositories / Spring Data JPA)             │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │   User   │  │Occurrence│  │Battalion │  │  Patent  │   │
│  │Repository│  │Repository│  │Repository│  │Repository│   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                      CAMADA DE DADOS                         │
│                      (PostgreSQL Database)                   │
│  ┌────────────────────────────────────────────────────┐     │
│  │  Tables: auth_user, occurrence, battalion, etc.    │     │
│  └────────────────────────────────────────────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

---

## Camadas do Sistema

### 1. Camada de Apresentação (Controllers)

**Responsabilidade:** Expor endpoints REST e gerenciar requisições HTTP

**Componentes:**
- `AuthController`: Autenticação, registro e gerenciamento de usuários
- `OccurrenceController`: Operações CRUD de ocorrências
- `BattalionController`: Gerenciamento de batalhões
- `PatentController`: Gerenciamento de patentes
- `VehicleController`: Controle de veículos (em desenvolvimento)

**Características:**
- Validação de entrada via Bean Validation (`@Valid`)
- Tratamento de exceções HTTP
- Documentação via Swagger/OpenAPI
- Suporte a paginação

**Exemplo:**
```java
@RestController
@RequestMapping("/api/occurrences")
public class OccurrenceController {
    @Autowired
    private OccurrenceService occurrenceService;
    
    @PostMapping
    public ResponseEntity<?> createOccurrence(@Valid @RequestBody OccurrenceRequestDTO dto) {
        // Delega para camada de serviço
        boolean success = occurrenceService.createOccurrence(dto);
        return success ? ResponseEntity.ok() : ResponseEntity.badRequest().build();
    }
}
```

---

### 2. Camada de Segurança (Spring Security)

**Responsabilidade:** Autenticação, autorização e segurança da aplicação

**Componentes:**
- `SecurityConfig`: Configuração principal de segurança
- Filtros JWT
- Password Encoder (BCrypt)
- CORS Configuration

**Fluxo de Autenticação:**

```
┌─────────┐     ┌──────────────┐     ┌─────────────┐     ┌──────────┐
│ Request │────►│ JWT Filter   │────►│   Spring    │────►│Controller│
│         │     │              │     │  Security   │     │          │
└─────────┘     └──────────────┘     └─────────────┘     └──────────┘
                      │                     │
                      │ Validate            │ Check
                      │ Token               │ Roles
                      ▼                     ▼
                ┌─────────────┐       ┌──────────┐
                │ JWT Decoder │       │  Roles   │
                └─────────────┘       └──────────┘
```

**Configuração:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll()
                .anyRequest().authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
```

**Recursos:**
- JWT com chaves RSA (app.key / app.pub)
- Refresh Token para renovação
- Sessões Stateless
- CORS habilitado para todos os origins (desenvolvimento)

---

### 3. Camada de Negócios (Services)

**Responsabilidade:** Lógica de negócios, regras e orquestração

**Componentes:**
- `AuthService`: Lógica de autenticação e usuários
- `OccurrenceService`: Regras de negócio para ocorrências
- `BattalionService`: Lógica de batalhões
- `PatentService`: Gerenciamento de patentes

**Responsabilidades:**
- Validação de regras de negócio
- Orquestração entre repositories
- Mapeamento DTO ↔ Entity (via ModelMapper)
- Geração de tokens JWT
- Hash de senhas
- Implementação de soft delete

**Exemplo:**
```java
@Service
public class OccurrenceService {
    @Autowired
    private OccurrenceRepository occurrenceRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public boolean createOccurrence(OccurrenceRequestDTO dto) {
        // Validações de negócio
        // Mapeamento DTO -> Entity
        Occurrence occurrence = modelMapper.map(dto, Occurrence.class);
        occurrence.setStatus(OccurrenceStatus.EM_ATENDIMENTO);
        
        // Persistência
        occurrenceRepository.save(occurrence);
        return true;
    }
}
```

---

### 4. Camada de Acesso a Dados (Repositories)

**Responsabilidade:** Abstração de persistência e acesso ao banco

**Componentes:**
- `IRepositoryUser`: Operações de usuário
- `OccurrenceRepository`: Operações de ocorrências
- `IBattalionRepository`: Operações de batalhão
- `IPatentRepository`: Operações de patentes

**Tecnologia:**
- Spring Data JPA
- Hibernate como provedor JPA
- Queries derivadas de nomes de métodos
- Suporte a Specifications para filtros complexos
- Paginação nativa

**Exemplo:**
```java
@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
    Page<Occurrence> findByActiveTrue(Pageable pageable);
    
    @Query("SELECT o FROM Occurrence o WHERE o.status = :status")
    List<Occurrence> findByStatus(@Param("status") OccurrenceStatus status);
}
```

---

### 5. Camada de Dados (PostgreSQL)

**Banco de Dados:** PostgreSQL 12+

**Características:**
- Timezone: UTC
- Tipo preferencial de data: TIMESTAMP WITH TIMEZONE
- DDL Auto: update (desenvolvimento)

**Tabelas Principais:**
- `auth_user`: Usuários/bombeiros
- `auth_roles`: Perfis de acesso
- `auth_user_roles`: Relacionamento usuário-role
- `occurrence`: Ocorrências
- `battalion`: Batalhões
- `patent`: Patentes
- `address`: Endereços

---

## Padrões e Práticas

### 1. DTOs (Data Transfer Objects)

Separação clara entre entidades de domínio e objetos de transferência:

**Request DTOs:** Validação de entrada
```java
public class CadastreRequestDTO {
    @NotBlank
    private String username;
    
    @Email
    private String email;
    
    @Size(min = 11, max = 11)
    private String cpf;
    // ...
}
```

**Response DTOs:** Formatação de saída
```java
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private BattalionResponseDTO battalion;
    // Sem campos sensíveis como senha
}
```

---

### 2. Soft Delete

Todos os registros possuem flag `active` para deleção lógica:

```java
@Entity
public class Base {
    @Column(nullable = false)
    private boolean active = true;
    
    // Métodos de soft delete nos services
}
```

**Benefícios:**
- Manutenção de histórico
- Possibilidade de recuperação
- Integridade referencial preservada

---

### 3. Auditoria Automática

Todas as entidades herdam de `Base` com campos de auditoria:

```java
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private boolean active = true;
    
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}
```

---

### 4. Tratamento de Exceções

Respostas HTTP padronizadas:

```java
try {
    var result = service.operation();
    return ResponseEntity.ok(result);
} catch (NotFoundException e) {
    return ResponseEntity.notFound().build();
} catch (ValidationException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
} catch (Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
```

---

### 5. Paginação

Implementação consistente em todos os endpoints:

```java
@GetMapping("/paginator")
public ResponseEntity<PaginatorGeneric> getPaginated(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(required = false) String filter,
    @RequestParam(defaultValue = "true") boolean active) {
    
    Pageable pageable = PageRequest.of(page - 1, size);
    var result = service.getPaginated(pageable, active, filter);
    return ResponseEntity.ok(result);
}
```

---

## Segurança

### Autenticação JWT

**Fluxo:**
1. Cliente faz login com username/password
2. Sistema valida credenciais
3. Gera JWT assinado com chave RSA privada
4. Gera refresh token (UUID)
5. Retorna ambos tokens ao cliente
6. Cliente usa JWT no header Authorization
7. Sistema valida JWT com chave pública
8. Permite acesso aos recursos protegidos

**Estrutura do JWT:**
```json
{
  "sub": "bombeiro01",
  "id": 1,
  "roles": ["admin"],
  "exp": 1234567890,
  "iat": 1234567890
}
```

---

### Autorização

**Baseada em Roles:**
- `admin`: Acesso total ao sistema
- `superuser`: Acesso elevado (não implementado completamente)

**Futuramente:**
- Permissões granulares por recurso
- Hierarquia de roles mais complexa

---

## Escalabilidade

### Considerações

1. **Stateless:** Aplicação sem estado de sessão
2. **Horizontal Scaling:** Pode adicionar mais instâncias
3. **Database Connection Pool:** Configurável via Hikari
4. **Caching:** Não implementado (futuro)

### Melhorias Futuras

- Redis para cache de tokens
- Mensageria para eventos (RabbitMQ/Kafka)
- Load balancer
- Containerização (Docker)
- Orchestração (Kubernetes)

---

## Documentação Automática

### Swagger/OpenAPI

Configuração via SpringDoc:

```java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Central Controle Fogo")
            .version("1.0"))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
}
```

**Acesso:**
```
http://localhost:8080/swagger-ui/index.html
```

---

## Configurações

### Profiles

Atualmente em desenvolvimento com profile único. Futuro:
- `dev`: Desenvolvimento
- `staging`: Homologação
- `prod`: Produção

### Variáveis de Ambiente

Principais configurações:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `jwt.public.key`
- `jwt.private.key`
- `server.port`

---

## Dependências Principais

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring Security + OAuth2 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>
    
    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
```

---

## Conclusão

A arquitetura do sistema segue princípios SOLID e boas práticas de desenvolvimento Spring Boot, garantindo:

✅ Separação de responsabilidades  
✅ Baixo acoplamento  
✅ Alta coesão  
✅ Testabilidade  
✅ Manutenibilidade  
✅ Escalabilidade
