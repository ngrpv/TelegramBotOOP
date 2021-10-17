package first;

import first.hangman.HangmanGameState;

public class User {
    public UserState state;
    public Boolean isPlaying;
    public IGame gameState;

    public User() {
        isPlaying = false;
        state = UserState.onMenu;
        gameState = new HangmanGameState();
    }

    public User(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    public void setGame(IGame game){
        gameState = game;
    }
    public void setGameName(UserState gameName) {state = gameName;}

    public String startGame() {
        isPlaying = true;
        gameState.start();
        return gameState.getStartMessage();
    }
    public UserState getUserStateEnum() {return state;}

}