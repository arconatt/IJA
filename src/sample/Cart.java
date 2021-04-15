package sample;

import javafx.util.Pair;

import java.util.ArrayList;

public class Cart {
    private ArrayList<ArrayList<Integer>> coords;
    ArrayList<Integer> currCoord = new ArrayList<>();

    public Cart(int x, int y) {
        currCoord.add(x);
        currCoord.add(y);
        coords.add(currCoord);
    }

    public void moveCart(Integer x, Integer y) {
        currCoord.add(x);
        currCoord.add(y);
        coords.add(currCoord);
        currCoord.clear();
    }

    public  void showPath() {

    }

    public void finished() {
        // vrati vozik, vymaze jeho obsah a cestu
    }

}
