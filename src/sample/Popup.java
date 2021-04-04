package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup{

    public void showGoods(ActionEvent event) {
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

    public static void display()
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Shelf options");


        Label label1= new Label("What action do you want to make?");

        Button show = new Button("Show goods");
        show.setOnAction(e -> Goods.display());
        Button add = new Button("Add goods");
        add.setOnAction(e -> Goods.display());
        Button button1= new Button("Close this pop up window");

        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, show, add, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }
}
