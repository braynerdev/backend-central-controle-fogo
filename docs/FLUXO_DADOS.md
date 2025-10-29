# 🔄 Fluxo de Dados

## Visão Geral

Este documento detalha os principais fluxos de dados no sistema Backend Central Controle de Fogo.

---

## 1. Fluxo de Autenticação (Login)

**Endpoint:** `POST /api/auth/login`

**Sequência:**
1. Cliente envia username e password
2. Controller recebe e valida entrada
3. Service busca usuário no banco via username normalizado
4. Service valida senha com BCrypt
5. Service gera JWT token assinado com RSA
6. Service gera refresh token (UUID)
7. Service atualiza usuário com refresh token e expiração
8. Controller retorna token e refresh token

**Request:**
```json
{
  "username": "bombeiro01",
  "password": "senha123"
}
```

**Response:**
```json
{
  "success": true,
  "token": "eyJhbGc...",
  "refreshToken": "uuid...",
  "message": "Login realizado com sucesso"
}
```

---

## 2. Fluxo de Refresh Token

**Endpoint:** `POST /api/auth/refresh-token/`

**Sequência:**
1. Cliente envia username e refresh token
2. Service busca usuário
3. Service valida refresh token e expiração
4. Service gera novo JWT token
5. Controller retorna novo token

---

## 3. Fluxo de Validação de Requisições

**Toda requisição autenticada:**
1. Spring Security extrai JWT do header Authorization
2. JWT Filter valida token com chave pública RSA
3. Extrai claims (id, roles) do token
4. Configura SecurityContext com autenticação
5. Verifica permissões necessárias
6. Permite acesso ao Controller

---

## 4. Cadastro de Usuário

**Endpoint:** `POST /api/auth/created/user`

**Sequência:**
1. Admin envia dados do novo usuário
2. Service valida CPF, email e username únicos
3. Service busca Battalion por ID
4. Service busca Patent por ID
5. Service cria Address
6. Service cria User (hash senha com BCrypt)
7. Service salva relacionamento UserRole
8. Controller retorna sucesso

**Request:**
```json
{
  "username": "bombeiro01",
  "email": "bombeiro@email.com",
  "cpf": "12345678901",
  "patentId": 1,
  "battalionId": 1,
  "address": {...}
}
```

---

## 5. Criação de Ocorrência

**Endpoint:** `POST /api/occurrences`

**Sequência:**
1. Bombeiro envia dados da ocorrência
2. JWT validado pelo Spring Security
3. Service mapeia DTO para Entity (ModelMapper)
4. Service cria Address
5. Service cria Occurrence com status EM_ATENDIMENTO
6. Service salva no banco
7. Controller retorna 201 Created

**Request:**
```json
{
  "occurrenceHasVictims": true,
  "occurrenceRequester": "Maria Santos",
  "occurrenceRequesterPhoneNumber": "81988887777",
  "occurrenceSubType": "Incêndio residencial",
  "address": {...}
}
```

---

## 6. Conclusão de Ocorrência

**Endpoint:** `PUT /api/occurrences/complete/{id}`

**Sequência:**
1. Bombeiro envia detalhes finais da ocorrência
2. Service busca occurrence por ID
3. Service atualiza campos:
   - details
   - latitude/longitude
   - arrival_time
   - status = CONCLUIDA
4. Service cria vínculos OccurrenceUsers para cada bombeiro
5. Service salva occurrence
6. Controller retorna 200 OK

**Request:**
```json
{
  "occurrenceDetails": "Incêndio controlado após 2h...",
  "latitude": -8.0476,
  "longitude": -34.8770,
  "occurrenceArrivalTime": "2025-10-29T15:30:00",
  "usersId": [1, 5, 8, 12]
}
```

---

## 7. Atualização de Ocorrência

**Endpoint:** `PUT /api/occurrences/{id}`

**Sequência:**
1. Cliente envia campos a atualizar
2. Service busca occurrence
3. Service atualiza apenas campos não-nulos
4. Service salva occurrence
5. Controller retorna ResponseDTO

---

## 8. Paginação Genérica

**Endpoint:** `GET /api/*/paginator`

**Query Params:**
- `page` (default: 1)
- `size` (default: 10)
- `filterGeneric` (opcional)
- `active` (default: true)

**Sequência:**
1. Cliente solicita página
2. Service cria Pageable (page-1, size)
3. Service constrói query com filtros:
   - WHERE active = ?
   - AND (campo LIKE %filter% OR campo2 LIKE %filter%)
   - LIMIT size OFFSET (page-1)*size
4. Service executa query no banco
5. Service recebe Page<Entity>
6. Service mapeia para DTOs
7. Service constrói PaginatorGeneric
8. Controller retorna dados paginados

**Response:**
```json
{
  "items": [...],
  "totalItems": 50,
  "totalPages": 5,
  "currentPage": 1,
  "itemsPerPage": 10
}
```

---

## 9. Soft Delete (Desativação)

**Endpoint:** `PUT /api/*/deactivate/{id}`

**Sequência:**
1. Admin solicita desativação
2. Service busca entidade por ID
3. Service seta active = false
4. Service salva entidade
5. Controller retorna sucesso

**Nota:** Registro permanece no banco para histórico.

---

## 10. Ativação

**Endpoint:** `PUT /api/*/activate/{id}`

**Sequência:**
1. Admin solicita ativação
2. Service busca entidade inativa
3. Service seta active = true
4. Service salva entidade
5. Controller retorna sucesso

---

## 11. Logout

**Endpoint:** `POST /api/auth/logout/{id}`

**Sequência:**
1. Cliente solicita logout
2. Service busca user por ID
3. Service limpa refreshToken e expiration
4. Service salva user
5. Controller retorna 200 OK

**Nota:** JWT continua válido até expirar (stateless).

---

## 12. Criação de Batalhão

**Endpoint:** `POST /api/battalion/created`

**Sequência:**
1. Admin envia dados do batalhão
2. Service valida email único
3. Service cria Address
4. Service cria Battalion vinculado ao address
5. Service salva no banco
6. Controller retorna sucesso

---

## 13. Tratamento de Erros

**Padrão de resposta:**

**400 Bad Request:**
```json
{
  "mensagem": "Dados inválidos"
}
```

**404 Not Found:**
```json
{
  "mensagem": "Registro não encontrado"
}
```

**401 Unauthorized:**
- Retornado automaticamente pelo Spring Security

**500 Internal Server Error:**
```json
{
  "mensagem": "Erro interno do servidor"
}
```

---

## Mapeamento DTO ↔ Entity

**ModelMapper** é usado para conversão automática:

```java
// DTO -> Entity
Occurrence occurrence = modelMapper.map(dto, Occurrence.class);

// Entity -> DTO
UserResponseDTO response = modelMapper.map(user, UserResponseDTO.class);
```

---

## Transações

**Spring gerencia transações automaticamente:**

- `@Transactional` em Services críticos
- Rollback automático em exceptions
- Commit ao final do método sem erros

---

## Validações

**Bean Validation (Jakarta):**

```java
@NotBlank(message = "Campo obrigatório")
@Size(min = 3, max = 50)
@Email
@CPF (customizado)
```

**Validação ocorre antes de chegar no Service via `@Valid`**

---

## Auditoria Automática

**Base class com @PrePersist e @PreUpdate:**

```java
@PrePersist
protected void onCreate() {
    createdAt = OffsetDateTime.now();
    updatedAt = OffsetDateTime.now();
}

@PreUpdate
protected void onUpdate() {
    updatedAt = OffsetDateTime.now();
}
```

---

## Conclusão

Os fluxos seguem padrão consistente:
1. Controller recebe requisição
2. Valida entrada
3. Delega para Service
4. Service aplica regras de negócio
5. Repository persiste dados
6. Service retorna resultado
7. Controller formata resposta HTTP
