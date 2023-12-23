package backend.utils.fileio;

import java.lang.reflect.Type;
import java.util.List;

public interface FileReadAndWrite<T> {
    List<T> readFromFile(Type type, String filePath);
    void writeToFile(Iterable<T> data, String filePath);

    T readObjectFromFile(Type type, String filePath);
    void writeObjectToFile(T data, String filePath);
}
