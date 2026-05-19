#Eerste Stadium
FROM gradle:8-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

#Tweede Stadium
FROM eclipse-temurin:21-jre
COPY --from=build /app/build/libs/Belasting*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]