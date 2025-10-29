# 📊 Resumo Executivo - Backend Central Controle de Fogo

## 🎯 Visão Geral do Projeto

**Nome:** Backend Central Controle de Fogo  
**Versão:** 0.0.1-SNAPSHOT  
**Parceiros:** Faculdade Senac + Corpo de Bombeiros de Pernambuco  
**Status:** 🚧 Em Desenvolvimento Ativo

---

## 📝 Descrição

Sistema backend REST API para gerenciamento centralizado de ocorrências de incêndio e operações do Corpo de Bombeiros de Pernambuco. Oferece controle completo de usuários (bombeiros), batalhões, patentes militares e registro de ocorrências com geolocalização.

---

## 🎯 Objetivos Principais

1. ✅ **Gestão de Ocorrências** - Registro, acompanhamento e conclusão de chamados
2. ✅ **Controle de Usuários** - Gerenciamento de bombeiros com patentes e batalhões
3. ✅ **Autenticação Segura** - JWT com chaves RSA para acesso controlado
4. ✅ **Geolocalização** - Rastreamento de coordenadas das ocorrências
5. ✅ **Auditoria** - Histórico completo com soft delete

---

## 💻 Stack Tecnológica

### Backend
- **Java 21** (LTS)
- **Spring Boot 3.5.6**
- **Spring Security** + OAuth2
- **Spring Data JPA** + Hibernate

### Banco de Dados
- **PostgreSQL 42.7.2**

### Segurança
- **JWT** (JSON Web Tokens) com RSA
- **BCrypt** para hash de senhas

### Documentação
- **Swagger/OpenAPI 2.7.0**

### Utilitários
- **Lombok** - Redução de boilerplate
- **ModelMapper** - Mapeamento DTO/Entity
- **Maven** - Gerenciamento de dependências

---

## 🏗️ Arquitetura

### Padrão
**Arquitetura em Camadas (Layered Architecture)**

```
Controllers (REST API)
      ↓
Services (Business Logic)
      ↓
Repositories (Data Access)
      ↓
PostgreSQL Database
```

### Características
- ✅ Separação de responsabilidades
- ✅ Baixo acoplamento
- ✅ Alta coesão
- ✅ Testabilidade
- ✅ Manutenibilidade

---

## 📊 Estatísticas do Projeto

### Estrutura de Código

| Componente | Quantidade |
|------------|------------|
| Controllers | 5 |
| Services | 9 |
| Repositories | 8 |
| Entities (Models) | 13 |
| DTOs | 28+ |
| Enums | 3 |
| Configurações | 4 |

### Entidades Principais

| Entidade | Descrição | Relacionamentos |
|----------|-----------|-----------------|
| User | Bombeiros/usuários | 1:N Patent, 1:N Battalion, N:M Roles |
| Occurrence | Ocorrências/chamados | 1:1 Address, N:M User |
| Battalion | Batalhões | 1:N User, 1:1 Address |
| Patent | Patentes militares | 1:N User |
| Roles | Perfis de acesso | N:M User |

---

## 🔐 Segurança

### Autenticação
- **Método:** JWT com chaves RSA (2048 bits)
- **Validade Token:** 10 horas
- **Refresh Token:** 30 dias
- **Hash Senha:** BCrypt

### Autorização
- **Roles:** admin, superuser
- **Proteção:** Todas as rotas exceto login
- **CORS:** Configurado (produção ajustável)

---

## 📡 APIs Disponíveis

### Resumo de Endpoints

| Módulo | Endpoints | Funcionalidades |
|--------|-----------|-----------------|
| **Auth** | 9 endpoints | Login, cadastro, refresh token, logout |
| **Occurrences** | 7 endpoints | CRUD completo + conclusão |
| **Battalion** | 6 endpoints | CRUD completo |
| **Patent** | 7 endpoints | CRUD completo |
| **Vehicle** | - | Em desenvolvimento |

### Principais Operações

- ✅ Autenticação e autorização
- ✅ CRUD de usuários com paginação
- ✅ CRUD de ocorrências com geolocalização
- ✅ CRUD de batalhões
- ✅ CRUD de patentes
- ✅ Soft delete (desativação)
- ✅ Filtros e buscas
- ✅ Paginação em todas as listagens

---

## 🗄️ Modelo de Dados

### Principais Tabelas

1. **auth_user** - Usuários/bombeiros (26 campos)
2. **occurrence** - Ocorrências (14 campos)
3. **battalion** - Batalhões (8 campos)
4. **patent** - Patentes (5 campos)
5. **auth_roles** - Perfis de acesso (6 campos)
6. **address** - Endereços (11 campos)

### Características

- ✅ Normalização adequada
- ✅ Integridade referencial
- ✅ Soft delete em todas as tabelas
- ✅ Auditoria automática (created_at, updated_at)
- ✅ Índices em campos únicos
- ✅ Campos normalizados para busca

---

## 📈 Funcionalidades Implementadas

### ✅ Completo

- [x] Autenticação JWT completa
- [x] Cadastro de usuários
- [x] Gestão de batalhões
- [x] Gestão de patentes
- [x] Criação de ocorrências
- [x] Conclusão de ocorrências
- [x] Atualização de ocorrências
- [x] Paginação e filtros
- [x] Soft delete
- [x] Ativação/desativação de registros
- [x] Documentação Swagger
- [x] Validações de entrada
- [x] Tratamento de erros

### 🚧 Em Desenvolvimento

- [ ] Módulo de veículos
- [ ] Tipos e subtipos de ocorrências
- [ ] Relatórios e dashboards
- [ ] Notificações em tempo real
- [ ] Integração com mapas
- [ ] Upload de arquivos/imagens

---

## 🔄 Fluxos Principais

### 1. Autenticação
1. Usuário envia credenciais
2. Sistema valida e gera JWT
3. Cliente usa token em requisições
4. Token válido por 10h
5. Refresh disponível por 30 dias

### 2. Registro de Ocorrência
1. Bombeiro cria ocorrência (dados básicos)
2. Status: EM_ATENDIMENTO
3. Equipe se desloca
4. Ao chegar, completa ocorrência (detalhes, localização)
5. Status: CONCLUIDA
6. Histórico mantido

### 3. Gestão de Usuários
1. Admin cria usuário
2. Define batalhão e patente
3. Usuário recebe senha padrão
4. Primeiro login: trocar senha
5. Roles definidas

---

## 📚 Documentação Disponível

1. **README.md** - Guia principal e instalação
2. **ARQUITETURA.md** - Detalhes arquiteturais
3. **MODELO_DADOS.md** - Estrutura do banco de dados
4. **FLUXO_DADOS.md** - Fluxos de processos
5. **API_REFERENCE.md** - Referência completa de APIs
6. **Swagger UI** - Documentação interativa

---

## 🚀 Como Começar

### Instalação Rápida

```bash
# 1. Clonar repositório
git clone <url>

# 2. Criar banco PostgreSQL
createdb central_controle_fogo

# 3. Gerar chaves RSA
openssl genrsa -out src/main/resources/app.key 2048
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub

# 4. Configurar application.properties
cp application.properties.examples.dev application.properties
# Editar com suas credenciais

# 5. Executar
./mvnw spring-boot:run

# 6. Acessar Swagger
http://localhost:8080/swagger-ui/index.html
```

---

## 📊 Métricas de Qualidade

### Código
- ✅ Padrões de projeto aplicados
- ✅ Separation of Concerns
- ✅ DRY (Don't Repeat Yourself)
- ✅ SOLID principles
- ✅ Clean Code

### Segurança
- ✅ Autenticação robusta
- ✅ Senhas hasheadas
- ✅ Tokens assinados
- ✅ Validações de entrada
- ✅ SQL Injection prevenido (JPA)

### Performance
- ✅ Queries otimizadas
- ✅ Paginação implementada
- ✅ Índices no banco
- ✅ Connection pooling
- ✅ Lazy loading (JPA)

---

## 🎓 Aprendizados e Tecnologias Aplicadas

### Conceitos Implementados

1. **REST API** - Princípios RESTful
2. **JWT Authentication** - Tokens stateless
3. **ORM** - Mapeamento objeto-relacional
4. **Design Patterns** - Repository, DTO, Service Layer
5. **Dependency Injection** - IoC do Spring
6. **Validation** - Bean Validation (Jakarta)
7. **Documentation** - OpenAPI/Swagger
8. **Security** - Spring Security
9. **Database Design** - Normalização e relacionamentos

---

## 👥 Benefícios para os Stakeholders

### Corpo de Bombeiros
- ✅ Centralização de informações
- ✅ Rastreamento de ocorrências
- ✅ Histórico completo
- ✅ Gestão de equipes
- ✅ Relatórios (futuro)

### Alunos Senac
- ✅ Experiência prática
- ✅ Tecnologias modernas
- ✅ Projeto real
- ✅ Trabalho em equipe
- ✅ Portfolio profissional

---

## 🔮 Roadmap Futuro

### Curto Prazo (3 meses)
- [ ] Completar módulo de veículos
- [ ] Implementar tipos de ocorrências
- [ ] Testes unitários
- [ ] Testes de integração

### Médio Prazo (6 meses)
- [ ] Dashboard web
- [ ] Notificações push
- [ ] Relatórios PDF
- [ ] Integração Google Maps
- [ ] Deploy em nuvem

### Longo Prazo (1 ano)
- [ ] App mobile
- [ ] BI e analytics
- [ ] Machine Learning (previsões)
- [ ] Integração com outros sistemas
- [ ] Módulo de treinamento

---

## 📞 Contato e Suporte

**Instituição:** Faculdade Senac  
**Parceiro:** Corpo de Bombeiros de Pernambuco  
**Documentação:** pasta `docs/`  
**Swagger:** http://localhost:8080/swagger-ui/index.html

---

## 📄 Licença

Projeto acadêmico desenvolvido em parceria institucional.

---

## 🏆 Conclusão

O **Backend Central Controle de Fogo** é uma solução robusta, escalável e segura que atende às necessidades do Corpo de Bombeiros de Pernambuco. Construído com tecnologias modernas e seguindo as melhores práticas de desenvolvimento, o sistema está pronto para evolução contínua e expansão de funcionalidades.

**Desenvolvido com ❤️ e dedicação pelos alunos da Faculdade Senac**
