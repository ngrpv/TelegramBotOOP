package first;

import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameState;

public class UserState {
    public UserStateEnum state;
    public Boolean isPlaying;
    public IGame gameState;

    public UserState() {
        isPlaying = false;
        state = UserStateEnum.onMenu;
        gameState = new HangmanGameState();
    }

    public UserState(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    public void setGame(IGame game){
        gameState = game;
    }

    public String startGame() {
        isPlaying = true;
        gameState.start();
        return gameState.getStartMessage();
    }
}