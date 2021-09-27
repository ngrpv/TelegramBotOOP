package first;

import java.io.FileNotFoundException;

public class UserState {
    private UserStateEnum state;
    public Boolean isPlaying;
    public HangmanGame gameState;

    public UserState() {
        isPlaying = false;
        state = UserStateEnum.onMenu;
        try {
            gameState = new HangmanGame();
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
            gameState = new HangmanGame();
            return gameState.getHiddenWord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gameState.getHiddenWord();
    }
}
