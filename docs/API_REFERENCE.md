# 📡 API Reference

## Base URL

```
http://localhost:8080/api
```

## Documentação Interativa

```
http://localhost:8080/swagger-ui/index.html
```

---

## Autenticação

Todas as rotas (exceto login e algumas públicas) requerem autenticação JWT.

**Header:**
```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🔐 Auth APIs

### 1. Login

**POST** `/api/auth/login`

Autentica usuário e retorna JWT token.

**Request Body:**
```json
{
  "username": "string",
  "password": "string"
}
```

**Response 200:**
```json
{
  "success": true,
  "token": "eyJhbGc...",
  "refreshToken": "uuid",
  "message": "Login realizado com sucesso"
}
```

**Response 404:**
```json
{
  "success": false,
  "message": "Usuário ou senha inválidos"
}
```

---

### 2. Buscar Usuário Autenticado

**GET** `/api/auth`

Retorna dados do usuário autenticado.

**Headers:** `Authorization: Bearer <token>`

**Response 200:**
```json
{
  "id": 1,
  "username": "bombeiro01",
  "email": "bombeiro@email.com",
  "name": "João Silva",
  "cpf": "12345678901",
  "phoneNumber": "81999999999",
  "gender": "M",
  "dateBirth": "1990-05-15T00:00:00Z",
  "battalion": {
    "id": 1,
    "name": "1º Batalhão"
  },
  "patent": {
    "id": 1,
    "name": "Soldado"
  },
  "roles": [
    {
      "id": 1,
      "name": "admin"
    }
  ]
}
```

---

### 3. Buscar Usuário por ID

**GET** `/api/auth/{id}`

**Path Params:**
- `id` (long): ID do usuário

**Response 200:** (mesmo formato do item 2)

**Response 404:**
```json
"Usuário não encontrado!"
```

---

### 4. Criar Usuário

**POST** `/api/auth/created/user`

Cadastra novo usuário/bombeiro.

**Request Body:**
```json
{
  "username": "bombeiro01",
  "email": "bombeiro@email.com",
  "phoneNumber": "81999999999",
  "cpf": "12345678901",
  "matriculates": "BM123456",
  "name": "João Silva",
  "dateBirth": "1990-05-15T00:00:00Z",
  "gender": "M",
  "password": "senha123",
  "patentId": 1,
  "battalionId": 1,
  "address": {
    "street": "Rua Exemplo",
    "number": 123,
    "complement": "Apto 101",
    "neighborhood": "Centro",
    "city": "Recife",
    "state": "PE",
    "zipCode": "50000000"
  }
}
```

**Response 201:**
```json
{
  "sucesso": true,
  "mensagem": "Usuário criado com sucesso"
}
```

**Response 404:**
```json
{
  "sucesso": false,
  "mensagem": "CPF já cadastrado" // ou outro erro
}
```

---

### 5. Refresh Token

**POST** `/api/auth/refresh-token/`

Renova o JWT token.

**Request Body:**
```json
{
  "username": "bombeiro01",
  "refreshToken": "uuid"
}
```

**Response 200:**
```json
{
  "token": "new-jwt-token"
}
```

---

### 6. Logout

**POST** `/api/auth/logout/{id}`

**Path Params:**
- `id` (long): ID do usuário

**Response 200:** (sem body)

---

### 7. Desativar Usuário

**PUT** `/api/auth/deactivate/{id}`

**Path Params:**
- `id` (long): ID do usuário

**Response 200:**
```json
{
  "mensagem": "Usuário desativado com sucesso"
}
```

---

### 8. Ativar Usuário

**PUT** `/api/auth/activate/{id}`

**Path Params:**
- `id` (long): ID do usuário

**Response 200:**
```json
{
  "mensagem": "Usuário ativado com sucesso"
}
```

---

### 9. Listar Usuários (Paginado)

**GET** `/api/auth/paginator`

**Query Params:**
- `page` (int, default: 1): Número da página
- `size` (int, default: 10): Itens por página
- `filterGeneric` (string, optional): Filtro de busca
- `active` (boolean, default: true): Filtrar ativos

**Response 200:**
```json
{
  "items": [
    {
      "id": 1,
      "username": "bombeiro01",
      "email": "bombeiro@email.com",
      "name": "João Silva"
    }
  ],
  "totalItems": 50,
  "totalPages": 5,
  "currentPage": 1,
  "itemsPerPage": 10
}
```

---

## 🔥 Occurrence APIs

### 1. Criar Ocorrência

**POST** `/api/occurrences`

**Request Body:**
```json
{
  "occurrenceHasVictims": true,
  "occurrenceRequester": "Maria Santos",
  "occurrenceRequesterPhoneNumber": "81988887777",
  "occurrenceSubType": "Incêndio residencial",
  "address": {
    "street": "Rua das Flores",
    "number": 456,
    "complement": "Casa",
    "neighborhood": "Jardim Europa",
    "city": "Recife",
    "state": "PE",
    "zipCode": "50000123"
  }
}
```

**Response 201:**
```json
"Ocorrência criada com sucesso"
```

---

### 2. Buscar Ocorrência por ID

**GET** `/api/occurrences/{id}`

**Path Params:**
- `id` (long): ID da ocorrência

**Response 200:**
```json
{
  "id": 1,
  "occurrenceHasVictims": true,
  "occurrenceRequester": "Maria Santos",
  "occurrenceRequesterPhoneNumber": "81988887777",
  "occurrenceSubType": "Incêndio residencial",
  "status": "EM_ATENDIMENTO",
  "occurrenceDetails": null,
  "latitude": null,
  "longitude": null,
  "occurrenceArrivalTime": null,
  "address": {
    "street": "Rua das Flores",
    "number": 456,
    "neighborhood": "Jardim Europa",
    "city": "Recife",
    "state": "PE"
  },
  "createdAt": "2025-10-29T18:00:00Z"
}
```

---

### 3. Concluir Ocorrência

**PUT** `/api/occurrences/complete/{id}`

**Path Params:**
- `id` (long): ID da ocorrência

**Request Body:**
```json
{
  "occurrenceDetails": "Incêndio controlado após 2 horas. Sem vítimas.",
  "latitude": -8.0476,
  "longitude": -34.8770,
  "occurrenceArrivalTime": "2025-10-29T15:30:00",
  "usersId": [1, 5, 8, 12]
}
```

**Response 200:**
```json
"Ocorrência completada com sucesso"
```

---

### 4. Atualizar Ocorrência

**PUT** `/api/occurrences/{id}`

**Path Params:**
- `id` (long): ID da ocorrência

**Request Body:**
```json
{
  "occurrenceRequester": "João Santos",
  "occurrenceSubType": "Incêndio comercial"
}
```

**Response 200:**
```json
{
  "mensagem": "Ocorrência atualizada com sucesso"
}
```

---

### 5. Desativar Ocorrência

**PUT** `/api/occurrences/deactivate/{id}`

**Path Params:**
- `id` (long): ID da ocorrência

**Response 200:**
```json
{
  "mensagem": "Ocorrência desativada com sucesso"
}
```

---

### 6. Ativar Ocorrência

**PUT** `/api/occurrences/activate/{id}`

**Path Params:**
- `id` (long): ID da ocorrência

**Response 200:**
```json
{
  "mensagem": "Ocorrência ativada com sucesso"
}
```

---

### 7. Listar Ocorrências (Paginado)

**GET** `/api/occurrences/paginator`

**Query Params:**
- `page` (int, default: 1)
- `size` (int, default: 10)
- `filterGeneric` (string, optional)
- `active` (boolean, default: true)

**Response 200:**
```json
{
  "items": [
    {
      "id": 1,
      "occurrenceRequester": "Maria Santos",
      "occurrenceSubType": "Incêndio residencial",
      "status": "CONCLUIDA",
      "createdAt": "2025-10-29T18:00:00Z"
    }
  ],
  "totalItems": 100,
  "totalPages": 10,
  "currentPage": 1
}
```

---

## 🚒 Battalion APIs

### 1. Criar Batalhão

**POST** `/api/battalion/created`

**Request Body:**
```json
{
  "name": "1º Batalhão de Incêndio",
  "phoneNumber": "81988887777",
  "email": "1batalh@bombeiros.pe.gov.br",
  "address": {
    "street": "Av. Principal",
    "number": 500,
    "complement": "Quartel Central",
    "neighborhood": "Boa Vista",
    "city": "Recife",
    "state": "PE",
    "zipCode": "50000000"
  }
}
```

**Response 200:**
```json
{
  "mensagem": "Batalhão criado com sucesso"
}
```

---

### 2. Buscar Batalhão por ID

**GET** `/api/battalion/{id}`

**Path Params:**
- `id` (long): ID do batalhão

**Response 200:**
```json
{
  "id": 1,
  "name": "1º Batalhão de Incêndio",
  "phoneNumber": "81988887777",
  "email": "1batalh@bombeiros.pe.gov.br",
  "endereco": {
    "street": "Av. Principal",
    "number": 500,
    "city": "Recife",
    "state": "PE"
  }
}
```

---

### 3. Atualizar Batalhão

**PUT** `/api/battalion/{id}`

**Path Params:**
- `id` (long): ID do batalhão

**Request Body:**
```json
{
  "name": "1º Batalhão de Incêndio - Atualizado",
  "phoneNumber": "81988887777",
  "email": "1batalh@bombeiros.pe.gov.br",
  "address": {...}
}
```

**Response 200:**
```json
{
  "mensagem": "Batalhão atualizado com sucesso"
}
```

---

### 4. Desativar Batalhão

**PUT** `/api/battalion/deactivate/{id}`

**Query Params:**
- `id` (long): ID do batalhão

**Response 200:**
```json
"Batalhão desativado com sucesso."
```

---

### 5. Ativar Batalhão

**PUT** `/api/battalion/activate/{id}`

**Query Params:**
- `id` (long): ID do batalhão

**Response 200:**
```json
"Batalhão ativado com sucesso."
```

---

### 6. Listar Batalhões (Paginado)

**GET** `/api/battalion/paginator`

**Query Params:**
- `page` (int, default: 1)
- `size` (int, default: 10)
- `name` (string, optional): Filtro por nome
- `active` (boolean, default: true)

**Response 200:**
```json
{
  "items": [
    {
      "id": 1,
      "name": "1º Batalhão",
      "phoneNumber": "81988887777",
      "email": "1batalh@bombeiros.pe.gov.br"
    }
  ],
  "totalItems": 20,
  "totalPages": 2,
  "currentPage": 1
}
```

---

## 🎖️ Patent APIs

### 1. Criar Patente

**POST** `/api/patent/register/patent`

**Request Body:**
```json
{
  "name": "Soldado"
}
```

**Response 200:**
```json
{
  "id": 1,
  "name": "Soldado",
  "active": true
}
```

---

### 2. Buscar Patente por ID

**GET** `/api/patent/{id}`

**Query Params:**
- `id` (long): ID da patente

**Response 200:**
```json
{
  "id": 1,
  "name": "Soldado",
  "active": true
}
```

---

### 3. Atualizar Patente

**PUT** `/api/patent/{id}`

**Query Params:**
- `id` (long): ID da patente

**Request Body:**
```json
{
  "name": "Soldado BM"
}
```

**Response 200:**
```json
{
  "id": 1,
  "name": "Soldado BM",
  "active": true
}
```

---

### 4. Desativar Patente

**PUT** `/api/patent/deactivate/{id}`

**Path Params:**
- `id` (long): ID da patente

**Response 200:**
```json
{
  "mensagem": "Patente desativada com sucesso"
}
```

---

### 5. Ativar Patente

**PUT** `/api/patent/activate/{id}`

**Path Params:**
- `id` (long): ID da patente

**Response 200:**
```json
{
  "mensagem": "Patente ativada com sucesso"
}
```

---

### 6. Listar Patentes (Paginado)

**GET** `/api/patent/paginator`

**Query Params:**
- `page` (int, default: 1)
- `size` (int, default: 10)
- `filterGeneric` (string, optional)
- `active` (boolean, default: true)

**Response 200:**
```json
{
  "items": [
    {
      "id": 1,
      "name": "Soldado"
    },
    {
      "id": 2,
      "name": "Cabo"
    }
  ],
  "totalItems": 15,
  "totalPages": 2,
  "currentPage": 1
}
```

---

### 7. Listar Todas as Patentes

**GET** `/api/patent`

**Response 200:**
```json
{
  "patentResponseDTOList": [
    {
      "id": 1,
      "name": "Soldado"
    },
    {
      "id": 2,
      "name": "Cabo"
    },
    {
      "id": 3,
      "name": "Sargento"
    }
  ]
}
```

---

## 🚗 Vehicle APIs (Em Desenvolvimento)

Módulo de veículos ainda em desenvolvimento. Endpoints não disponíveis.

---

## Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| 200 | OK - Requisição bem sucedida |
| 201 | Created - Recurso criado com sucesso |
| 400 | Bad Request - Dados inválidos |
| 401 | Unauthorized - Não autenticado |
| 403 | Forbidden - Não autorizado |
| 404 | Not Found - Recurso não encontrado |
| 500 | Internal Server Error - Erro interno |

---

## Enumerações

### OccurrenceStatus

```java
AGUARDANDO_ATENDIMENTO  // Aguardando equipe
EM_ATENDIMENTO          // Em curso
CONCLUIDA               // Finalizada
FALSO_ALARME            // Falso alarme
CANCELADA               // Cancelada
```

### RolesEnum

```java
admin      // Administrador
superuser  // Super usuário
```

---

## Exemplos de Uso

### Exemplo 1: Fluxo Completo de Autenticação

```bash
# 1. Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"bombeiro01","password":"senha123"}'

# Response:
# {"success":true,"token":"eyJhbGc...","refreshToken":"uuid..."}

# 2. Usar token
curl -X GET http://localhost:8080/api/auth \
  -H "Authorization: Bearer eyJhbGc..."

# 3. Refresh token
curl -X POST http://localhost:8080/api/auth/refresh-token/ \
  -H "Content-Type: application/json" \
  -d '{"username":"bombeiro01","refreshToken":"uuid..."}'
```

---

### Exemplo 2: Criar e Concluir Ocorrência

```bash
# 1. Criar ocorrência
curl -X POST http://localhost:8080/api/occurrences \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "occurrenceHasVictims": true,
    "occurrenceRequester": "Maria",
    "occurrenceRequesterPhoneNumber": "81988887777",
    "occurrenceSubType": "Incêndio",
    "address": {...}
  }'

# Response: "Ocorrência criada com sucesso"
# ID retornado: 1

# 2. Concluir ocorrência
curl -X PUT http://localhost:8080/api/occurrences/complete/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "occurrenceDetails": "Incêndio controlado",
    "latitude": -8.0476,
    "longitude": -34.8770,
    "occurrenceArrivalTime": "2025-10-29T15:30:00",
    "usersId": [1, 5, 8]
  }'
```

---

### Exemplo 3: Paginação

```bash
# Listar primeira página
curl -X GET "http://localhost:8080/api/auth/paginator?page=1&size=10&active=true" \
  -H "Authorization: Bearer <token>"

# Listar com filtro
curl -X GET "http://localhost:8080/api/auth/paginator?page=1&size=10&filterGeneric=bombeiro&active=true" \
  -H "Authorization: Bearer <token>"
```

---

## Validações de Entrada

### Campos Obrigatórios

**User:**
- username (3-30 caracteres)
- email (formato válido)
- cpf (11 dígitos)
- password (mínimo 6 caracteres)

**Occurrence:**
- occurrenceRequester
- occurrenceRequesterPhoneNumber
- occurrenceSubType
- address

**Battalion:**
- name
- phoneNumber
- email (único)
- address

**Patent:**
- name (único)

---

## Segurança

### Rotas Públicas

Não requerem autenticação:
- `POST /api/auth/login`
- `POST /api/auth/created/user` (primeira criação)
- `POST /api/battalion/created` (primeira criação)
- `POST /api/patent/register/patent` (primeira criação)
- `/swagger-ui/**`
- `/v3/api-docs/**`

### Rotas Protegidas

Todas as demais rotas requerem JWT válido.

---

## Rate Limiting

Atualmente não implementado. Recomendado para produção.

---

## CORS

Configurado para aceitar todas as origins em desenvolvimento:

```java
configuration.setAllowedOrigins(List.of("*"));
configuration.setAllowedMethods(List.of("*"));
configuration.setAllowedHeaders(List.of("*"));
```

**Produção:** Configurar origins específicas.

---

## Versionamento

API atual: **v1** (implícito)

Futuro: `/api/v2/...`

---

## Suporte

Para mais detalhes, consulte:
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Documentação técnica na pasta `docs/`
