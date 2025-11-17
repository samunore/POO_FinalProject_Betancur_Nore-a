package utils;
import java.util.ArrayList;
import java.util.Scanner;
import domain.Customer;
import domain.Order;
import domain.VideoGame;

public class IOStoreConsole implements IOStore {

    private static Scanner input = new Scanner(System.in);

    @Override
    public String inputText(String title) {
        System.out.println(title);

        return input.nextLine();
    }

    @Override
    public int inputInt(String numInt) {

        return Integer.parseInt(inputText(numInt));
    }

    @Override
    public double inputDouble(String numDouble) {

        return Double.parseDouble(inputText(numDouble));
    }

    @Override
    public long inputLong(String numLong) {
        return Long.parseLong(inputText(numLong));
    }

    @Override
    public void showText(String message) {
        System.out.println(message);
    }

    @Override
    public void showListVideoGames(ArrayList<VideoGame> videoGames) {
        for (VideoGame videoGame : videoGames) {
            System.out.println("Titulo: " + videoGame.getTitle() + ", Genero: " + videoGame.getGenre() + ", Precio: $ "
                    + videoGame.getPrice() + "\n");
        }
    }

    @Override
    public void showListOrder(ArrayList<Order> orders) {
        for (Order order : orders) {
            System.out.println("ID: " + order.getId() + " | Precio total: $ " + order.getTotalPrice() + "\n");
        }
    }

    @Override
    public void showListCustomer(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("Nombre: " + customer.getName() + ", C.C: " + customer.getCC() + "\n");
        }
    }
}