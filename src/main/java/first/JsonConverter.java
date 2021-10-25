package first;

import first.user.User;
import org.json.JSONArray;

import java.util.HashMap;

public class JsonConverter {
    public static void convert( HashMap<Long, User> userStates){
        var jsonObj = new JSONArray(userStates);
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
        }
        var gson = new Gson();
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
            System.out.println(key.gameState);




        try {
            gson.toJson(userStates,  new FileWriter("usersStates"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
