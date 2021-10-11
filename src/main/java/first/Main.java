package first;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;


public class Main {
    public static void main(String[] args){
        ConsoleBot.launch();
        //launchBot();
        /*HashMap<Long, UserState> userStates = new HashMap<>();
        var user = new UserState();
        user.startAndGetAnswer();
        user.gameState.checkAnswer('м');
        user.gameState.checkAnswer('ш');
        userStates.put((long)23, user);
        var gson = new Gson();
        try {
            var fileWriter = new FileWriter("users");
            gson.toJson(userStates,  fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*var gson = new Gson();
        HashMap<Long, UserState> userStates = new HashMap<>();

        try {
            JsonReader getLocalJsonFile = new JsonReader(new FileReader("users"));
            Type mapTokenType = new TypeToken<HashMap<String, UserState>>(){}.getType();
            userStates =  gson.fromJson(getLocalJsonFile, mapTokenType);


            System.out.println(userStates);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (var key :
                userStates.values()) {
            System.out.println(key.gameState);*/




        /*try {
            gson.toJson(userStates,  new FileWriter("usersStates"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private static void launchBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TGBot("NRGNbot"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}