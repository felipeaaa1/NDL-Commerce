# Security Policy

## Supported Versions

A tabela abaixo define quais versões do NDL-Commerce recebem atualizações de segurança:

| Version | Supported |
|--------|-----------|
| 1.x.x  | :white_check_mark: Active |
| 0.x.x  | :x: End of Support |

Versões abaixo de 1.x.x estão em fase de desenvolvimento ativo e não recebem patches formais de segurança, exceto em casos críticos.

---

## Reporting a Vulnerability

Se você encontrar uma vulnerabilidade de segurança, **não abra uma issue pública**.

Para garantir um processo seguro e responsável, siga estas etapas:

1. Envie um e-mail para: **arnaud.felipe96@gmail.com.br**  

2. Inclua no relatório:
   - Descrição detalhada da vulnerabilidade  
   - Passos para reprodução  
   - Impacto potencial  
   - Logs, prints ou payloads relevantes  
   - Sugestão de correção (opcional)

3. Você receberá uma resposta em até **72 horas** confirmando o recebimento.

4. O prazo médio para triagem é de **7 a 14 dias**.

Se a vulnerabilidade for confirmada:
- Um identificador interno poderá ser atribuído  
- A correção será planejada e divulgada na próxima versão  
- Você será informado durante todo o processo  

---

## Reporting Unhandled Exceptions

Caso você encontre uma **exceção não tratada** durante o uso da API, o sistema retornará algo semelhante a:


``` java
"🎉 Parabeeens🎉 você achou um erro não tratado! Por gentileza entre em contato com a mensagem e causa do erro: <message> | causa: <cause> | localização: <localizedMessage>"
```

Esse tipo de resposta significa que ocorreu um erro inesperado que ainda não possui tratamento adequado na aplicação.

Para reportar corretamente:

1. Copie a mensagem completa retornada pela API  
2. Inclua o endpoint chamado e o corpo da requisição (se aplicável)  
3. Envie o relatório para **arnaud.felipe96@gmail.com.br**  
4. O time avaliará se o erro é:  
   - Uma vulnerabilidade de segurança  
   - Um bug funcional  
   - Um problema de configuração  

Erros não tratados são de alta prioridade, pois podem expor comportamento inesperado e, potencialmente, riscos de segurança.

---

Agradecemos qualquer contribuição que ajude a tornar o NDL-Commerce mais seguro e confiável.
