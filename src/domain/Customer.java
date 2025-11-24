package domain;
import java.io.Serializable;
//import java.util.ArrayList;

public class Customer implements Serializable {
    private String name = "";
    private long cc = 0;
    private long number=0;

    //private ArrayList<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCC() {
        return cc;
    }

    public void setCC(long cc) {
        this.cc = cc;
    }

    public void setNumber(long number){
        this.number=number;
    }

    public long getNumber(){
        return number;
    }

    /*public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }*/
}