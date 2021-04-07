package sample;
import java.util.ArrayList;


public class Shelf {
    /**
     *
     */
    private int shelfID;
    private ArrayList<ShelfItems> goodsList;

    /**
     *
     * @param shelfID
     */
    public Shelf(int shelfID) {
        this.shelfID = shelfID;
        goodsList = new ArrayList<>();
    }

    /**
     *
     * @param itemsGroup
     * @return
     */
    public boolean addItems(ShelfItems itemsGroup) {
        int prevSize = goodsList.size();
        goodsList.add(itemsGroup);
        return goodsList.size() != prevSize;
    }

    /**
     *
     * @return
     */
    public String getShelfData() {
        String retval = "";
        for (int i = 0; i < goodsList.size(); i++) {
            retval += goodsList.get(i).getShelfData();
        }
        return retval;
    }
}
