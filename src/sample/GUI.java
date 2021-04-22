/**
 * Class to define general user interface and its methods.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to define general user interface and its methods.
 */
public class GUI {

    private final File map = new File("./data/map.txt");
    private Scene scenehelp, scenerequest, scene;
    private Stage pStage;
    private Goods goodsManager;
    private CartManagement cartManager;
    public GridPane tile;
//    private Integer activeCart = 0;

    /**
     * Create menu on the top.
     *
     * @return Top bar with multiple buttons (Help, Request, Start, Restart).
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
        //TODO buttonStart.setOnAction(play);

        Button buttonRestart = new Button("Restart");
        buttonRestart.setStyle("-fx-font-weight: bold");
        buttonRestart.setPrefSize(100, 20);

        hbox.getChildren().addAll(buttonHelp, buttonRequest , buttonStart, buttonRestart);

        return hbox;
    }

    /**
     * Create a bar with a label containing credits.
     *
     * @return Bottom bar with credits.
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
     * Create info menu on the right side of the application.
     *
     * @return Box containing info
     */
    private VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setPrefWidth(300);
        vbox.setStyle("-fx-background-color: #87CEEB ");
        vbox.setSpacing(8);

        Text title = new Text("INFO");
        title.setStyle("-fx-font-size:25; -fx-font-weight: bold");
        vbox.getChildren().add(title);

//        this.activeCart = cartManager.getActiveCart();
//        System.out.println(this.activeCart);

        Label options[] = new Label[] {
                new Label("Active trolleys"),
                new Label("tu bude 5 vacsinou"),
                new Label("Filling of warehouse"),
                new Label("160 shelf * 50 zbozi je 100%"),
                new Label("Time"),
                new Label("tu bude timer"),
                new Label("Speed"),
                new Label("I am speed")};


        options[0].setStyle("-fx-font-weight: bold");
        options[1].setPrefSize(120,60);
        options[1].setStyle("-fx-border-style: solid");
        options[2].setStyle("-fx-font-weight: bold");
        options[3].setPrefSize(120,60);
        options[3].setStyle("-fx-border-style: solid");
        options[4].setStyle("-fx-font-weight: bold");
        options[5].setPrefSize(120,60);
        options[5].setStyle("-fx-border-style: solid");
        options[6].setStyle("-fx-font-weight: bold");
        options[7].setPrefSize(120,60);
        options[7].setStyle("-fx-border-style: solid");

        for (int i=0; i<8; i++) {
            options[i].setAlignment(Pos.CENTER);
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }



        return vbox;
    }

    /**
     * Create parent for map layout.
     *
     * @return A parent.
     */
    private AnchorPane addAnchorPane() {
        AnchorPane anchorpane = new AnchorPane();
        GridPane centerTable = addGridPane();
        anchorpane.getChildren().addAll(centerTable);
        return anchorpane;
    }

    /**
     * Create layout for the warehouse map.
     *
     * @return Layout containing shelfs.
     */
    private GridPane addGridPane() {
        tile = new GridPane();
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

        Map mapBuilder = new Map(tile);
        ArrayList<Shelf> goodsShelf = new ArrayList<Shelf>();
        ArrayList<Button> buttonsShelf = new ArrayList<Button>();
        buttonsShelf = mapBuilder.getShelfButtons();
        goodsShelf =  mapBuilder.getShelf();
        goodsManager = new Goods(mapBuilder.getShelfList(), tile, goodsShelf, buttonsShelf);
//        goodsManager.requestManager.cartManager.setShelves(mapBuilder.getShelfButtons());
        return tile;
    }

    /**
     * Long text including help for user.
     */
    private static final String HELP =
            "GUI aplikacie Warehouse:\n" +
            "1. \n" +
            "Po kliknuti na lubovolny regal sa zobrazi jeho obsah (napr. 10, 12, 180, 305),\n" +
            "(pri prazdnom regali sa zobrazi informacia pre uzivatela: empty shelf (napr. 2, 15).\n" +
            "2. \n" +
            "Po kliknuti na tlacitko Request sa zobrazi aktualny zoznam pozadovanych poloziek a formular na vkladanie novych.\n" +
            "(vkladanie aktualne implementovane ako vymazanie Textfieldu) \n" +
            "3. \n" +
            "Prava sekcia bude zobrazovat aktualne informacie o sklade s moznostou editacie rychlosti pohybu vozikov.\n" +
            "(momentalne neimplementovane) \n" +
            "4. \n" +
            "Tlacidla Start a Restart budu po stlaceni spustat vykonavanie requestov vozikmi.\n" +
            "(momentalne neimplementovane) \n" +
            "Na vyskusanie: \n * Zmena pozadovanych poloziek data/request.txt: \n riadok v tvare typ a mnozstvo rozdelene ciarkou.\n" +
            " * Zmena poloziek v sklade data/goods.txt: \n 1. riadok v tvare \"Typ:polozka\", kazdy dalsi riadok pod polozkou v tvare \"shelfID,pocet\"";

    /**
     * Display popup window with title of shelf and scrollpane of containing goods.
     *
     * @param shelfData List of goods on the shelf.
     * @param shelfID Shelf number.
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
     * Create primary scene of application and secondary scenes for menu buttons.
     *
     * @param primaryStage Scene of warehouse.
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
        border.setRight(addVBox());
        border.setCenter(addAnchorPane());
        HBox hbox = addHBox();
        HBox hbox2 = addCredits();
        border.setTop(hbox);


        Label labelhelp= new Label(HELP);
        labelhelp.setStyle("-fx-font-size:20");
        Button closebut = new Button("Close");
        closebut.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #336699");
        closebut.setOnAction(e -> primaryStage.setScene(scene));
        VBox layouthelp = new VBox(20);
        layouthelp.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        layouthelp.getChildren().addAll(labelhelp, closebut);
        layouthelp.setAlignment(Pos.CENTER);

        Label labeltitle = new Label("List of requested goods:");
        labeltitle.setStyle("-fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: #336699");
        Label labelrequest = new Label(goodsManager.requestManager.listofGoods());
        labelrequest.setStyle("-fx-font-size:15");

        Label add = new Label("Submit additional requests:");
        add.setStyle("-fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: #336699");
        Label labelgood = new Label("Type of good:");
        Label labelamount = new Label("Amount of good:");
        TextField textField_g = new TextField ();
        TextField textField_a = new TextField ();
        Button submit = new Button("Submit");
        submit.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #336699");

        //clear textfields when submitting goods
        submit.setOnAction(value -> { textField_a.clear() ; textField_g.clear();});

        HBox hb = new HBox();
        hb.getChildren().addAll(labelgood, textField_g, labelamount, textField_a, submit);
        hb.setSpacing(10);


        Button closerequestbut = new Button("Close");
        closerequestbut.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #336699");
        closerequestbut.setOnAction(e -> primaryStage.setScene(scene));
        GridPane layoutrequest = new GridPane();
        layoutrequest.setHgap(10);
        layoutrequest.setVgap(10);
        layoutrequest.setPadding(new Insets(40, 40, 40, 50));
        layoutrequest.add(labeltitle, 0,0);
        layoutrequest.add(labelrequest, 0,1);
        layoutrequest.add(add, 0,2);
        layoutrequest.add(hb, 0,3);
        layoutrequest.add(closerequestbut, 1, 3);
        layoutrequest.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));


        scenehelp = new Scene(layouthelp, 1150, 750);
        scenerequest = new Scene(layoutrequest, 1150, 750);

        border.setBottom(hbox2);
        primaryStage.show();
    }

}
