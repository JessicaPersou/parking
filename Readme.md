# Parking

### Descrição do Projeto

O Projeto **Parking** é uma solução moderna para um sistema de parquímetro, desenvolvido para gerenciar, controlar e monitorar estacionamentos de maneira eficiente. Ele utiliza tecnologias avançadas e princípios de arquitetura para garantir escalabilidade e fácil manutenção.

O sistema foi construído com base nos princípios de **Clean Architecture** e **Domain-Driven Design (DDD)**, garantindo uma estrutura modular, testável e independente de frameworks ou tecnologias específicas.

---

### Arquitetura do Projeto

#### **Clean Architecture**
A Clean Architecture organiza o sistema em camadas, separando responsabilidades de forma clara:
- **Controller:** Recebe as requisições HTTP e transforma os dados para o formato necessário.
- **Use Cases:** Contém as regras de negócio e coordena as interações com o domínio.
- **Domain:** Representa o núcleo do sistema, contendo as entidades e objetos de valor que refletem o domínio do negócio.
- **Gateways:** Adaptam o sistema para interagir com tecnologias externas, como bancos de dados ou APIs.

#### **Domain-Driven Design (DDD)**
O DDD é aplicado para alinhar o código com os conceitos do domínio:
- **Entidades**: Modelos principais do sistema, como `Vehicle` e `Owner`.
- **Value Objects**: Objeto imutável que representa um valor específico, como `OwnerDocument` e `VehiclePlate`.
- **Casos de Uso**: Classes que encapsulam a lógica de aplicação, como `CreateVehicleUseCase` e `CreateTicketUseCase`.
- **Gateways**: Interfaces que conectam o domínio com a infraestrutura, como o `VehicleJpaGateway`.

---

### Fluxo de Comunicação

- O **Controller** recebe a requisição e transforma os dados de entrada.
- O **Controller** chama o Caso de Uso, passando os dados processados.
- O Caso de Uso interage com as **Entidades** e utiliza interfaces de repositórios para acessar ou manipular dados.
- O Caso de Uso passa o resultado para o **Gateway**, que adapta os dados para o formato necessário.
- O **Gateway** devolve a resposta ao **Controller**, que a retorna ao cliente.

---

### Tecnologias Utilizadas

- **Java 21**
- **Spring Framework 3.0**
- **Docker Compose** 
- **PostgreSQL** *(Banco de dados configurado no Docker Compose)

---

### Documentação e Testes

A documentação das APIs foi desenvolvida no **Postman**.  
Você pode utilizar a coleção do Postman para testar os endpoints e entender como o sistema funciona.

[Link para a documentação no Postman](https://github.com/JessicaPersou/parking/blob/main/parking_documentation.postman_collection.json)

---

### Como Executar o Projeto

#### Pré-requisitos
- **Docker** e **Docker Compose** instalados.
- **Maven** para build e execução do projeto.

### Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/parking.git

2. Navegue até a pasta do projeto:
   ```bash
   cd parking

####  * **Certifique-se de ter o Docker e o Docker Compose instalados**

3. Subir os serviços com:
   ```bash
   docker-compose up -d

4. O banco de dados configurada no arquivo Compose.
   ```bash
   porta:5432

5. Execute o projeto com o Maven:
   ```bash
   ./mvnw spring-boot:run

