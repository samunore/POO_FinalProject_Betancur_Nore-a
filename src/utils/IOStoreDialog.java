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
        JOptionPane.showMessageDialog(null, message);
    }

    StringBuilder sb = new StringBuilder();

    @Override
    public void showListVideoGames(ArrayList<VideoGame> videoGames) {
        for (VideoGame videoGame : videoGames) {
            sb.append("Titulo: ").append(videoGame.getTitle()).append(", Genero: ").append(videoGame.getGenre())
                    .append(", Precio: $ ").append(videoGame.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        sb.setLength(0);        
    }

    @Override
    public void showListOrder(ArrayList<Order> orders) {
        int k = 0;
        for (Order order : orders) {
            sb.append("ID: ").append((order.getId()+k)).append(", \n Precio total: $ ").append(order.getTotalPrice()+"\n")
                    .append("\n");
                k+=1;
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        sb.setLength(0);
    }

    @Override
    public void showListCustomer(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            sb.append("Nombre: ").append(customer.getName()).append("C.C: ").append(customer.getCC())
                    .append(", Celular: ").append(customer.getNumber()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        sb.setLength(0);
    }
}