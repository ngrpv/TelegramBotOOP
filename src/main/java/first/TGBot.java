package first;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private String userName;
    private String token;
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
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(BotLogic.getMessageForUser(messageText, userState));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public UserState getUserState(Long chatId) {
        UserState userState;
        if (userStates.containsKey(chatId))
            userState = userStates.get(chatId);
        else {
            userState = new UserState();
            userStates.put(chatId, userState);
        }
        return userState;
    }


}
