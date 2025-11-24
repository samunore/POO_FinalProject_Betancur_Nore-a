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
        while (true) {
            try {
                return Integer.parseInt(inputText(numInt));
            } catch (NumberFormatException e) {
                showText("Error: ingrese un número válido.");
            }
        }
    }

    @Override
    public double inputDouble(String numDouble) {
        while (true) {
            try {
                return Double.parseDouble(inputText(numDouble));
            } catch (NumberFormatException e) {
                showText("Error: ingrese un número válido.");
            }
        }
    }

    @Override
    public long inputLong(String numLong) {
        while (true) {
            try {
                return Long.parseLong(inputText(numLong));
            } catch (NumberFormatException e) {
                showText("Error: ingrese un número válido.");
            }
        }
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
        int k=0;
            for (Order order : orders) {
            System.out.println("ID: " + (order.getId()+k) + " | Precio total: $ " + order.getTotalPrice() + "\n");  
            k++;
        }
    }

    @Override
    public void showListCustomer(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("Nombre: " + customer.getName() + ", C.C: " + customer.getCC() + ", Celular: "
                    + customer.getNumber() + "\n");
        }
    }
}