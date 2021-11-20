package first.bot;

import first.user.UserStore;
import first.user.UserState;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TGBot extends TelegramLongPollingBot {
    private final String userName;

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
        var user = UserStore.getUser(chatId);
        var messageText = update.getMessage().getText();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        var text = BotLogic.handleMessage(messageText, user);

        sendMessage.setText(text);

        if (user.stateIsChanged) {
            setKeyboard(sendMessage, user.state);
            user.stateIsChanged = false;
        }

        trySend(sendMessage);
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
