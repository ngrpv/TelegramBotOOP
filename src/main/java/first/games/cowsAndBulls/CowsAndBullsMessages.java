package first.games.cowsAndBulls;

class CowsAndBullsMessages {

    private static final String WORD_EQUAL_LENGTH = "Введите число той же длины, что и загадано!";
    private static final String ALREADY_USED_WORD = "Слово было введено ранее, введите другое!";
    private static final String WIN_TEXT = "Ты выиграл!";
    private static final String NEW_WORD_TEXT = "Новое слово загадано!";
    private static final String UNKNOWN_COMMAND = "Неизвестная команда";
    private static final String RULES = """
            https://ru.wikipedia.org/wiki/Быки_и_коровы
            В классическом варианте игра рассчитана на двух игроков. Каждый из игроков задумывает и записывает тайное 4-значное число с неповторяющимися цифрами[1]. Игрок, который начинает игру по жребию, делает первую попытку отгадать число. Попытка — это 4-значное число с неповторяющимися цифрами, сообщаемое противнику. Противник сообщает в ответ, сколько цифр угадано без совпадения с их позициями в тайном числе (то есть количество коров) и сколько угадано вплоть до позиции в тайном числе (то есть количество быков)\s
            
            Например:
            Задумано тайное число «3219».

            Попытка: «2310».

            Результат: две «коровы» (две цифры: «2» и «3» — угаданы на неверных позициях) и один «бык» (одна цифра «1» угадана вплоть до позиции).""";


    public static String getRules() {
        return RULES;
    }
    protected static String getMessageForUser(CowsAndBullsEnum checkResult, CowsAndBullsState gameState)
    {
        switch (checkResult)
        {
            case WRONG_WORD:
                return WORD_EQUAL_LENGTH;
            case ALREADY_USED_WORD:
                return ALREADY_USED_WORD;
            case WIN:
                gameState.setWord();
                return WIN_TEXT + "\n" + NEW_WORD_TEXT;
        }
        return UNKNOWN_COMMAND;
    }

    protected static String getMessageForUser(int cows, int bulls)
    {
        return String.format("Быки:%s\nКоровы:%s",bulls,cows);
    }
}
