FROM eclipse-temurin:21-jdk
LABEL maintainer="Dias"
COPY kordim.jar kordim-backend-spring-project.jar
ENTRYPOINT ["java", "-jar", "kordim-backend-spring-project.jar"]