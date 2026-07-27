Запуск проекта:

БД:
```bash
docker run --name billing-postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=billing \
  -p 5432:5432 \
  -d postgres:17
```

Spring boot:
```bash
./mvnw spring-boot:run
```

```bash
cp src/main/resources/application.properties.dist src/main/resources/application.properties
```
