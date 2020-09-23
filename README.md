# Challenge GuiaBolso

This is the development of the chanllenge https://github.com/GuiaBolso/seja-um-guia-back

# Table of contents
* [Technologies](#Technologies)
* [Docker](#Docker)
* [Endpoints](#Endpoints)

# Technologies

- Maven 3.6
- Java 1.8.0_265
- Docker 19.03.12
- Spring 2.3.4.RELEASE

# Docker

- Build
```
docker build -t guiabolso/mockapi .
```
- Run
```
docker run -p 8080:8080 guiabolso/mockapi
```

# Endpoints

- Get list of transactions
    - 1000 <= id <= 100.000.000
    - 1 <= mes <= 12 
```
[GET] /<id>/transacoes/<ano>/<mes>
Content-type: application/json

[
  {
     "descricao": "string(10, 120)"
     "data": "long(timestamp)"
     "valor": "integer(-9.999.999, 9.999.999)"
     "duplicated": "boolean"
  }  
]
```

- local host

```
localhost:8080/{id}/transacoes/{ano}/{mes}
```

- heroku

```
https://guia-bolso-challenge.herokuapp.com/{id}/transacoes/{ano}/{mes}
```

- Swagger documentation

```
http://localhost:8080/swagger-ui/#/
```