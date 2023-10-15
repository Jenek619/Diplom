# Дипломный проект.
## Документация:
- [Тест-план](https://github.com/Jenek619/Diplom/blob/main/docs/Plan.md)
- [Отчётные документы по итогам тестирования](https://github.com/Jenek619/Diplom/blob/main/docs/Report.md)
- [Отчётные документы по итогам автоматизации](https://github.com/Jenek619/Diplom/blob/main/docs/Summary.md)
## Информация о проекте
Приложение представляет из себя веб-сервис для путешествий.
![image](https://github.com/netology-code/qa-diploma/blob/master/pic/service.png?raw=true)
Приложение предлагает купить тур по определённой цене с помощью двух способов:

- Обычная оплата по дебетовой карте
- Выдача кредита по данным банковской карты

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

- Сервису платежей - Payment Gate
- Кредитному сервису - Credit Gate

## Цели проекта
В рамках проекта необходимо автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

### Для запуска приложения:

- С помощью Git клонировать репозиторий командой git clone [Проект тестирования](https://github.com/Jenek619/Diplom/tree/main)
- Запустить Docker;
- Открыть проект в IntelliJ IDEA;
- В терминале IntelliJ IDEA запустить необходимые базы данных с помощью команды `docker-compose up --build`
- В новой вкладке терминала ввести следующую команду:  
   `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar` для MySQL
   `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar` для PostgresQL
- Проверить доступность приложения в браузере по адресу - `http://localhost:8080/`

### Для запуска автотестов:

- В новой вкладке терминала ввести следующую команду:  
  `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"` для MySQL
  `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"` для PostgresQL

### Для просмотра отчетов по результатам тестирования:
- Сгенерировать отчет Allure, выполнив команду в терминале IDEA: `./gradlew allureServe`
* Если отчет не открывается автоматически в браузере, то выполнить команду: `./gradlew allureReport` и открыть отчет вручную (файл index.html) по адресу: `.\build\reports\allure-report\allureReport`
- При необходимости изменить подключение к другой БД.

