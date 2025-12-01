# 🛒 NDL-Commerce

NDL-Commerce é um projeto de **E-commerce** desenvolvido em **Java 21** com **Spring Boot**, seguindo os princípios da **Arquitetura Limpa (Clean Architecture)**.

O objetivo é construir uma aplicação modular, escalável e de fácil manutenção, separando regras de negócio de frameworks e detalhes de infraestrutura.

---

## 📖 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Flyway**
- **PostgreSQL**
- **JPA/Hibernate**
- **Arquitetura Limpa (Clean Architecture)**

---

## 🚀 Branches

- **`dev`**  
  Branch principal de desenvolvimento.  
  Aqui ficam as implementações em andamento, novas features, ajustes e experimentos antes de revisão.  
  Pode conter mudanças ainda não validadas para produção.

- **`main`**  
  Branch estável, destinada a releases.  
  Atualmente contém apenas a base inicial do projeto.  
  Novas features serão integradas após revisão e validação da branch `dev`.

---

## 📦 Migrações de Banco de Dados

O projeto utiliza **Flyway** para versionamento e controle de migrações no banco de dados.  
Todas as alterações de schema são versionadas em `src/main/resources/db/migration`.

### 🔧 Configuração
Antes de rodar as migrações, crie o arquivo `ndlFlywayConfig.conf` na raiz do projeto com o seguinte conteúdo:

```properties
flyway.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
flyway.user=<USUARIO>
flyway.password=<SENHA>
```

### ▶️ Executando as migrações

```bash
mvn "-Dflyway.configFiles=ndlFlywayConfig.conf" flyway:migrate
```

Esse comando executa todas as migrações pendentes no banco configurado.

---

## 📌 Status do Projeto

Atualmente em desenvolvimento ativo na branch `dev`, onde estão sendo adicionadas **validações iniciais ao salvar usuário** e tratativas de **exceptions personalizadas**.

A branch `main` permanece vazia, servindo como base para futuras revisões e publicações oficiais da aplicação.
