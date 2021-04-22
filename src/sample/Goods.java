/**
 * Management of goods.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
package sample;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Management of goods.
 */
public class Goods {
    private final File goods = new File("./data/goods.txt");
    private String[] goodsType;
    private ArrayList<Shelf> shelf;
    private ArrayList<Pair<Integer, Integer>> currType;
    /**
     * Manages incoming requests
     */
    public Request requestManager;

    /**
     * Adds the items to the warehouse.
     *
     * @param shelfPar List of shelves.
     */
    public Goods(ArrayList<Shelf> shelfPar, GridPane tile) {

        this.shelf = shelfPar;
        HashMap<String, Object> typesMap = new HashMap<>();
        currType = new ArrayList<>();
        String prevType = "";

        try {
            Scanner myReader = new Scanner(goods);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // set current goods type
                if (data.matches("^Typ(e)?:[\\s\\S]*$")) {
                    if (currType.size() != 0) {
                        typesMap.put(prevType, currType.clone());
                    }
                    goodsType = data.split(":", 2);
                    prevType = goodsType[1];
                    currType.clear();
                    continue;
                }
                // create items of one type in one shelf
                String[] goodsParam = data.split(",", 2);
                Integer shelfID = Integer.parseInt(goodsParam[0]);
                Integer goodsAmount = Integer.parseInt(goodsParam[1]);
                Shelf actualShelf = this.shelf.get(shelfID);
                actualShelf.addItems(goodsType[1], goodsAmount);
                Pair<Integer,Integer> amountInShelf = new Pair<>(shelfID, goodsAmount);
                currType.add(amountInShelf);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        requestManager = new Request(tile);
        //requestManager.cartManager.alg.sendShelves();
    }



}
