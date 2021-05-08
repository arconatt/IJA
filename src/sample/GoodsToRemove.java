/**
 * One batch of items to be dropped off.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */

package sample;

/**
 * One batch of items to be dropped off.
 */
public class GoodsToRemove {
    private Integer shelfID;
    private String type;
    private Integer amount;
    private Integer x;
    private Integer y;

    public GoodsToRemove() {
    }

    /**
     * X coordinate getter.
     * @return X coordinate.
     */
    public Integer getX() {
        return x;
    }

    /**
     * X coordinate setter.
     * @param x X coordinate.
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Y coordinate getter.
     * @return Y coordinate.
     */
    public Integer getY() {
        return y;
    }

    /**
     * Y coordinate setter.
     * @param y Y coordinate.
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Shelf ID getter.
     * @return Shelf ID.
     */
    public Integer getShelfID() {
        return shelfID;
    }

    /**
     * Shelf ID setter.
     * @param id Shelf ID.
     */
    public void setShelfID(Integer id) {
        this.shelfID = id;
    }

    /**
     * Goods type getter.
     * @return Goods type.
     */
    public String getType() {
        return type;
    }

    /**
     * Goods type setter.
     * @param type Goods type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Goods amount getter.
     * @return Goods amount.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Goods amount setter.
     * @param amount Goods amount.
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
