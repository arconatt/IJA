package sample;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Queue;

public class Algorithm {
    private GridPane tile;
    private ArrayList<Shelf> shelves = new ArrayList<>();
    private ArrayList<Button> shelfButtons;
    private Queue<String> itemsQueue;
    private ArrayList<HashMap<Integer, Button>> columns;
    private HashMap<String, Integer> items;
    private ArrayList<GoodsToRemove> toBeRemoved = new ArrayList<>();;
    private Timeline timeline;
    private ArrayList<Integer> closed;


    public Algorithm(GridPane tile, ArrayList<Shelf> shelves, ArrayList<Button> shelfButtons, ArrayList<HashMap<Integer, Button>> columns, Queue<String> itemsQueue, Timeline timeline) {
        this.timeline = timeline;
        Iterator<Shelf> it = shelves.iterator();
        while (it.hasNext()) {
            Shelf s = it.next();
            if (s == null) {
                this.shelves.add(null);
                continue;
            }
            Shelf newS = new Shelf(s.getShelfID());
            Iterator ithm = s.goodsList.entrySet().iterator();

            while (ithm.hasNext()) {
                Map.Entry mapElement = (Map.Entry)ithm.next();
                newS.addItems((String)mapElement.getKey(), (Integer)mapElement.getValue());
            }

            this.shelves.add(newS);
        }
        this.tile = tile;
        this.shelfButtons = shelfButtons;
        this.itemsQueue = itemsQueue;
        this.columns = columns;
    }

    public HashMap<String, Integer> getItems() {
        return items;
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
            if (item == null && i == 0) {
                return null;
            }
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
        this.items = cartRequests;
        return cartRequests;
    }


    /**
     * Gets the buttons (=shelves) containing all the needed goods.
     *
     * @return
     */
    public ArrayList<Button> getButtons(Queue<String> itemsQueue) {
        HashMap<String, Integer> cartreq = getItems(itemsQueue);
        if (cartreq == null) {
            return null;
        }
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
        for (int i = 1; i < this.shelves.size(); i++) {
            GoodsToRemove batch = new GoodsToRemove();
            if (amount <= 0) {
                break;
            }
            Shelf shelf = this.shelves.get(i);
            int inStock = shelf.searchForItem(type);
            if (inStock != 0) {
                int id = shelf.getShelfID();
                if (closed.contains(id)) {
                    continue;
                }
                Integer removed = shelf.removeItems(type, amount);
                Button button = shelfButtons.get(id);
                batch.setAmount(removed);
                batch.setType(type);
                batch.setShelfID(id);
                toBeRemoved.add(batch);
                goods.add(button);
                amount -= inStock;
            }
        }
        if (amount > 0) {
            timeline.pause();
            GUI.displayError("Item " + type + " in amount " + amount + " not found.");
        }
        return goods;
    }

    /**
     * Finds out the target coordinates (stops) of the cart.
     *
     * @return Array of coordinates.
     */
    public ArrayList<GoodsToRemove> getTargets() {
        this.toBeRemoved.clear();
        ArrayList<Button> buttons = getButtons(this.itemsQueue);
        if (buttons == null) {
            return null;
        }
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
                    this.toBeRemoved.get(i).setX(x);
                    this.toBeRemoved.get(i).setY(y);
                    break;
                }
            }
        }
        return toBeRemoved;
    }

}
