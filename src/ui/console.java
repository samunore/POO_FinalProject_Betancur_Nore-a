package ui;
import data.*;
import domain.*;
import utils.*;

public class Console {
    static IOStoreConsole scanner = new IOStoreConsole();
    private static Store store = new Store();
    private static Customer customer= new Customer();
    private static VideoGame videogame = new VideoGame();

    private static void showMenuPrimary(){
              scanner.showText("BIENVENIDO A LA TIENDA "+store.getName());
              scanner.showText("que desea hacer?");
              scanner.showText("(1) Sobre nosotros");
              scanner.showText("(2) Opciones de clientes");
              scanner.showText("(3) Opciones de videojuegos");
              scanner.showText("(4) Opciones de pedidos");
              scanner.showText("(5) Salir");
    }
    private static void showAboutUs(){
              scanner.showText("Buen dia, esta empresa se basa en el proyecto de programacion de Santiago Betancur y Samuel Noreña");
              scanner.showText("En esta tienda vamos a aplicar lo visto en la clase de Programacion Orientada a Objetos");
    }

    private static void showOptionsCustomer(){
        scanner.showText("OPCIONES DE CLIENTE");
        scanner.showText("(1) Buscar cliente por su Cedula de Ciudadania");
        scanner.showText("(2) Agregar cliente nuevo");
    }
    public static void main(String[] args) {
        store.setName("la cucha");
        boolean menu= true;
        int option;
        while(menu){
            showMenuPrimary();
            option=scanner.inputInt("");
            if(option==1){
                showAboutUs();
            }
            if(option==2){
                showOptionsCustomer();
            }
        }
      
    }
}
