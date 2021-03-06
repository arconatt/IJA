/**
 * Management of one shelf.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
package sample;
import java.util.HashMap;

/**
 * Management of one shelf.
 */
public class Shelf {
    private int shelfID;
    public HashMap<String, Integer> goodsList;
    public Integer total = 0;

    /**
     * Setter for shelfID, creates the list of goods.
     *
     * @param shelfID ID of the current shelf.
     */
    public Shelf(int shelfID) {
        this.shelfID = shelfID;
        goodsList = new HashMap<>();
    }

    /**
     * Adds the items group to the current shelf.
     *
     * @param type Goods type.
     * @param amount Goods amount.
     */
    public void addItems(String type, Integer amount) {
        if (goodsList.containsKey(type)) {
            Integer value = goodsList.get(type);
            value += amount;
            this.total += value;
            if (this.total > 50) {
                String error = "The maximum shelf capacity has been reached. Shelf ID: " + shelfID + ".\nItem " + type + " has been discarded.";
                GUI.displayError(error);
                return;
            }
            goodsList.put(type, value);
        } else {
            this.total += amount;
            if (this.total > 50) {
                String error = "The maximum shelf capacity has been reached. Shelf ID: " + shelfID + ".\nItem " + type + " has been discarded.";
                GUI.displayError(error);
                return;
            }
            goodsList.put(type, amount);
        }
    }

    /**
     * Removes the specified amount of goods from the shelf.
     *
     * @param type Type of goods.
     * @param amount Amount of goods.
     * @return Amount of goods that was removed from the actual shelf.
     */
    public Integer removeItems(String type, Integer amount) {
        if (goodsList.containsKey(type)) {
            Integer inStock = goodsList.get(type);
            if (amount < inStock) {
                inStock -= amount;
                goodsList.put(type, inStock);
                return amount;
            } else {
                goodsList.remove(type);
                return inStock;
            }
        } else {
            return 0;
        }
    }

    /**
     * Searches for an item in the shelf.
     *
     * @param type The item that is being searched for.
     * @return Amount if present, else 0.
     */
    public int searchForItem(String type) {
        if (this.goodsList.containsKey(type)) {
            return goodsList.get(type);
        } else {
            return 0;
        }
    }

    /**
     * Prints goods in the current shelf.
     * @return String of present goods.
     */
    public String getShelfData() {
        String retval = "";
        for (String i : goodsList.keySet()) {
            retval += "Type: " + i + "\nAmount: " + goodsList.get(i) + "\n\n";
        }
        return retval;
    }

    /**
     * Shelf ID getter.
     * @return Shelf ID.
     */
    public int getShelfID() {
        return shelfID;
    }
}
