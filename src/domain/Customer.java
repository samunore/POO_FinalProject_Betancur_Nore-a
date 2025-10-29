package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String name = "";
    private int id = 0;
    private ArrayList<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}