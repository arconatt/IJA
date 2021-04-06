package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class Popup{

    public void showGoods() {
        //TODO: display goods to scrollbar
    }

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

        //TODO: showGoods() call
        Button button1= new Button("Close");

        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(root, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }
}
