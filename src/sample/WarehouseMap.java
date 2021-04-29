/**
 * Class to process map from file.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;

import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to process map from file.
 */
public class WarehouseMap {

    private final File map = new File("./data/map.txt");
    private ArrayList<Shelf> shelf;
    private ArrayList<Button> shelfButtons;


    public ArrayList<Button> getShelfButtons() {
        return shelfButtons;
    }

    public ArrayList<Shelf> getShelf() {
        return shelf;
    }

    /**
     * Getter for the list of shelves.
     *
     * @return List of shelves.
     */
    public ArrayList<Shelf> getShelfList() {
        return this.shelf;
    }

    /**
     * Function to read map file and process it to GUI.
     *
     * @param tile Layout in which map will appear.
     */
    public WarehouseMap(GridPane tile) {
        //TODO: zoom map
        shelf = new ArrayList<>();
        shelfButtons = new ArrayList<>();
        shelf.add(null); // start the list from 1 to match the shelfID
        shelfButtons.add(null); // start the list from 1 to match the shelfID
        int ShelfID = 0;
        int y = 0;
        try{
            Scanner myReader = new Scanner(map);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

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
                    GenerateCell(tile, ShelfID, result[x], x, y);
                }
                y++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error with file occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Fuction to generate shelfs, path and startin position .
     *
     * @param tile Layout in which map will appear.
     * @param SID Shelf number.
     * @param isButton Integer value of button (1 == shelf, 0 == path, 2 == start)
     */
    private void GenerateCell(GridPane tile, int SID, Integer isButton, int x, int y){
        // create button (shelf) or label (path) based on map.txt
        if (isButton == 1) {
            Button button = new Button(Integer.toString(SID));
            button.setPrefSize(35,30);
            button.setStyle("-fx-font-size:10; -fx-margin:0; -fx-background-color: #DEB887");
            tile.add(button, x, y);
            shelf.add(new Shelf(SID));
            shelfButtons.add(button);
            button.setOnAction(e -> GUI.display(shelf.get(SID).getShelfData(), SID));
        } else if (isButton == 0){
            javafx.scene.control.Label label = new javafx.scene.control.Label();
            label.setPrefSize(35,30);
            label.setStyle("-fx-margin:0");
            tile.add(label, x, y);
        }
        else{
            javafx.scene.control.Label label = new javafx.scene.control.Label(" Start");
            label.setPrefSize(35,30);
            label.setStyle("-fx-font-size:10px; -fx-font-weight: bold; -fx-margin:0; -fx-background-color: #D2B48C");
            label.setContentDisplay(ContentDisplay.CENTER);
            tile.getChildren().add(label);
        }
    }



}
