package first;

import first.hangman.HangmanGameState;

import java.io.FileNotFoundException;

public class UserState {
    private UserStateEnum state;
    public Boolean isPlaying;
    public HangmanGameState gameState;

    public UserState() {
        isPlaying = false;
        state = UserStateEnum.onMenu;
        try {
            gameState = new HangmanGameState(new FileHandler());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserState(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public String startPlaying(){
        isPlaying = true;
        try {
            gameState = new HangmanGameState(new FileHandler());
            return gameState.getHiddenWord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gameState.getHiddenWord();
    }
}
