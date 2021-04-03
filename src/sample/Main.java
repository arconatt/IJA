package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;

import java.awt.*;

public class Main extends Application {

    File map = new File("./data/map.txt");
    Scene scenepop, scene;
    public static Stage pStage;

    public static void main(String[] args) {
        launch(args);
    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setOnAction(e -> pStage.setScene(scenepop));
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }

    public HBox addCredits() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        Label cred = new Label("Naty & Teri, 2021");
        hbox.getChildren().addAll(cred);
        return hbox;
    }

    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Data");
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] {
                new Hyperlink("Sales"),
                new Hyperlink("Marketing"),
                new Hyperlink("Distribution"),
                new Hyperlink("Costs")};

        for (int i=0; i<4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        return vbox;
    }

    public AnchorPane addAnchorPane() {
        AnchorPane anchorpane = new AnchorPane();
        TilePane centerTable = addTilePane();
        anchorpane.getChildren().addAll(centerTable);
        return anchorpane;
    }

    public TilePane addTilePane() {
        TilePane tile = new TilePane();
        tile.setPadding(new Insets(5, 0, 5, 0));
        tile.setVgap(4);
        tile.setHgap(4);
        String[] colrow = new String[2];

        try{
            Scanner myReader = new Scanner(map);
            String data = myReader.nextLine();
            colrow = data.split(",");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Integer cols = Integer.parseInt(colrow[0]);
        Integer rows = Integer.parseInt(colrow[1]);

        tile.setPrefColumns(cols);
        tile.setPrefRows(rows);

        BuildMap(tile);

        return tile;
    }

    public void BuildMap(TilePane tile) {
        int ShelfID = 0;
        int y = 0;
        int counter = 0;
        try{
            Scanner myReader = new Scanner(map);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // skip first line
                if (counter == 0) {
                    counter++;
                    continue;
                }
                counter++;
                // parse one row
                String[] row = data.split(" ", 50);
                // string to integer
                Integer[] result = new Integer[row.length];
                for (int i = 0; i < row.length; i++) {
                    result[i] = Integer.parseInt(row[i]);
                }

                for (int x = 0; x < result.length; x++) {
                    if (result[x] == 1) {
                        ShelfID++;
                    }
                    GenerateCell(tile, ShelfID, result[x]);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Custom button text
            Object[] options = {"Show goods", "Add goods"};
            String longMessage = "stolicka\n"
                    +"stol\n"
                    +"hhhhh\n"
                    +"hhhhh\n"
                    +"hhhhh\n"
                    +"hhhhh\n"
                    +"hhhhh\n";

            event.consume();
        }
    };



    public void GenerateCell(TilePane tile, int SID, Integer isButton){
        // create button (shelf) or label (path) based on map.txt
        if (isButton == 1) {
            Button button = new Button(Integer.toString(SID));
            button.setPrefSize(35,20);
            button.setStyle("-fx-font-size:10; -fx-margin:0");
            tile.getChildren().add(button);
        } else {
            javafx.scene.control.Label label = new javafx.scene.control.Label();
            label.setPrefSize(35,20);
            label.setStyle("-fx-margin:0");
            tile.getChildren().add(label);
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane border = new BorderPane();
        pStage = primaryStage;
        scene = new Scene(border, 1100, 700);
        Button but1 = new Button("Click");
        but1.setOnAction(e -> primaryStage.setScene(scene));
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(but1);
        scenepop = new Scene(layout2, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Warehouse");
        HBox hbox = addHBox();
        HBox hbox2 = addCredits();
        border.setTop(hbox);
        border.setRight(addVBox());
        border.setCenter(addAnchorPane());
        border.setBottom(hbox2);
        primaryStage.show();

    }
}