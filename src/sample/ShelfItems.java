/**
 * Class for one batch of goods in one shelf.
 *
 * @author Natalia Markova, xmarko20
 * @author Tereza Burianova, xburia28
 * @version 1.0
 *
 */
package sample;

/**
 * Class for one batch of goods in one shelf.
 */
public class ShelfItems {

    private String type;
    private int amount;

    /**
     * Setter for type and amount of goods in one batch.
     *
     * @param type Type of goods.
     * @param amount Amount of goods.
     */
    public ShelfItems(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Prints the batch data that is later showed in the shelf info.
     *
     * @return Type and amount of goods.
     */
    public String getShelfData() {
        return "Type: " + type + "\nAmount: " + amount + "\n\n";
    }
}
