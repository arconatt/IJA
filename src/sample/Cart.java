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
    private ArrayList<Integer> coords = new ArrayList<>();
    public Integer currCoord;
    private GridPane tile;
    private Integer home;
    public Integer itemsAmount;

    public Cart(Integer coord, GridPane tile) {
        this.tile = tile;
        this.home = coord;
        moveCart(coord);
    }


    public void unloadItems() {
        if (this.itemsAmount != 0) {
            moveCart(1); //TODO: tam, kde je na mape 2
            this.itemsAmount = 0;
        }
        goHome();
    }

    public void goHome() {
        moveCart(this.home);
    }

    public void moveCart(Integer coord) {
        if (coord != null) {
            currCoord = coord;
        }
        this.coords.add(currCoord);

        showCart(1,0);
//        counter();
        showCart(2,0);
//        counter();
        showCart(3,0);
//        counter();
        showCart(4,0);
//        counter();
        showCart(5,0);
//        counter();
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
