# Cadastro de cervejas artesanais


## Set up environment

Para iniciar o projeto é necessário realizar o clone deste repositório

```bash
    $ git clone https://github.com/Bendazzoli/CraftBeer
```

Ter banco de dados MySQL instalado.
Para criar o banco de dados e, a tabela para esse projeto, seguir com os comandos registrados em

    craftbeer
    |
    |docs
    |    |___cria-banco-dados-craftbeer


## Especificação das APIS

As APIs seguem especificadas no swagger que está nesse projeto.

    craftbeer
    |
    |docs
    |    |___swagger-craftbeer


### Funcionalidade das APIs

```bash
    GET /craftbeer/v1/beers/
```
Retorna todas as cervejas cadastradas.


```bash
    GET /craftbeer/v1/beers/{id}
```
Retorna uma cerveja de acordo com o id informado na URI.


```bash
    POST /craftbeer/v1/beers/
```
Cadastra uma cerveja de acordo com o RequestBody informado.


```bash
    PUT /craftbeer/v1/beers/{id}
```
Atualiza todos os dados de uma cerveja de acordo com o id informado na URI e o RequestBody (todos os campos obrigatórios).


```bash
    PATCH /craftbeer/v1/beers/{id}
```
Atualiza todos os dados de uma cerveja de acordo com o id informado na URI e o RequestBody (apenas informar o campo desejado a ser atualizado).


```bash
    DELETE /craftbeer/v1/beers/{id}
```
Deleta uma cerveja de acordo com o id informado na URI.