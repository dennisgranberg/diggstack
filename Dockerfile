
FROM node:20-alpine AS diggvue-build

WORKDIR /app

COPY diggvue/package*.json ./
RUN npm install

COPY diggvue/ .
RUN npm run build


FROM maven:3.9-amazoncorretto-23 AS diggbackend-build

WORKDIR /app

COPY diggbackend/pom.xml .
COPY diggbackend/src ./src

# Copy the built diggvue into Spring Boot's static resources
COPY --from=diggvue-build /app/dist ./src/main/resources/static

# Run Maven build and tests (do NOT skip tests)
RUN mvn clean package


# ----------- Stage 3: Runtime image -----------
FROM amazoncorretto:23-alpine AS runtime

WORKDIR /app

# Copy built Spring Boot JAR from diggbackend build stage
COPY --from=diggbackend-build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
