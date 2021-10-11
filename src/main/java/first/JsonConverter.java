package first;

import org.json.JSONArray;

import java.util.HashMap;

public class JsonConverter {
    public static void convert( HashMap<Long, UserState> userStates){
        var jsonObj = new JSONArray(userStates);
    }
}
