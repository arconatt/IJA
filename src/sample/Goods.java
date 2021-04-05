package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Goods {
    File goods = new File("./data/goods.txt");
    String[] goodsType;
    ArrayList<Shelf> shelf;

    public Goods(ArrayList<Shelf> shelfPar) {
        this.shelf = shelfPar;

        try {
            Scanner myReader = new Scanner(goods);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // set current goods type
                if (data.matches("^Typ(e)?:[\\s\\S]*$")) {
                    goodsType = data.split(":", 2);
                    continue;
                }
                // create items of one type in one shelf
                String[] goodsParam = data.split(",", 2);
                Integer shelfID = Integer.parseInt(goodsParam[0]);
                Integer goodsAmount = Integer.parseInt(goodsParam[1]);
                ShelfItems items = new ShelfItems(goodsType[1], goodsAmount);
                Shelf actualShelf = this.shelf.get(shelfID);
                actualShelf.addItems(items);
            }
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean removeItems() {
        /* TODO */
        return true;
    }


}
