# CQRS com Axon Framework

Aplicação desenvolvida para fins de estudo e validar o uso de CQRS com Axon Framework.

O contexto utilizado é o gerenciamento de uma conta bancária.

A Aplicação está dividida em 2 módulos:
- bank-account-command
- bank-account-query

O **bank-account-command** é responsável por realizar operações de comando, ou seja, criar a conta, atualizar o saldo e excluir a conta.

Já o **bank-account-query** realiza a operação de query, retornando a lista de contas criadas e também a lista de eventos realizados para uma determinada conta (Event Sourcing).

### Docker
Será necessário subir as imagens abaixo.

A primeira é do Axon Server, que será o Barramento responsável por gerenciar os eventos.

`docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver`

A segunda é a do MySQL, para armazenar as contas. (Caso queira você poderá usar o H2 aqui, só lembre de alterar no application.yml)

`docker run --name mysql -p 3306:3306 -h localhost -e MYSQL_ROOT_PASSWORD=root -d mysql:8`

### Endpoints

#### Criar uma conta

```sh
POST http://localhost:8081/bank-accounts
{
    "name": "Itau"
}
```

#### Atualizar o saldo

```sh
PUT http://localhost:8081/bank-accounts/{accountId}/balances
{
    "balance": 200
}
```

#### Remover uma conta

```sh
DELETE http://localhost:8081/bank-accounts/{accountId}
```

#### Visualizar uma conta

```sh
GET http://localhost:8082/bank-accounts/{accountId}
{
    "id": "f3fe0d24-23da-4865-b629-67fedf1299b0",
    "name": "Itau",
    "balance": 200.00
}
```

#### Visualizar os eventos relacionados à conta (Event Sourcing)

```sh
GET http://localhost:8081/events/{accountId}
[
    {
        "type": "BankAccountAggregate",
        "aggregateIdentifier": "f3fe0d24-23da-4865-b629-67fedf1299b0",
        "sequenceNumber": 0,
        "timestamp": "2020-09-17T20:20:01.007Z",
        "identifier": "7783b424-f5f9-4ddd-abf9-052b583b4721",
        "payload": {
            "id": "f3fe0d24-23da-4865-b629-67fedf1299b0",
            "name": "Itau",
            "balance": 0
        },
        "payloadType": "br.com.fuzeto.bankaccountcommand.event.BankAccountAddedEvent",
        "metaData": {
            "traceId": "49a502fc-09b8-46fe-9f57-a853a9c3ba41",
            "correlationId": "49a502fc-09b8-46fe-9f57-a853a9c3ba41"
        }
    },
    {
        "type": "BankAccountAggregate",
        "aggregateIdentifier": "f3fe0d24-23da-4865-b629-67fedf1299b0",
        "sequenceNumber": 1,
        "timestamp": "2020-09-17T20:20:52.748Z",
        "identifier": "e3b4534b-371e-4e55-a164-4eb396eef5c4",
        "payload": {
            "bankId": "f3fe0d24-23da-4865-b629-67fedf1299b0",
            "balance": 200
        },
        "payloadType": "br.com.fuzeto.bankaccountcommand.event.BankAccountBalanceUpdatedEvent",
        "metaData": {
            "traceId": "ecf5886b-5e15-46bc-b1f4-9dbe1a94165c",
            "correlationId": "ecf5886b-5e15-46bc-b1f4-9dbe1a94165c"
        }
    }
]
```

### Referências:
- https://axoniq.io/
- https://coderef.com.br/cqrs-e-event-sourcing-com-axon-framework-e-spring-boot-7bd83093782d
- https://blog.nebrass.fr/playing-with-cqrs-and-event-sourcing-in-spring-boot-and-axon/