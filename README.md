# NDL-Commerce
E-commerce desenvolvido com **Spring Boot**, **Java 21** e **arquitetura limpa**.

## 📦 Migrações de Banco de Dados

O projeto utiliza **Flyway** para controle de versões do banco de dados.

### Arquivo de configuração
Antes de rodar as migrações, crie o arquivo `ndlFlywayConfig.conf` na raiz do projeto com o seguinte conteúdo:

```properties
flyway.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
flyway.user=<USUARIO>
flyway.password=<SENHA>
```

### Rodando as migrações
```
mvn "-Dflyway.configFiles=ndlFlywayConfig.conf" flyway:migrate
```
Esse comando executa todas as migrações pendentes no banco de dados configurado.
