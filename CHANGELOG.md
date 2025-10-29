# 📝 Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Semantic Versioning](https://semver.org/lang/pt-BR/).

---

## [0.0.1-SNAPSHOT] - 2025-10-29

### 🎉 Versão Inicial

Primeira versão do Backend Central Controle de Fogo desenvolvido em parceria entre Faculdade Senac e Corpo de Bombeiros de Pernambuco.

### ✅ Adicionado

#### Infraestrutura
- Projeto Spring Boot 3.5.6 inicializado
- Configuração PostgreSQL
- Autenticação JWT com chaves RSA
- Spring Security configurado
- Swagger/OpenAPI documentação
- Maven como gerenciador de dependências

#### Entidades
- `User` - Usuários/bombeiros com autenticação completa
- `Roles` e `UserRoles` - Sistema de permissões
- `Occurrence` - Registro de ocorrências
- `OccurrenceUsers` - Vínculo bombeiros-ocorrências
- `Battalion` - Batalhões do Corpo de Bombeiros
- `Patent` - Patentes militares
- `Address` - Endereços genéricos
- `OccurrenceType` e `OccurrenceSubType` - Categorização
- `Base` - Classe abstrata com auditoria

#### APIs - Auth
- `POST /api/auth/login` - Login de usuários
- `GET /api/auth` - Buscar usuário autenticado
- `GET /api/auth/{id}` - Buscar usuário por ID
- `POST /api/auth/created/user` - Criar usuário
- `POST /api/auth/refresh-token/` - Renovar token
- `POST /api/auth/logout/{id}` - Logout
- `PUT /api/auth/deactivate/{id}` - Desativar usuário
- `PUT /api/auth/activate/{id}` - Ativar usuário
- `GET /api/auth/paginator` - Listar usuários paginado

#### APIs - Occurrences
- `POST /api/occurrences` - Criar ocorrência
- `GET /api/occurrences/{id}` - Buscar ocorrência
- `PUT /api/occurrences/complete/{id}` - Concluir ocorrência
- `PUT /api/occurrences/{id}` - Atualizar ocorrência
- `PUT /api/occurrences/deactivate/{id}` - Desativar
- `PUT /api/occurrences/activate/{id}` - Ativar
- `GET /api/occurrences/paginator` - Listar paginado

#### APIs - Battalion
- `POST /api/battalion/created` - Criar batalhão
- `GET /api/battalion/{id}` - Buscar batalhão
- `PUT /api/battalion/{id}` - Atualizar batalhão
- `PUT /api/battalion/deactivate/{id}` - Desativar
- `PUT /api/battalion/activate/{id}` - Ativar
- `GET /api/battalion/paginator` - Listar paginado

#### APIs - Patent
- `POST /api/patent/register/patent` - Criar patente
- `GET /api/patent/{id}` - Buscar patente
- `PUT /api/patent/{id}` - Atualizar patente
- `PUT /api/patent/deactivate/{id}` - Desativar
- `PUT /api/patent/activate/{id}` - Ativar
- `GET /api/patent/paginator` - Listar paginado
- `GET /api/patent` - Listar todas

#### Funcionalidades
- Soft delete em todas as entidades
- Auditoria automática (created_at, updated_at)
- Validação de entrada com Bean Validation
- Paginação genérica em listagens
- Filtros de busca
- Normalização de campos para busca
- Mapeamento DTO/Entity com ModelMapper
- Hash de senhas com BCrypt
- Geolocalização de ocorrências (latitude/longitude)
- Status de ocorrências (enum)
- Refresh token com expiração

#### Documentação
- README.md principal completo
- Swagger UI integrado
- Documentação técnica em `/docs`:
  - RESUMO_EXECUTIVO.md
  - ARQUITETURA.md
  - MODELO_DADOS.md
  - FLUXO_DADOS.md
  - API_REFERENCE.md
  - GUIA_CONFIGURACAO.md
  - README.md (índice)
- Exemplos de configuração
- CHANGELOG.md

#### Segurança
- JWT com chaves RSA 2048 bits
- Tokens com expiração (10h)
- Refresh tokens (30 dias)
- CORS configurado
- Senhas hasheadas (BCrypt)
- Endpoints públicos apenas para criação inicial

### 🚧 Em Desenvolvimento

#### Módulos Parciais
- Vehicle (código comentado, aguardando implementação completa)

### 📋 Pendente

#### Próximas Versões
- Módulo de veículos completo
- Tipos e subtipos de ocorrências (funcional)
- Testes unitários
- Testes de integração
- Relatórios
- Notificações
- Upload de arquivos
- Dashboard analytics

---

## [Unreleased]

### Planejado para v0.1.0
- [ ] Módulo de veículos
- [ ] Relacionamento ocorrências-veículos
- [ ] Tipos de ocorrências funcional
- [ ] Testes unitários (cobertura 70%)
- [ ] CI/CD básico

### Planejado para v0.2.0
- [ ] Relatórios PDF
- [ ] Dashboard básico
- [ ] Notificações por email
- [ ] Logs estruturados
- [ ] Métricas (Actuator)

### Planejado para v1.0.0
- [ ] Testes completos (80%+)
- [ ] Performance otimizada
- [ ] Documentação completa
- [ ] Deploy em produção
- [ ] Monitoring configurado
- [ ] Backup automático

---

## Notas de Versão

### Convenções

Este projeto usa [Semantic Versioning](https://semver.org/):

- **MAJOR.MINOR.PATCH**
  - **MAJOR:** Mudanças incompatíveis na API
  - **MINOR:** Novas funcionalidades compatíveis
  - **PATCH:** Correções de bugs compatíveis

### Tipos de Mudanças

- `Adicionado` para novas funcionalidades
- `Modificado` para mudanças em funcionalidades existentes
- `Obsoleto` para funcionalidades que serão removidas
- `Removido` para funcionalidades removidas
- `Corrigido` para correções de bugs
- `Segurança` para vulnerabilidades corrigidas

---

## Roadmap

### Curto Prazo (1-3 meses)
- ✅ Core APIs funcionais
- ✅ Autenticação e autorização
- ✅ CRUD completo das entidades principais
- 🚧 Módulo de veículos
- ⏳ Testes automatizados

### Médio Prazo (3-6 meses)
- ⏳ Relatórios e analytics
- ⏳ Dashboard web
- ⏳ Notificações
- ⏳ Integração com mapas
- ⏳ App mobile (início)

### Longo Prazo (6-12 meses)
- ⏳ Sistema completo em produção
- ⏳ Machine Learning para previsões
- ⏳ Integração com outros sistemas
- ⏳ App mobile completo
- ⏳ BI e analytics avançado

---

## Contribuidores

### Equipe Faculdade Senac
- Desenvolvedor Backend
- Desenvolvedor Frontend (futuro)
- DBA
- QA/Tester
- Tech Lead

### Corpo de Bombeiros de Pernambuco
- Product Owner
- Domain Experts
- Usuários finais (testers)

---

## Licença

Projeto acadêmico - Faculdade Senac em parceria com Corpo de Bombeiros de Pernambuco.

---

**Legenda:**
- ✅ Completo
- 🚧 Em desenvolvimento
- ⏳ Planejado
- ❌ Cancelado

---

**Última atualização:** 29 de Outubro de 2025  
**Mantido por:** Equipe Senac
