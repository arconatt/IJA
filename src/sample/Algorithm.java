package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

public class Algorithm {
    private GridPane tile;
    private ArrayList<Shelf> shelves;
    private ArrayList<Button> shelfButtons;
    private Queue<String> itemsQueue;
    private ArrayList<HashMap<Integer, Button>> columns;

    public Algorithm(GridPane tile, ArrayList<Shelf> shelves, ArrayList<Button> shelfButtons, ArrayList<HashMap<Integer, Button>> columns, Queue<String> itemsQueue) {
        this.shelves = shelves;
        this.tile = tile;
        this.shelfButtons = shelfButtons;
        this.itemsQueue = itemsQueue;
        this.columns = columns;
    }

    /**
     *
     * @param itemsQueue
     * @return
     */
    public HashMap<String, Integer> getItems(Queue<String> itemsQueue) {
        HashMap<String, Integer> cartRequests = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            String item = itemsQueue.poll();
            if (item == null) {
                // there are no more requested items
                break;
            }
            if (cartRequests.containsKey(item)) {
                Integer amount = cartRequests.get(item);
                amount++;
                cartRequests.put(item, amount);
            } else {
                cartRequests.put(item, 1);
            }
        }
        return cartRequests;
    }


    /**
     * Gets the buttons (=shelves) containing all the needed goods.
     *
     * @return
     */
    public ArrayList<Button> getButtons(Queue<String> itemsQueue) {
        HashMap<String, Integer> cartreq = getItems(itemsQueue);
        Iterator<String> itr = cartreq.keySet().iterator();
        ArrayList<Button> buttons = new ArrayList<>();
        while (itr.hasNext()) {
            String key = itr.next();
            Integer value = cartreq.get(key);
            ArrayList<Button> itemButtons = findItem(key, value);
            buttons.addAll(itemButtons);
        }
        return buttons;
    }

    /**
     * Finds the shelf/shelves which contain the needed amount of goods.
     *
     * @param type Goods type.
     * @param amount Amount of items.
     * @return Array of buttons (=shelves) containing the goods.
     */
    public ArrayList<Button> findItem(String type, int amount) {
        ArrayList<Button> goods = new ArrayList<>();
        for (int i = 1; i < this.shelves.size() + 1; i++) {
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

    /**
     * Finds out the target coordinates (stops) of the cart.
     *
     * @return Array of coordinates.
     */
    public ArrayList<HashMap<Character, Integer>> getTargets() {
        ArrayList<Button> buttons = getButtons(this.itemsQueue);
        ArrayList<HashMap<Character, Integer>> coordinates = new ArrayList<>();
        for (int i = 0; i < buttons.size(); i++) {
            Button b = buttons.get(i);
            String btext = b.getText();
            Integer id = Integer.valueOf(btext);
            for (int j = 0; j < this.columns.size(); j++) {
                if (this.columns.get(j).containsKey(id)) {
                    Button shelfB = this.columns.get(j).get(id);
                    int x = GridPane.getColumnIndex(shelfB);
                    int y = GridPane.getRowIndex(shelfB);
                    if (id % 2 == 0) {
                        x++;
                    } else {
                        x--;
                    }
                    HashMap<Character, Integer> coord = new HashMap<Character, Integer>();
                    coord.put('x',x);
                    coord.put('y',y);
                    coordinates.add(coord);
                    break;
                }
            }
        }
        return coordinates;
    }

}
