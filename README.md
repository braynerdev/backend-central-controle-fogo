# 🔥 Backend Central Controle de Fogo

## 📋 Sobre o Projeto

Sistema backend desenvolvido em parceria com o **Corpo de Bombeiros de Pernambuco** pela **Faculdade Senac** para gerenciamento centralizado de ocorrências de incêndio e operações de combate a incêndios.

### 🎯 Objetivos

- ✅ Registro e acompanhamento de ocorrências em tempo real
- ✅ Gestão de usuários (bombeiros) com controle de patentes
- ✅ Gerenciamento de batalhões e localizações
- ✅ Controle de acesso baseado em roles e permissões
- ✅ Rastreamento geográfico de ocorrências

---

## 🚀 Tecnologias

- **Java 21** (LTS)
- **Spring Boot 3.5.6**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring OAuth2 Resource Server
- **PostgreSQL 42.7.2**
- **JWT** com chaves RSA
- **Swagger/OpenAPI** (SpringDoc 2.7.0)
- **Lombok 1.18.34**
- **ModelMapper 3.1.1**
- **Maven**

---

## 📁 Estrutura do Projeto

```
backend-central-controle-fogo/
├── src/
│   ├── main/
│   │   ├── java/central_controle_fogo/com/backend_central_controle_fogo/
│   │   │   ├── config/              # Configurações (Security, OpenAPI, etc)
│   │   │   ├── controller/          # Controllers REST
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── Enum/                # Enumerações
│   │   │   ├── exception/           # Exceções customizadas
│   │   │   ├── model/               # Entidades JPA
│   │   │   ├── repository/          # Repositories JPA
│   │   │   ├── service/             # Camada de negócios
│   │   │   ├── validation/          # Validações customizadas
│   │   │   └── BackendCentralControleFogoApplication.java
│   │   └── resources/
│   │       ├── application.properties.examples.dev
│   │       ├── app.key.example.dev
│   │       └── app.pub.example.dev
│   └── test/
├── docs/                            # Documentação técnica
├── pom.xml
└── README.md
```

---

## ⚙️ Configuração e Instalação

### Pré-requisitos

- Java 21 ou superior
- Maven 3.8+
- PostgreSQL 12+
- Git

### 1. Clonar o Repositório

```bash
git clone <repository-url>
cd backend-central-controle-fogo
```

### 2. Configurar Banco de Dados

Criar banco de dados PostgreSQL:

```sql
CREATE DATABASE central_controle_fogo;
```

### 3. Gerar Chaves RSA para JWT

Execute os comandos abaixo para gerar as chaves:

```bash
# Gerar chave privada
openssl genrsa -out src/main/resources/app.key 2048

# Gerar chave pública a partir da privada
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

### 4. Configurar application.properties

Copie o arquivo de exemplo e configure:

```bash
cp src/main/resources/application.properties.examples.dev src/main/resources/application.properties
```

Edite o arquivo `application.properties`:

```properties
# JWT keys
jwt.public.key=classpath:app.pub
jwt.private.key=classpath:app.key

# App identity
spring.application.name=backend-central-controle-fogo

# Server port
server.port=8080

# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/central_controle_fogo
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate and JPA
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.type.preferred_datetime_jdbc_type=TIMESTAMP_WITH_TIMEZONE
```

### 5. Instalar Dependências e Compilar

```bash
./mvnw clean install
```

### 6. Executar a Aplicação

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 📚 Documentação

### Swagger UI
Acesse a documentação interativa das APIs em:
```
http://localhost:8080/swagger-ui/index.html
```

### Documentação Técnica Completa

Consulte os arquivos na pasta `docs/`:

- 📄 [**ARQUITETURA.md**](docs/ARQUITETURA.md) - Arquitetura do sistema
- 📄 [**MODELO_DADOS.md**](docs/MODELO_DADOS.md) - Modelo de dados e entidades
- 📄 [**FLUXO_DADOS.md**](docs/FLUXO_DADOS.md) - Fluxos de dados do sistema
- 📄 [**API_REFERENCE.md**](docs/API_REFERENCE.md) - Referência completa de APIs

---

## 🔐 Autenticação

O sistema utiliza **JWT (JSON Web Tokens)** com chaves RSA para autenticação.

### Como Autenticar

1. **Fazer Login:**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "seu_usuario",
  "password": "sua_senha"
}
```

2. **Receber Token:**
```json
{
  "success": true,
  "token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "uuid-refresh-token"
}
```

3. **Usar Token nas Requisições:**
```bash
GET /api/auth
Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 🗃️ Principais Entidades

### User (Usuário/Bombeiro)
- Informações pessoais do bombeiro
- Vinculado a batalhão e patente
- Controle de autenticação (username, email, senha)

### Occurrence (Ocorrência)
- Registro de incidentes/chamados
- Geolocalização (latitude/longitude)
- Status do atendimento
- Bombeiros envolvidos

### Battalion (Batalhão)
- Unidades do Corpo de Bombeiros
- Localização e contatos
- Usuários vinculados

### Patent (Patente)
- Hierarquia militar
- Exemplos: Soldado, Cabo, Sargento, Tenente, Capitão

### Roles (Perfis de Acesso)
- `admin`: Administrador do sistema
- `superuser`: Super usuário

---

## 🔄 Principais Endpoints

### Autenticação
- `POST /api/auth/login` - Login
- `GET /api/auth` - Dados do usuário autenticado
- `POST /api/auth/created/user` - Criar usuário
- `POST /api/auth/refresh-token/` - Renovar token
- `POST /api/auth/logout/{id}` - Logout

### Ocorrências
- `POST /api/occurrences` - Criar ocorrência
- `GET /api/occurrences/{id}` - Buscar por ID
- `PUT /api/occurrences/complete/{id}` - Concluir ocorrência
- `PUT /api/occurrences/{id}` - Atualizar ocorrência
- `GET /api/occurrences/paginator` - Listar paginado

### Batalhões
- `POST /api/battalion/created` - Criar batalhão
- `GET /api/battalion/{id}` - Buscar por ID
- `PUT /api/battalion/{id}` - Atualizar batalhão
- `GET /api/battalion/paginator` - Listar paginado

### Patentes
- `POST /api/patent/register/patent` - Criar patente
- `GET /api/patent/{id}` - Buscar por ID
- `PUT /api/patent/{id}` - Atualizar patente
- `GET /api/patent/paginator` - Listar paginado
- `GET /api/patent` - Listar todas

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 🛠️ Build para Produção

```bash
./mvnw clean package -DskipTests
```

O arquivo JAR será gerado em: `target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar`

---

## 📊 Status do Projeto

🚧 **Em Desenvolvimento Ativo**

### ✅ Funcionalidades Implementadas
- Autenticação JWT completa
- CRUD de usuários
- CRUD de batalhões
- CRUD de patentes
- CRUD de ocorrências
- Paginação e filtros
- Soft delete (desativação)

### 🔨 Em Desenvolvimento
- Módulo de veículos
- Relatórios e dashboards
- Notificações em tempo real
- Integração com mapas

---

## 👥 Equipe

Projeto desenvolvido pelos alunos da **Faculdade Senac** em parceria com o **Corpo de Bombeiros de Pernambuco**.

---

## 📄 Licença

Este projeto é desenvolvido para fins acadêmicos em parceria institucional.

---

## 📞 Suporte

Para questões técnicas ou dúvidas, consulte a documentação completa na pasta `docs/` ou entre em contato com a equipe de desenvolvimento.

---
