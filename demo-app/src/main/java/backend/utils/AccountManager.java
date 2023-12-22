package backend.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccountManager {
    private BufferedReader reader;

    public AccountManager(String filePath) {
        try {
            this.reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String[] changeAccount(String filePath) {
        String[] accountDetails = new String[3];

        try {
            accountDetails[0] = reader.readLine(); // username
            accountDetails[1] = reader.readLine(); // password
            accountDetails[2] = reader.readLine(); // email
            if (accountDetails[0] == null || accountDetails[1] == null) {
                resetReader(filePath);
                accountDetails[0] = reader.readLine(); // username
                accountDetails[1] = reader.readLine(); // password
                accountDetails[2] = reader.readLine(); // email
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountDetails;
    }

    private void resetReader(String filePath) {
        try {
            reader.close();
            reader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}