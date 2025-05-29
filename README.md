# Система пошуку та купівлі квитків на події / Event Ticket Search and Purchase System

## Опис / Description

Цей проєкт є імітацією системи пошуку та купівлі квитків на події.  
Він реалізує основні бізнес-логіки:

- Кожна подія (Event) має фіксований набір квитків (Ticket).
- Один квиток може належати лише одному покупцю (Customer).
- Один покупець може мати багато квитків.
- У одному місці проведення (Place) може проходити багато подій, але не в один і той же день.
- Пошук вільних квитків за назвою події.
- Пошук найближчих майбутніх подій.
- Присвоєння квитка користувачу.

---

## Технології / Technologies

- Java 21
- Spring Framework (Spring Boot, Spring Data JPA)
- JWT (JSON Web Token) для авторизації
- Hibernate / JPA для роботи з базою даних
- Faker (JavaFaker) для генерації тестових даних
- JUnit 5 для юніт-тестування

---

## Структура проекту / Project Structure

- `domain` — сутності моделі даних (Customer, Ticket, Event, Place)
- `application.services/dto` — сервіси з бізнес-логікою та DTO
- `infrastructure.repositories` — інтерфейси репозиторіїв (Spring Data JPA)
- `infrastructure.security` — налаштування JWT для авторизації
- `infrastructure.datainitializer` — утилітні класи для генерації тестових даних за допомогою Faker
- `infrastructure.config` — конфігураційні класи
- `tests` — юніт-тести з використанням JUnit 5 та Mockito
---

![idea64_aDVcDFMUMy](https://github.com/user-attachments/assets/dec1a8ee-dbaf-4505-b99e-777cd0a0f945)

---
