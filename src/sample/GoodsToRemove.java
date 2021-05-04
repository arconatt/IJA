package sample;

public class GoodsToRemove {
    private final Integer shelfID;
    private final String type;
    private final Integer amount;

    public GoodsToRemove(Integer shelfID, String type, Integer amount) {
        this.shelfID = shelfID;
        this.type = type;
        this.amount = amount;
    }

    public Integer getShelfID() {
        return shelfID;
    }

    public String getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }
}
