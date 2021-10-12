package first;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private String userName;
    SendMessage sendMessage = new SendMessage();
    private HashMap<Long, UserState> userStates = new HashMap<>();

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
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(BotLogic.getMessageForUser(messageText, userState, sendMessage));

        var replyMarkup = new ReplyKeyboardMarkup();
        var tgButtons = new TGBotButtons();
        replyMarkup.setKeyboard(TGBotButtons.getButtons(userState.state));
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setOneTimeKeyboard(false);
        sendMessage.setReplyMarkup(replyMarkup);


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
