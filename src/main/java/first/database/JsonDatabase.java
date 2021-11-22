package first.database;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import first.ISerializeAbleById;
import first.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class JsonDatabase implements IDatabase {
    private final File directory;
    private Gson gson = new GsonBuilder().addSerializationExclusionStrategy(getGameStateExclusion()).create();


    public JsonDatabase(String directory) {
        this.directory = getDirectory(directory);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        persist(entity);
    }

    @Override
    public void persist(Object object) {
        if (!(object instanceof ISerializeAbleById entity)) throw new IllegalStateException();
        try {
            var classDirectory = getDirectory(directory + "/" + object.getClass().getName());
            var fileWriter = new FileWriter(classDirectory + "/" + entity.getId() + ".json");
            gson.toJson(object, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T get(Class<T> aClass, Serializable serializable) {
        return getFromFile(directory + "/" + aClass.getName() + "/" + serializable + ".json", aClass);
    }

    @Override
    public <T> ArrayList<T> getAll(Class<T> tClass) {
        var users = new ArrayList<T>();
        var classDirectory = new File(directory + "/" + tClass.getName() + "/").listFiles();
        for (var file : Objects.requireNonNull(classDirectory)) {
            users.add(getFromFile(String.valueOf(file), tClass));
        }
        return users;
    }

    @Override
    public <T> ArrayList<T> getTop(Class<T> aClass, int count, String orderedBy) {
        var all = getAll(aClass);
        var top = new ArrayList<T>();
        return all;
    }

    private File getDirectory(String name) {
        var directory = new File(name);

        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    private ExclusionStrategy getGameStateExclusion() {
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getDeclaringClass() == User.class && field.getName().equals("gameState");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
    }

    private <T> T getFromFile(String filename, Class<T> aClass) {
        FileReader reader;
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            return null;
        }
        var getLocalJsonFile = new JsonReader(reader);
        return gson.fromJson(getLocalJsonFile, aClass);

    }
}
