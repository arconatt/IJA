package sample;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Goods {
    File goods = new File("./data/goods.txt");
    String[] goodsType;
    ArrayList<Shelf> shelf;
    ArrayList<Object> types;
    ArrayList<Pair<Integer, Integer>> currType;

    public Goods(ArrayList<Shelf> shelfPar) {
        this.shelf = shelfPar;
        types = new ArrayList<>();
        currType = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(goods);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // set current goods type
                if (data.matches("^Typ(e)?:[\\s\\S]*$")) {
                    goodsType = data.split(":", 2);
                    if (currType.size() != 0) {
                        this.types.add(currType.clone());
                    }
                    currType.clear();
                    continue;
                }
                // create items of one type in one shelf
                String[] goodsParam = data.split(",", 2);
                Integer shelfID = Integer.parseInt(goodsParam[0]);
                Integer goodsAmount = Integer.parseInt(goodsParam[1]);
                ShelfItems items = new ShelfItems(goodsType[1], goodsAmount);
                Shelf actualShelf = this.shelf.get(shelfID);
                actualShelf.addItems(items);
                Pair<Integer,Integer> amountInShelf = new Pair<>(shelfID, goodsAmount);
                currType.add(amountInShelf);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}
