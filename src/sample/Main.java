package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;

import java.awt.*;

public class Main extends Application {

    File map = new File("./data/map.txt");
    Scene scenepop, scenehelp, scenerequest, scene;
    public static Stage pStage;

    public static void main(String[] args) {
        launch(args);
    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonHelp = new Button("Help");
        buttonHelp.setStyle("-fx-font-weight: bold");
        buttonHelp.setOnAction(e -> pStage.setScene(scenehelp));
        buttonHelp.setPrefSize(100, 20);

        Button buttonRequest = new Button("Request");
        //TODO: manually add request by user -> some goods in amount x to "Start"
        buttonRequest.setStyle("-fx-font-weight: bold");
        buttonRequest .setOnAction(e -> pStage.setScene(scenerequest));
        buttonRequest .setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setStyle("-fx-font-weight: bold");
        buttonProjected.setOnAction(e -> pStage.setScene(scenepop));
        buttonProjected.setPrefSize(100, 20);

        hbox.getChildren().addAll(buttonHelp, buttonRequest , buttonProjected);

        return hbox;
    }

    public HBox addCredits() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #C0C0C0");
        hbox.setSpacing(10);
        Label cred = new Label("Naty & Teri, 2021©");
        hbox.getChildren().addAll(cred);
        return hbox;
    }

    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: #87CEEB ");
        vbox.setSpacing(8);

        Text title = new Text("INFO");
        title.setStyle("-fx-font-size:25; -fx-font-weight: bold");
        vbox.getChildren().add(title);

        Label options[] = new Label[] {
                new Label("Active trolleys"),
                //new Label("number of trolleys"),
                new Label("Filling of warehouse"),
                //new Label("x% "),
                new Label("Time"),
                //new Label("XX:XX:XX"),
                new Label("Speed")};
                //new Label("1x or 2x or 0.5x etc.")};

        for (int i=0; i<4; i++) {
            options[i].setStyle("-fx-font-weight: bold");
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
        //TODO: zoom map
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

    public void LoadGoods(File file){
        //TODO: load from ./data/goods.txt to shelfs
    }

    public void LoadRequests(File file){
        //TODO: load from ./data/requests.txt
    }

    public void GenerateCell(TilePane tile, int SID, Integer isButton){
        // create button (shelf) or label (path) based on map.txt
        if (isButton == 1) {
            Button button = new Button(Integer.toString(SID));
            button.setPrefSize(35,20);
            button.setStyle("-fx-font-size:10; -fx-margin:0; -fx-background-color: #DEB887");
            tile.getChildren().add(button);
            button.setOnAction(e -> Popup.display());
        } else if (isButton == 0){
            javafx.scene.control.Label label = new javafx.scene.control.Label();
            label.setPrefSize(35,20);
            label.setStyle("-fx-margin:0");
            tile.getChildren().add(label);
        }
        else{
            //TODO: ak sa ti nepaci kde je "Start" zmen v map.txt '2' na ine miesto
            javafx.scene.control.Label label = new javafx.scene.control.Label(" Start");
            label.setPrefSize(35,20);
            label.setStyle("-fx-font-size:12px; -fx-font-weight: bold; -fx-margin:0; -fx-background-color: #D2B48C");
            label.setContentDisplay(ContentDisplay.CENTER);
            tile.getChildren().add(label);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane border = new BorderPane();
        pStage = primaryStage;
        scene = new Scene(border, 1150, 750);

        //TODO: mozno dat tieto obludnosti do funkcie?
        Label labelhelp= new Label("Tu bude napoveda");
        labelhelp.setStyle("-fx-font-size:20");
        Button closebut = new Button("Close");
        closebut.setOnAction(e -> primaryStage.setScene(scene));
        VBox layouthelp = new VBox(20);
        layouthelp.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        layouthelp.getChildren().addAll(labelhelp, closebut);
        layouthelp.setAlignment(Pos.CENTER);

        Label labelrequest = new Label("Tu bude request od uzivatela (supis zbozi a pocet kusov na vydajne miesto)");
        labelrequest.setStyle("-fx-font-size:20");
        Button closerequestbut = new Button("Close");
        closerequestbut.setOnAction(e -> primaryStage.setScene(scene));
        VBox layoutrequest = new VBox(20);
        layoutrequest.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        layoutrequest.getChildren().addAll(labelrequest, closerequestbut);
        labelrequest.setAlignment(Pos.CENTER); //TODO: why the fuck it is NOT in center?

        Button but1 = new Button("Click");
        but1.setOnAction(e -> primaryStage.setScene(scene));
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(but1);

        scenehelp = new Scene(layouthelp, 1150, 750);
        scenerequest = new Scene(layoutrequest, 1150, 750);
        scenepop = new Scene(layout2, 1150, 750);

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