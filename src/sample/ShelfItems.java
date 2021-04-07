package sample;

public class ShelfItems {
    /**
     *
     */
    private String type;
    private int amount;

    /**
     *
     * @param type
     * @param amount
     */
    public ShelfItems(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public String getShelfData() {
        return "Type: " + type + "\nAmount: " + amount + "\n\n";
    }
}
