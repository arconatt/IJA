package sample;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Queue;

public class Cart {
    private ArrayList<Integer> coords = new ArrayList<>();
    public Integer currCoord;
    private GridPane tile;
    private Integer home;
    public Integer itemsAmount;

    public Cart(Integer coord, GridPane tile) {
        this.tile = tile;
        this.home = coord;
        moveCart(coord);
    }

    public void unloadItems() {
        if (this.itemsAmount != 0) {
            moveCart(1); //TODO: tam, kde je na mape 2
            this.itemsAmount = 0;
        }
        goHome();
    }

    public void goHome() {
        moveCart(this.home);
    }

    public void moveCart(Integer coord) {
        if (coord != null) {
            currCoord = coord;
        }
        this.coords.add(currCoord);
        Node current = tile.getChildren().get(currCoord);
        current.setStyle("-fx-background-color: red");
    }

    public  void showPath() {

    }

    public void finished() {
        // vrati vozik, vymaze jeho obsah a cestu
    }

}
