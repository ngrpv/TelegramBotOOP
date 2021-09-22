public class UserState {
    private Long chatId;
    public Boolean isPlaying;
    public HangmanGame gameState;

    public UserState(Long chatId, Boolean isPlaying) {
        this.chatId = chatId;
        this.isPlaying = isPlaying;
    }

    public Long getChatId(){
        return chatId;
    }
}
