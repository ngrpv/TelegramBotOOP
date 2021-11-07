package first.user;

import first.IGame;
import first.hangman.HangmanGameState;

import java.util.Comparator;

public class User implements Comparable<User> {
    public UserState state;
    public IGame gameState;
    public Boolean stateIsChanged = false;
    public Integer guessedWords;
    public String userName;
    public Boolean flagName;

    public User() {
        state = UserState.onMenu;
        gameState = new HangmanGameState();
        guessedWords = 0;
        flagName = true;
    }

    public Boolean isPlaying() {
        return state == UserState.Playing;
    }

    public void changeGame(IGame game) {
        gameState = game;
    }

    public String getName() {
        return userName;
    }

    public int getGuessedWord() {
        return guessedWords;
    }

    public void changeState(UserState state) {
        stateIsChanged = true;
        this.state = state;
        switch (state) {
            case Playing:
                gameState.start();
                break;
            case onMenu:
                break;
        }
    }

    @Override
    public int compareTo(User user) {
        return getName().compareTo(user.getName());
    }

    public static class GuessedWordComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getGuessedWord() - o2.getGuessedWord();
        }
    }
}
