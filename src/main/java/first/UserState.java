package first;

import java.io.FileNotFoundException;

public class UserState {
    private Long chatId;
    public Boolean isPlaying;
    public HangmanGame gameState;

    public UserState(Long chatId, Boolean isPlaying) {
        this.chatId = chatId;
        this.isPlaying = isPlaying;
    }

    public String startPlaying(){
        isPlaying = true;
        try {
            gameState = new HangmanGame();
            gameState.setFile("hangmanWords.txt");
            return gameState.getHiddenWord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gameState.getHiddenWord();
    }

    public Long getChatId(){
        return chatId;
    }
}
