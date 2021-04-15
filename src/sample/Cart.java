package sample;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;

public class Cart {
    //private ArrayList<Integer> coords;
    private Integer currCoord;

    public Cart(int coord) {
        currCoord = coord;
        //coords.add(currCoord);
    }

    public void moveCart(Integer coord, GridPane tile) {
        //coords.add(coord);
        Node current = tile.getChildren().get(coord);
        current.setStyle("-fx-background-color: red");
    }

    public  void showPath() {

    }

    public void finished() {
        // vrati vozik, vymaze jeho obsah a cestu
    }

}
