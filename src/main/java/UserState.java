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
        gameState = new HangmanGame("hello");
        return gameState.getHiddenWord();
    }

    public Long getChatId(){
        return chatId;
    }
}
