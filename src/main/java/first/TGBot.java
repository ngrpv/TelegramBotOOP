package first;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotOptions;

import java.util.HashMap;

public class TGBot extends TelegramLongPollingBot {
    private String userName;
    private String token;
    private HangmanGame game;
    SendMessage sendMessage = new SendMessage();
    private HashMap<Long, UserState> userStates = new HashMap<>();
    private BotLogic logic = new BotLogic();

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
        var userState = logic.getUserState(chatId, BotLogic.hundlerType.Telegram,userStates);
        var messageText = update.getMessage().getText();
        sendMessage.setText("Напишите: /hangman");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(logic.getMessageForUser(messageText, BotLogic.hundlerType.Telegram,userState));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
