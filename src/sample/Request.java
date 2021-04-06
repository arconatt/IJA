package sample;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Request {

    File request = new File("./data/request.txt");
    private ArrayList<ShelfItems> requestsData;

    public Request(HashMap goodsMap) {
        try {
            Scanner myReader = new Scanner(request);
            requestsData = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // read one request
                String[] goodsParam = data.split(",", 2);
                Integer goodsAmount = Integer.parseInt(goodsParam[1]);
                Object goodsOfType = goodsMap.get(goodsParam[0]);
                // TODO: vozik zpracuje aktualni request, pokud neni plny ceka na dalsi request, pokud je plny nebo neni dalsi req pokracuje
                // vozik.zpracuj(goodsOfType)
            }
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}
