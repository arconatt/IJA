/**
 * Management of one shelf.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
package sample;
import java.util.ArrayList;

/**
 * Management of one shelf.
 */
public class Shelf {

    private int shelfID;
    private ArrayList<ShelfItems> goodsList;

    /**
     * Setter for shelfID, creates the list of goods.
     *
     * @param shelfID ID of the current shelf.
     */
    public Shelf(int shelfID) {
        this.shelfID = shelfID;
        goodsList = new ArrayList<>();
    }

    /**
     * Adds the items group to the current shelf.
     *
     * @param itemsGroup Group of items.
     * @return True if success, else false.
     */
    public boolean addItems(ShelfItems itemsGroup) {
        int prevSize = goodsList.size();
        goodsList.add(itemsGroup);
        return goodsList.size() != prevSize;
    }

    /**
     * Prints goods in the current shelf.
     *
     * @return String of present goods.
     */
    public String getShelfData() {
        String retval = "";
        for (int i = 0; i < goodsList.size(); i++) {
            retval += goodsList.get(i).getShelfData();
        }
        return retval;
    }
}
