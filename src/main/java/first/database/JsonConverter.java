package first.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import first.hangman.HangmanGameState;
import first.user.User;

import java.io.*;
import java.util.ArrayList;

public class JsonConverter implements IDatabase {
    private final String directory;
    private Gson gson = new Gson();

    public JsonConverter(String directory) {
        this.directory = directory;
    }

    @Override
    public void addUser(User user) {
        try {
            var fileWriter = new FileWriter(directory + "/" + user.getId() + ".json");
            gson.toJson(user, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(long id) {
        JsonReader getLocalJsonFile;
        try {
            var gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            getLocalJsonFile = new JsonReader(new FileReader(directory + "/" + id + ".json"));
            var user = gson.fromJson(getLocalJsonFile, User.class);
            return (User) user;
        } catch (FileNotFoundException e) {
            var f = new File(directory + "/" + id + ".json");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void updateOrAdd(User user) {
        addUser(user);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    private void IGameDeserializer(){

    }

}
