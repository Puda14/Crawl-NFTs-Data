package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Awesome App");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch();
    }
}