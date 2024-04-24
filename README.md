# Agrix - API Rest para Gestão e Monitoramento de Fazendas

O Agrix é um sistema desenvolvido pela AgroTech para permitir a gestão e o monitoramento de fazendas, visando melhorar a eficiência no cultivo de plantações e reduzir o desperdício de recursos.

## Visão Geral

Este projeto consiste em uma API Rest desenvolvida em Java Spring para fornecer funcionalidades de gestão e monitoramento de fazendas e cultivos. Ele oferece endpoints para manipulação de dados relacionados às fazendas, aos cultivos, aos fertilizantes e às associações entre fazendas, cultivos e fertilizantes.

## Requisitos do Sistema

Para executar esta API, você precisará ter instalado:

- Java 17
- Maven
- MySQL
- Docker

## Configuração

1. Clone o repositório para a sua máquina local.
2. Instale as dependências com o comando `mvn install`
4. Suba o container docker com o comando `docker-compose up -d`
5. Aguarde alguns segundos e depois execute o comando `mvn spring-boot:run` para iniciar o servidor.

## Rotas Disponíveis

> ⚠️  Para ter acesso as rotas é necessário criar um usuário, efetuar o login e utulizar o token de acesso retornado.

<details>
  
<summary>Persons</summary>
  
#### Criar Usuário:

- Endpoint: `POST /persons`
- **Exemplo de requisição:**

  ```json
  {
    "username": "hari_seldon",
    "password": "12069",
    "role": "ADMIN"
  }
  ```
- **Exemplo de resposta:**

   ```json
   {
     "id": 2,
     "username": "hari_seldon",
     "password": "12069",
     "role": "ADMIN"
   }
   
</details>

<details>
  
<summary>Login</summary>

- Endpoint: `POST /auth/login`
- **Exemplo de requisição:**

  ```json
   {
     "username": "hari_seldon",
     "password": "12069"
   }
  ```
- **Exemplo de resposta:**

  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJoYXJ..."
  }
  ```
  
  
</details>

<details>
<summary>Farms</summary>
  
#### Criar Fazenda:

- Endpoint: `POST /farms`
- **Exemplo de requisição:**
  
  ```json
  {
    "name": "Fazendinha",
    "size": 5
  }
  ```

- **Exemplo de resposta:**

  ```json
  {
    "id": 1,
    "name": "Fazendinha",
    "size": 5
  }

  ```
#### Listar Fazendas:

  - Endpoint: `GET /farms`
  - **Exemplo de resposta:**

    ```json
    [
      {
        "id": 1,
        "name": "Fazendinha",
        "size": 5.0
      },
      {
        "id": 2,
        "name": "Fazenda do Júlio",
        "size": 2.5
      }
    ]
    ```

#### Encontrar Fazenda Por ID:

  - Endpoint: `GET /farms/{id}`
  - **Exemplo de resposta:**

    ```json
    {
      "id": 3,
      "name": "My Cabbages!",
      "size": 3.49
    }
    ```
#### Listar Cultivos de uma Fazenda:

  - Endpoint: `GET /farms/{farmId}/crops`
  - **Exemplo de resposta: (para `/farms/1/crops`)**

    ```json
    [
      {
        "id": 1,
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "plantedDate": "2022-12-05",
        "harvestDate": "2023-06-08",
        "farmId": 1
      },
      {
        "id": 2,
        "name": "Alface",
        "plantedArea": 21.3,
        "plantedDate": "2022-02-15",
        "harvestDate": "2023-02-20",
        "farmId": 1
      }
    ]
    ```
</details>

<details>
<summary>Crops</summary>

#### Criar Cultivo:

  - Endpoint: `POST /farms/{farmId}/crops`
  - **Exemplo de requisição:**

    ```json
    {
      "name": "Couve-flor",
      "plantedArea": 5.43,
      "plantedDate": "2022-12-05",
      "harvestDate": "2023-06-08"
    }
    ```

 - **Exemplo de resposta:**

    ```json
    {
      "id": 1,
      "name": "Couve-flor",
      "plantedArea": 5.43,
      "plantedDate": "2022-12-05",
      "harvestDate": "2023-06-08",
      "farmId": 1
    }
    ```
    
#### Encontrar Cultivo Por ID:

   - Endpoint: `GET /crops/{id}`
   - **Exemplo de resposta: (para `/crops/3`)**

	   ```json
	   {
	     "id": 3,
	     "name": "Tomate",
	     "plantedArea": 1.9,
	     "plantedDate": "2023-05-22",
	     "harvestDate": "2024-01-10",
	     "farmId": 2
	   }
	   ```

#### Pesquisar Cultivos por Intervalo de Datas:

  - Endpoint: `GET /crops/search?start={startDate}&end={endDate}`
  - Exemplo de resposta: (para /crops/search?start=2023-01-07&end=2024-01-10)

    ```json
    [
      {
        "id": 1,
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "plantedDate": "2022-02-15",
        "harvestDate": "2023-02-20",
        "farmId": 1
      },
      {
        "id": 3,
        "name": "Tomate",
        "plantedArea": 1.9,
        "plantedDate": "2023-05-22",
        "harvestDate": "2024-01-10",
        "farmId": 2
      }
    ]
    ```
</details>

<details>
<summary>Fertilizers</summary>

#### Criar Fertilizante:

   - Endpoint: `POST /fertilizers`
   - **Exemplo de requisição:**

	  ```json
	  {
	    "name": "Compostagem",
	    "brand": "Feita em casa",
	    "composition": "Restos de alimentos"
	  }
	  ```

   - **Exemplo de resposta:**

	   ```json
	    {
	      "id": 1,
	      "name": "Compostagem",
	      "brand": "Feita em casa",
	      "composition": "Restos de alimentos"
	    }
	   ```

#### Listar Fertilizantes:

  - Endpoint: `GET /fertilizers`
  - **Exemplo de resposta:**

	   ```json
	    [
	      {
	        "id": 1,
	        "name": "Compostagem",
	        "brand": "Feita em casa",
	        "composition": "Restos de alimentos"
	      },
	      {
	        "id": 2,
	        "name": "Húmus",
	        "brand": "Feito pelas minhocas",
	        "composition": "Muitos nutrientes"
	      }
	    [
	   ```
</details>

## Contribuição

Este projeto está aberto para contribuições. Se você deseja contribuir, siga estas etapas:

1. Faça um fork do projeto
2. Crie uma nova branch (`git checkout -b feature/sua-feature`)
3. Faça commit das alterações (`git commit -m 'Adicione sua feature'`)
4. Faça push para a branch (`git push origin feature/sua-feature`)
5. Abra um Pull Request

## Contato

Para questões ou sugestões relacionadas a este projeto, sinta-se à vontade para entrar em contato via e-mail: [jonataslaguna.js@gmail.com](mailto:jonataslaguna.js@gmail.com)

