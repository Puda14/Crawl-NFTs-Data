package backend.utils.file;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    public static String generateFileNameWithTimestamp(String baseFileName, String fileExtension) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String timestamp = dtf.format(localDateTime);
        return baseFileName + "_" + timestamp + "." + fileExtension;
    }

    public static String getNewestFile(String directoryPath, String fileExtension) {
        try {
            List<Path> files = Files.walk(Paths.get(directoryPath))
                    .filter(path -> path.toString().endsWith("." + fileExtension))
                    .sorted(Comparator.comparingLong(path -> getFileCreationTime(path)))
                    .collect(Collectors.toList());

            if (!files.isEmpty()) {
                return files.get(files.size() - 1).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static long getFileCreationTime(Path path) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            return attrs.creationTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
