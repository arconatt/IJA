//package sample;
//
//import javafx.util.Pair;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Request {
//
//    File request = new File("./data/request.txt");
//    private ArrayList<ShelfItems> requestsData;
//
//    public boolean addItems(Pair<Integer, Integer> goodsPair) {
//        int prevSize = goodsList.size();
//        return goodsList.size() != prevSize;
//    }
//
//    public void loadRequests() {
//        try {
//            Scanner myReader = new Scanner(request);
//            requestsData = new ArrayList<>();
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                // read one request
//                String[] goodsParam = data.split(",", 2);
//                Integer goodsShelf = Integer.parseInt(goodsParam[0]);
//                Integer goodsAmount = Integer.parseInt(goodsParam[1]);
//
//
//                actualShelf.addItems(items);
//            }
//            myReader.close();
//        } catch (
//                FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//
//
//
//}
