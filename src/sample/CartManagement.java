/**
 * Management of carts.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */

package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.*;

/**
 * Management of carts.
 */
public class CartManagement {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;
    ArrayList<HashMap<Integer, Button>> columns;
    public Integer activeCart = 0;
    private final Integer mapHeight = 13;
    private final Integer mapWidth = 25;
    public Integer time = 0;
    public GUI gui;
    public Timeline timeline;

    Algorithm alg;
    private ArrayList<Button> shelfButtons;
    private ArrayList<Shelf> shelves;
    private GridPane tile;
    public StringBuilder unloaded = new StringBuilder("");

    /**
     * Constructor of carts manager.
     * @param tile GridPane map.
     * @param itemsQueue Queue of requested items.
     * @param goodsShelf ArrayList of shelves.
     * @param buttonsShelf ArrayList of buttons corresponding to shelves.
     * @param closed List of closed paths.
     * @param gui GUI instance.
     */
    public CartManagement(GridPane tile, Queue<String> itemsQueue, ArrayList<Shelf> goodsShelf, ArrayList<Button> buttonsShelf, ArrayList<Integer> closed, GUI gui) {
        this.shelves = goodsShelf;
        this.shelfButtons = buttonsShelf;
        this.tile = tile;
        this.gui = gui;
        tl();
        cart1 = new Cart(1, 0, tile, timeline);
        cart2 = new Cart(2, 0, tile, timeline);
        cart3 = new Cart(3, 0, tile, timeline);
        cart4 = new Cart(4, 0, tile, timeline);
        cart5 = new Cart(5, 0, tile, timeline);
        fillPaths();
        this.alg = new Algorithm(tile, this.shelves, this.shelfButtons, this.columns, itemsQueue, timeline, closed);
    }

    /**
     * Fills the paths with corresponding shelves.
     */
    public void fillPaths() {
        HashMap<Integer, Button> column1 = new HashMap<>();
        HashMap<Integer, Button> column2 = new HashMap<>();
        HashMap<Integer, Button> column3 = new HashMap<>();
        HashMap<Integer, Button> column4 = new HashMap<>();
        HashMap<Integer, Button> column5 = new HashMap<>();
        HashMap<Integer, Button> column6 = new HashMap<>();
        HashMap<Integer, Button> column7 = new HashMap<>();
        HashMap<Integer, Button> column8 = new HashMap<>();
        HashMap<Integer, Button> column9 = new HashMap<>();
        for (int i = 1; i < 146 ; i += 16) {
            column1.put(i, this.shelfButtons.get(i));
        }
        for (int i = 16; i < 161 ; i += 16) {
            column9.put(i, this.shelfButtons.get(i));
        }
        fillColumns(column2, 2, 147);
        fillColumns(column3, 4, 149);
        fillColumns(column4, 6, 151);
        fillColumns(column5, 8, 153);
        fillColumns(column6, 10, 155);
        fillColumns(column7, 12, 157);
        fillColumns(column8, 14, 159);
        this.columns = new ArrayList<>();
        columns.add(column1);
        columns.add(column2);
        columns.add(column3);
        columns.add(column4);
        columns.add(column5);
        columns.add(column6);
        columns.add(column7);
        columns.add(column8);
        columns.add(column9);
    }

    /**
     * Fill one path.
     * @param column Current path.
     * @param start Start position.
     * @param end End position.
     */
    private void fillColumns(HashMap<Integer, Button> column, int start, int end) {
        for (int i = start; i < end; i += 16) {
            column.put(i, this.shelfButtons.get(i));
            column.put(i+1, this.shelfButtons.get(i+1));
        }
    }

    /**
     * Find out if the requested goods is in current path.
     * @param cart Current cart instance.
     */
    private void isReqInLine(Cart cart) {
        for (int i = 0; i < cart.targetCoords.size(); i++) {
            if (cart.targetCoords.get(i).getX().equals(cart.currCoord.get('x'))) {
                cart.yTarget = cart.targetCoords.get(i).getY();
                cart.currentlyFetched = cart.targetCoords.get(i);
                cart.targetCoords.remove(i);
                break;
            }
        }
    }

    /**
     * Timeline animation.
     */
    private void tl() {
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(gui.getFinalspeed()), e -> {
                    updateCarts();
                    gui.setActiveCarts(activeCart);
                    gui.setTime(time);
                })
        );
    }

    /**
     * Moves all carts by one.
     */
    public void updateCarts() {
        cartBehaviour(cart1);
        cartBehaviour(cart2);
        cartBehaviour(cart3);
        cartBehaviour(cart4);
        cartBehaviour(cart5);
        this.time++;
    }

    /**
     * Algorithm for cart movement and behaviour.
     * @param cart Current cart.
     */
    public void cartBehaviour(Cart cart) {
        Integer moveres = 0;

        if (cart.isHome) {
            return;
        }

        if (cart.unloading){
            cart.noMove();
            cart.unloading = false;
            cart.isBack = true;
            return;
        }

        if (cart.goHome) {
            this.activeCart--;
            cart.removeCartView();
            cart.showCart(cart.home.get('x'), cart.home.get('y'));
            cart.isHome = true;
            return;
        }

        // carts are starting
        if (cart.currCoord.get('y') == 0) {
            if (cart.currCoord.get('x').equals(cart.home.get('x'))) {
                Integer reqres = cart.getRequest(alg);
                while (reqres == -1) {
                    reqres = cart.getRequest(alg);
                }
                if (cart.goHome) {
                    cart.isHome = true;
                    return;
                }
            }
            if (cart.currCoord.get('x') == 1) {
                cart.moveDown();
                this.activeCart++;
            } else {
                cart.moveLeft();
            }
            return;
        }

        // check if carts should go to the bottom of the map
        if (cart.currCoord.get('x') == this.mapWidth - 1) {
            if (cart.onTheWay) {
                if (cart.currCoord.get('y') < cart.yTarget) {
                    cart.moveDown();
                } else if (cart.currCoord.get('y') > cart.yTarget) {
                    cart.moveUp();
                } else if (cart.currCoord.get('y').equals(cart.yTarget)) {
                    cart.noMove();
                    // remove from shelf
                    shelves.get(cart.currentlyFetched.getShelfID()).removeItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    // add to cart
                    cart.loadItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    cart.yTarget = -1;
                    cart.onTheWay = false;
                }
                return;
            }
            isReqInLine(cart);
            if (cart.yTarget != -1) {
                moveres = cart.moveDown();
                if (moveres == -1) return;
                cart.onTheWay = true;
                return;
            } else {
                if (cart.currCoord.get('y') != this.mapHeight - 1) {
                    cart.moveDown();
                } else {
                    moveres = cart.moveLeft();
                    if (moveres == -1) return;
                    cart.isDown = true;
                }
            }
            return;
        }

        // check if carts should go to the top of the map
        if (cart.currCoord.get('x') == 0 && !cart.isBack) {
            if (cart.onTheWay) {
                if (cart.currCoord.get('y') < cart.yTarget) {
                    cart.moveDown();
                } else if (cart.currCoord.get('y') > cart.yTarget) {
                    cart.moveUp();
                } else if (cart.currCoord.get('y').equals(cart.yTarget)) {
                    cart.noMove();
                    // remove from shelf
                    shelves.get(cart.currentlyFetched.getShelfID()).removeItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    // add to cart
                    cart.loadItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    cart.yTarget = -1;
                    cart.onTheWay = false;

                }
                return;
            }
            isReqInLine(cart);
            if (cart.yTarget != -1) {
                moveres = cart.moveUp();
                if (moveres == -1) return;
                cart.onTheWay = true;
                return;
            } else {
                if (cart.currCoord.get('y') != 1) {
                    cart.moveUp();
                    return;
                } else { // is on the start
                    cart.isDown = false;
                    unloaded.append(cart.unloadItems());
                    Integer reqres = cart.getRequest(alg);
                    while (reqres == -1) {
                        reqres = cart.getRequest(alg);
                    }
                    cart.unloading = true;
                    cart.noMove();
                    return;
                }
            }
        }

        // main part
        if (!cart.isDown) {
            if (cart.comingBack) {
                if (cart.currCoord.get('y') > 1) {
                    cart.moveUp();
                    return;
                } else {
                    cart.comingBack = false;
                    cart.isBack = true;
                }
            }
            if (!cart.onTheWay) {
                if ((tile.getChildren().get((cart.currCoord.get('y') + 1) * this.mapWidth + cart.currCoord.get('x')) instanceof Button) || cart.isBack || (cart.currCoord.get('x') == 0 && cart.currCoord.get('y') == 1)) {
                    moveres = cart.moveRight();
                    if (moveres == -1) return;
                    cart.isBack = false;
                } else {
                    cart.yTarget = -1;
                    isReqInLine(cart);
                    if (cart.yTarget != -1) {
                        cart.onTheWay = true;
                        cart.moveDown();
                    } else {
                        cart.moveRight();
                    }
                }
                return;
            } else { // onTheWay = true
                if (cart.currCoord.get('y') < cart.yTarget) {
                    cart.moveDown();
                } else if (cart.currCoord.get('y') > cart.yTarget) {
                    cart.moveUp();
                } else if (cart.currCoord.get('y').equals(cart.yTarget)) {
                    cart.noMove();
                    // remove from shelf
                    shelves.get(cart.currentlyFetched.getShelfID()).removeItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    // add to cart
                    cart.loadItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    cart.yTarget = -1;
                    isReqInLine(cart);
                    if (cart.yTarget != -1) {
                        return;
                    }
                    cart.onTheWay = false;
                    cart.comingBack = true;
                }
                return;
            }
        } else { // isDown == true;
            if (cart.comingBack) {
                if (cart.currCoord.get('y') < this.mapHeight - 1) {
                    cart.moveDown();
                    return;
                } else {
                    cart.comingBack = false;
                    cart.isBack = true;
                }
            }
            if (!cart.onTheWay) {
                if ((tile.getChildren().get((cart.currCoord.get('y') - 1) * this.mapWidth + cart.currCoord.get('x')) instanceof Button) || cart.isBack) {
                    moveres = cart.moveLeft();
                    if (moveres == -1) return;
                    cart.isBack = false;
                    return;
                } else {
                    cart.yTarget = -1;
                    isReqInLine(cart);
                    if (cart.yTarget != -1) {
                        cart.onTheWay = true;
                        cart.moveUp();
                        return;
                    } else {
                        cart.moveLeft();
                        return;
                    }
                }
            } else { // onTheWay = true
                if (cart.currCoord.get('y') > cart.yTarget) {
                    cart.moveUp();
                } else if (cart.currCoord.get('y') < cart.yTarget) {
                    cart.moveDown();
                }
                else if (cart.currCoord.get('y').equals(cart.yTarget)) {
                    cart.noMove();
                    // remove from shelf
                    shelves.get(cart.currentlyFetched.getShelfID()).removeItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    // add to cart
                    cart.loadItems(cart.currentlyFetched.getType(), cart.currentlyFetched.getAmount());
                    cart.yTarget = -1;
                    isReqInLine(cart);
                    if (cart.yTarget != -1) {
                        return;
                    }
                    cart.onTheWay = false;
                    cart.comingBack = true;

                }
                return;
            }
        }
    }

}
