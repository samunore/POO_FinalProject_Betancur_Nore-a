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
        JOptionPane.showMessageDialog(null, message);
    }

    StringBuilder sb = new StringBuilder();

    @Override
    public void showListVideoGames(ArrayList<VideoGame> videoGames) {
        for (VideoGame videoGame : videoGames) {
            sb.append("Titulo: ").append(videoGame.getTitle()).append(", Genero: ").append(videoGame.getGenre())
                    .append(", Precio: ").append(videoGame.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    @Override
    public void showListOrder(ArrayList<Order> orders) {
        for (Order order : orders) {
            sb.append("ID: ").append(order.getId()).append(", \n Precio total: ").append(order.getTotalPrice())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    @Override
    public void showListCustomer(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            sb.append("Nombre: ").append(customer.getName()).append("C.C: ").append(customer.getName()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}