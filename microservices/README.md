## Passo a passo para execução

### 1. Inicializar o Eureka Server

Navegue até o diretório `eureka-server` e execute o seguinte comando:

```bash
./mvnw spring-boot:run
```

O Eureka Server será iniciado em [http://localhost:8761](http://localhost:8761).

### 2. Inicializar os microserviços

Inicie cada um dos microserviços da seguinte maneira:

- **User Service**:
    - Navegue até o diretório `user-service`:
      ```bash
      ./mvnw spring-boot:run
      ```

- **Task Service**:
    - Navegue até o diretório `task-service`:
      ```bash
      ./mvnw spring-boot:run
      ```

- **Notification Service**:
    - Navegue até o diretório `notification-service`:
      ```bash
      ./mvnw spring-boot:run
      ```

### 3. Testar os Endpoints

#### **User Service**

- **Criar um usuário**:
    ```http
    POST http://localhost:8081/users
    Content-Type: application/json

    {
      "id": 1,
      "name": "John Doe"
    }
    ```

- **Obter todas as tarefas de um usuário**:
    ```http
    GET http://localhost:8081/users/1/tasks
    ```

- **Obter todas as notificações de um usuário**:
    ```http
    GET http://localhost:8081/users/1/notifications
    ```

#### **Task Service**

- **Criar uma tarefa para um usuário**:
    ```http
    POST http://localhost:8082/tasks
    Content-Type: application/json

    {
      "id": 1,
      "description": "Finish project",
      "userId": 1
    }
    ```

- **Obter todas as tarefas**:
    ```http
    GET http://localhost:8082/tasks
    ```

#### **Notification Service**

- **Criar uma notificação para um usuário**:
    ```http
    POST http://localhost:8083/notifications
    Content-Type: application/json

    {
      "id": 1,
      "message": "Task completed",
      "userId": 1
    }
    ```

- **Obter todas as notificações**:
    ```http
    GET http://localhost:8083/notifications
    ```

---

O projeto agora está completo, com todos os endpoints para criar, atualizar, listar e deletar usuários, tarefas e notificações. Todos os serviços estão integrados e podem ser testados conforme indicado acima.

## Instalação das Dependências

Antes de iniciar o servidor Eureka e os microserviços, é necessário garantir que todas as dependências do Maven sejam baixadas e instaladas. Isso pode ser feito com o comando:

```bash
./mvnw clean install
```

Esse comando deve ser executado em cada um dos diretórios dos microserviços e no servidor Eureka.

## Verificação do Eureka Dashboard

Uma vez que o Eureka Server estiver em execução, você pode acessar o **Eureka Dashboard** para verificar se os microserviços estão registrados corretamente. Acesse:

```http
http://localhost:8761
```

## Configuração de Portas

Certifique-se de que as portas 8081, 8082, 8083, e 8761 estão livres em seu sistema, pois são usadas pelos serviços.

## Erros Comuns

- **Dependências do Maven não encontradas**: Verifique o arquivo `pom.xml` e certifique-se de que todas as dependências estão corretamente configuradas e instaladas.
- **Problemas de conexão entre serviços**: Certifique-se de que o Eureka Server esteja em execução antes de iniciar os microserviços.

## Construção e Execução

- Navegar até cada diretório de projeto e executar:
  ```bash
  mvn clean package

- Construa as imagens Docker:
  ```bash
  docker build -t eureka-server .
  docker build -t api-gateway .
  docker build -t user-service .
  docker build -t task-service .
  docker build -t notification-service .

- Execute os serviços em um contêiner Docker:
  ```bash
  docker run -p 8761:8761 eureka-server
  docker run -p 8080:8080 api-gateway
  docker run -p 8081:8081 user-service
  docker run -p 8082:8082 task-service
  docker run -p 8083:8083 notification-service

- Executar o Docker Compose :
  ```bash
  docker-compose up --build
  
- Gerar Certificados TLS :
  ```bash
  openssl req -newkey rsa:2048 -nodes -keyout keystore.key -x509 -days 365 -out keystore.crt

  Formato p12: openssl pkcs12 -export -in keystore.crt -inkey keystore.key -out keystore.p12 -name alias

- Build dos Microsserviços
    ```bash
    mvn clean package -DskipTests

- Verificar o Status dos Serviços
    ```bash
  Eureka Server: http://localhost:8761
  
  Config Server: http://localhost:8888/actuator/health
  
  User Service: http://localhost:8081/actuator/health
  
  Task Service: http://localhost:8082/actuator/health
  
  Notification Service: http://localhost:8083/actuator/health
  
  Hystrix Dashboard: http://localhost:7979/hystrix

- Monitoramento com Hystrix Dashboard
    ```bash
    http://localhost:7979/hystrix
  
  Ex: http://localhost:8081/actuator/hystrix.stream
