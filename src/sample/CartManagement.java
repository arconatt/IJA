package sample;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.*;

public class CartManagement {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;

    Algorithm alg;
    private ArrayList<Button> shelfButtons;
    private ArrayList<Shelf> shelves;
    private Queue<String> itemsQueue;

    public CartManagement(GridPane tile, Queue<String> itemsQueue, ArrayList<Shelf> goodsShelf, ArrayList<Button> buttonsShelf) {
        this.itemsQueue = itemsQueue;
        this.shelves = goodsShelf;
        this.shelfButtons = buttonsShelf;

        cart1 = new Cart(1, tile);
        cart2 = new Cart(2, tile);
        cart3 = new Cart(3, tile);
        cart4 = new Cart(4, tile);
        cart5 = new Cart(5, tile);

        this.alg = new Algorithm(tile, this.shelves, this.shelfButtons);
        ArrayList<Button> cart1Shelves = getButtons(cart1);
        System.out.println(cart1Shelves);
    }

    public ArrayList<Button> getButtons(Cart cart) {
        HashMap<String, Integer> cartreq = cart.getItems(itemsQueue);
        Iterator<String> itr = cartreq.keySet().iterator();
        ArrayList<Button> buttons = new ArrayList<>();
        while (itr.hasNext()) {
            String key = itr.next();
            Integer value = cartreq.get(key);
            ArrayList<Button> itemButtons = alg.findItem(key, value);
            buttons.addAll(itemButtons);
        }
        return buttons;
    }

    public void setShelves(ArrayList<Button> shelfButtons){
        this.shelfButtons = shelfButtons;
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
