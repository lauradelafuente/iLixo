# Etapa 1: Utiliza uma imagem Maven para construir o projeto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copia o projeto para o container
COPY . .
# Roda o Maven para construir o projeto e criar o .jar
RUN mvn clean package -DskipTests
# Etapa 2: Utilizar uma imagem Java para rodar o .jar
FROM openjdk:17-jdk-alpine
WORKDIR /app
# Copia o .jar gerado na etapa anterior para o container
COPY --from=build /app/target/iLixo-0.0.1-SNAPSHOT.jar /app/iLixo.jar
# Expoe a porta na qual a aplicação vai rodar
EXPOSE 8080
# Comando para executar a aplicação
CMD ["java", "-jar", "/app/iLixo.jar"]