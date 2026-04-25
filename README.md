# 🎬 Movie Catalog API

Uma API REST para gerenciamento de catálogo de filmes, construída com Java e Spring Boot. O projeto nasceu como portfólio pessoal, mas foi desenvolvido com as mesmas boas práticas que você encontraria num ambiente profissional — autenticação JWT, integração com AWS S3, deploy em nuvem e muito mais.

---

## ✨ O que essa API faz?

- Cadastro e autenticação de usuários com **JWT**
- CRUD completo de filmes (criar, listar, buscar, editar, deletar)
- Upload de poster de filme direto para o **AWS S3**
- Busca de filmes por título
- Paginação nas listagens
- Rotas públicas e privadas com controle de acesso

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Uso |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot 3 | Framework web |
| Spring Security | Autenticação e autorização |
| JWT (jjwt) | Geração e validação de tokens |
| Spring Data JPA + Hibernate | Persistência de dados |
| PostgreSQL | Banco de dados em produção |
| AWS S3 | Armazenamento de imagens |
| Railway | Deploy e hospedagem |
| Maven | Gerenciamento de dependências |
| Lombok | Redução de boilerplate |

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas, com responsabilidades bem separadas:

```
controller/   → recebe e responde requisições HTTP
service/      → regras de negócio
repository/   → acesso ao banco de dados
dto/          → objetos de transferência de dados
model/        → entidades do banco
mapper/       → conversão entre entidades e DTOs
security/     → filtros JWT e configuração do Spring Security
exception/    → tratamento global de erros
```

Os princípios **SOLID** foram aplicados ao longo do projeto — cada classe tem uma responsabilidade clara, e a injeção de dependências é feita via construtor.

---

## 🔐 Autenticação

A API usa **JWT (JSON Web Token)** para autenticação stateless. O fluxo é:

1. Usuário se registra ou faz login
2. API retorna um token JWT
3. Nas rotas protegidas, o token deve ser enviado no header:

```
Authorization: Bearer <seu_token_aqui>
```

### Rotas públicas (sem token):
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/movies` e `GET /api/movies/**`

### Rotas protegidas (precisam de token):
- `POST /api/movies`
- `PUT /api/movies/{id}`
- `DELETE /api/movies/{id}`
- `POST /api/movies/{id}/poster`

---

## 🚀 Rodando localmente

### Pré-requisitos
- Java 21+
- Maven
- PostgreSQL rodando localmente

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/movie-catalog.git
cd movie-catalog
```

**2. Configure o banco de dados**

Crie um banco PostgreSQL chamado `moviecatalog` e configure o arquivo `src/main/resources/application-dev.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/moviecatalog
    username: seu_usuario
    password: sua_senha

jwt:
  secret: sua-chave-secreta-com-pelo-menos-32-caracteres
  expiration: 86400000

aws:
  access-key: sua_access_key
  secret-key: sua_secret_key
  region: us-east-2
  s3:
    bucket: nome-do-seu-bucket
```

**3. Rode a aplicação**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

A API estará disponível em `http://localhost:8080`.

---

## 📋 Exemplos de uso

### Registrar usuário
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "joaosilva",
    "email": "joao@email.com",
    "password": "minhasenha123"
  }'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9...",
  "username": "joaosilva",
  "role": "USER"
}
```

### Criar um filme (autenticado)
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer seu_token_aqui" \
  -d '{
    "title": "Inception",
    "description": "Um ladrão que rouba segredos através dos sonhos",
    "genre": "Sci-Fi",
    "releaseYear": 2010,
    "director": "Christopher Nolan"
  }'
```

### Listar filmes (público)
```bash
curl http://localhost:8080/api/movies?page=0&size=10
```

---

## ☁️ Deploy

A aplicação está em produção no **Railway** com banco **PostgreSQL** gerenciado.

🔗 **URL da API:** `https://movie-catalog-production-900c.up.railway.app`

As variáveis de ambiente configuradas no Railway:

```
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
AWS_ACCESS_KEY
AWS_SECRET_KEY
S3_BUCKET
```

---

## 📚 O que aprendi construindo isso

Esse projeto foi minha primeira API completa em produção. Ao longo do desenvolvimento, enfrentei e resolvi problemas reais:

- Configuração de profiles do Spring Boot para ambientes diferentes
- Debug de erros de deploy lendo logs de produção
- Incidente de segurança com credenciais vazadas no Git (e como remediar com `git filter-branch`)
- Diferença entre HTTP e HTTPS em produção
- Integração com serviços externos (AWS S3, banco remoto)

---

## 👨‍💻 Autor

Feito com muito café e paciência.
