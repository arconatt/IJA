package sample;

public class GoodsToRemove {
    private Integer shelfID;
    private String type;
    private Integer amount;
    private Integer x;
    private Integer y;

    public GoodsToRemove() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getShelfID() {
        return shelfID;
    }

    public void setShelfID(Integer id) {
        this.shelfID = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
