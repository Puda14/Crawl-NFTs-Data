package org.group10.utils.file;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    public String generateFileNameWithTimestamp(String baseFileName, String fileExtension) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        return baseFileName + "_" + timestamp + "." + fileExtension;
    }

    public String getNewestFile(String directoryPath, String fileExtension) {
        try {
            List<Path> files = Files.walk(Paths.get(directoryPath))
                    .filter(path -> path.toString().endsWith("." + fileExtension))
                    .sorted(Comparator.comparingLong(path -> getFileCreationTime(path)))
                    .collect(Collectors.toList());

            if (!files.isEmpty()) {
                // Chọn file mới nhất
                return files.get(files.size() - 1).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private long getFileCreationTime(Path path) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            return attrs.creationTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        // Tạo tên file mới kèm timestamp
        String newFileName = fileManager.generateFileNameWithTimestamp("example", "txt");
        System.out.println("New File Name: " + newFileName);

        // Lấy đường dẫn của file mới nhất trong thư mục cụ thể
        String newestFilePath = fileManager.getNewestFile("/path/to/directory", "txt");
        if (newestFilePath != null) {
            System.out.println("Newest File Path: " + newestFilePath);
        } else {
            System.out.println("No files found.");
        }
    }
}
