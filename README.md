<a name="readme-top"></a>

<!--
*** Template baseado em https://github.com/othneildrew/Best-README-Template 
-->

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![[mysql]](https://img.shields.io/badge/Mysql-316192?style=for-the-badge&logo=mysql&logoColor=white)
![[kubernetes]](https://img.shields.io/badge/Kubernetes-3069DE?style=for-the-badge&logo=kubernetes&logoColor=white)




<br />
<div align="center">
  <h3 align="center">Tastytap</h3>
  <img src="https://cdn.cisscloud.com.br/portal/pages/cisstotem/img/cisstotem2021.png" width="150px"/>
</div>

## Sobre o Projeto

Projeto atualizado para a segunda entrega da pós graduação em [Software Architecture da FIAP](https://postech.fiap.com.br/curso/software-architecture/).

Link do miro: https://miro.com/app/board/uXjVKTJ4mvk=/?share_link_id=556895060297


## Começando

Para executar o projeto em sua máquina siga os seguintes passos. 

### Pré-requisitos

* Docker com compose
  Veja a [documentação](https://docs.docker.com/engine/install/) para instalar o docker no seu sistema se ainda não tiver instalado.

### Instalação

A instalação é bem simples, siga as seguintes etapas:

1. Clone o repositório
   ```sh
   git clone https://github.com/gabrielronei/tastytap.git
   ```
2. Entre na pasta do projeto
   ```sh
   cd tastytap
   ```
3. Agora execute o projeto usando o docker compose
   ```sh
   docker compose up
   ```
4. Para acessar a documentação no swagger, acesse em seu navegador.
   ```
   http://localhost:8080/
   ```
## Kubernetes
Da para rodar localmente nossa arquitetura usando o minikube, siga os seguintes passos:
1. Inicie o minikube
   ```sh
   minikube start
   ```
2. Entre na pasta do kubernetes dentro do nosso projeto
   ```sh
   cd kubernetes/
   ```
3. Agora execute o script
   ```sh
   bash configure.sh
   ```
4. Só acessar a url com ip do cluster que foi mostrado na execução do script
   ```sh
   http://IP_DO_CLUSTER:30000/
   ```


## Exemplos de requests

As requests podem ser feitas na seguinte ordem:

<details>
  <summary>1. Criar usuário (opcionalmente): </summary>

  ```json
// GET /user
{
  "name": "Saul Hudson",
  "email": "saul.hudson@gmail.com",
  "cpf": "285.977.970-10"
}
```
</details>

<details>
  <summary>2. Buscar usuário por CPF (opcionalmente): </summary>

  ```json
// GET /user/285.977.970-10
```
</details>

<details>
  <summary>3. Criar produto de tipo lanche: </summary>

  ```json
// POST /product
{
  "name": "Universitario",
  "description": "Pão de brioche selado na manteiga, hambúrguer artesanal 160g, queijo cheddar, alface, tomate e molho john's",
  "imageURL": "https://assets.unileversolutions.com/recipes-v2/106684.jpg",
  "category": "SANDWICH",
  "price": 33.90
}
```
</details>

<details>
  <summary>4. Criar produto de tipo lanche para atualizar e deletar: </summary>

  ```json
// POST /product
{
  "name": "Produto para deletar e atualizar",
  "description": "XPTOPTOPTO",
  "imageURL": "https://assets.unileversolutions.com/recipes-v2/106684.jpg",
  "category": "SIDE_DISH",
  "price": 10
}
```
</details>

<details>
  <summary>5. Criar produto de tipo bebida: </summary>

  ```json
// POST /product
{
  "name": "Coca-Cola Original 350ml",
  "description": "Lata 350ml",
  "imageURL": "https://hiperideal.vtexassets.com/arquivos/ids/197362/55723-4.jpg",
  "category": "DRINK",
  "price": 7.90
}
```
</details>

<details>
  <summary>6. Criar produto de tipo sobremesa: </summary>

  ```json
// POST /product
{
  "name": "Pudim cremoso individual",
  "description": "Pudim cremoso e sem furinho",
  "imageURL": "https://revistamenu.com.br/wp-content/uploads/sites/24/2020/05/diadopudim-1.jpg",
  "category": "DESSERT",
  "price": 13.90
}
```
</details>

<details>
  <summary>7. Atualizar produto: </summary>

  ```json
// PUT /product
{
  "id": 2,
  "description": "descricão novissima",
  "imageURL": "https://assets.unileversolutions.com/recipes-v2/106684.jpg",
  "price": 1
}
```
</details>

<details>
  <summary>8. Buscar por categoria: </summary>

  ```json
// GET /product/SIDE_DISH
```
</details>

<details>
  <summary>9. Deletar por id: </summary>

  ```json
// DELETE /product/2
```
</details>

<details>
  <summary>10. Criar um pedido(com usuario): </summary>

  ```json
// POST /order

{
  "cpf": "285.977.970-10",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 2
    }
  ]
}
```
</details>

<details>
  <summary>11. Criar um pedido(sem usuario): </summary>

  ```json
// POST /order

{
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```
</details>

<details>
  <summary>12. Busca todos os pedidos: </summary>

  ```json
// GET /order
```
</details>

<details>
  <summary>13. Chama webhook que seria chamado pelo gateway: </summary>

  ```json
// POST /payment/provider/webhook
{
  "transactionId": "{{TRANSACTION_ID}}",
  "status": "APPROVED"
}
```
</details>

<details>
  <summary>14. Checa status pelo numero do pedido: </summary>

  ```json
// GET /order/{{ORDER_NUMBER}}/status
```
</details>

<details>
  <summary>15. Atualiza status do pedido: </summary>

  ```json
// GET /order/{{ORDER_NUMBER}}/status
```
</details>


## Por dentro de nossa arquitetura
Link do desenho da arquitetura: https://drive.google.com/file/d/186D8N2BxR907FRHDmWTzllt5tJ4GfxJi/view?usp=drive_link
![TECH CHALLENGE 2 - ARQUITETURA - FIAP drawio (1)](https://github.com/user-attachments/assets/54f3f0c4-5ab7-47a2-a9ac-980a0072f6d6)

Basicamente a nossa divisão dos pacotes funciona da seguinte maneira:
- **Domain**: este é o core da nossa aplicação, lá se encontram nossos objetos de negócio que encapsulam as regras centrais de nosso sistema.
- **Application**: aqui é onde se encontram nossos casos de uso, que definem as logicas especificas de nosso negócio, essa camada orquestra o fluxo de dados entre as entidades e outras camadas externas.
- **Utils**: é onde se encontram alguns recursos uteis para aplicação no geral, ele consegue centralizar algumas lógicas/validações/formatações, para que mantenham-se as assinaturas independente da implementação.
- **Infraestructure**: aqui estão nossas pontes com a camada de aplicação em conjunto com o framework, é onde eles se juntam.
- **Presentation**: Onde ficam nossos endpoints, a comunicação com nossa camada mais externa, que no caso desse projeto, é somente web.



<p align="right">(<a href="#readme-top">Voltar para o topo</a>)</p>
