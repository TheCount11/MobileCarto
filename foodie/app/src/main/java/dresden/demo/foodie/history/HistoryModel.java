package dresden.demo.foodie.history;

public class HistoryModel {

    private String time;
    private String source;
    private String destination;
    private int id;

    // creating getter and setter methods
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public HistoryModel(String time, String source, String destination) {
        this.time = time;
        this.source = source;
        this.destination = destination;
    }

}

