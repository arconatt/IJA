package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Algorithm {
    private GridPane tile;
    private ArrayList<Shelf> shelves;
    private ArrayList<Button> shelfButtons;

    public Algorithm(GridPane tile, ArrayList<Shelf> shelves, ArrayList<Button> shelfButtons) {
        this.shelves = shelves;
        this.tile = tile;
        this.shelfButtons = shelfButtons;
    }

//    public sendShelves(ArrayList<Shelf> shelves) {
//        this.shelves = shelves;
//    }

    /**
     * Searches for the wanted item near the cart position.
     * @param type Goods type.
     * @return Shelf ID if found, else 0.
     */
    public ArrayList<Button> findItem(String type, int amount) {
        ArrayList<Button> goods = new ArrayList<>();
        for (int i = 1; i < shelves.size() + 1; i++) {
            if (amount <= 0) {
                return goods;
            }
            Shelf shelf = shelves.get(i);
            int inStock = shelf.searchForItem(type);
            if (inStock != 0) {
                int id = shelf.getShelfID();
                Button button = shelfButtons.get(id);
                goods.add(button);
                amount -= inStock;
            }
        }
        return goods;
    }
}
