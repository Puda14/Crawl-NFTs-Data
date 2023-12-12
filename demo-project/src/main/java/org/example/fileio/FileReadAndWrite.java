package org.example.fileio;

import java.util.List;

public interface FileReadAndWrite<T> {
    List<T> readFromFile();
    void writeToFile(List<T> data);
}
