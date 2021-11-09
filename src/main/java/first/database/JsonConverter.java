package first.database;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import first.IGame;
import first.cowsAndBulls.CowsAndBullsState;
import first.hangman.HangmanGameState;
import first.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class JsonConverter implements IDatabase {
    private final String directory;
    private GsonBuilder gsonBuilder = new GsonBuilder();

    public JsonConverter(String directory) {
        this.directory = directory;
    }

    @Override
    public void addUser(User user) {
        try {
            var fileWriter = new FileWriter(directory + "/" + user.getId() + ".json");
            gsonBuilder.registerTypeAdapter(IGame.class, new IGameAdapter());
            var gson = gsonBuilder.create();
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
            gsonBuilder.registerTypeHierarchyAdapter(IGame.class, new IGameAdapter());
            var gson = gsonBuilder.create();
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
        gsonBuilder.registerTypeHierarchyAdapter(IGame.class, new IGameAdapter());
        var users = new ArrayList<User>();
        var gson = gsonBuilder.create();
        for (var file: Objects.requireNonNull(new File(directory+"/").listFiles())){
            var user = gson.fromJson(String.valueOf(file), User.class);
            users.add(user);
        }
        return users;
    }


}
