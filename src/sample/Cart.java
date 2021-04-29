package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Cart {
    private ArrayList<HashMap<Character, Integer>> coords = new ArrayList<>();
    public HashMap<Character, Integer> currCoord = new HashMap<>();
    private GridPane tile;
    private HashMap<Character, Integer> home = new HashMap<>();
    public Integer itemsAmount;

    public Cart(Integer home_x, Integer home_y, GridPane tile) {
        this.tile = tile;
        this.home.put('x', home_x);
        this.home.put('y', home_y);
        this.currCoord.put('x', home_x);
        this.currCoord.put('y', home_y);
        showCart(home_x, home_y);
    }

    public void unloadItems() {
        if (this.itemsAmount != 0) {
            //TODO
            showCart(0, 1);
            this.itemsAmount = 0;
        }
        goHome();
    }

    public void goHome() {
        // TODO
        showCart(home.get('x'), home.get('y'));
    }

    public void moveLeft() {
        coords.add(currCoord);
        currCoord.put('x', currCoord.get('x') - 1);
        currCoord.put('y', currCoord.get('y'));
        showCart(currCoord.get('x'), currCoord.get('y'));
    }

    public void moveRight() {
        coords.add(currCoord);
        currCoord.put('x', currCoord.get('x') + 1);
        currCoord.put('y', currCoord.get('y'));
        showCart(currCoord.get('x'), currCoord.get('y'));
    }

    public void moveDown() {
        coords.add(currCoord);
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y') + 1);
        showCart(currCoord.get('x'), currCoord.get('y'));
    }

    public void moveUp() {
        coords.add(currCoord);
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y') - 1);
        showCart(currCoord.get('x'), currCoord.get('y'));
    }

    public void noMove() {
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y'));
    }

    /**
     * Graphic representation of cart
     */
    public  void  showCart(int posX, int posY){
        try {
            FileInputStream input = new FileInputStream("./data/cart.png");
            Image cart_img = new Image(input);
            ImageView imageView = new ImageView(cart_img);
            Button cart1 = new Button("",imageView);
            cart1.setPrefSize(10,8);
            tile.add(cart1, posX,posY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


//    public  void showPath() {
//
//    }


//
//    public void finished() {
//        // vrati vozik, vymaze jeho obsah a cestu
//    }

}
