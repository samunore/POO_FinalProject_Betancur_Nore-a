package utils;
import java.util.ArrayList;
import domain.Customer;
import domain.Order;
import domain.VideoGame;

public interface IOStore {
    public abstract String inputText(String title);

    public abstract int inputInt(String title);

    public abstract double inputDouble(String title);

    public abstract void showText(String message);

    public abstract void showListVideoGames(ArrayList<VideoGame> videoGames);

    public abstract void showListOrder(ArrayList<Order> orders);

    public abstract void showListCustomer(ArrayList<Customer> customers);
}