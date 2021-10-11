package first;

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

    public String startAndGetAnswer() {
        isPlaying = true;
        gameState = new HangmanGameState();
        return gameState.getStartMessage();
    }
}
