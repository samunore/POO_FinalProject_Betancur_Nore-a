package ui;

import java.util.Scanner;
import domain.Customer;
import domain.Order;
import domain.Store;
import domain.VideoGame;
import utils.IOStore;
import utils.IOStoreConsole;
import utils.IOStoreDialog;

public class Main {
    static boolean showWindows = false;

    static inout;
    private static void showInformation(){
        if (showWindows == true) {
            IOStore inout = new IOStoreDialog();
        }else{
            IOStore inout = new IOStoreConsole();
        }
    }

    private static Store store = new Store();
    private static Customer customer = new Customer();
    private static VideoGame videogame = new VideoGame();
    static Scanner input = new Scanner(System.in);

    private static void showMenuPrimary() {
        inout.showText("BIENVENIDO A LA TIENDA " + store.getName());
        scanner.showText("¿Qué desea hacer?");
        scanner.showText("(1) Sobre nosotros");
        scanner.showText("(2) Opciones de clientes");
        scanner.showText("(3) Opciones de videojuegos");
        scanner.showText("(4) Opciones de pedidos");
        scanner.showText("(5) Salir");
    }

    private static void showAboutUs() {
        scanner.showText(
                "Buen día, esta empresa se basa en el proyecto de programación de Santiago Betancur y Samuel Noreña.");
        scanner.showText("En esta tienda aplicamos lo visto en la clase de Programación Orientada a Objetos.");
    }

    private static void showOptionsCustomer() {
        scanner.showText("OPCIONES DE CLIENTE");
        scanner.showText("(1) Buscar cliente por su Cédula de Ciudadanía");
        scanner.showText("(2) Agregar cliente nuevo");
    }

    public static void main(String[] args) {
        store.setName("La Cucha");
        System.out.println(
                "Hola.\nPulse '1' si quiere que la información se muestre por consola. \nPulse '2' si quiere que la información se muestre por ventanas.");
        int answer = Integer.parseInt(input.nextLine());
        boolean menu = true;
        int option;
        while (menu) {
            showMenuPrimary();
            option = scanner.inputInt("");
            if (option == 1) {
                showAboutUs();
            } else if (option == 2) {
                showOptionsCustomer();
            } else if (option == 5) {
                menu = false;
                scanner.showText("Saliendo de la tienda...");
            }
        }
    }
}