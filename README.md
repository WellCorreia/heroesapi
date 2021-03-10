# heroesapi
Projeto de estudo para prática de java spring boot com DynamoDB e Swagger.

## Lista de Stacks Utilizadas

#### `Java8`
#### `spring Webflux`
#### `Spring Data`
#### `dynamodb`
#### `sl4j`
#### `Reactor`

## Run DynamoDB:

É necessário fazer o download do DynamoDB

Após o download e configuração deve ser executado no repositório rapaz do dynamo o comando:

`java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb`

Para listar as tabelas criadas:

`aws dynamodb list-tables --endpoint-url http://localhost:8000`

## Documentação Swegger

Documentação gerada na aplicação pelo swagger:
`http://localhost:8080/swagger-ui.html`

![image](https://user-images.githubusercontent.com/17796246/110627645-fc158d80-8180-11eb-93db-1e60a765525b.png)
