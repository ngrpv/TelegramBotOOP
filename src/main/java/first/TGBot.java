package first;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private String userName;
    private HashMap<Long, User> userStates = new HashMap<>();

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
        sendMessage.setText(BotLogic.handleMessage(messageText, userState));
        if(userState.stateIsChanged) {
            var replyMarkup = new ReplyKeyboardMarkup();
            new TGBotButtons();
            replyMarkup.setKeyboard(TGBotButtons.getButtons(userState.state));
            replyMarkup.setResizeKeyboard(true);
            replyMarkup.setOneTimeKeyboard(true);
            sendMessage.setReplyMarkup(replyMarkup);
            userState.stateIsChanged = false;
        }


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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


}
