package dresden.demo.foodie.ui.order;

public class OrderModel {

    private String time;
    private String item;
    private String money;
    private String place;
    private int id;

    // creating getter and setter methods
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public OrderModel(int id, String time, String item, String money, String place) {
        this.id = id;
        this.time = time;
        this.item = item;
        this.money = money;
        this.place = place;
    }
}
