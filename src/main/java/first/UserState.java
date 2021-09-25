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
        var message = "w";
        try {
            gameState = new HangmanGame();
            message = "gameIsCreated";
            gameState.setFile("hangmanWords.txt");
            message = "fileSetted";
            return gameState.getHiddenWord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    public Long getChatId(){
        return chatId;
    }
}
