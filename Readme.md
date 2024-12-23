# Parking

### Descrição do Projeto

O Projeto **Parking** é uma solução atualizada para um Sistema de Parquímetro, que estava precisando ser refatorado. 
O sistema deve gerenciar, controlar e monitorar um estacionamento de maneira eficiente, com tecnologia e arquitetura mais modernas.
Este sistema usa os princípios de Clean Architecture, garantindo uma estrutura modular, facilmente testável e
independente de frameworks ou tecnologias específicas.

### Fluxo de Comunicação

1. Controller recebe a requisição e transforma os dados de entrada.
2. Controller chama o Caso de Uso, passando os dados processados.
3. O Caso de Uso interage com as Entidades e usa interfaces de repositórios para acessar dados.
4. O resultado do Caso de Uso é enviado para um JpaGateway, que adapta o dado para a interface.
5. Gateway devolve a resposta ao Controller, que retorna ao cliente.

### Tecnologias Utilizadas

- **Java 21**
- **Spring Framework 3.0**
- **Docker Compose**- (Em implementação) 

### Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/parking.git

2. Navegue até a pasta do projeto:
   ```bash
   cd parking

3. Execute o projeto com o Maven:
   ```bash
   ./mvnw spring-boot:run

**TODO**: Explicação Docker Compose
