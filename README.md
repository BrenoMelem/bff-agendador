# bff-agendador (bff-agendador-tarefas)

**BFF (Backend For Frontend)** construído em **Java 21 + Spring Boot**, responsável por agregar e orquestrar as chamadas aos microsserviços do ecossistema **agendador de tarefas**.

## 📖 Sobre o projeto

O `bff-agendador-tarefas` funciona como uma camada intermediária entre o front-end e os microsserviços internos, simplificando o consumo da API para o cliente. Ele se comunica com os demais serviços via **OpenFeign** (usando o cliente HTTP Apache — `feign-hc5`) e expõe sua própria documentação de API com **Springdoc OpenAPI (Swagger UI)**.

### 🧩 Arquitetura do ecossistema

Este BFF integra e orquestra os seguintes microsserviços (conforme o `docker-compose.yml` do projeto):

| Serviço | Porta | Responsabilidade | Banco |
|---|---|---|---|
| `usuario` | 8080 | Cadastro/autenticação de usuários | PostgreSQL |
| `agendador-tarefas` | 8081 | Agendamento de tarefas | MongoDB |
| `notificacao` | 8082 | Envio de e-mails/notificações | — |
| **`bff-agendador-tarefas`** | **8083** | **Agregação das chamadas acima (este repositório)** | — |

## 🚀 Tecnologias utilizadas

**Linguagem e build**
- Java 21
- Maven (Maven Wrapper incluído — não precisa ter o Maven instalado)

**Framework e core**
- Spring Boot 4.0.6 (via `spring-boot-starter-parent`)
- Spring Web MVC — camada REST
- Spring Cloud OpenFeign — comunicação HTTP com os demais microsserviços
- Feign HC5 (`feign-hc5`) — cliente HTTP Apache para o OpenFeign

**Documentação de API**
- Springdoc OpenAPI (Swagger UI)

**Produtividade**
- Lombok

**Testes**
- Spring Boot Starter Test (Web)

**Infraestrutura**
- Docker (build multi-stage)
- Docker Compose — sobe o ecossistema completo (bff, usuario, agendador-tarefas, notificacao, PostgreSQL e MongoDB)
- GitHub Actions (CI/CD)

## 📂 Estrutura do projeto

```
bff-agendador/
├── .github/workflows/     # Pipelines de CI (GitHub Actions)
├── .mvn/wrapper/            # Maven Wrapper
├── src/                      # Código-fonte da aplicação
├── docker-compose.yml       # Orquestração de todo o ecossistema de microsserviços
├── Dockerfile                # Build multi-stage da imagem da aplicação
├── mvnw / mvnw.cmd          # Maven Wrapper (Unix/Windows)
└── pom.xml
```

## ⚙️ Pré-requisitos

- Java 21 (JDK)
- Docker e Docker Compose (para rodar o ecossistema completo)
- Os repositórios `usuario`, `agendador-tarefas` e `notificacao` clonados nos caminhos esperados pelo `docker-compose.yml` (`../../usuario/usuario` e `../../agendador-tarefas/agendador-tarefas`, relativos a este projeto)

## ▶️ Como executar

### Via Docker Compose (recomendado — sobe todo o ecossistema)

1. Organize os repositórios na estrutura de pastas esperada pelo `docker-compose.yml` (ou ajuste os caminhos de `build:` conforme sua organização local).
2. Crie um arquivo `.env` na raiz deste projeto com as variáveis usadas pelo compose, por exemplo:

   ```env
   AGENDADOR_DB_URL=mongodb://mongo:27017/db_agendador
   AGENDADOR_USUARIO_URL=http://usuario:8080
   BFF_USUARIO_URL=http://usuario:8080
   BFF_AGENDADOR_URL=http://agendador-tarefas:8081
   BFF_NOTIFICACAO_URL=http://notificacao:8082
   USUARIO_DB_URL=jdbc:postgresql://postgres:port/db_name
   USUARIO_DB_USERNAME=postgres
   USUARIO_DB_PASSWORD=postgres
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   MAIL_USERNAME=seu-email@gmail.com
   MAIL_PASSWORD=sua-senha-de-app
   ```

3. Suba todos os serviços:

   ```bash
   docker-compose up --build
   ```

4. O BFF estará disponível em `http://localhost:8083`.

### Localmente com Maven

1. Garanta que os serviços dependentes (`usuario`, `agendador-tarefas`, `notificacao`) estejam em execução.
2. Configure as URLs desses serviços em `application.properties`/`application.yml`.
3. Execute:

   ```bash
   ./mvnw spring-boot:run
   ```

## 📑 Documentação da API

Com a aplicação em execução, a documentação interativa (Swagger UI) fica disponível em:

```
http://localhost:8083/swagger-ui.html
```

## 🧪 Testes

```bash
./mvnw test
```

## 🔁 CI/CD

O repositório conta com workflows em `.github/workflows` para automatizar build e verificações a cada push/pull request.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.
