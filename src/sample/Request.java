/**
 * Request data processing.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */

package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Request data processing.
 */
public class Request {

    public final File request = new File("./data/request.txt");
    public ArrayList<String> listOfReqGoods = new ArrayList<>();


    /**
     * Load requests from file.
     *
     * @param goodsMap Requested goods loaded in hashmap.
     */
    public Request(HashMap goodsMap) {
        try {
            Scanner myReader = new Scanner(request);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // read one request
                String[] goodsParam = data.split(",", 2);
                Integer goodsAmount = Integer.parseInt(goodsParam[1]);
                Object goodsOfType = goodsMap.get(goodsParam[0]);
                // TODO: vozik zpracuje aktualni request, pokud neni plny ceka na dalsi request, pokud je plny nebo neni dalsi req pokracuje
                // vozik.zpracuj(goodsOfType)
                listOfReqGoods.add(data);
            }
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Load array of goods to string.
     *
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
