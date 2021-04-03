package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
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

    public static void main(String[] args) {
        launch(args);
    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

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
        Button buttonSave = new Button("Save");
        buttonSave.setPrefSize(100, 20);
        Button buttonCancel = new Button("Cancel");
        buttonCancel.setPrefSize(50, 20);
        anchorpane.getChildren().addAll(buttonSave, buttonCancel);

        return anchorpane;
    }

    public TilePane addTilePane() {
        TilePane tile = new TilePane();
        tile.setPadding(new Insets(5, 0, 5, 0));
        tile.setVgap(4);
        tile.setHgap(4);
        String[] colrow = new String[2];

        try{
            File map = new File("map.txt");
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
        System.out.println(cols);
        System.out.println(rows);

        tile.setPrefColumns(cols);
        tile.setPrefRows(rows);
        tile.setStyle("-fx-background-color: DAE6F3;");

//        for (int i=0; i<8; i++) {
//            pages[i] = new ImageView(
//                    new Image(LayoutSample.class.getResourceAsStream(
//                            "graphics/chart_"+(i+1)+".png")));
//            tile.getChildren().add(pages[i]);
//        }
        return tile;
    }

    public void BuildMap() {
        int ShelfID = 0;
        int y = 0;
        try{
            File map = new File("map.txt");
            Scanner myReader = new Scanner(map);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                String[] row = data.split(" ", 50);
                //System.out.println(Arrays.toString(row));
                Integer[] result = new Integer[row.length];
                for (int i = 0; i < row.length; i++) {
                    result[i] = Integer.parseInt(row[i]);
                }

                for (int x = 0; x < result.length; x++) {
                    if (result[x] == 1) {
                        ShelfID++;
                    }
                    GenerateCell(x, y, ShelfID, result[x]);
                }

                y++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void GenerateCell(int x, int y, int SID, Integer isButton){

        if (isButton == 1) {
            Button button = new Button(Integer.toString(SID));
            button.setPrefSize(30,30);
            button.setStyle("-fx-font-size:9");
        } else {
            Label label = new Label();
            label.setPreferredSize(new Dimension(30,30));
        }
//        c.gridx = x;
//        c.gridy = y;
//        if (isButton == 1) {
//            pane.add(button, c);
//        } else {
//            pane.add(label, c);
//        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setLeft(addVBox());
        border.setCenter(addAnchorPane());
        addTilePane();
        Scene scene = new Scene(border, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Layout Sample");
        primaryStage.show();


    }
}