package sample;

import javafx.util.Pair;

import java.util.ArrayList;

public class CartManagement {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;

    public CartManagement() {
        cart1 = new Cart(0, 1);
        cart2 = new Cart(0, 2);
        cart3 = new Cart(0, 3);
        cart4 = new Cart(0, 4);
        cart5 = new Cart(0, 5);

        ArrayList<Integer[]> cart1Path;
        ArrayList<ArrayList<Integer>> cart2Path;
        ArrayList<ArrayList<Integer>> cart3Path;
        ArrayList<ArrayList<Integer>> cart4Path;
        ArrayList<ArrayList<Integer>> cart5Path;

    }


    // list souradnic od algoritmu
    // posune vsechny voziky o 1 policko
    public void updateCarts(Integer x1, Integer y1) {
        cart1.moveCart(x1, y1);
        cart2.moveCart(x1, y1);
        cart3.moveCart(x1, y1);
        cart4.moveCart(x1, y1);
        cart5.moveCart(x1, y1);
    }


}
