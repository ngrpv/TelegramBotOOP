import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private String userName;
    private String token;
    private HangmanGame game;
    SendMessage msg = new SendMessage();
    private HashMap<Long, UserState> userStates = new HashMap<>();

    public TGBot(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        msg.setText("Напишите: /hangman");
        msg.setChatId(update.getMessage().getChatId().toString());
        UserState userState;
        if(userStates.containsKey(chatId))
            userState = userStates.get(chatId);
        else {
            userState = new UserState(chatId, false);
            userStates.put(chatId, userState);
        }
        if(userState.isPlaying){
            msg.setText(userStates.get(chatId).gameState.checkAnswer(update.getMessage().getText()));
        }
        if(update.getMessage().getText().equals("/hangman")){
            userState.isPlaying = true;
            userState.gameState = new HangmanGame("Helloworld");
            msg.setText(userState.gameState.getHiddenWord());
        }
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
