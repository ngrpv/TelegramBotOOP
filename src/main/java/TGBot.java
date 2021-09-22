import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private String userName;
    private String token;
    private HangmanGame game;
    SendMessage sendMessage = new SendMessage();
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
        var userState = getUserState(chatId);
        var messageText = update.getMessage().getText();
        sendMessage.setText("Напишите: /hangman");
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        if (userState.isPlaying) {
            sendMessage.setText(userState.gameState.checkAnswer(messageText));
        }
        if (messageText.equals("/hangman")) {
            sendMessage.setText(userState.startPlaying());
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private UserState getUserState(Long chatId) {
        UserState userState;
        if (userStates.containsKey(chatId))
            userState = userStates.get(chatId);
        else {
            userState = new UserState(chatId, false);
            userStates.put(chatId, userState);
        }
        return userState;
    }
}
