package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {
    private String name = "";
    private int saleRecord = 0;
    private Double incomeReport = 0.0;
    private int idOrder = 0;
    private ArrayList<VideoGame> videoGames = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public Store(){}

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getSaleRecord() {
        return saleRecord;
    }

    public double getIncomeReport() {
        return incomeReport;
    }

    public void addVideoGameStore(VideoGame game) {
        for (VideoGame videoGame : videoGames) {
            if (videoGame.getTitle().toLowerCase().equals(game.getTitle().toLowerCase())) {
                return;
            }
        }
        if (game != null) {
            videoGames.add(game);
        }
    }

    public ArrayList<VideoGame> availableVideoGames() {
        return videoGames;
    }

    public ArrayList<VideoGame> searchTitle(String title) {
        ArrayList<VideoGame> results = new ArrayList<>();
        for (VideoGame game : videoGames) {
            if (game.getTitle().toLowerCase().equals(title.toLowerCase())) {
                results.add(game);
            }
        }
        return results;
    }

    public ArrayList<VideoGame> searchGenre(String genre) {
        ArrayList<VideoGame> results = new ArrayList<>();
        for (VideoGame game : videoGames) {
            if (game.getGenre().toLowerCase().equals(genre.toLowerCase())) {
                results.add(game);
            }
        }
        return results;
    }

    public int getIdOrder() {
        ++idOrder;
        return idOrder;
    }

    public void addOrder(Order order) {
        if (order != null) {
            order.setId(getIdOrder());
            orders.add(order);
            saleRecord++;
            incomeReport += order.getTotalPrice();
        }
    }

    public void addCustomer(Customer customer) {
        for (Customer customer2 : customers) {
            if (customer2.getCC() == customer.getCC()) {
                return;
            }
        }
        if (customer != null) {
            customers.add(customer);
        }
    }

    public Customer FindByCC(long CC){
        for (Customer customer : customers) {
            if (CC==customer.getCC()) {
                return customer;
            }
        }
        return null;
    }
}