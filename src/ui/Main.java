package ui;
import java.util.ArrayList;
import java.util.Scanner;
import data.StoreStorage;
import domain.Customer;
import domain.Order;
import domain.Store;
import domain.VideoGame;
import utils.IOStore;
import utils.IOStoreConsole;
import utils.IOStoreDialog;

public class Main {
    private static void loadStore() {
        var loaded = StoreStorage.load(FILE_NAME);
        if (loaded != null) {
            store = loaded;
        }
    }

    private static final String FILE_NAME = "store.dat";

    private static void saveStore() {
        StoreStorage.save(store, FILE_NAME);
    }

    static IOStore inout;

    private static void showInformation(int answer) {
        if (answer == 1) {
            inout = new IOStoreConsole();
        } else if (answer == 2) {
            inout = new IOStoreDialog();
        } else {
            System.out.println("Opcion invalida.");
        }
    }

    private static Store store = new Store();
    private static Customer customer = new Customer();
    private static VideoGame videogame = new VideoGame();
    private static Order order = new Order(store.getIdOrder());
    static Scanner input = new Scanner(System.in);

    private static void showMenuPrimary() {
        inout.showText(
                "      MENU PRINCIPAL\n    ¿Qué desea hacer?\n(1) Sobre nosotros.\n(2) Opciones de clientes.\n(3) Opciones de video juegos.\n(4) Opciones de pedidos.\n(5) Opciones de tienda.\n(6) Salir.");
    }

    private static void showAboutUs() {
        inout.showText(
                "Buen día, esta empresa se basa en el proyecto de programación de Santiago Betancur y Samuel Noreña.\nEn esta tienda aplicamos lo visto en la clase de Programación Orientada a Objetos.\n");
    }

    private static void showOptionsCustomer() {
        inout.showText(
                "         OPCIONES DE CLIENTE\n(1) Buscar cliente por su Cédula de Ciudadanía.\n(2) Agregar cliente nuevo.\n(3) Volver al menu principal.");
    }

    private static void optionCustomer(int option) {
        switch (option) {
            case 1 -> {
                store.FindByCC(inout.inputLong("Ingrese la cedula del cliente:"));
            }
            case 2 -> {
                customer.setName(inout.inputText("Ingrese el nombre:"));
                customer.setCC(inout.inputLong("Ingrese la cedula:"));
                customer.setNumber(inout.inputLong("Ingrese el numero de celular:"));
                store.addCustomer(customer);
            }
            case 3 -> option = 3;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void showOptionsVideoGames() {
        inout.showText("         OPCIONES DE VIDEO JUEGOS\n(1) Agregar video juego.\n(2) Volver al menu principal.");
    }

    private static void optionVideoGames(int option) {
        switch (option) {
            case 1 -> {
                int times = inout.inputInt("Cuantos VideoJuegos desea ingresar:");
                for (int i = 0; i < times; i++) {
                    videogame = new VideoGame();
                    videogame.setTitle(inout.inputText("Ingrese el titulo del Video Juego:"));
                    videogame.setGenre(inout.inputText("Ingrese el genero del Video Juego:"));
                    videogame.setPrice(inout.inputDouble("Ingrese el valor del Video Juego:"));
                    store.addVideoGameStore(videogame);
                }
            }
            case 2 -> option = 2;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void showOptionsOrder() {
        inout.showText("         OPCIONES DE PEDIDOS\n(1) Hacer pedido.\n(2) Volver al menu principal.");
    }

    private static void optionOrder(int option) {
        switch (option) {
            case 1 -> {
                int options = 0;
                do {
                    inout.showText(
                            "(1) Ver Video Juegos disponibles.\n(2) Buscar por titulo.\n(3) Buscar por genero.\n(4) Detalles de la orden \n(5) Finalizar compra.\n(6) Salir.");
                    int answerOrder = inout.inputInt("");
                    switch (answerOrder) {
                        case 1 -> {
                            ArrayList<VideoGame> result = store.availableVideoGames();
                            for (int i = 0; i < result.size(); i++) {
                                StringBuilder sb = new StringBuilder();
                                VideoGame game = result.get(i);
                                sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                        .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                        .append(game.getPrice());
                                inout.showText(sb.toString());
                            }
                            int choice = inout.inputInt("Seleccione un videojuego por número:");
                            if (choice < 1 || choice > result.size()) {
                                inout.showText("Opción inválida.");
                            } else {
                                VideoGame selected = result.get(choice - 1);
                                order.addVideoGameOrder(selected);
                                inout.showText("Videojuego agregado a la orden: " + selected.getTitle());
                            }
                        }
                        case 2 -> {
                            ArrayList<VideoGame> result = store
                                    .searchTitle(inout.inputText("Ingrese el titulo del Video juego:"));
                            if (result.size() == 0) {
                                inout.showText("No se encontraron coinsidencias");
                            } else {
                                inout.showText("Resultados encontrados:");
                                for (int i = 0; i < result.size(); i++) {
                                    StringBuilder sb = new StringBuilder();
                                    VideoGame game = result.get(i);
                                    sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                            .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                            .append(game.getPrice());
                                    inout.showText(sb.toString());
                                }
                                int choice = inout.inputInt("Seleccione un videojuego por número:");
                                if (choice < 1 || choice > result.size()) {
                                    inout.showText("Opción inválida.");
                                } else {
                                    VideoGame selected = result.get(choice - 1);
                                    order.addVideoGameOrder(selected);
                                    inout.showText("Videojuego agregado a la orden: " + selected.getTitle());
                                }
                            }
                        }
                        case 3 -> {
                            ArrayList<VideoGame> result = store
                                    .searchGenre(inout.inputText("Ingrese el genero del Video Juego: "));
                            StringBuilder sb = new StringBuilder();
                            if (result.size() == 0) {
                                inout.showText("No se encontraron coinsidencias");
                            } else {
                                for (int i = 0; i < result.size(); i++) {
                                    VideoGame game = result.get(i);
                                    sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                            .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                            .append(game.getPrice());
                                    inout.showText(sb.toString());
                                }
                                int choice = inout.inputInt("Seleccione el videojuego por su número:");
                                if (choice < 1 || choice > result.size()) {
                                    inout.showText("Opción inválida.");
                                } else {
                                    VideoGame selected = result.get(choice - 1);
                                    order.addVideoGameOrder(selected);
                                    inout.showText("Videojuego agregado a la orden: " + selected.getTitle());
                                }
                            }
                        }
                        case 4 -> {
                            inout.showListVideoGames(order.getVideoGames());
                            if (order.getVideoGameCount() == 0) {
                                inout.showText("La order actual esta vacia.");
                            } else {
                                inout.showText("Para un total de " + order.getVideoGameCount() + " Video Juegos.");
                                inout.showText("Precio total: $ " + order.getTotalPrice());
                            }
                        }
                        case 5 -> {
                            store.addOrder(order);
                            inout.showText("Orden # " + order.getId());
                            inout.showText("Video Juegos Comprados: ");
                            inout.showListVideoGames(order.getVideoGames());
                            inout.showText("Valor total: $ " + order.getTotalPrice());
                        }
                        case 6 -> options = 6;
                        default -> inout.showText("Opción no válida");
                    }
                } while (options != 6);
            }
            case 2 -> option = 2;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void showOptionStore() {
        inout.showText(
                "         OPCIONES DE TIENDA\n(1) Nombre de la tienda.\n(2) Registro de ventas.\n(3) Informe de ingresos.\n(4) Ver Video Juegos disponibles.\n(5) Ver clientes registrados.\n(6) Ver ordenes de la tienda.\n(7) Salir.");
    }

    private static void optionStore(int option) {
        switch (option) {
            case 1 -> inout.showText("Nombre de la tienda: " + store.getName());
            case 2 -> inout.showText("Registro de ventas de la tienda es: " + store.getSaleRecord());
            case 3 -> inout.showText("Informe de ingresos de la tienda es: " + store.getIncomeReport());
            case 4 -> {
                inout.showText("Video Juegos disponibles Actualmente:");
                inout.showListVideoGames(store.availableVideoGames());
            }
            case 5 -> {
                inout.showText("Clientes Registrados:");
                inout.showListCustomer(store.registeredCustomers());
            }
            case 6 -> {
                inout.showText("Order de la tienda:");
                inout.showListOrder(store.ordersStore());
            }
            case 7 -> option = 7;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void optionMenuPrimary(int option) {
        switch (option) {
            case 1 -> showAboutUs();
            case 2 -> {
                int answerCustomer;
                do {
                    showOptionsCustomer();
                    answerCustomer = inout.inputInt("");
                    optionCustomer(answerCustomer);
                } while (answerCustomer != 3);
            }
            case 3 -> {
                int answerVideoGames;
                do {
                    showOptionsVideoGames();
                    answerVideoGames = inout.inputInt("");
                    optionVideoGames(answerVideoGames);
                } while (answerVideoGames != 2);
            }
            case 4 -> {
                int answerOrder;
                do {
                    showOptionsOrder();
                    answerOrder = inout.inputInt("");
                    optionOrder(answerOrder);
                } while (answerOrder != 2);
            }
            case 5 -> {
                int answerStore;
                do {
                    showOptionStore();
                    answerStore = inout.inputInt("");
                    optionStore(answerStore);
                } while (answerStore != 7);
            }
            case 6 -> option = 6;
            default -> inout.showText("Opción no válida");
        }
    }

    public static void main(String[] args) {
        loadStore();
        store.setName("La Cucha");
        System.out.println(
                "Hola.\nPulse (1) si quiere que la información se muestre por consola. \nPulse (2) si quiere que la información se muestre por ventanas.");
        int answer = Integer.parseInt(input.nextLine());
        showInformation(answer);
        inout.showText("BIENVENIDO A LA TIENDA " + store.getName());
        int menuPrimary;
        do {
            showMenuPrimary();
            menuPrimary = inout.inputInt("");
            optionMenuPrimary(menuPrimary);
        } while (menuPrimary != 6);
        saveStore();
    }
}