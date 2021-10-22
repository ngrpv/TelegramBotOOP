package first;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private final String userName;
    private final HashMap<Long, User> userStates = new HashMap<>(); // вынести, например, в StateStore

    public TGBot(String userName) {
        this.userName = userName;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return System.getenv("tgBotToken");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var userState = getUserState(chatId);
        var messageText = update.getMessage().getText();
        var sendMessage = new SendMessage();

        sendMessage.setChatId(update.getMessage().getChatId().toString());

        var text = BotLogic.handleMessage(messageText, userState);

        sendMessage.setText(text);

        if (userState.stateIsChanged) {
            setKeyboard(sendMessage, userState.state);
            userState.stateIsChanged = false;
        }

        trySend(sendMessage);
    }

    public User getUserState(Long chatId) {
        User user;
        if (userStates.containsKey(chatId))
            user = userStates.get(chatId);
        else {
            user = new User();
            userStates.put(chatId, user);
        }
        return user;
    }

    private void trySend(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setKeyboard(SendMessage sendMessage, UserState state) {
        var replyMarkup = new ReplyKeyboardMarkup();
        TGBotButtons.setDefaultButtons();
        replyMarkup.setKeyboard(TGBotButtons.getButtons(state));
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(true);
        sendMessage.setReplyMarkup(replyMarkup);
    }
}
