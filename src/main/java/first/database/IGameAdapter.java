package first.database;

import com.google.gson.*;
import first.IGame;
import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameState;

import java.lang.reflect.Type;

public class IGameAdapter implements JsonSerializer, JsonDeserializer {

    public IGame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var gson = new Gson();
        return gson.fromJson(jsonElement, type);

    }


//***** Helper method to get the className of the object to be deserialized ****

    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        if (o instanceof HangmanGameState) {
            o = (HangmanGameState) o;
        } else {
            o = (CowsAndBullsState) o;
        }

        var gson = new Gson();
        return gson.toJsonTree(o, HangmanGameState.class );
    }
}

