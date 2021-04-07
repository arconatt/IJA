package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map {
    /**
     *
     */

    private File map = new File("./data/map.txt");
    private ArrayList<Shelf> shelf;

    /**
     *
     * @return
     */
    public ArrayList<Shelf> getShelfList() {
        return this.shelf;
    }

    /**
     *
     * @param tile
     */
    public Map(TilePane tile) {
        //TODO: zoom map
        shelf = new ArrayList<>();
        shelf.add(null); // start the list from 1 to match the shelfID
        int ShelfID = 0;
        int counter = 0;
        try{
            Scanner myReader = new Scanner(map);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // skip first line
                if (counter == 0) {
                    counter++;
                    continue;
                }
                counter++;
                // parse one row
                String[] row = data.split(" ", 50);
                // string to integer
                Integer[] result = new Integer[row.length];
                for (int i = 0; i < row.length; i++) {
                    result[i] = Integer.parseInt(row[i]);
                }

                for (int x = 0; x < result.length; x++) {
                    if (result[x] == 1) {
                        ShelfID++;
                    }
                    GenerateCell(tile, ShelfID, result[x]);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param tile
     * @param SID
     * @param isButton
     */
    private void GenerateCell(TilePane tile, int SID, Integer isButton){
        // create button (shelf) or label (path) based on map.txt
        if (isButton == 1) {
            Button button = new Button(Integer.toString(SID));
            button.setPrefSize(35,20);
            button.setStyle("-fx-font-size:10; -fx-margin:0; -fx-background-color: #DEB887");
            tile.getChildren().add(button);
            shelf.add(new Shelf(SID));
            button.setOnAction(e -> GUI.display(shelf.get(SID).getShelfData(), SID));
        } else if (isButton == 0){
            javafx.scene.control.Label label = new javafx.scene.control.Label();
            label.setPrefSize(35,20);
            label.setStyle("-fx-margin:0");
            tile.getChildren().add(label);
        }
        else{
            javafx.scene.control.Label label = new javafx.scene.control.Label(" Start");
            label.setPrefSize(35,20);
            label.setStyle("-fx-font-size:12px; -fx-font-weight: bold; -fx-margin:0; -fx-background-color: #D2B48C");
            label.setContentDisplay(ContentDisplay.CENTER);
            tile.getChildren().add(label);
        }
    }



}
