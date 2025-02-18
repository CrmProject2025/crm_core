# crm_core

Описание архитектуры:

- spring boot микросервис.
- postgres база данных.
- кластер kafka брокеров для связи микросервисов.
- docker-compose для сборки приложения.
- liquibase для миграций бд.
- jenkins - CI/CD инструменты для автоматизации
сборки, тестирования и развертывания проекта.
- redis для кеширования наиболее частых запросов.
- фронтенд на react.

## Описание функционала:

- Аутентификация по jwt токену.
- Создание и редактирование заказов.
- Добавление и редактирование продуктов.
- Добавление и редактирование складских
помещений.
- Админка.
- Unit тесты на основной функционал.

Технологии:
Backend: Spring, Spring Boot, Spring Security, PostgreSQL, Hibernate orm, Gradle, Junit, Liquibase, jenkins, Postman


## Postman для тестирования api:
![image](https://github.com/Tework123/springBlog/assets/115368408/c169376d-3c1f-4fc8-8cc9-d886e387c1ab)
