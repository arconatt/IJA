/**
 * Request data processing.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */

package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Request data processing.
 */
public class Request {

    private final File request = new File("./data/request.txt");
    private ArrayList<String> listOfReqGoods = new ArrayList<>();
    public Queue<String> reqOneItem;
    public CartManagement cartManager;
    public GridPane tile;
    public ArrayList<Shelf> goodsShelf;
    public ArrayList<Button> buttonsShelf;

    /**
     * Load requests from file.
     * @param tile GridPane map.
     * @param goodsShelf List of shelves.
     * @param buttonsShelf List of buttons corresponding to shelves.
     * @param additionalReq List of additional requests.
     * @param closed List of closed paths.
     * @param gui GUI instance.
     */
    public Request(GridPane tile, ArrayList<Shelf> goodsShelf, ArrayList<Button> buttonsShelf, ArrayList<String> additionalReq, ArrayList<Integer> closed, GUI gui) {
        this.tile = tile;
        this.goodsShelf = goodsShelf;
        this.buttonsShelf = buttonsShelf;
        try {
            Scanner myReader = new Scanner(request);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listOfReqGoods.add(data);
            }
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        listOfReqGoods.addAll(additionalReq);
        this.divideGoods(listOfReqGoods);
        this.cartManager = new CartManagement(tile, reqOneItem, goodsShelf, buttonsShelf, closed, gui);
    }


    /**
     * Take the requests and add the separate items to a queue.
     * @param reqList List of requests.
     */
    public void divideGoods(ArrayList<String> reqList) {
        this.reqOneItem = new LinkedList<>();
        for (int i = 0; i < reqList.size(); i++) {
            String[] goodsParam = reqList.get(i).split(",", 2);
            Integer goodsAmount = Integer.parseInt(goodsParam[1]);
            String goodsOfType = goodsParam[0];
            for (int j = 0; j < goodsAmount; j++) {
                reqOneItem.add(goodsOfType);
            }
        }
    }

    /**
     * Load array of goods to string.
     * @return long string of requested values
     */
    public String listofGoods(){
        String text = "";
        for (int i=0; i < listOfReqGoods.size(); i++){
            text += listOfReqGoods.get(i) + "\n";
        }
        return text;
    }

}
