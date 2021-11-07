package first.user;

import first.IGame;
import first.hangman.HangmanGameState;

public class User {
    private final long id;
    public int score;
    public UserState state;
    public IGame gameState;
    public Boolean stateIsChanged = false;

    public User(long id) {
        state = UserState.onMenu;
        gameState = new HangmanGameState();
        this.id = id;
    }
    public User withScore(int score){
        this.score = score;
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

    public long getId() {
        return id;
    }
}
