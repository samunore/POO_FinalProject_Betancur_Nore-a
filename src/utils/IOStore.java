package utils;
import java.util.ArrayList;
import domain.Customer;
import domain.Order;
import domain.VideoGame;

public interface IOStore {
    public abstract String inputText(String title);
    public abstract int inputInt(String numInt);
    public abstract double inputDouble(String numDouble);
    public abstract long inputLong(String numLong);
    public abstract void showText(String message);
    public abstract void showListVideoGames(ArrayList<VideoGame> videoGames);
    public abstract void showListOrder(ArrayList<Order> orders);
    public abstract void showListCustomer(ArrayList<Customer> customers);
}