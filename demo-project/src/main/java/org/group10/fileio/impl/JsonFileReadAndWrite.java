package org.group10.fileio.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.group10.fileio.FileReadAndWrite;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
public class JsonFileReadAndWrite<T> implements FileReadAndWrite<T> {

    private String filePath;

    @Override
    public List<T> readFromFile() {
        try (FileReader fileReader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<T>>(){}.getType();
            return new Gson().fromJson(fileReader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeToFile(List<T> data) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
