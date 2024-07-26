<a name="readme-top"></a>

<!--
*** Template baseado em https://github.com/othneildrew/Best-README-Template 
-->

![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![[mysql]](https://img.shields.io/badge/Mysql-316192?style=for-the-badge&logo=mysql&logoColor=white)



<br />
<div align="center">
  <h3 align="center">Tastytap</h3>
  <img src="https://cdn.cisscloud.com.br/portal/pages/cisstotem/img/cisstotem2021.png" width="150px"/>
</div>

## Sobre o Projeto

Projeto realizado para a primeira entrega da pós graduação em [Software Architecture da FIAP](https://postech.fiap.com.br/curso/software-architecture/).

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

## Por dentro de nossa arquitetura
Basicamente a nossa divisão dos pacotes funciona da seguinte maneira:

- **Domain**: este é o core da nossa aplicação, lá se encontram nossos objetos de negócio que encapsulam as regras centrais de nosso sistema.
- **Application**: aqui é onde se encontram nossos casos de uso, que definem as logicas especificas de nosso negócio, essa camada orquestra o fluxo de dados entre as entidades e outras camadas externas.
- **Utils**: é onde se encontram alguns recursos uteis para aplicação no geral, ele consegue centralizar algumas lógicas/validações/formatações, para que mantenham-se as assinaturas independente da implementação.
- **Infraestructure**: aqui estão nossas pontes com a camada de aplicação em conjunto com o framework, é onde eles se juntam.
- **Presentation**: Onde ficam nossos endpoints, a comunicação com nossa camada mais externa, que no caso desse projeto, é somente web.



<p align="right">(<a href="#readme-top">Voltar para o topo</a>)</p>
