# 🗄️ Modelo de Dados

## Visão Geral

O sistema utiliza **PostgreSQL** como banco de dados relacional com mapeamento ORM via **Hibernate/JPA**.

---

## Diagrama Entidade-Relacionamento

```
                    ┌─────────────────┐
                    │     Patent      │
                    ├─────────────────┤
                    │ PK id           │
                    │    name         │
                    │    active       │
                    │    created_at   │
                    │    updated_at   │
                    └────────┬────────┘
                             │
                             │ 1:N
                             │
┌─────────────────┐         │         ┌─────────────────┐
│   Battalion     │         │         │      Roles      │
├─────────────────┤         │         ├─────────────────┤
│ PK id           │         │         │ PK id           │
│    name         │         │         │    name         │
│    phone_number │         │         │    description  │
│    email        │         │         │    active       │
│ FK endereco_id  │         │         │    created_at   │
│    active       │         │         │    updated_at   │
│    created_at   │         │         └────────┬────────┘
│    updated_at   │         │                  │
└────────┬────────┘         │                  │
         │                  │                  │ N:M
         │ 1:N              ▼                  │
         │         ┌─────────────────┐         │
         │         │      User       │         │
         │         ├─────────────────┤         │
         └────────►│ PK id           │◄────────┘
                   │    username     │         │
                   │    norm_username│         │
                   │    email        │         │
                   │    norm_email   │    ┌────▼───────────┐
                   │    password     │    │   UserRoles    │
                   │    phone_number │    ├────────────────┤
                   │    cpf          │    │ PK id          │
                   │    matriculates │    │ FK user_id     │
                   │    name         │    │ FK role_id     │
                   │    norm_name    │    │    active      │
                   │    date_birth   │    │    created_at  │
                   │    gender       │    │    updated_at  │
                   │    refresh_token│    └────────────────┘
                   │    refresh_exp  │
                   │    using_def_pwd│
                   │    email_conf   │         ┌────────────────┐
                   │    phone_conf   │         │    Address     │
                   │ FK patent_id    │         ├────────────────┤
                   │ FK battalion_id │◄────────│ PK id          │
                   │ FK address_id   │   1:1   │    street      │
                   │    active       │         │    number      │
                   │    created_at   │         │    complement  │
                   │    updated_at   │         │    neighborhood│
                   └────────┬────────┘         │    city        │
                            │                  │    state       │
                            │                  │    zip_code    │
                            │ N:M              │    active      │
                            │                  │    created_at  │
                            │                  │    updated_at  │
                            │                  └────────────────┘
                            │
                   ┌────────▼─────────┐
                   │ OccurrenceUsers  │
                   ├──────────────────┤
                   │ PK id            │
                   │ FK occurrence_id │
                   │ FK user_id       │
                   │    active        │
                   │    created_at    │
                   │    updated_at    │
                   └────────┬─────────┘
                            │
                            │ N:1
                            │
                   ┌────────▼─────────┐
                   │   Occurrence     │
                   ├──────────────────┤
                   │ PK id            │
                   │    has_victims   │
                   │    requester     │
                   │    req_phone     │
                   │    sub_type      │
                   │ FK address_id    │
                   │    status        │
                   │    details       │
                   │    latitude      │
                   │    longitude     │
                   │    arrival_time  │
                   │    active        │
                   │    created_at    │
                   │    updated_at    │
                   └──────────────────┘
```

---

## Tabelas e Relacionamentos

### 1. auth_user (Usuário/Bombeiro)

**Descrição:** Armazena informações dos bombeiros do sistema.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| username | VARCHAR(30) | UNIQUE, NOT NULL | Nome de usuário |
| normalized_username | VARCHAR(30) | UNIQUE, NOT NULL | Username normalizado (uppercase) |
| email | VARCHAR(100) | UNIQUE, NOT NULL | Email do usuário |
| normalized_email | VARCHAR(100) | UNIQUE, NOT NULL | Email normalizado |
| password | VARCHAR(256) | NOT NULL | Hash da senha (BCrypt) |
| phone_number | VARCHAR(11) | NOT NULL | Telefone (apenas números) |
| cpf | CHAR(11) | UNIQUE, NOT NULL | CPF (apenas números) |
| matriculates | VARCHAR(30) | UNIQUE, NOT NULL | Matrícula do bombeiro |
| name | VARCHAR(200) | NOT NULL | Nome completo |
| normalized_name | VARCHAR(200) | NOT NULL | Nome normalizado |
| date_birth | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de nascimento |
| gender | CHAR(1) | NOT NULL | Gênero (M/F) |
| refresh_token | VARCHAR(256) | NULL | Token de refresh JWT |
| refresh_token_expiration | TIMESTAMP WITH TIMEZONE | NULL | Expiração do refresh token |
| using_default_password | BOOLEAN | NOT NULL | Flag senha padrão |
| email_confirmed | BOOLEAN | NOT NULL | Email confirmado |
| phone_number_confirmed | BOOLEAN | NOT NULL | Telefone confirmado |
| patent_id | BIGINT | FK, NOT NULL | Referência à patente |
| battalion_id | BIGINT | FK, NOT NULL | Referência ao batalhão |
| address_id | BIGINT | FK | Referência ao endereço |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Índices:**
- PK: `id`
- UNIQUE: `username`, `normalized_username`, `email`, `normalized_email`, `cpf`, `matriculates`
- FK: `patent_id`, `battalion_id`, `address_id`

**Relacionamentos:**
- N:1 com `patent`
- N:1 com `battalion`
- 1:1 com `address`
- N:M com `roles` (via `auth_user_roles`)
- N:M com `occurrence` (via `occurrence_users`)

---

### 2. auth_roles (Perfis de Acesso)

**Descrição:** Define os perfis de acesso do sistema.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| name | VARCHAR(50) | UNIQUE, NOT NULL | Nome do perfil |
| description | VARCHAR(150) | NOT NULL | Descrição do perfil |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Valores:**
- `admin`: Administrador do sistema
- `superuser`: Super usuário

**Relacionamentos:**
- N:M com `user` (via `auth_user_roles`)

---

### 3. auth_user_roles (Tabela Associativa)

**Descrição:** Relacionamento muitos-para-muitos entre usuários e roles.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| user_id | BIGINT | FK, NOT NULL | Referência ao usuário |
| role_id | BIGINT | FK, NOT NULL | Referência ao role |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Índices:**
- PK: `id`
- FK: `user_id`, `role_id`
- UNIQUE: `(user_id, role_id)`

---

### 4. occurrence (Ocorrência)

**Descrição:** Registros de ocorrências/chamados de incêndio.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| occurrence_has_victims | BOOLEAN | NOT NULL | Indica se há vítimas |
| occurrence_requester | VARCHAR(50) | NOT NULL | Nome do solicitante |
| occurrence_requester_phone_number | VARCHAR(11) | NOT NULL | Telefone do solicitante |
| occurrence_sub_type | VARCHAR(100) | NOT NULL | Subtipo da ocorrência |
| address_id | BIGINT | FK, NOT NULL | Referência ao endereço |
| status | VARCHAR(30) | NOT NULL | Status da ocorrência (ENUM) |
| occurrence_details | VARCHAR(2000) | NULL | Detalhes da ocorrência |
| latitude | DECIMAL(10,7) | NULL | Latitude da ocorrência |
| longitude | DECIMAL(11,7) | NULL | Longitude da ocorrência |
| occurrence_arrival_time | TIMESTAMP | NULL | Horário de chegada |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Enum Status:**
```java
public enum OccurrenceStatus {
    AGUARDANDO_ATENDIMENTO,  // Aguardando equipe
    EM_ATENDIMENTO,          // Em curso
    CONCLUIDA,               // Finalizada
    FALSO_ALARME,            // Falso alarme
    CANCELADA                // Cancelada
}
```

**Relacionamentos:**
- 1:1 com `address`
- N:M com `user` (via `occurrence_users`)

---

### 5. occurrence_users (Tabela Associativa)

**Descrição:** Relaciona bombeiros envolvidos em cada ocorrência.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| occurrence_id | BIGINT | FK, NOT NULL | Referência à ocorrência |
| user_id | BIGINT | FK, NOT NULL | Referência ao bombeiro |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

---

### 6. battalion (Batalhão)

**Descrição:** Unidades do Corpo de Bombeiros.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| name | VARCHAR(100) | NOT NULL | Nome do batalhão |
| phone_number | VARCHAR(11) | NOT NULL | Telefone |
| email | VARCHAR(100) | UNIQUE, NOT NULL | Email institucional |
| endereco_id | BIGINT | FK | Referência ao endereço |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Relacionamentos:**
- 1:N com `user`
- 1:1 com `address`

---

### 7. patent (Patente Militar)

**Descrição:** Hierarquia militar dos bombeiros.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| name | VARCHAR(100) | UNIQUE, NOT NULL | Nome da patente |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Exemplos de Patentes:**
- Soldado
- Cabo
- Sargento
- Subtenente
- Tenente
- Capitão
- Major
- Tenente Coronel
- Coronel

**Relacionamentos:**
- 1:N com `user`

---

### 8. address (Endereço)

**Descrição:** Endereços de batalhões, usuários e ocorrências.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| street | VARCHAR(100) | NOT NULL | Rua/Avenida |
| number | INTEGER | NOT NULL | Número |
| complement | VARCHAR(100) | NULL | Complemento |
| neighborhood | VARCHAR(100) | NOT NULL | Bairro |
| city | VARCHAR(100) | NOT NULL | Cidade |
| state | VARCHAR(100) | NOT NULL | Estado (sigla: PE, SP, etc) |
| zip_code | VARCHAR(8) | NOT NULL | CEP (apenas números) |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Relacionamentos:**
- 1:1 com `user`
- 1:1 com `battalion`
- 1:1 com `occurrence`

---

### 9. occurrence_type (Tipo de Ocorrência)

**Descrição:** Categorias principais de ocorrências.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| name | VARCHAR(100) | UNIQUE, NOT NULL | Nome do tipo |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Exemplos:**
- Incêndio Urbano
- Incêndio Florestal
- Salvamento
- Emergência Médica

---

### 10. occurrence_sub_type (Subtipo de Ocorrência)

**Descrição:** Subcategorias de ocorrências.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| name | VARCHAR(100) | NOT NULL | Nome do subtipo |
| occurrence_type_id | BIGINT | FK, NOT NULL | Tipo pai |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

---

### 11. vehicles (Veículos) - EM DESENVOLVIMENTO

**Descrição:** Viaturas do Corpo de Bombeiros.

| Coluna | Tipo | Constraints | Descrição |
|--------|------|-------------|-----------|
| id | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| name | VARCHAR(30) | UNIQUE, NOT NULL | Identificação do veículo |
| battalion_id | BIGINT | FK, NOT NULL | Batalhão responsável |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Registro ativo |
| created_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de criação |
| updated_at | TIMESTAMP WITH TIMEZONE | NOT NULL | Data de atualização |

**Status:** Comentado no código, aguardando implementação completa.

---

## Classe Base

Todas as entidades herdam da classe abstrata `Base`:

```java
@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
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

**Benefícios:**
- Consistência em todas as tabelas
- Auditoria automática
- Suporte a soft delete
- Redução de código duplicado

---

## Estratégias de Normalização

### Campos Normalizados

Alguns campos possuem versão normalizada (uppercase) para facilitar buscas:

- `username` → `normalized_username`
- `email` → `normalized_email`
- `name` → `normalized_name`

**Exemplo:**
```
username: "bombeiro01"
normalized_username: "BOMBEIRO01"
```

**Vantagem:** Buscas case-insensitive eficientes.

---

## Integridade Referencial

### Cascade Operations

**CascadeType.ALL:**
- `user.address`: Ao salvar usuário, salva endereço
- `battalion.endereco`: Ao salvar batalhão, salva endereço

**CascadeType.REMOVE:**
- `user.roles`: Ao deletar usuário, remove relacionamentos
- `occurrence.users`: Ao deletar ocorrência, remove vínculos

### Orphan Removal

Utilizado em relacionamentos 1:N para remover automaticamente registros órfãos:

```java
@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
private List<UserRoles> roles;
```

---

## Índices e Performance

### Índices Automáticos

JPA cria automaticamente índices para:
- Chaves primárias (PK)
- Chaves estrangeiras (FK)
- Campos UNIQUE

### Índices Recomendados (Futuro)

Para melhor performance em queries frequentes:

```sql
-- Busca de usuários por nome
CREATE INDEX idx_user_normalized_name ON auth_user(normalized_name);

-- Busca de ocorrências por status
CREATE INDEX idx_occurrence_status ON occurrence(status);

-- Busca de ocorrências por data
CREATE INDEX idx_occurrence_created_at ON occurrence(created_at);

-- Geolocalização
CREATE INDEX idx_occurrence_location ON occurrence(latitude, longitude);
```

---

## Timezone e Timestamps

### Configuração

O sistema opera em **UTC** globalmente:

```properties
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
spring.jpa.properties.hibernate.type.preferred_datetime_jdbc_type=TIMESTAMP_WITH_TIMEZONE
```

**Tipos de Data:**
- `OffsetDateTime`: Datas com timezone
- `LocalDateTime`: Datas sem timezone (usado em occurrence_arrival_time)

---

## Migrações

### Estratégia Atual

```properties
spring.jpa.hibernate.ddl-auto=update
```

**Desenvolvimento:** Hibernate atualiza schema automaticamente.

### Estratégia Futura (Produção)

Recomendado usar ferramentas de migração:
- **Flyway**
- **Liquibase**

Vantagens:
- Versionamento de schema
- Histórico de mudanças
- Rollback controlado
- Deployment seguro

---

## Constraints e Validações

### Validações JPA

```java
@Entity
public class User {
    @Column(unique = true, nullable = false, length = 30)
    private String username;
    
    @Column(nullable = false, length = 11, columnDefinition = "CHAR(11)")
    private String cpf;
}
```

### Validações Bean Validation

```java
@NotBlank(message = "O nome é obrigatório")
@Size(min = 3, max = 50, message = "Nome entre 3 e 50 caracteres")
private String name;

@Email(message = "Email inválido")
private String email;
```

---

## Backup e Recuperação

### Recomendações

1. **Backup Diário:** PostgreSQL pg_dump
2. **Backup Incremental:** WAL archiving
3. **Retenção:** Mínimo 30 dias
4. **Testes de Restore:** Mensal
5. **Disaster Recovery:** Plano documentado

---

## Segurança de Dados

### Dados Sensíveis

- **Senhas:** Hash BCrypt (irreversível)
- **Tokens:** Armazenados temporariamente
- **CPF:** Apenas números, sem formatação
- **Telefone:** Apenas números

### LGPD/GDPR

Considerações:
- Soft delete permite recuperação
- Dados pessoais identificáveis
- Necessário consentimento explícito
- Direito ao esquecimento (hard delete futuro)

---

## Estatísticas e Métricas

### Estimativa de Volume

**Cenário: Médio Porte**

| Tabela | Registros/Ano | Crescimento |
|--------|---------------|-------------|
| auth_user | 500 | Baixo |
| occurrence | 10.000 | Alto |
| battalion | 20 | Muito Baixo |
| patent | 15 | Muito Baixo |
| address | 10.500 | Médio |

**Total estimado:** ~21.000 registros/ano

### Tamanho Estimado

- **Usuário:** ~1 KB
- **Ocorrência:** ~2 KB
- **Endereço:** ~500 bytes

**Banco total (1 ano):** ~25 MB (sem índices)

---

## Conclusão

O modelo de dados foi projetado para:

✅ Suportar operações do Corpo de Bombeiros  
✅ Manter histórico completo (soft delete)  
✅ Permitir auditoria e rastreabilidade  
✅ Escalar conforme crescimento  
✅ Garantir integridade referencial  
✅ Facilitar manutenção e evolução
