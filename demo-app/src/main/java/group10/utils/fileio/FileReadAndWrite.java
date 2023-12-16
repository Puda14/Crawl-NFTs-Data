package group10.utils.fileio;

import java.lang.reflect.Type;
import java.util.List;

public interface FileReadAndWrite<T> {
    List<T> readFromFile(Type type);
    void writeToFile(List<T> data);
}
