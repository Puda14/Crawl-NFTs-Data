package backend.utils.test;

import static backend.utils.file.FileManager.getNewestFile;

public class FileTest {
    public static void main(String[] args){
        String filename = getNewestFile("D:\\code java\\Crawl-NFTs-Data\\demo-app","json");
        System.out.println(filename);
    }
}
