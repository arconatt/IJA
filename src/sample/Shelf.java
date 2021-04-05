package sample;
import java.util.ArrayList;

public class Shelf {
    private int shelfID;
    private ArrayList<ShelfItems> goodsList;

    public Shelf(int shelfID) {
        this.shelfID = shelfID;
        goodsList = new ArrayList<>();
    }

    public boolean addItems(ShelfItems itemsGroup) {
        int prevSize = goodsList.size();
        goodsList.add(itemsGroup);
        return goodsList.size() != prevSize;
    }

    public boolean removeItems() {
        /* TODO */
        return true;
    }

    public String getShelfData() {
        String retval = "";
        for (int i = 0; i < goodsList.size(); i++) {
            retval += goodsList.get(i).getShelfData();
        }
        return retval;
    }
}
