
## TCC - Parking Spot - Documentação

Documentação para uso dos endpoints da API 

Endereço de acesso: https://tcc-parking-iot.herokuapp.com



## Usuários (users)

### Busca por id

* **URL**
    
  `/users/{id}`
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    
    {
    "id": 1,
    "firstName": "Breno",
    "lastName": "Araujo",
    "email": "breno.souza.araujo@hotmail.com",
    "cpf": "12132123",
    "vehicles": [
        {
            "id": 3,
            "plate": {
                "id": 3,
                "plateNumber": "ccc-000"
            },
            "model": "Nissan versa"
        }
    ]
}
    ``` 
    
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do colaborador                 | sim             | -                | 125             |
   
    ### Busca todos

* **URL**

    `/users`
    
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
        [
    {
        "id": 1,
        "firstName": "Breno",
        "lastName": "Araujo",
        "email": "breno.souza.araujo@hotmail.com",
        "cpf": "12132123",
        "vehicles": [
            {
                "id": 3,
                "plate": {
                    "id": 3,
                    "plateNumber": "ccc-000"
                },
                "model": "Nissan versa"
            }
        ]
    },
    {
        "id": 2,
        "firstName": "Gustavo",
        "lastName": "Sousa",
        "email": "gustavo@hotmail.com",
        "cpf": "12132123",
        "vehicles": []
    },
    {
        "id": 3,
        "firstName": "Guilherme",
        "lastName": "Andrade",
        "email": "guilherme@hotmail.com",
        "cpf": "12132123",
        "vehicles": []
    }
    ]

    ``` 
    ### Remover usuário
    
    
* **URL**

    `/users{id}`
    
* **Method**

  `DELETE`
  
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do colaborador                 | sim             | -                | 125             |
  
* **Retorno**
  
  **Status Code:** 204
  
  ### Cadastro usuário
  
* **URL**
    
  `/users`
  

* **Method**

  `POST`
  
* **Retorno**
  
  **Status Code:** 201
  
* **Exemplo de envio**

    ```json
    
    {

    "firstName": "Breno",
    "lastName": "Araujo",
    "email": "breno.souza.araujo@hotmail.com",
    "cpf": "12132123",
    "vehicles": [
        {
            "id": 3
        }
    ]
}
    ``` 

*  **Parâmetros via body**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | firstName          | String         | Nome do usuário                 | Sim  | -   | Breno             |
   | lastName          | String         | Sobrenome do usuário             | Sim  | -   | Araújo             |
   | email    | String         | email do usuário                 | Sim  | -   | breno.souza.araujo@hotmail.com             |
   | cpf          | String         | cpf usuário                 | Sim  | -   | 123123123             |
   | vehicles          | Conjunto de veículos         | Nome do usuário                 | não  | -   |              |
  
-----

## Veículos (vehicles)

### Busca por id

* **URL**
    
  `/vehicles/{id}`
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    
    {
    "id": 1,
    "firstName": "Breno",
    "lastName": "Araujo",
    "email": "breno.souza.araujo@hotmail.com",
    "cpf": "12132123",
    "vehicles": [
        {
            "id": 3,
            "plate": {
                "id": 3,
                "plateNumber": "ccc-000"
            },
            "model": "Nissan versa"
        }
            ]
        }
    ``` 
    
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do veículo               | sim             | -                | 125             |
   
    ### Busca todos

* **URL**

    `/vehicles`
    
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
    {
        "id": 1,
        "plate": {
            "id": 1,
            "plateNumber": "aaa-123"
        },
        "model": "Nissan versa"
    },
    {
        "id": 2,
        "plate": {
            "id": 2,
            "plateNumber": "bbb-456"
        },
        "model": "Ford Focus"
    },
    {
        "id": 3,
        "plate": {
            "id": 3,
            "plateNumber": "ccc-000"
        },
        "model": "Nissan versa"
    }
    ]
    ``` 
### Remover veículo
    
*  **URL**

    `/vehicles{id}`
    
* **Method**

  `DELETE`
  
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do veículo                 | sim             | -                | 125             |
  
* **Retorno**
  
  **Status Code:** 204
  
   ### Cadastro veículo
  
* **URL**
    
  `/vehicles`
  

* **Method**

  `POST`
  
* **Retorno**
  
  **Status Code:** 201
  
* **Exemplo de envio**

    ```json
{

        "plate": "123-aaa",
        "model": "Ford Focus"

    }
    ``` 

*  **Parâmetros via body**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | plate          | String         |placa             | Sim  | -   | 123-aaa             |
   | model          | String         | Modelo             | Sim  | -   | Ford focus             |




-----

## Locação de vaga (Parking rental)

### Busca por id

* **URL**
    
  `/parking-rentals/{id}`
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    
 {
    "id": 1,
    "plate": {
        "id": 1,
        "plateNumber": "aaa-123"
    },
    "startDate": "2022-11-05T10:19:59",
    "endDate": "2022-11-05T11:19:59",
    "hour": 1,
    "value": 10.0
}
    ``` 
    
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador da locação               | sim             | -                | 125             |
   
    ### Busca todos

* **URL**

    `/parking-rentals`
    
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
[
    {
        "id": 1,
        "plate": {
            "id": 1,
            "plateNumber": "aaa-123"
        },
        "startDate": "2022-11-05T10:19:59",
        "endDate": "2022-11-05T11:19:59",
        "hour": 1,
        "value": 10.0
    },
    {
        "id": 2,
        "plate": {
            "id": 2,
            "plateNumber": "bbb-456"
        },
        "startDate": "2022-11-05T11:19:59",
        "endDate": "2022-11-05T14:19:59",
        "hour": 3,
        "value": 30.0
    },
    {
        "id": 3,
        "plate": {
            "id": 3,
            "plateNumber": "ccc-000"
        },
        "startDate": "2022-11-05T10:19:59",
        "endDate": null,
        "hour": 1,
        "value": 10.0
    }
]

    ``` 


### Busca por placa

* **URL**

    `/parking-rentals/findByPlateNumber?plateNumber={plateNumber}`
    
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
[
    {
        "id": 1,
        "plate": {
            "id": 1,
            "plateNumber": "aaa-123"
        },
        "startDate": "2022-11-05T10:19:59",
        "endDate": "2022-11-05T11:19:59",
        "hour": 1,
        "value": 10.0
    }
]

    ``` 
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | plateNumber           | String         | Placa do carro                 | sim             | -                | 125             |
  
   ### Cadastro locação
  
  * **URL**
    
  `/parking-rentals`
  

* **Method**

  `POST`
  
* **Retorno**
  
  **Status Code:** 201
  
* **Exemplo de envio**

    ```json
{

    "plate": {
        "plateNumber": "aaa-1230"

    },
    "startDate": "2022-11-05T10:19:59",
    "endDate": "2022-11-05T11:19:59"

        }
    ``` 
* **Observação**

    **Caso a placa não seja encontrada no sistema, uma nova placa é cadastrada na mesma requisição**

*  **Parâmetros via body**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | plate          | String         |placa               | Sim  | -   | 123-aaa             |
   | startDate          | datetime         | Data de entrada             | Sim  | -   | 2022-11-05T10:19:59           |
   | endDate          | datetime         | Data de saída             | Sim  | -   |2022-11-05T11:19:59         |

----


## Vagas (parking spot)

### Busca por id

* **URL**
    
  `/parking-spots/{id}`
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
{
    "id": 1,
    "name": "p1",
    "available": false,
    "plate": {
        "id": 4,
        "plateNumber": "aaa-1230"
        }
}
    ``` 
    
*  **Parâmetros via url**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador da vaga               | sim             | -                | 125             |
   
    ### Busca todos

* **URL**

    `/parking-spots`
    
  

* **Method**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
[
    {
        "id": 1,
        "name": "p1",
        "available": false,
        "plate": {
            "id": 4,
            "plateNumber": "aaa-1230"
        }
    },
    {
        "id": 2,
        "name": "p2",
        "available": false,
        "plate": null
    },
    {
        "id": 3,
        "name": "p3",
        "available": false,
        "plate": null
    },
    {
        "id": 4,
        "name": "p4",
        "available": false,
        "plate": null
    }
]

    ``` 

  
   ### Cadastro vaga
  
  * **URL**
    
  `/parking-spots`
  

* **Method**

  `POST`
  
* **Retorno**
  
  **Status Code:** 201
  
* **Exemplo de envio**

    ```json
    {
        "name": "p7"
    }
    ``` 

*  **Parâmetros via body**
    
   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |---------------------------------------    |-------------    |--------------    |------------    |
   | name          | String         |nome da vaga             | Sim  | -   | p7             |


