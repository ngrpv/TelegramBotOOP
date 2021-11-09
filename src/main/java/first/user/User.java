package first.user;

import first.IGame;
import first.hangman.HangmanGameState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Comparator;

@Entity
@Getter
@Setter
public class User implements Comparable<User> {
    @Id
    private long id;

    public int score;
    public UserState state;
    @Transient
    public IGame gameState;
    public Boolean stateIsChanged = true;
    public Integer guessedWords;
    public String userName;
    public Boolean flagName;

    public User(long id) {
        state = UserState.onMenu;
        gameState = new HangmanGameState();
        guessedWords = 0;
        flagName = true;
        this.id = id;
    }

    public User() {

    }

    public User withScore(int score) {
        this.score = score;
        return this;
    }

    public User withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Boolean isPlaying() {
        return state == UserState.Playing;
    }

    public void changeGame(IGame game) {
        gameState = game;
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
        return userName.compareTo(user.userName);
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static class GuessedWordComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getGuessedWords() - o2.getGuessedWords();
        }
    }
}
