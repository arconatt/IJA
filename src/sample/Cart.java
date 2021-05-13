/**
 * Logic for one cart.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */

package sample;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import java.util.*;

/**
 * Logic for one cart.
 */
public class Cart {
    private ArrayList<HashMap<Character, Integer>> coords = new ArrayList<>();
    public HashMap<Character, Integer> currCoord = new HashMap<>();
    private GridPane tile;
    public HashMap<Character, Integer> home = new HashMap<>();
    private final Integer mapWidth = 25;
    private HashMap<String, Integer> goodsInCart = new HashMap<>();
    public GoodsToRemove currentlyFetched = new GoodsToRemove();
    public ArrayList<GoodsToRemove> targetCoords;

    public boolean isDown = false;
    public boolean onTheWay = false;
    public boolean comingBack = false;
    public boolean isBack = false;
    public boolean goHome = false;
    public boolean isHome = false;
    public  boolean unloading = false;
    public Integer yTarget = -1;

    private final Button cart1 = new Button();

    /**
     * Cart constructor.
     * @param home_x X coordinate of cart's home.
     * @param home_y Y coordinate of cart's home.
     * @param tile GridPane map.
     * @param timeline Timeline animation.
     */
    public Cart(Integer home_x, Integer home_y, GridPane tile, Timeline timeline) {
        this.tile = tile;
        this.home.put('x', home_x);
        this.home.put('y', home_y);
        this.currCoord.put('x', home_x);
        this.currCoord.put('y', home_y);
        this.targetCoords = new ArrayList<>();
        showCart(home_x, home_y);
        this.cart1.setStyle("-fx-background-image: url('/img/cart.png'); -fx-background-repeat: no-repeat; -fx-background-position: center; -fx-border-width: 0; -fx-background-color: transparent;");
        this.cart1.setPrefSize(35,30);
        cart1.setOnAction(e -> {
            timeline.pause();
            this.showPath();
            GUI.displayCart(this.goodsInCart, timeline, this);
        });
    }

    /**
     * Loads items from a shelf into the cart.
     * @param type Type of goods.
     * @param amount Amount of goods.
     */
    public void loadItems(String type, Integer amount) {
        if (goodsInCart.containsKey(type)) {
            Integer mapAmount = goodsInCart.get(type);
            mapAmount += amount;
            goodsInCart.put(type, mapAmount);
        } else {
            goodsInCart.put(type, amount);
        }
    }

    /**
     * Shows the covered route of the cart.
     */
    public  void showPath(){
        for (int i = 0; i < this.coords.size(); i++){
            Integer x_path = this.coords.get(i).get('x');
            Integer y_path = this.coords.get(i).get('y');
            switch (getCartId()) {
                case 1:
                    tile.getChildren().get(x_path + this.mapWidth * y_path).setStyle("-fx-background-image: url('/img/red.png');");
                    break;
                case 2:
                    tile.getChildren().get(x_path + this.mapWidth * y_path).setStyle("-fx-background-image: url('/img/blue.png');");
                    break;
                case 3:
                    tile.getChildren().get(x_path + this.mapWidth * y_path).setStyle("-fx-background-image: url('/img/green.png');");
                    break;
                case 4:
                    tile.getChildren().get(x_path + this.mapWidth * y_path).setStyle("-fx-background-image: url('/img/purple.png');");
                    break;
                case 5:
                    tile.getChildren().get(x_path + this.mapWidth * y_path).setStyle("-fx-background-image: url('/img/yellow.png');");
                    break;
            }
        }
    }

    /**
     * Removes the covered route of the cart.
     */
    public  void deletePath(){
        for (int i = 0; i < this.coords.size(); i++){
            Integer x_path = this.coords.get(i).get('x');
            Integer y_path = this.coords.get(i).get('y');
            tile.getChildren().get(x_path + this.mapWidth * y_path).setStyle("-fx-background-image: none;");
        }
    }

    /**
     * Cart ID getter.
     * @return Cart ID.
     */
    public Integer getCartId() {
        return home.get('x');
    }

    /**
     * Unloads item on the goods drop off.
     * @return List of dropped off goods in text format.
     */
    public StringBuilder unloadItems() {
        StringBuilder text = new StringBuilder("");

        if (goodsInCart.size() == 0) {
            return text;
        }

        for (String type: goodsInCart.keySet()) {
            String amount = goodsInCart.get(type).toString();
            text.append(type).append(", ").append(amount).append("\n");
        }

        goodsInCart.clear();
        return text;
    }

    /**
     * Gets the next request for the cart.
     * @param alg Item searching algorithm.
     * @return -1 if no item was found, else 0.
     */
    public Integer getRequest(Algorithm alg) {
        ArrayList<GoodsToRemove> target;
        if ((target = alg.getTargets()) == null) {
            this.goHome = true;
            return 0;
        }
        if (target.size() == 0) {
            return -1;
        }
        this.targetCoords = new ArrayList<>();
        Iterator<GoodsToRemove> iterator = target.iterator();
        while (iterator.hasNext()){
            this.targetCoords.add((GoodsToRemove) iterator.next());
        }
        return 0;
    }

    /**
     * Moves the cart to the left.
     * @return 0 if successful.
     */
    public Integer moveLeft(){
        if (tile.getChildren().get(currCoord.get('y') * this.mapWidth + (currCoord.get('x') - 1)) instanceof Button) {
            return -1;
        }
        HashMap<Character, Integer> currCoordCopy = new HashMap<>();
        currCoordCopy.put('x', currCoord.get('x'));
        currCoordCopy.put('y', currCoord.get('y'));
        coords.add(currCoordCopy);
        removeCartView();
        currCoord.put('x', currCoord.get('x') - 1);
        currCoord.put('y', currCoord.get('y'));
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    /**
     * Moves the cart to the right.
     * @return 0 if successful.
     */
    public Integer moveRight() {
        if (tile.getChildren().get(currCoord.get('y') * this.mapWidth + (currCoord.get('x') + 1)) instanceof Button) {
            return -1;
        }
        HashMap<Character, Integer> currCoordCopy = new HashMap<>();
        currCoordCopy.put('x', currCoord.get('x'));
        currCoordCopy.put('y', currCoord.get('y'));
        coords.add(currCoordCopy);
        removeCartView();
        currCoord.put('x', currCoord.get('x') + 1);
        currCoord.put('y', currCoord.get('y'));
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    /**
     * Moves the cart to the bottom.
     * @return 0 if successful.
     */
    public Integer moveDown() {
        if (tile.getChildren().get((currCoord.get('y') + 1) * this.mapWidth + currCoord.get('x')) instanceof Button) {
            return -1;
        }
        HashMap<Character, Integer> currCoordCopy = new HashMap<>();
        currCoordCopy.put('x', currCoord.get('x'));
        currCoordCopy.put('y', currCoord.get('y'));
        coords.add(currCoordCopy);
        removeCartView();
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y') + 1);
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    /**
     * Moves the cart to the top.
     * @return 0 if successful.
     */
    public Integer moveUp() {
        if (tile.getChildren().get((currCoord.get('y') - 1) * this.mapWidth + currCoord.get('x')) instanceof Button) {
            return -1;
        }
        HashMap<Character, Integer> currCoordCopy = new HashMap<>();
        currCoordCopy.put('x', currCoord.get('x'));
        currCoordCopy.put('y', currCoord.get('y'));
        coords.add(currCoordCopy);
        removeCartView();
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y') - 1);
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    /**
     * Cart stays in one place.
     */
    public void noMove() {
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y'));
    }

    /**
     * Graphic representation of cart.
     * @param posX X coordinate.
     * @param posY Y coordinate.
     */
    public  void  showCart(int posX, int posY){
            tile.add(this.cart1, posX,posY);
    }

    /**
     * Remove graphic representation of cart.
     */
    public  void  removeCartView(){
        tile.getChildren().remove(this.cart1);
    }

}
