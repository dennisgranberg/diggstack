# How to build and run application:

## In container
The dockerfile contains all the steps of building each application and bundle them together, where the vue app is server by the spring backend application.

```
docker build -t digg-fullstack .
docker run -p 8080:8080 digg-fullstack
```

The application can then be accessed on http://localhost:8080/

## In dev setup

When developing simply start each application by themselves.

```
# BACKEND (from diggbackend folder)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# FRONTEND (from diggvue folder)
npm run dev
```