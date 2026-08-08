Запуск проекта:

БД:
```bash
docker run --name billing-postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=billing -p 5432:5432 -d postgres:17
```

Secret TODO выпилить
```bash
export DB_HOST="jdbc:postgresql://localhost:5432/billing"
export DB_USERNAME="postgres"
export DB_PASSWORD="postgres"
export JWT_SECRET="my-super-secret-key-my-super-secret-key"
```

Spring boot:
```bash
./mvnw spring-boot:run
```

```bash
cp src/main/resources/application.properties.dist src/main/resources/application.properties
```
