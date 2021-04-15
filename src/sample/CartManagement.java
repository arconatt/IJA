package sample;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;

public class CartManagement {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;
    private int[] cart1Path;
    private int[] cart2Path;
    private int[] cart3Path;
    private int[] cart4Path;

    //public CartManagement(ArrayList<Integer> c1, ArrayList<Integer> c2, ArrayList<Integer> c3, ArrayList<Integer> c4) {
    public CartManagement(GridPane tile) {
        cart1 = new Cart(1);
        cart2 = new Cart(2);
        cart3 = new Cart(3);
        cart4 = new Cart(4);
        //cart5 = new Cart(5, tile);

        cart1Path = new int[]{106, 140, 174, 208};
        cart2Path = new int[]{107, 141, 175, 209};
        cart3Path = new int[]{110, 144, 178, 212};
        cart4Path = new int[]{213, 179, 145, 111};

        for (int i=0; i < 4; i++) {
            updateCarts(i, tile);
        }

    }


    // list souradnic od algoritmu
    // posune vsechny voziky o 1 policko
    public void updateCarts(int i, GridPane tile) {
        cart1.moveCart(cart1Path[i], tile);
        cart2.moveCart(cart2Path[i], tile);
        cart3.moveCart(cart3Path[i], tile);
        cart4.moveCart(cart4Path[i], tile);
        //cart5.moveCart(x1, y1);
    }


}
