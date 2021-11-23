package first.games;

public interface IGame {
 String checkAnswer(String answer);
 String getStartMessage();
 void start();
 String getRules();
 Integer getGuessedWords();
 void resetScore();
}
