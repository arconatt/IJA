/**
 * Class to define general user interface and its methods.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
package sample;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;

/**
 * Class to define general user interface and its methods.
 */
public class GUI {

    private final File map = new File("./data/map.txt");
    private Scene scenehelp, scenerequest, scene, sceneListOfReq;
    private Stage pStage;
    private Goods goodsManager;
    public GridPane tile;
    public ArrayList<Integer> closed = new ArrayList<>();
    public ArrayList<String> additionalReq = new ArrayList<>();
    public Label[] VBoxOptions;
    public Integer mins = 0, secs = 0;
    public Integer WarehouseMax = 8000;
    public String value;
    public Double finalspeed = 1.0;

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
        buttonHelp.setOnAction(e -> {
            goodsManager.requestManager.cartManager.timeline.pause();
            pStage.setScene(scenehelp);
        });
        buttonHelp.setPrefSize(100, 20);

        Button buttonRequest = new Button("Request");
        buttonRequest.setStyle("-fx-font-weight: bold");
        buttonRequest.setOnAction(e -> {
            goodsManager.requestManager.cartManager.timeline.pause();
            displayGoods(goodsManager);
        });
        buttonRequest .setPrefSize(100, 20);

        Button buttonStart = new Button("Play");
        buttonStart.setStyle("-fx-font-weight: bold");
        buttonStart.setPrefSize(100, 20);
        goodsManager.requestManager.cartManager.timeline.setCycleCount(Animation.INDEFINITE);
        buttonStart.setOnAction(e -> goodsManager.requestManager.cartManager.timeline.play());

        Button buttonPause = new Button("Pause");
        buttonPause.setStyle("-fx-font-weight: bold");
        buttonPause.setPrefSize(100, 20);
        buttonPause.setOnAction(e -> goodsManager.requestManager.cartManager.timeline.pause());

        Button buttonStop = new Button("Stop"); // TODO stop nefunguje
        buttonStop.setStyle("-fx-font-weight: bold");
        buttonStop.setPrefSize(100, 20);
        buttonStop.setOnAction(e -> goodsManager.requestManager.cartManager.timeline.stop());

        hbox.getChildren().addAll(buttonHelp, buttonRequest , buttonStart, buttonPause, buttonStop);

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



        Label options[] = new Label[]{
                new Label("Active trolleys"),
                new Label("0"),
                new Label("Filling of warehouse"),
                new Label("default"),
                new Label("Time"),
                new Label("00:00"),
                new Label("Speed"),
                new Label(value),
        };


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


        this.VBoxOptions = options;
        return vbox;
    }

    public void setActiveCarts(Integer active) {
        this.VBoxOptions[1].setText(active.toString());
    }

    public void setTime(Integer time) {
        if (goodsManager.requestManager.cartManager.time != 60){
            this.secs = goodsManager.requestManager.cartManager.time;
        }
        else{
            this.secs = 0;
            goodsManager.requestManager.cartManager.time = 0;
            this.mins++;
        }

        this.VBoxOptions[5].setText(mins + ":" + secs);
    }



    public void setWarehouseMax(Integer max) {
        Integer filling = (max * 100 ) / WarehouseMax;
        this.VBoxOptions[3].setText(max.toString() + " items in stock, \n" + filling.toString() + "% fulfillment" );
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

        WarehouseMap warehouseMapBuilder = new WarehouseMap(tile);
        ArrayList<Shelf> goodsShelf = new ArrayList<Shelf>();
        ArrayList<Button> buttonsShelf = new ArrayList<Button>();
        buttonsShelf = warehouseMapBuilder.getShelfButtons();
        goodsShelf =  warehouseMapBuilder.getShelf();
        this.goodsManager = new Goods(warehouseMapBuilder.getShelfList(), tile, goodsShelf, buttonsShelf, this.additionalReq, this.closed, this);
        Button new_start = warehouseMapBuilder.getStart_button();
        new_start.setOnAction(e -> {
            goodsManager.requestManager.cartManager.timeline.pause();
            displayStart(goodsManager.requestManager.cartManager.unloaded, goodsManager.requestManager.cartManager.timeline);
        });

        return tile;
    }


    /**
     * Long text including help for user.
     */
    private static final String HELP =
            "GUI application Warehouse:\n" +
            "1. \n" +
            "After clicking on arbitrary shelf, its content is displayed,\n" +
            "(when the shelf is empty appears the popup window with : empty shelf.\n" +
            "2. \n" +
            "After clicking on request button, current request list is displayed.\n" +
            "3. \n" +
            "Right section shows current information about the stock (active carts, speed, time and fulfillness of stock).\n" +
            "4. \n" +
            "Start button starts the demonstration and Pause button pauses it.\n";
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

        button1.setOnAction(e -> {
            // TODO timeline.play();
            popupwindow.close();
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public static void displayError(String error)
    {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Error");
        Label label1= new Label(error);
        ScrollPane root = new ScrollPane();
        root.setContent(label1);
        Button button1= new Button("Close");
        button1.setOnAction(e -> {
            popupwindow.close();
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 500, 350);
        popupwindow.setScene(scene1);
        popupwindow.show();
    }

    public static void displayCart(HashMap<String, Integer> goodsData, Timeline timeline, Cart cart)
    {
        Stage popupwindow = new Stage();
        StringBuilder text = new StringBuilder();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Cart " + cart.getCartId().toString());
        if (goodsData.size() == 0) {
            text = new StringBuilder("Empty cart");
        }

        for (String type: goodsData.keySet()) {
            String amount = goodsData.get(type).toString();
            text.append(type).append(", ").append(amount).append("\n");
        }

        Label label1= new Label(text.toString());

        ScrollPane root = new ScrollPane();
        root.setContent(label1);

        Button button1= new Button("Close");

        button1.setOnAction(e -> {
            timeline.play();

            cart.deletePath();
            popupwindow.close();
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }


    public static void displayStart(StringBuilder goodsData, Timeline timeline)
    {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Goods drop off");

        Label label1= new Label(goodsData.toString());

        ScrollPane root = new ScrollPane();
        root.setContent(label1);

        Button button1= new Button("Close");

        button1.setOnAction(e -> {
            timeline.play();
            popupwindow.close();
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public static void displayGoods(Goods goodsManager){


        Label labelrequestlist = new Label(goodsManager.requestManager.listofGoods());
        labelrequestlist.setStyle("-fx-font-size:15");

        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("List of requested goods");

        Label labeltitle = new Label("List of requested goods:");
        labeltitle.setStyle("-fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: #336699");

        ScrollPane root = new ScrollPane();
        root.setContent(labelrequestlist);

        Button button1= new Button("Close");

        button1.setOnAction(e -> {
            popupwindow.close();
        });

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public Double getFinalspeed() {
        return finalspeed;
    }

    /**
     * Create primary scene of application and secondary scenes for menu buttons.
     *
     * @param primaryStage Scene of warehouse.
     */
    public GUI(Stage primaryStage) {
        BorderPane border = new BorderPane();
        pStage = primaryStage;
        scene = new Scene(border, 1150, 750);

        Label app = new Label("WAREHOUSE APP");
        app.setStyle("-fx-font-size:30; -fx-font-weight: bold; -fx-text-fill: #336699");

        Label add = new Label("Submit additional requests:");
        add.setStyle("-fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: #336699");
        Label labelgood = new Label("Type of good:");
        Label labelamount = new Label("Amount of good:");
        TextField textField_g = new TextField ();
        TextField textField_a = new TextField ();
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            String req = textField_g.getText() + "," + textField_a.getText();
            this.additionalReq.add(req);
            textField_a.clear() ; textField_g.clear();
        });
        submit.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #336699");
        //A checkbox without a caption
        CheckBox cb1 = new CheckBox();
        CheckBox cb2 = new CheckBox();
        CheckBox cb3 = new CheckBox();
        CheckBox cb4 = new CheckBox();
        CheckBox cb5 = new CheckBox();
        CheckBox cb6 = new CheckBox();
        CheckBox cb7 = new CheckBox();

        cb1.setText("1");
        cb1.setSelected(false);
        cb2.setText("2");
        cb2.setSelected(false);
        cb3.setText("3");
        cb3.setSelected(false);
        cb4.setText("4");
        cb4.setSelected(false);
        cb5.setText("5");
        cb5.setSelected(false);
        cb6.setText("6");
        cb6.setSelected(false);
        cb7.setText("7");
        cb7.setSelected(false);


        HBox hb = new HBox();
        hb.getChildren().addAll(labelgood, textField_g, labelamount, textField_a, submit);
        hb.setSpacing(10);

        Label block = new Label("Closed paths:");
        block.setStyle("-fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: #336699");

        Label speedlabel = new Label("Speed:");
        speedlabel.setStyle("-fx-font-size:20; -fx-font-weight: bold; -fx-text-fill: #336699");
        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("1");
        comboBox.getItems().add("0.7");
        comboBox.getItems().add("0.5");
        comboBox.getItems().add("0.3");


        Button closerequestbut = new Button("Done");
        closerequestbut.setPrefSize(70,50);
        closerequestbut.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #336699");

        GridPane layoutrequest = new GridPane();
        layoutrequest.setHgap(10);
        layoutrequest.setVgap(10);
        layoutrequest.setPadding(new Insets(40, 40, 40, 50));
        layoutrequest.add(app, 0,1);
        layoutrequest.add(add, 0,2);
        layoutrequest.add(hb, 0,3);
        layoutrequest.add(block, 0,4);
        layoutrequest.add(cb1, 0,5);
        layoutrequest.add(cb2, 0,6);
        layoutrequest.add(cb3, 0,7);
        layoutrequest.add(cb4, 0,8);
        layoutrequest.add(cb5, 0,9);
        layoutrequest.add(cb6, 0,10);
        layoutrequest.add(cb7, 0,11);
        layoutrequest.add(speedlabel, 0,12);
        layoutrequest.add(comboBox, 0,13);
        layoutrequest.add(closerequestbut, 0, 15);
        layoutrequest.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));

        scenerequest = new Scene(layoutrequest, 1150, 750);
        primaryStage.setScene(scenerequest);
        primaryStage.setTitle("Warehouse");

        //Help
        Label labelhelp= new Label(HELP);
        labelhelp.setStyle("-fx-font-size:20");
        Button closebut = new Button("Close");
        closebut.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #336699");
        closebut.setOnAction(e -> primaryStage.setScene(scene));
        VBox layouthelp = new VBox(20);
        layouthelp.setBackground(new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        layouthelp.getChildren().addAll(labelhelp, closebut);
        layouthelp.setAlignment(Pos.CENTER);




        scenehelp = new Scene(layouthelp, 1150, 750);

        closerequestbut.setOnAction(e -> {
            if (cb1.isSelected()) {this.closed.add(1);}
            if (cb2.isSelected()) {this.closed.add(2);}
            if (cb3.isSelected()) {this.closed.add(3);}
            if (cb4.isSelected()) {this.closed.add(4);}
            if (cb5.isSelected()) {this.closed.add(5);}
            if (cb6.isSelected()) {this.closed.add(6);}
            if (cb7.isSelected()) {this.closed.add(7);}

            value = (String) comboBox.getValue();
            finalspeed = Double.valueOf(value);

            border.setRight(addVBox());
            border.setCenter(addAnchorPane());
            HBox hbox = addHBox();
            HBox hbox2 = addCredits();
            border.setTop(hbox);
            border.setBottom(hbox2);
            primaryStage.setScene(scene);
        });


        primaryStage.show();
    }

}
