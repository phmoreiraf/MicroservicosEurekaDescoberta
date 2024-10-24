# Usa uma imagem base do OpenJDK 17
FROM openjdk:17-jdk
WORKDIR /app

# Copia o arquivo JAR gerado para o contêiner
ADD target/*.jar app.jar

# Expor a porta 8080 para o gateway
EXPOSE 8080

# Executa o API Gateway
ENTRYPOINT ["java", "-jar", "app.jar"]
