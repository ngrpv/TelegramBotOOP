# TelegramBotOOP
Telegram bot for OOP course

Contributors: Garipov Nazar(https://github.com/ngrpv), Nechaev Roman(https://github.com/RomanNechaev

Инструкция по развертыванию на heroku:
Вариант 1:
1. Выбрать деплой через github и выбрать соответствущий проект
2. Задеплоить
3. Запустить worker (можно по команде heroku ps:scale worker=1)

Вариант 2: (через консоль)
1. Сделать клон репозитория на пк
2. Запаковать проект (mvn package)
3. Задеплоить(команда mvn heroku:deploy)
4. Запустить worker (можно по команде heroku ps:scale worker=1)
