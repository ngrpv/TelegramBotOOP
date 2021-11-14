package first.user;

import first.games.GameType;
import first.games.IGame;
import first.games.hangman.HangmanGameState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Comparable<User> {
    @Id
    private long id;

    public int score;
    public UserState state;
    public GameType gameType;
    public long GameID = id;

    @Transient
    public IGame gameState;

    public Boolean stateIsChanged = true;
    public Integer guessedWords;
    @Column(name = "username")
    public String userName;
    public Boolean flagName;

    public User(long id) {
        state = UserState.onMenu;
        gameState = new HangmanGameState(GameID);
        guessedWords = 0;
        flagName = true;
        this.id = id;
    }

    public User(){

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



    public static class GuessedWordComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getGuessedWords() - o2.getGuessedWords();
        }
    }
}
