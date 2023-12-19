package org.group10.utils.fileio.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.group10.utils.fileio.FileReadAndWrite;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor
public class JsonFileReadAndWrite<T> implements FileReadAndWrite<T> {

    @Override
    public List<T> readFromFile(Type type, String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            return new Gson().fromJson(fileReader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeToFile(Iterable<T> data, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
