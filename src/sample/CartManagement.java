package sample;
import javafx.scene.layout.GridPane;

import java.util.Queue;

public class CartManagement {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    int[] cart1Path;
    int[] cart2Path;
    int[] cart3Path;
    int[] cart4Path;

    public CartManagement(GridPane tile, Queue<String> itemsQueue) {

        cart1 = new Cart(1, tile);
        cart2 = new Cart(2, tile);
        cart3 = new Cart(3, tile);
        cart4 = new Cart(4, tile);

//        cart1Path = new int[]{106, 140, 174, 208};
//        cart2Path = new int[]{107, 141, 175, 209};
//        cart3Path = new int[]{110, 144, 178, 212};
//        cart4Path = new int[]{213, 179, 145, 111};
    }

//    public void run() {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), this::updateCarts));
//        timeline.setCycleCount(4);
//        timeline.play();
//    }
//
//    // list souradnic od algoritmu
//    // posune vsechny voziky o 1 policko
//    public void updateCarts(javafx.event.ActionEvent actionEvent) {
//        cart1.moveCart(cart1Path[i], tile);
//        cart2.moveCart(cart2Path[i], tile);
//        cart3.moveCart(cart3Path[i], tile);
//        cart4.moveCart(cart4Path[i], tile);
//    }

}
