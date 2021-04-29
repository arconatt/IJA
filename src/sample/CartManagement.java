package sample;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.*;

public class CartManagement {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;
    ArrayList<HashMap<Integer, Button>> columns;
    public Integer activeCart = 0;

    Algorithm alg;
    private ArrayList<Button> shelfButtons;
    private ArrayList<Shelf> shelves;

    public CartManagement(GridPane tile, Queue<String> itemsQueue, ArrayList<Shelf> goodsShelf, ArrayList<Button> buttonsShelf) {
        this.shelves = goodsShelf;
        this.shelfButtons = buttonsShelf;

        cart1 = new Cart(1, 0, tile);
        activeCart++;
        cart2 = new Cart(2, 0, tile);
        activeCart++;
        cart3 = new Cart(3, 0, tile);
        activeCart++;
        cart4 = new Cart(4, 0, tile);
        activeCart++;
        cart5 = new Cart(5, 0, tile);
        activeCart++;
        fillPaths();
        this.alg = new Algorithm(tile, this.shelves, this.shelfButtons, this.columns, itemsQueue);
        ArrayList<HashMap<Character, Integer>> coordinates = new ArrayList<>();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
        coordinates = alg.getTargets();
    }

    public Integer getActiveCart() {
        System.out.println(activeCart);
        return activeCart;
    }

    public void fillPaths() {
        HashMap<Integer, Button> column1 = new HashMap<>();
        HashMap<Integer, Button> column2 = new HashMap<>();
        HashMap<Integer, Button> column3 = new HashMap<>();
        HashMap<Integer, Button> column4 = new HashMap<>();
        HashMap<Integer, Button> column5 = new HashMap<>();
        HashMap<Integer, Button> column6 = new HashMap<>();
        HashMap<Integer, Button> column7 = new HashMap<>();
        HashMap<Integer, Button> column8 = new HashMap<>();
        HashMap<Integer, Button> column9 = new HashMap<>();
        for (int i = 1; i < 146 ; i += 16) {
            column1.put(i, this.shelfButtons.get(i));
        }
        for (int i = 16; i < 161 ; i += 16) {
            column9.put(i, this.shelfButtons.get(i));
        }
        fillColumns(column2, 2, 147);
        fillColumns(column3, 4, 149);
        fillColumns(column4, 6, 151);
        fillColumns(column5, 8, 153);
        fillColumns(column6, 10, 155);
        fillColumns(column7, 12, 157);
        fillColumns(column8, 14, 159);
        this.columns = new ArrayList<>();
        columns.add(column1);
        columns.add(column2);
        columns.add(column3);
        columns.add(column4);
        columns.add(column5);
        columns.add(column6);
        columns.add(column7);
        columns.add(column8);
        columns.add(column9);
    }

    private void fillColumns(HashMap<Integer, Button> column, int start, int end) {
        for (int i = start; i < end; i += 16) {
            column.put(i, this.shelfButtons.get(i));
            column.put(i+1, this.shelfButtons.get(i+1));
        }
    }

    // TODO: cyklus: cart.getItem + shelf.removeItem dokud nebude cart.itemsAmount 5, takto pro kazdy cart, prvne je
    // potreba nacist vsech 5 itemu pro kazdy cart, aby nebyly vsechny u 1 itemu, jakmile je 5, updateCarts a cart.unloadItems,
    // vsechno musi byt pro kazdy vozik nezavisle
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
