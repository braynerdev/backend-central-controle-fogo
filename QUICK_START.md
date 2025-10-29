# ⚡ Quick Start Guide

Guia rápido para começar a usar o Backend Central Controle de Fogo em **5 minutos**.

---

## 🚀 Início Rápido (5 minutos)

### 1. Pré-requisitos

```bash
# Verificar Java 21
java -version

# Verificar PostgreSQL
psql --version

# Verificar Maven
mvn -version
```

---

### 2. Setup do Banco

```bash
# Conectar ao PostgreSQL
psql -U postgres

# Criar banco
CREATE DATABASE central_controle_fogo;

# Sair
\q
```

---

### 3. Gerar Chaves JWT

```bash
# Chave privada
openssl genrsa -out src/main/resources/app.key 2048

# Chave pública
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

---

### 4. Configurar Aplicação

```bash
# Copiar arquivo de exemplo
cp src/main/resources/application.properties.examples.dev src/main/resources/application.properties

# Editar (ajustar senha do PostgreSQL)
nano src/main/resources/application.properties
```

**Configuração mínima:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/central_controle_fogo
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
```

---

### 5. Executar

```bash
# Instalar e executar
./mvnw spring-boot:run
```

**Aguardar mensagem:**
```
Started BackendCentralControleFogoApplication in X seconds
```

---

### 6. Testar

Abra o navegador:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📝 Primeiros Passos na API

### 1. Criar Batalhão

```bash
POST http://localhost:8080/api/battalion/created
Content-Type: application/json

{
  "name": "1º Batalhão",
  "phoneNumber": "81988887777",
  "email": "1gbm@bombeiros.pe.gov.br",
  "address": {
    "street": "Av. Principal",
    "number": 100,
    "neighborhood": "Centro",
    "city": "Recife",
    "state": "PE",
    "zipCode": "50000000"
  }
}
```

---

### 2. Criar Patente

```bash
POST http://localhost:8080/api/patent/register/patent
Content-Type: application/json

{
  "name": "Soldado"
}
```

---

### 3. Criar Usuário

```bash
POST http://localhost:8080/api/auth/created/user
Content-Type: application/json

{
  "username": "bombeiro01",
  "email": "bombeiro@email.com",
  "phoneNumber": "81999999999",
  "cpf": "12345678901",
  "matriculates": "BM001",
  "name": "João Silva",
  "dateBirth": "1990-01-01T00:00:00Z",
  "gender": "M",
  "password": "senha123",
  "patentId": 1,
  "battalionId": 1,
  "address": {
    "street": "Rua Exemplo",
    "number": 123,
    "neighborhood": "Bairro",
    "city": "Recife",
    "state": "PE",
    "zipCode": "50000001"
  }
}
```

---

### 4. Fazer Login

```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "bombeiro01",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "success": true,
  "token": "eyJhbGc...",
  "refreshToken": "uuid..."
}
```

**Copie o token!**

---

### 5. Criar Ocorrência

```bash
POST http://localhost:8080/api/occurrences
Authorization: Bearer SEU_TOKEN_AQUI
Content-Type: application/json

{
  "occurrenceHasVictims": false,
  "occurrenceRequester": "Maria Santos",
  "occurrenceRequesterPhoneNumber": "81988886666",
  "occurrenceSubType": "Incêndio residencial",
  "address": {
    "street": "Rua das Flores",
    "number": 456,
    "neighborhood": "Jardim",
    "city": "Recife",
    "state": "PE",
    "zipCode": "50000002"
  }
}
```

---

### 6. Listar Ocorrências

```bash
GET http://localhost:8080/api/occurrences/paginator?page=1&size=10
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 🔧 Comandos Úteis

### Maven

```bash
# Compilar
./mvnw clean install

# Executar
./mvnw spring-boot:run

# Testes
./mvnw test

# Package
./mvnw clean package
```

---

### PostgreSQL

```bash
# Conectar
psql -U postgres -d central_controle_fogo

# Listar tabelas
\dt

# Ver estrutura de tabela
\d auth_user

# Contar registros
SELECT COUNT(*) FROM auth_user;

# Sair
\q
```

---

### Git

```bash
# Status
git status

# Adicionar mudanças
git add .

# Commit
git commit -m "Descrição da mudança"

# Push
git push origin main
```

---

## 📚 Documentação Rápida

### Links Importantes

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **H2 Console (se configurado):** http://localhost:8080/h2-console
- **Documentação:** `docs/README.md`

### Estrutura do Projeto

```
backend-central-controle-fogo/
├── src/main/java/.../
│   ├── controller/     # REST endpoints
│   ├── service/        # Business logic
│   ├── repository/     # Data access
│   ├── model/          # Entities
│   ├── dto/            # Data transfer objects
│   └── config/         # Configurations
├── src/main/resources/
│   ├── application.properties
│   ├── app.key         # JWT private key
│   └── app.pub         # JWT public key
├── docs/               # Documentação técnica
├── README.md           # Guia principal
└── pom.xml             # Maven dependencies
```

---

## 🔍 Troubleshooting Rápido

### Porta 8080 em uso

```bash
# Matar processo
# Linux/Mac:
sudo lsof -t -i:8080 | xargs kill -9

# Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

---

### PostgreSQL não conecta

```bash
# Verificar se está rodando
# Linux:
sudo systemctl status postgresql
sudo systemctl start postgresql

# Mac:
brew services start postgresql

# Windows:
net start postgresql-x64-15
```

---

### Chaves JWT não encontradas

```bash
# Verificar
ls -la src/main/resources/app.*

# Regenerar
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

---

### Build falha

```bash
# Limpar e reinstalar
./mvnw clean
rm -rf ~/.m2/repository
./mvnw install
```

---

## 📞 Precisa de Ajuda?

### Documentação Detalhada

- [Guia de Configuração](docs/GUIA_CONFIGURACAO.md)
- [Referência de APIs](docs/API_REFERENCE.md)
- [Arquitetura](docs/ARQUITETURA.md)
- [Modelo de Dados](docs/MODELO_DADOS.md)

### Swagger UI

Melhor forma de explorar as APIs:
```
http://localhost:8080/swagger-ui/index.html
```

---

## ✅ Checklist de Sucesso

- [ ] Java 21 instalado
- [ ] PostgreSQL rodando
- [ ] Banco `central_controle_fogo` criado
- [ ] Chaves RSA geradas
- [ ] `application.properties` configurado
- [ ] Aplicação rodando (porta 8080)
- [ ] Swagger UI acessível
- [ ] Primeiro batalhão criado
- [ ] Primeira patente criada
- [ ] Primeiro usuário criado
- [ ] Login bem-sucedido
- [ ] Primeira ocorrência criada

---

## 🎉 Parabéns!

Se chegou até aqui, seu ambiente está configurado e funcionando!

**Próximos passos:**
1. Explore o Swagger UI
2. Teste diferentes endpoints
3. Leia a documentação técnica
4. Comece a desenvolver

---

**Desenvolvido com ❤️ pela Faculdade Senac + Corpo de Bombeiros PE**
