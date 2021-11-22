package first.user;

import com.google.gson.annotations.Expose;
import first.games.ISerializeAbleById;
import first.games.GameType;
import first.games.IGame;
import first.games.cowsAndBulls.CowsAndBullsState;
import first.games.hangman.HangmanGameState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable, ISerializeAbleById {
    @Id
    private long id;
    public int score;
    public UserState state;
    public GameType gameType;
    public long GameID;
    @Transient
    @Expose(serialize = false)
    public IGame gameState;
    public Boolean stateIsChanged = true;
    public Integer guessedWords;
    @Column(name = "username")
    public String userName;
    public Boolean flagName;

    public User(long id) {
        state = UserState.OnMenu;
        this.GameID = id;
        this.id = id;
        gameState = new HangmanGameState(GameID);
        guessedWords = 0;
        flagName = true;
    }

    public User() {

    }

    public long getGameId() {
        return id;
    }

    public Boolean isPlaying() {
        return state == UserState.Playing;
    }

    public void changeGame(GameType gameType) {
        this.gameType = gameType;
        if (gameType == GameType.Hangman) {
            gameState = new HangmanGameState(GameID);
        } else {
            gameState = new CowsAndBullsState(GameID);
        }
    }


    public void changeState(UserState state) {
        stateIsChanged = true;
        this.state = state;
        switch (state) {
            case Playing:
                gameState.start();
                break;
            case OnMenu:
                break;
        }
    }
}
