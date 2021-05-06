package sample;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import java.util.*;

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
    public Integer yTarget = -1;

    private final Button cart1 = new Button();

    public Cart(Integer home_x, Integer home_y, GridPane tile, Timeline timeline) {
        this.tile = tile;
        this.home.put('x', home_x);
        this.home.put('y', home_y);
        this.currCoord.put('x', home_x);
        this.currCoord.put('y', home_y);
        this.targetCoords = new ArrayList<>();
        showCart(home_x, home_y);
        this.cart1.setStyle("-fx-background-image: url('./img/cart.png'); -fx-background-repeat: no-repeat; -fx-background-position: center; -fx-border-width: 0; -fx-background-color: transparent;");
        this.cart1.setPrefSize(35,30);
        cart1.setOnAction(e -> {
            timeline.pause();
            GUI.displayCart(this.goodsInCart, timeline);
        });
    }

    public void loadItems(String type, Integer amount) {
        if (goodsInCart.containsKey(type)) {
            Integer mapAmount = goodsInCart.get(type);
            mapAmount += amount;
            goodsInCart.put(type, mapAmount);
        } else {
            goodsInCart.put(type, amount);
        }
    }

    public void unloadItems() {
        return;
    }

    public Integer getRequest(Algorithm alg) {
        ArrayList<GoodsToRemove> target;
        if ((target = alg.getTargets()) == null) {
            this.goHome = true;
            return 0;
        }
        if (target.size() == 0) {
            // item not found
            return -1;
        }
        this.targetCoords = new ArrayList<>();
        Iterator<GoodsToRemove> iterator = target.iterator();
        while (iterator.hasNext()){
            this.targetCoords.add((GoodsToRemove) iterator.next());
        }
        return 0;
    }

    public Integer moveLeft(){
        if (tile.getChildren().get(currCoord.get('y') * this.mapWidth + (currCoord.get('x') - 1)) instanceof Button) {
            return -1;
        }
        coords.add(currCoord);
        removeCartView();
        currCoord.put('x', currCoord.get('x') - 1);
        currCoord.put('y', currCoord.get('y'));
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    public Integer moveRight() {
        if (tile.getChildren().get(currCoord.get('y') * this.mapWidth + (currCoord.get('x') + 1)) instanceof Button) {
            return -1;
        }
        coords.add(currCoord);
        removeCartView();
        currCoord.put('x', currCoord.get('x') + 1);
        currCoord.put('y', currCoord.get('y'));
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    public Integer moveDown() {
        if (tile.getChildren().get((currCoord.get('y') + 1) * this.mapWidth + currCoord.get('x')) instanceof Button) {
            return -1;
        }
        coords.add(currCoord);
        removeCartView();
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y') + 1);
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    public Integer moveUp() {
        if (tile.getChildren().get((currCoord.get('y') - 1) * this.mapWidth + currCoord.get('x')) instanceof Button) {
            return -1;
        }
        coords.add(currCoord);
        removeCartView();
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y') - 1);
        showCart(currCoord.get('x'), currCoord.get('y'));
        return 0;
    }

    public void noMove() {
        currCoord.put('x', currCoord.get('x'));
        currCoord.put('y', currCoord.get('y'));
    }

    /**
     * Graphic representation of cart
     */
    public  void  showCart(int posX, int posY){
            tile.add(this.cart1, posX,posY);
    }

    public  void  removeCartView(){
        tile.getChildren().remove(this.cart1);
    }

}
