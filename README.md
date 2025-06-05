
#  API - Sistema de Gestão Comercial e Financeira

Este projeto é uma API REST desenvolvida com **Spring Boot 3.5.0** e **Java 17**, voltada para a gestão comercial e financeira de empresas de serviços, como impermeabilização. A API realiza o gerenciamento de clientes, orçamentos, produtos, serviços, categorias, consumo de materiais e controle de produtividade.

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- Spring Web
- PostgreSQL (Supabase)
- Maven
- Lombok
- Hibernate
- Bean Validation
- Swagger (OpenAPI)
- Postman

---

##  Módulos Disponíveis

- **Clientes** (com login e autenticação)
- **Funcionários**
- **Produtos**
- **Categorias**
- **Serviços**
- **Orçamentos**
- **Financeiro** (em breve)
- **Consumo e Produtividade** (em breve)

---

## Como Rodar o Projeto Localmente

### Pré-requisitos:
- Java 17+
- Maven
- PostgreSQL (ou Supabase)

### Passos:

```bash
git clone https://github.com/seu-usuario/gestaocomercial-api.git
cd gestaocomercial-api
```

Configure `src/main/resources/application.properties` com sua base de dados PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://<host>:5432/<database>
spring.datasource.username=<usuario>
spring.datasource.password=<senha>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Execute o projeto:

```bash
./mvnw spring-boot:run
```

---

## Como Usar a API (Ensinamento)

### 1. Cadastrar um Cliente

**Endpoint:** `POST /api/clientes`

```json
{
  "nome": "João da Silva",
  "cpf": "12345678900",
  "telefone": "61999999999",
  "email": "joao@email.com",
  "senha": "123456"
}
```

---

### 2. Fazer Login

**Endpoint:** `POST /api/clientes/login`

```json
{
  "cpf": "12345678900",
  "senha": "123456"
}
```

---

### 3. Cadastrar uma Categoria

**Endpoint:** `POST /api/categorias`

```json
{
  "nome": "Impermeabilizantes"
}
```

---

### 4. Cadastrar um Produto

**Endpoint:** `POST /api/produtos`

```json
{
  "nome": "Produto A",
  "descricao": "Impermeabilizante de alta resistência",
  "precoUnitario": 250.00,
  "categoriaId": 1,
  "imagemUrl": ["https://url1.com", "https://url2.com"]
}
```

---

### 5. Cadastrar um Serviço

**Endpoint:** `POST /api/servicos`

```json
{
  "nome": "Aplicação de Manta Asfáltica",
  "descricao": "Serviço especializado em coberturas",
  "precoBase": 300.00
}
```

---

### 6. Criar um Orçamento

**Endpoint:** `POST /api/orcamentos`

```json
{
  "clienteId": 1,
  "formaPagamento": "A_VISTA",
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "servicoId": 1,
      "quantidade": 1
    }
  ]
}
```

---

## Testes com Postman

 Função             | Método | Rota                     

 Cadastrar Cliente  | POST   | `/api/clientes`          
 Login Cliente      | POST   | `/api/clientes/login`    
 Cadastrar Produto  | POST   | `/api/produtos`          
 Cadastrar Serviço  | POST   | `/api/servicos`          
 Cadastrar Orçamento| POST   | `/api/orcamentos`        
 Listar Clientes    | GET    | `/api/clientes`          
 Listar Produtos    | GET    | `/api/produtos`          |

---

##  Estrutura de Pacotes

```
com.upsolucoes.gestaocomercial
├── controller
├── service
├── model
├── repository
├── dto
└── config
```

---

## Funcionalidades Futuras

- Relatórios financeiros
- Controle de consumo de materiais
- Dashboard com indicadores
- Sistema de permissões e login para funcionários

---


## Autor

Desenvolvido por **Rian A. Costa**  
🔗 [LinkedIn](https://www.linkedin.com/in/rian-costa) | 💻 Java Backend Developer
