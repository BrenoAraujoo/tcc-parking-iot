
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
    ### Remover
    
    
* **URL**

    `/users{id}`
    
* **Method**

  `DELETE`
  
* **Retorno**
  
  **Status Code:** 201
-----





