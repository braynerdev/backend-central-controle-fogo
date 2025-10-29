# ⚙️ Guia de Configuração

Guia detalhado para configuração e instalação do **Backend Central Controle de Fogo**.

---

## 📋 Pré-requisitos

### Software Necessário

| Software | Versão Mínima | Versão Recomendada | Download |
|----------|---------------|-------------------|----------|
| Java JDK | 21 | 21 LTS | https://adoptium.net/ |
| Maven | 3.8.0 | 3.9.x | https://maven.apache.org/ |
| PostgreSQL | 12 | 15+ | https://www.postgresql.org/ |
| Git | 2.30 | Latest | https://git-scm.com/ |
| OpenSSL | 1.1.1 | 3.x | https://www.openssl.org/ |

### Verificar Instalações

```bash
# Verificar Java
java -version
# Esperado: openjdk version "21.x.x"

# Verificar Maven
mvn -version
# Esperado: Apache Maven 3.x.x

# Verificar PostgreSQL
psql --version
# Esperado: psql (PostgreSQL) 12.x ou superior

# Verificar Git
git --version
# Esperado: git version 2.x.x

# Verificar OpenSSL
openssl version
# Esperado: OpenSSL 1.1.1 ou superior
```

---

## 📥 Instalação Passo a Passo

### 1. Clonar o Repositório

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre no diretório
cd backend-central-controle-fogo

# Verifique os arquivos
ls -la
```

---

### 2. Configurar PostgreSQL

#### 2.1 Criar Banco de Dados

**Linux/Mac:**
```bash
# Acessar PostgreSQL como superuser
sudo -u postgres psql

# Ou se tiver senha configurada
psql -U postgres
```

**Windows:**
```bash
# Usar pgAdmin ou linha de comando
psql -U postgres
```

**Comandos SQL:**
```sql
-- Criar banco de dados
CREATE DATABASE central_controle_fogo;

-- Verificar criação
\l

-- Conectar ao banco
\c central_controle_fogo

-- Sair do psql
\q
```

#### 2.2 Configurar Usuário (Opcional)

```sql
-- Criar usuário específico para a aplicação
CREATE USER bombeiros_user WITH PASSWORD 'senha_segura';

-- Dar permissões
GRANT ALL PRIVILEGES ON DATABASE central_controle_fogo TO bombeiros_user;

-- Verificar
\du
```

---

### 3. Gerar Chaves RSA para JWT

#### 3.1 Criar Diretório (se necessário)

```bash
# Garantir que o diretório existe
mkdir -p src/main/resources
```

#### 3.2 Gerar Chaves

**Linux/Mac/Git Bash (Windows):**

```bash
# Gerar chave privada (2048 bits)
openssl genrsa -out src/main/resources/app.key 2048

# Gerar chave pública a partir da privada
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub

# Verificar permissões (Linux/Mac)
chmod 600 src/main/resources/app.key
chmod 644 src/main/resources/app.pub

# Verificar conteúdo
cat src/main/resources/app.key
cat src/main/resources/app.pub
```

**Windows PowerShell (se OpenSSL não disponível):**

```powershell
# Instalar OpenSSL via Chocolatey
choco install openssl

# Ou baixar manualmente de: https://slproweb.com/products/Win32OpenSSL.html
# Após instalação, executar os comandos acima
```

#### 3.3 Verificar Chaves

A chave privada deve começar com:
```
-----BEGIN PRIVATE KEY-----
```

A chave pública deve começar com:
```
-----BEGIN PUBLIC KEY-----
```

---

### 4. Configurar application.properties

#### 4.1 Copiar Arquivo de Exemplo

```bash
# Copiar arquivo exemplo
cp src/main/resources/application.properties.examples.dev src/main/resources/application.properties
```

#### 4.2 Editar Configurações

Abra `src/main/resources/application.properties` e configure:

```properties
# ========================================
# JWT Configuration
# ========================================
jwt.public.key=classpath:app.pub
jwt.private.key=classpath:app.key

# ========================================
# Application Identity
# ========================================
spring.application.name=backend-central-controle-fogo

# ========================================
# Server Configuration
# ========================================
server.port=8080

# ========================================
# Database Configuration
# ========================================
spring.datasource.url=jdbc:postgresql://localhost:5432/central_controle_fogo
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=org.postgresql.Driver

# ========================================
# JPA/Hibernate Configuration
# ========================================
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.type.preferred_datetime_jdbc_type=TIMESTAMP_WITH_TIMEZONE

# ========================================
# Logging (Opcional)
# ========================================
logging.level.root=INFO
logging.level.central_controle_fogo=DEBUG
logging.level.org.springframework.security=DEBUG
```

#### 4.3 Configurações Importantes

**Desenvolvimento:**
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Produção:**
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

---

### 5. Instalar Dependências

```bash
# Limpar e instalar dependências
./mvnw clean install

# Ou no Windows
mvnw.cmd clean install

# Pular testes (se necessário)
./mvnw clean install -DskipTests
```

**Saída esperada:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
[INFO] Finished at: YYYY-MM-DDTHH:MM:SS
```

---

### 6. Executar a Aplicação

#### 6.1 Modo Desenvolvimento

```bash
# Executar com Maven
./mvnw spring-boot:run

# Ou no Windows
mvnw.cmd spring-boot:run
```

#### 6.2 Via JAR (Produção)

```bash
# Compilar
./mvnw clean package -DskipTests

# Executar JAR
java -jar target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar
```

#### 6.3 Verificar Inicialização

**Console deve mostrar:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.5.6)

...
Started BackendCentralControleFogoApplication in X.XXX seconds
```

**Porta padrão:** http://localhost:8080

---

### 7. Verificar Instalação

#### 7.1 Acessar Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

Deve exibir a documentação interativa das APIs.

#### 7.2 Testar Endpoint Público

```bash
# Testar criação de batalhão (público na primeira vez)
curl -X POST http://localhost:8080/api/battalion/created \
  -H "Content-Type: application/json" \
  -d '{
    "name": "1º Batalhão Teste",
    "phoneNumber": "81988887777",
    "email": "teste@bombeiros.pe.gov.br",
    "address": {
      "street": "Rua Teste",
      "number": 100,
      "neighborhood": "Centro",
      "city": "Recife",
      "state": "PE",
      "zipCode": "50000000"
    }
  }'
```

---

## 🔧 Configurações Avançadas

### Configurar Múltiplos Ambientes

#### development.properties
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
logging.level.central_controle_fogo=DEBUG
```

#### production.properties
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.central_controle_fogo=INFO
```

**Executar com profile:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=development
```

---

### Configurar Pool de Conexões

Adicione ao `application.properties`:

```properties
# HikariCP Configuration
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
```

---

### Configurar CORS para Produção

Edite `SecurityConfig.java`:

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of(
        "https://meu-dominio.com",
        "https://app.meu-dominio.com"
    ));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
```

---

## 🐳 Docker (Opcional)

### Dockerfile

```dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
COPY src/main/resources/app.key /app/app.key
COPY src/main/resources/app.pub /app/app.pub
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### docker-compose.yml

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15-alpine
    environment:
      POSTGRES_DB: central_controle_fogo
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  backend:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/central_controle_fogo
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - postgres

volumes:
  postgres_data:
```

**Executar:**
```bash
docker-compose up -d
```

---

## 🔍 Troubleshooting

### Problema: Porta 8080 em uso

**Solução 1:** Mudar porta no `application.properties`:
```properties
server.port=8081
```

**Solução 2:** Matar processo na porta:
```bash
# Linux/Mac
sudo lsof -t -i:8080 | xargs kill -9

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

---

### Problema: Erro de conexão PostgreSQL

**Verificar:**
1. PostgreSQL está rodando
2. Porta 5432 está aberta
3. Credenciais corretas
4. Banco existe

**Linux/Mac:**
```bash
sudo systemctl status postgresql
sudo systemctl start postgresql
```

**Windows:**
```bash
# Verificar serviço no Services.msc
net start postgresql-x64-15
```

---

### Problema: Chaves RSA não encontradas

**Verificar:**
```bash
ls -la src/main/resources/app.*
```

**Regenerar:**
```bash
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

---

### Problema: Erro de compilação Maven

**Limpar cache:**
```bash
./mvnw clean
rm -rf ~/.m2/repository
./mvnw install
```

---

### Problema: OutOfMemoryError

**Aumentar heap:**
```bash
export MAVEN_OPTS="-Xmx1024m"
./mvnw spring-boot:run
```

Ou no JAR:
```bash
java -Xmx1024m -jar target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar
```

---

## 📊 Verificar Status da Aplicação

### Health Check

```bash
curl http://localhost:8080/actuator/health
```

### Logs

```bash
# Ver logs em tempo real
tail -f logs/spring.log

# Ou no console durante execução
./mvnw spring-boot:run
```

---

## 🔐 Segurança em Produção

### Checklist

- [ ] Mudar senha padrão do PostgreSQL
- [ ] Usar variáveis de ambiente para credenciais
- [ ] HTTPS configurado
- [ ] CORS restrito a domínios específicos
- [ ] Firewall configurado
- [ ] Backup automático do banco
- [ ] Monitoring configurado
- [ ] Logs centralizados

---

## 📚 Próximos Passos

Após configuração bem-sucedida:

1. ✅ Acesse Swagger UI
2. ✅ Crie primeiro batalhão
3. ✅ Crie primeira patente
4. ✅ Crie primeiro usuário
5. ✅ Faça login
6. ✅ Explore os endpoints

---

## 🆘 Suporte

**Problemas técnicos:**
- Consulte [Troubleshooting](#-troubleshooting)
- Revise logs da aplicação
- Verifique documentação do Spring Boot

**Dúvidas sobre configuração:**
- Consulte equipe de desenvolvimento
- Revise `application.properties.examples.dev`

---

**Última atualização:** 29/10/2025  
**Versão:** 1.0.0
