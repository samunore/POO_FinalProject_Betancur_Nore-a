package utils;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import domain.Customer;
import domain.Order;
import domain.VideoGame;

public class IOStoreDialog implements IOStore {

    @Override
    public String inputText(String title) {
        return JOptionPane.showInputDialog(title);
    }

    @Override
    public int inputInt(String title) {
        return Integer.parseInt(inputText(title));
    }

    @Override
    public double inputDouble(String title) {
        return Double.parseDouble(inputText(title));
    }

    @Override
    public void showText(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void showListVideoGames(ArrayList<VideoGame> videoGames) {
        for (VideoGame videoGame : videoGames) {

        }
    }

    @Override
    public void showListOrder(ArrayList<Order> orders) {
        for (Order order : orders) {

        }
    }

    @Override
    public void showListCustomer(ArrayList<Customer> customers) {
        for (Customer customer : customers) {

        }
    }
}