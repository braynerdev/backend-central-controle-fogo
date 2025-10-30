# 🚀 Deploy Quick Start - Render

## ✅ O que foi configurado

Seu projeto está pronto para deploy no Render! Aqui está o que foi feito:

### 📦 Arquivos Criados:

1. **`application.properties.render`** - Template de configuração com variáveis de ambiente
2. **`.env.example`** - Exemplo de variáveis de ambiente necessárias
3. **`build.sh`** - Script de build para o Render (Linux/Mac)
4. **`render.yaml`** - Configuração automática do serviço Render
5. **`generate-env-keys.ps1`** - Script PowerShell para gerar chaves Base64
6. **`generate-env-keys.sh`** - Script Bash para gerar chaves Base64 (Linux/Mac)
7. **`RsaKeyProperties.java`** - Componente para carregar chaves RSA via env vars
8. **`RENDER_DEPLOY.md`** - Guia completo de deploy
9. **`.env.render`** - Suas chaves RSA em Base64 (NÃO COMMITAR!)

### 🔧 Modificações:

- ✅ `SecurityConfig.java` atualizado para suportar chaves via variáveis de ambiente
- ✅ `.gitignore` atualizado para proteger arquivos sensíveis
- ✅ JAR gerado: `target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar`

---

## ⚡ Passos Rápidos para Deploy

### 1️⃣ Suas Chaves RSA (Já Geradas!)

As chaves em Base64 foram salvas em `.env.render`:

```
JWT_PUBLIC_KEY_BASE64=LS0tLS1CRUdJTi...
JWT_PRIVATE_KEY_BASE64=LS0tLS1CRUdJTi...
```

**⚠️ IMPORTANTE:** Não commite este arquivo!

### 2️⃣ Criar PostgreSQL no Render

1. Acesse https://dashboard.render.com
2. **New +** → **PostgreSQL**
3. Configure:
   - Name: `backend-central-controle-fogo-db`
   - Database: `central_controle_fogo`
   - Region: Oregon
4. Anote o **Internal Database URL**

### 3️⃣ Criar Web Service

1. **New +** → **Web Service**
2. Conecte seu repositório Git
3. Configure:
   - **Name:** `backend-central-controle-fogo`
   - **Build Command:** `./build.sh`
   - **Start Command:** `java -jar target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar`

### 4️⃣ Adicionar Variáveis de Ambiente

No painel do Web Service, vá em **Environment** e adicione:

```properties
JAVA_VERSION=21
APP_NAME=backend-central-controle-fogo
PORT=8088

# Database (substitua com seus valores)
DATABASE_URL=jdbc:postgresql://dpg-xxx.oregon-postgres.render.com:5432/central_controle_fogo
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha

# JPA
JPA_DDL_AUTO=update
JPA_SHOW_SQL=false

# JWT Keys (copie do arquivo .env.render)
JWT_PUBLIC_KEY_BASE64=<cole aqui>
JWT_PRIVATE_KEY_BASE64=<cole aqui>
```

### 5️⃣ Deploy!

Clique em **"Create Web Service"** e aguarde o deploy (5-10 min).

---

## 🧪 Testar a Aplicação

Após o deploy:

### Swagger UI:
```
https://seu-app.onrender.com/swagger-ui/index.html
```

### Health Check:
```bash
curl https://seu-app.onrender.com/actuator/health
```

### Login:
```bash
curl -X POST https://seu-app.onrender.com/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "usuario", "password": "senha"}'
```

---

## 📋 Checklist Final

Antes de fazer o deploy, verifique:

- [ ] PostgreSQL criado no Render
- [ ] Internal Database URL copiada
- [ ] Chaves RSA convertidas para Base64 (arquivo `.env.render` criado)
- [ ] Todas as variáveis de ambiente configuradas no Render
- [ ] Repositório Git commitado e pushed
- [ ] Web Service criado e vinculado ao repositório

---

## 🆘 Problemas?

Consulte o guia completo em **`RENDER_DEPLOY.md`** para:
- Troubleshooting detalhado
- Explicações aprofundadas
- Configurações avançadas
- Dicas de segurança

---

## 📝 Notas Importantes

### ⚠️ Não Commitar:
- `.env.render` (contém suas chaves!)
- `src/main/resources/application.properties`
- `src/main/resources/app.key`
- `src/main/resources/app.pub`

### ✅ Pode Commitar:
- `application.properties.render`
- `.env.example`
- `build.sh`
- `render.yaml`
- Scripts `generate-env-keys.*`
- Guias de documentação

---

## 🎯 Próximos Passos

Após o deploy bem-sucedido:

1. Teste todos os endpoints críticos
2. Configure CORS se necessário
3. Monitore logs e métricas no Render Dashboard
4. Configure domínio customizado (opcional)
5. Configure SSL/HTTPS (automático no Render)

---

**✨ Tudo pronto! Seu backend está configurado para rodar no Render!**

Para dúvidas, consulte `RENDER_DEPLOY.md` ou a [documentação do Render](https://render.com/docs).
