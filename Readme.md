# 🍔 Aplicativo de Lanchonete

> Projeto completo de sistema de pedidos de lanchonete com **Java 21 (Spring Boot)** no backend e **React** no frontend web.  
> O aplicativo mobile ainda não está incluído nesta etapa.  
> Arquitetura em camadas (Controller → Service → Repository) com persistência em **PostgreSQL**.

---

## 🧾 Sumário
- 📝[Descrição](#descrição)
- 🛠️[Tecnologias](#tecnologias)
- 🗂️[Arquitetura / Estrutura de pastas](#arquitetura--estrutura-de-pastas)
- 👥[Entidades principais](#entidades-principais)
- 🔗[Relacionamentos](#relacionamentos)
- 📦[Fluxo de pedido resumido](#fluxo-de-pedido-resumido)
- 📡[APIs principais (exemplos)](#apis-principais-exemplos)
- 💾[Configuração do banco (PostgreSQL)](#configuração-do-banco-postgresql)
- ▶️[Como rodar](#como-rodar)
- 🔒[Notas sobre autenticação (JWT)](#notas-sobre-autenticação-jwt)
- 👨‍💻[Equipe](#equipe)

---

## 📝 Descrição
A **API de Lanchonete** é um sistema backend responsável por gerenciar pedidos, produtos, usuários e entregas.  
Permite o cadastro de usuários, controle de pedidos, gerenciamento de status e geração de notas fiscais.  
O sistema tem como foco o aprendizado de **arquitetura em camadas**, **boas práticas REST** e **segurança com JWT**.

---

## 🛠️ Tecnologias
- Java 21
- Spring Boot (Spring Web, Spring Data JPA, Spring Security)
- Maven
- PostgreSQL
- JPA / Hibernate
- JWT para autenticação
- Lombok

## 🗂️ Arquitetura / Estrutura de pastas
```
src/
 └─ main/
    ├─ java/
    │  └─ com/seuprojeto/
    │     ├─ controller/   # RestControllers
    │     ├─ service/      # Regras de negócio
    │     ├─ repository/   # Spring Data JPA Repositories
    │     ├─ model/        # Entidades JPA
    │     ├─ dto/          # Objetos de transferência de dados
    │     └─ config/       # Segurança, JWT, CORS
    └─ resources/
       ├─ application.properties
```

---

## 👥 Entidades principais
- 👤 **Usuario** — id, nome, email, senha (hash), tipo (CLIENTE/ADMIN), endereço, telefone.
- 🍔 **Produto** — id, nome, descrição, preço, imagemUrl (opcional).
- 🛒 **Pedido** — id, data, valorTotal, status, taxaEntrega (fixa), usuarioId, entregadorId (sempre setado para um entregador disponível).
- 📦 **ItemPedido** — id, pedidoId, produtoId, quantidade, precoUnitario (copiado do produto no momento da compra).
- 🧾 **NotaFiscal** — id, número, dataEmissao, valorTotal, impostos, pedidoId.
- 🏍️
- **Entregador** — id, nome, telefone, veículo, placa, disponibilidade.

---

## 🔗 Relacionamentos
- `Usuario (1) — (N) Pedido`
- `Pedido (1) — (N) ItemPedido`
- `Produto (1) — (N) ItemPedido`
- `Pedido (1) — (1) NotaFiscal`
- `Entregador (1) — (N) Pedido`

---

## 📦 Fluxo de pedido resumido
1. Usuário cadastra-se e faz login.
2. Cliente envia o pedido com produtos e quantidades.
3. O sistema calcula o valor total, cria o `Pedido` e salva os `ItemPedido`.
4. Administrador atualiza o status para “Em preparo” / “Pronto para entrega”.
5. Entregador é atribuído e atualiza status para “Saiu para entrega” e “Entregue”.
6. Sistema gera a `NotaFiscal` após a conclusão.

---

## 📡 APIs principais

### Autenticação / Usuário
```http
POST /api/usuarios ✅
POST /api/login
# Retorna token JWT
```

### Produtos
```http
GET /api/produtos ✅
GET /api/produtos/{id} ✅
POST /api/produtos ✅         # ADMIN
PUT  /api/produtos/{id} ✅    # ADMIN
DELETE /api/produtos/{id} ✅  # ADMIN
```

### Pedidos
```http
POST /api/pedidos ✅
GET /api/pedidos ✅
PUT /api/pedidos/{id}/status?novoStatus=Em%20preparo
PUT /api/pedidos/{id}/entregador/{entregadorId}
PUT /api/pedidos/{id}/entregar
```

### Entregadores
```http
GET /api/entregadores/disponiveis ✅
POST /api/entregadores ✅   # ADMIN
PUT  /api/entregadores/{id}
```

### Nota Fiscal
```http
GET /api/notafiscal/{pedidoId}
```

---

## 💾 Configuração do banco (PostgreSQL)
Arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lanchonete_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Criação do banco:
```sql
CREATE DATABASE lanchonete_db;
```

---

## ▶️ Como rodar
1. Tenha **Java 21**, **Maven** e **PostgreSQL** instalados.
2. Configure o banco no arquivo `application.properties`.
3. No diretório do projeto, execute:
```bash
mvn clean package
mvn spring-boot:run
```
API disponível em: `http://localhost:8080`

---

## 🔒 Notas sobre autenticação (JWT)
- Usuário faz login e recebe um token JWT.
- Endpoints protegidos exigem `Authorization: Bearer <token>`.
- Roles disponíveis: `ROLE_CLIENTE`, `ROLE_ADMIN`, `ROLE_ENTREGADOR`.

---

## 👨‍💻 Equipe
- **Carlysson Kenneth**
- **João Victor**
- **Paulo Roberto** 

---