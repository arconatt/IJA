package sample;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    /**
     *
     * @param args arguments of application
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GUI app_gui = new GUI(primaryStage);
    }
}