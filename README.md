В данном разделе собрана информация о проекте автоматизированного тестирования, инструментах, методах и практиках автоматизации тестирования.

Цель данного проекта - обеспечить высокое качество разрабатываемого программного обеспечения, используя современные инструменты и эффективные стратегии тестирования.

Для тестирования пользовательского интерфейса и API приложения используются Java, Gradle, JUnit 5, Selenide и Rest Assured.

В этом разделе есть руководства по использованию инструментов, примеры тестов, а также ссылки на полезные ресурсы из Maven Central Repository и других источников.

Организация тестовых данных:
Необходимо хранить данные для тестирования в формате, совместимом с тестируемым приложением, и обеспечить их актуальность и доступность для автотестов (в проекте папка "resources", пакет с классами "dataTest" и проперти файл "config.properties" (его надо закинуть в корень проекта) c паролями и адресом стенда, добавлен в .gitignore)

Особенности и требования к тестовой среде:
Возможно, требуется специальная тестовая среда для запуска автотестов.
Для UI-тестирования (Selenide) может потребоваться определенный браузер, такой как Chrome, Firefox или определенная версия браузера.
Для API-тестирования (Rest Assured) необходим доступ к тестируемому API и возможность отправки запросов для выполнения тестов.
Описание используемых инструментов для автотестирования:
Gradle:

Система для автоматизации сборки приложений, применяющая языки Java, Groovy, JavaScript, Kotlin и другие.
JUnit 5:

Фреймворк для юнит-тестирования, обеспечивающий возможности для написания и запуска автоматизированных тестов.
Selenide:

 Фреймворк автоматизации тестирования пользовательского интерфейса (UI) для веб-приложений, базирующийся на Selenium WebDriver с упрощенным API.
Rest Assured:

 Библиотека для тестирования REST API, позволяющая создавать запросы, валидировать ответы и проверять различные аспекты работы API.
Jackson:

 Библиотека для работы с форматом JSON в Java, часто используется для сериализации и десериализации данных.
JavaFaker:

 Библиотека для создания фейковых данных (например, имена, адреса, тексты), упрощающая тестирование с использованием разнообразных сгенерированных данных