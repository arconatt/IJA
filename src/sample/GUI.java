package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class GUI {

    private File map = new File("./data/map.txt");
    Scene scenehelp, scenerequest, scene;
    private Stage pStage;
    Goods goodsManager;

    /**
     *
     * @return
     */
    private HBox addHBox() {
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

        Button buttonStart = new Button("Start");
        buttonStart.setStyle("-fx-font-weight: bold");
        buttonStart.setPrefSize(100, 20);

        Button buttonRestart = new Button("Restart");
        buttonRestart.setStyle("-fx-font-weight: bold");
        buttonRestart.setPrefSize(100, 20);

        hbox.getChildren().addAll(buttonHelp, buttonRequest , buttonStart, buttonRestart);

        return hbox;
    }

    /**
     *
     * @return
     */
    private HBox addCredits() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #C0C0C0");
        hbox.setSpacing(10);
        Label cred = new Label("Naty & Teri, 2021©");
        hbox.getChildren().addAll(cred);
        return hbox;
    }

    /**
     *
     * @return
     */
    private VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: #87CEEB ");
        vbox.setSpacing(8);

        Text title = new Text("INFO");
        title.setStyle("-fx-font-size:25; -fx-font-weight: bold");
        vbox.getChildren().add(title);

        Label options[] = new Label[] {
                new Label("Active trolleys"),
                new Label("Filling of warehouse"),
                new Label("Time"),
                new Label("Speed")};

        for (int i=0; i<4; i++) {
            options[i].setStyle("-fx-font-weight: bold");
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        return vbox;
    }

    /**
     *
     * @return
     */
    private AnchorPane addAnchorPane() {
        AnchorPane anchorpane = new AnchorPane();
        TilePane centerTable = addTilePane();
        anchorpane.getChildren().addAll(centerTable);
        return anchorpane;
    }

    /**
     *
     * @return
     */
    private TilePane addTilePane() {
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

        Map mapBuilder = new Map(tile);
        goodsManager = new Goods(mapBuilder.getShelfList());
        return tile;
    }

    /**
     *
     */
    private static final String HELP =
            "GUI aplikacie Warehouse:\n" +
            "1. \n" +
            "Po kliknuti na lubovolny regal sa zobrazi jeho obsah (napr. 10, 12, 180, 120),\n" +
            "(pri prazdnom regali sa zobrazi informacia pre uzivatela: empty shelf (napr. 2, 15).\n" +
            "2. \n" +
            "Po kliknuti na tlacitko Request sa zobrazi aktualny zoznam pozadovanych poloziek a formular na vkladanie novych.\n" +
            "(vkladanie aktualne implementovane ako vymazanie Textfieldu) \n" +
            "3. \n" +
            "Prava sekcia bude zobrazovat aktualne informacie o sklade s moznostou editacie rychlosti pohybu vozikov.\n" +
            "(momentalne neimplementovane) \n" +
            "4. \n" +
            "Tlacidla Start a Restart budu po stlaceni spustat vykonavanie requestov vozikmi.\n" +
            "(momentalne neimplementovane) \n";

    /**
     *
     * @param shelfData
     * @param shelfID
     */
    public static void display(String shelfData, int shelfID)
    {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Shelf " + shelfID);
        if (shelfData.equals("")) {
            shelfData = "Empty shelf";
        }
        Label label1= new Label(shelfData);

        ScrollPane root = new ScrollPane();
        root.setContent(label1);

        Button button1= new Button("Close");

        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    /**
     *
     * @param primaryStage
     */
    public GUI(Stage primaryStage) {
        /**
         *
         */
        BorderPane border = new BorderPane();
        pStage = primaryStage;
        scene = new Scene(border, 1150, 750);


        primaryStage.setScene(scene);
        primaryStage.setTitle("Warehouse");
        HBox hbox = addHBox();
        HBox hbox2 = addCredits();
        border.setTop(hbox);
        border.setRight(addVBox());
        border.setCenter(addAnchorPane());

        Label labelhelp= new Label(HELP);
        labelhelp.setStyle("-fx-font-size:20");
        Button closebut = new Button("Close");
        closebut.setOnAction(e -> primaryStage.setScene(scene));
        VBox layouthelp = new VBox(20);
        layouthelp.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        layouthelp.getChildren().addAll(labelhelp, closebut);
        layouthelp.setAlignment(Pos.CENTER);

        Label labelrequest = new Label("List of requested goods: \n" + goodsManager.requestManager.listofGoods());
        labelrequest.setStyle("-fx-font-size:20");
        Label add = new Label("Submit additional requests:");
        add.setStyle("-fx-font-size:20");
        Label labelgood = new Label("Type of good:");
        Label labelamount = new Label("Amount of good:");
        TextField textField_g = new TextField ();
        TextField textField_a = new TextField ();
        Button submit = new Button("Submit");

        //clear textfields when submitting goods
        submit.setOnAction(value -> { textField_a.clear() ; textField_g.clear();});

        HBox hb = new HBox();
        hb.getChildren().addAll(labelgood, textField_g, labelamount, textField_a, submit);
        hb.setSpacing(10);


        Button closerequestbut = new Button("Close");
        closerequestbut.setOnAction(e -> primaryStage.setScene(scene));
        GridPane layoutrequest = new GridPane();
        layoutrequest.setHgap(10);
        layoutrequest.setVgap(10);
        layoutrequest.setPadding(new Insets(40, 40, 40, 50));
        layoutrequest.add(labelrequest, 0,0);
        layoutrequest.add(add, 0,1);
        layoutrequest.add(hb, 0,2);
        layoutrequest.add(closerequestbut, 1, 2);
        layoutrequest.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));


        Button but1 = new Button("Click");
        but1.setOnAction(e -> primaryStage.setScene(scene));
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(but1);

        scenehelp = new Scene(layouthelp, 1150, 750);
        scenerequest = new Scene(layoutrequest, 1150, 750);

        border.setBottom(hbox2);
        primaryStage.show();
    }

}
