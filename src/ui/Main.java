package ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import data.StoreStorage;
import domain.Customer;
import domain.Order;
import domain.Store;
import domain.VideoGame;
import utils.CSVEncoder;
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

    private static final String FILE_NAME = "src/Storage/store.dat";

    private static void saveStore() {
        StoreStorage.save(store, FILE_NAME);
    }

    static IOStore inout;

    private static void showInformation() {
        int answer = 0;
        boolean valid = false;
        while (valid != true) {
            try {
                answer = Integer.parseInt(input.nextLine());
                if (answer < 0) {
                    System.out.println("Error: no se permiten números negativos.");
                } else if (answer != 1 && answer != 2) {
                    System.out.println("Opción inválida. Debe ser 1 o 2.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número válido.");
            }
        }
        switch (answer) {
            case 1 -> inout = new IOStoreConsole();
            case 2 -> inout = new IOStoreDialog();
        }
    }

    private static Store store = new Store();
    private static Customer customer;
    private static VideoGame videogame;
    private static Order order;

    private static int inputPositiveInt(int number) {
        while (number < 0) {
            inout.showText("Error: No se permiten números negativos.");
            number = inout.inputInt("Ingrese un número válido:");
        }
        return number;
    }

    private static Long inputPositiveLong(long number) {
        while (number < 0) {
            inout.showText("Error: No se permiten números negativos.");
            number = inout.inputLong("Ingrese un número válido:");
        }

        return number;
    }

    private static double inputPositiveDouble(double number) {
        while (number < 0) {
            inout.showText("Error: No se permiten valores negativos.");
            number = inout.inputDouble("Ingrese un número válido:");
        }
        return number;
    }

    static Scanner input = new Scanner(System.in);

    private static void showMenuPrimary() {
        inout.showText(
                "      MENU PRINCIPAL     \n    ¿Qué desea hacer?\n(1) Sobre nosotros.\n(2) Opciones de clientes.\n(3) Opciones de Videojuegos.\n(4) Opciones de pedidos.\n(5) Opciones de tienda.\n(6) Guardar archivos csv. \n(7) Salir.");
    }

    private static void showAboutUs() {
        inout.showText(
                "Buen día, esta empresa se basa en el proyecto de programación de Santiago Betancur y Samuel Noreña.\nEn esta tienda aplicamos lo visto en la clase de Programación Orientada a Objetos.\n");
    }

    private static void showOptionsCustomer() {
        inout.showText(
                "        OPCIONES DE CLIENTE     \n(1) Buscar cliente por su Cédula de Ciudadanía.\n(2) Buscar cliente por numero de celular.\n(3) Agregar cliente nuevo.\n(4) Volver al menu principal.");
    }

    private static void optionCustomer(int option) {
        switch (option) {
            case 1 -> {
                if (store.registeredCustomers().size() == 0) {
                    inout.showText("No hay clientes registrados.");
                } else {
                    Long customerFindCc = inputPositiveLong(inout.inputLong("Ingrese la cedula del cliente: "));
                    Customer customer = store.FindByCC(customerFindCc);
                    if (customer != null) {
                        ArrayList<Customer> result = new ArrayList<>();
                        result.add(customer);
                        inout.showListCustomer(result);
                    } else {
                        inout.showText("La cedula " + customerFindCc + " no se encontro.");
                    }
                }
            }
            case 2 -> {
                if (store.registeredCustomers().size() == 0) {
                    inout.showText("No hay clientes registrados.");
                } else {
                    Long customerFindNumber = inputPositiveLong(inout.inputLong("Ingrese el numero de celular: "));
                    Customer customer = store.findNumber(customerFindNumber);
                    if (customer != null) {
                        ArrayList<Customer> result = new ArrayList<>();
                        result.add(customer);
                        inout.showListCustomer(result);
                    } else {
                        inout.showText("El numero " + customerFindNumber + " no se encontro.");
                    }
                }
            }
            case 3 -> {
                customer = new Customer();
                customer.setName(inout.inputText("Ingrese el nombre:"));
                customer.setCC(inputPositiveLong(inout.inputLong("Ingrese la cedula:")));
                customer.setNumber(inputPositiveLong(inout.inputLong("Ingrese el numero de celular:")));
                store.addCustomer(customer);
                inout.showText("Cliente registrado exitosamente.");
            }
            case 4 -> option = 4;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void showOptionsVideoGames() {
        inout.showText(
                "        OPCIONES DE VIDEOJUEGOS     \n(1) Agregar Videojuego.\n(2) Volver al menu principal.");
    }

    private static void optionVideoGames(int option) {
        switch (option) {
            case 1 -> {
                int times = inputPositiveInt(inout.inputInt("Cuantos Videojuegos desea ingresar:"));
                for (int i = 0; i < times; i++) {
                    inout.showText("     Videojuego " + (i + 1));
                    videogame = new VideoGame();
                    videogame.setTitle(inout.inputText("Ingrese el titulo del Videojuego:"));
                    boolean exists = false;
                    for (VideoGame game : store.availableVideoGames()) {
                        if (game.getTitle().toLowerCase().equals(videogame.getTitle().toLowerCase())) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists != false) {
                        inout.showText("El Videojuego que desea ingresar ya esta en la tienda.");
                    } else {
                        videogame.setGenre(inout.inputText("Ingrese el genero del Videojuego:"));
                        videogame.setPrice(inputPositiveDouble(inout.inputDouble("Ingrese el valor del Videojuego:")));
                        store.addVideoGameStore(videogame);
                        inout.showText("Videojuego agregado exitosamente.");
                    }
                }
            }
            case 2 -> option = 2;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void showOptionOrderCustomer() {
        inout.showText("Atencion: Para continuar debe estar en registrado en la tienda.");
        inout.showText(
                "(1) Buscar cliente por su Cédula de Ciudadanía.\n(2) Buscar cliente por numero de celular.\n(3) Volver al menu principal.");
    }

    private static void showOptionsOrder() {
        inout.showText(
                "        OPCIONES DE PEDIDOS     \n(1) Hacer pedido.\n(2) Ver ordernes del cliente.\n(3) Volver al menu anterior.");
    }

    private static void optionOrderCustomer(int option) {
        switch (option) {
            case 1 -> {
                if (store.registeredCustomers().size() == 0) {
                    inout.showText("No hay clientes registrados.");
                } else {
                    Long customerFindCc = inputPositiveLong(inout.inputLong("Ingrese la cedula del cliente: "));
                    Customer customer = store.FindByCC(customerFindCc);
                    if (customer != null) {
                        if (order == null) {
                            order = new Order(store.getIdOrder(), customer);
                        }
                        optionsOrder(customer);
                    } else {
                        inout.showText("La cedula " + customerFindCc + " no se encontro.");
                    }
                }
            }
            case 2 -> {
                if (store.registeredCustomers().size() == 0) {
                    inout.showText("No hay clientes registrados.");
                } else {
                    Long customerFindNumber = inputPositiveLong(inout.inputLong("Ingrese el numero de celular: "));
                    Customer customer = store.findNumber(customerFindNumber);
                    if (customer != null) {
                        if (order == null) {
                            order = new Order(store.getIdOrder(), customer);
                        }
                        optionsOrder(customer);
                    } else {
                        inout.showText("El numero " + customerFindNumber + " no se encontro.");
                    }
                }
            }
            case 3 -> option = 3;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void optionsOrder(Customer customer) {
        inout.showText("Bienvenido " + customer.getName());
        int answerOption;
        do {
            if (order == null) {
                order = new Order(store.getIdOrder(), customer);
            }
            showOptionsOrder();
            answerOption = inout.inputInt("Seleccione una opción:");
            switch (answerOption) {
                case 1 -> {
                    int options = 0;
                    do {

                        inout.showText(
                                "(1) Ver Videojuegos disponibles.\n(2) Buscar por titulo.\n(3) Buscar por genero.\n(4) Borrar Videojuego de la orden.\n(5) Detalles de la orden \n(6) Finalizar compra.\n(7) Salir.");
                        int answerOrder = inout.inputInt("Seleccione una opción:");
                        switch (answerOrder) {
                            case 1 -> {
                                if (store.availableVideoGames().size() == 0) {
                                    inout.showText("Por el momento no tenemos ningun Videojuego diponible.");
                                } else {
                                    ArrayList<VideoGame> result = store.availableVideoGames();
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < result.size(); i++) {
                                        VideoGame game = result.get(i);
                                        sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                                .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                                .append(game.getPrice()).append("\n");
                                    }
                                    sb.append((result.size() + 1) + ". Para salir.");
                                    inout.showText(sb.toString());
                                    int choice = inout.inputInt("Seleccione un videojuego por número:");
                                    if (choice < 1 || choice > (result.size() + 1)) {
                                        inout.showText("Opción inválida.");
                                    } else if (choice == (result.size() + 1)) {
                                        break;
                                    } else {
                                        VideoGame selected = result.get(choice - 1);
                                        boolean exists = false;
                                        for (VideoGame vg : order.getVideoGames()) {
                                            if (vg.getTitle().equalsIgnoreCase(selected.getTitle())) {
                                                exists = true;
                                                break;
                                            }
                                        }
                                        if (exists == true) {
                                            inout.showText("El Videojuego ya está en la lista");
                                        } else {
                                            order.addVideoGameOrder(selected);
                                            inout.showText("Videojuego agregado a la orden: " + selected.getTitle());
                                        }
                                    }
                                }
                            }
                            case 2 -> {
                                if (store.availableVideoGames().size() == 0) {
                                    inout.showText("Por el momento no tenemos ningun Videojuego diponible.");
                                } else {
                                    ArrayList<VideoGame> result = store
                                            .searchTitle(inout.inputText("Ingrese el titulo del Videojuego:"));
                                    if (result.size() == 0) {
                                        inout.showText("No se encontraron coinsidencias");
                                    } else {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Resultados encontrados:").append("\n");
                                        for (int i = 0; i < result.size(); i++) {
                                            VideoGame game = result.get(i);
                                            sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                                    .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                                    .append(game.getPrice()).append("\n");
                                        }
                                        sb.append((result.size() + 1) + ". Para salir.");
                                        inout.showText(sb.toString());
                                        int choice = inout.inputInt("Seleccione un videojuego por número:");
                                        if (choice < 1 || (choice > result.size() + 1)) {
                                            inout.showText("Opción inválida.");
                                        } else if (choice == (result.size() + 1)) {
                                            break;
                                        } else {
                                            VideoGame selected = result.get(choice - 1);
                                            boolean exists = false;
                                            for (VideoGame vg : order.getVideoGames()) {
                                                if (vg.getTitle().equalsIgnoreCase(selected.getTitle())) {
                                                    exists = true;
                                                    break;
                                                }
                                            }
                                            if (exists == true) {
                                                inout.showText("El Videojuego ya está en la lista");
                                            } else {
                                                order.addVideoGameOrder(selected);
                                                inout.showText(
                                                        "Videojuego agregado a la orden: " + selected.getTitle());
                                            }
                                        }
                                    }
                                }
                            }
                            case 3 -> {
                                if (store.availableVideoGames().size() == 0) {
                                    inout.showText("Por el momento no tenemos ningun Videojuego diponible.");
                                } else {
                                    ArrayList<VideoGame> result = store
                                            .searchGenre(inout.inputText("Ingrese el genero del Videojuego: "));
                                    if (result.size() == 0) {
                                        inout.showText("No se encontraron coinsidencias");
                                    } else {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Resultados encontrados:").append("\n");
                                        for (int i = 0; i < result.size(); i++) {
                                            VideoGame game = result.get(i);
                                            sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                                    .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                                    .append(game.getPrice()).append("\n");
                                        }
                                        sb.append((result.size() + 1) + ". Para salir.");
                                        inout.showText(sb.toString());
                                        int choice = inout.inputInt("Seleccione el videojuego por su número:");
                                        if (choice < 1 || choice > (result.size() + 1)) {
                                            inout.showText("Opción inválida.");
                                        } else if (choice == (result.size() + 1)) {
                                            break;
                                        } else {
                                            VideoGame selected = result.get(choice - 1);
                                            boolean exists = false;
                                            for (VideoGame vg : order.getVideoGames()) {
                                                if (vg.getTitle().equalsIgnoreCase(selected.getTitle())) {
                                                    exists = true;
                                                    break;
                                                }
                                            }
                                            if (exists == true) {
                                                inout.showText("El Videojuego ya está en la lista");
                                            } else {
                                                order.addVideoGameOrder(selected);
                                                inout.showText(
                                                        "Videojuego agregado a la orden: " + selected.getTitle());
                                            }
                                        }
                                    }
                                }
                            }
                            case 4 -> {
                                if (order.getVideoGames().size() == 0) {
                                    inout.showText("Su orden esta vacia.");
                                } else {
                                    if (order.getVideoGames().size() == 0) {
                                        inout.showText("Su orden esta vacia.");
                                    } else {
                                        ArrayList<VideoGame> result = order.getVideoGames();
                                        StringBuilder sb = new StringBuilder();
                                        for (int i = 0; i < result.size(); i++) {
                                            VideoGame game = result.get(i);
                                            sb.append(i + 1).append(". ").append("Titulo: ").append(game.getTitle())
                                                    .append(", Genero: ").append(game.getGenre()).append(", Precio: $")
                                                    .append(game.getPrice()).append("\n");
                                        }
                                        sb.append((result.size() + 1) + ". Para cancelar.");
                                        inout.showText(sb.toString());
                                        int choice = inout
                                                .inputInt("Seleccione el videojuego que desea eliminar de la orden:");
                                        if (choice < 1 || (choice > result.size() + 1)) {
                                            inout.showText("Opción inválida.");
                                        } else if (choice == (result.size() + 1)) {
                                            break;
                                        } else {
                                            order.removeVideoGameOrder(choice - 1);
                                            inout.showText("Videojuego eliminado exitosamente.");
                                        }
                                    }
                                }
                            }
                            case 5 -> {
                                if (order.getVideoGameCount() == 0) {
                                    inout.showText("La order actual esta vacia.");
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("Videojuegos actuales:\n");
                                    for (VideoGame videoGame : order.getVideoGames()) {
                                        sb.append("Titulo: ").append(videoGame.getTitle()).append(", Genero: ")
                                                .append(videoGame.getGenre())
                                                .append(", Precio: $ ").append(videoGame.getPrice()).append("\n");
                                    }
                                    sb.append("Para un total de ").append(order.getVideoGameCount())
                                            .append(" Videojuegos.\n");
                                    sb.append("Precio total $ ").append(order.getTotalPrice());
                                    inout.showText(sb.toString());
                                }
                            }
                            case 6 -> {
                                if (order.getVideoGameCount() == 0) {
                                    inout.showText(
                                            "Su orden esta vacia.\n(1) Para agregar Videojuegos.\n(2) Cancelar compra.");
                                    int answerOptionExit = inout.inputInt("Seleccione una opción:");
                                    switch (answerOptionExit) {
                                        case 1 -> {
                                            break;
                                        }
                                        case 2 -> {
                                            inout.showText("Compra cancelada.");
                                            order = null;
                                            options = 7;
                                            answerOption = 2;
                                        }
                                        default -> inout.showText("Opción no válida");
                                    }
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    store.addOrder(order);
                                    customer.addOrder(order);
                                    sb.append("Su compra ha sido exitosa.\n");
                                    sb.append("Orden # ").append(order.getId()).append("\n");
                                    sb.append("VideoJuegos Comprados:\n");
                                    for (VideoGame videoGame : order.getVideoGames()) {
                                        sb.append("Titulo: ").append(videoGame.getTitle()).append(", Genero: ")
                                                .append(videoGame.getGenre())
                                                .append(", Precio: $ ").append(videoGame.getPrice()).append("\n");
                                    }
                                    sb.append("Valor total: $ ").append(order.getTotalPrice());
                                    inout.showText(sb.toString());
                                    options = 7;
                                }
                            }
                            case 7 -> {
                                inout.showText("Compra cancelada.");
                                order = null;
                                options = 7;
                            }
                            default -> inout.showText("Opción no válida");
                        }
                    } while (options != 7);
                }
                case 2 -> {
                    if (customer.getOrdersCustomer().size() == 0) {
                        inout.showText(customer.getName() + " no tiene ordenes finalizadas.");
                    } else if (customer.getOrdersCustomer().size() == 1) {
                        inout.showText(customer.getName() + " tiene: " + customer.getOrdersCustomer().size()
                                + " orden registrada a su nombre.");
                        inout.showListOrder(customer.getOrdersCustomer());
                    } else {
                        inout.showText(customer.getName() + " tiene: " + customer.getOrdersCustomer().size()
                                + " ordenes registradas a su nombre.");
                        inout.showListOrder(customer.getOrdersCustomer());
                    }
                }
                case 3 -> answerOption = 3;
                default -> inout.showText("Opción no válida");
            }
        } while (answerOption != 3);
    }

    private static void showOptionStore() {
        inout.showText(
                "        OPCIONES DE TIENDA     \n(1) Nombre de la tienda.\n(2) Registro de ventas.\n(3) Informe de ingresos.\n(4) Ver Videojuegos disponibles.\n(5) Ver clientes registrados.\n(6) Ver ordenes de la tienda.\n(7) Salir.");
    }

    private static void optionStore(int option) {
        switch (option) {
            case 1 -> inout.showText("Nombre de la tienda: " + store.getName());
            case 2 -> {
                if (store.getSaleRecord() == 0) {
                    inout.showText("No hemos hecho ventas por lo cual nuestro registro de ventas es : "
                            + store.getSaleRecord());
                } else {
                    inout.showText("Registro de ventas de la tienda es: " + store.getSaleRecord());
                }
            }
            case 3 -> {
                if (store.getIncomeReport() == 0.0) {
                    inout.showText("No hemos hecho nuestra primera venta por lo que nuestros ingresos son: $ "
                            + store.getIncomeReport());
                } else {
                    inout.showText("Informe de ingresos de la tienda es: $ " + store.getIncomeReport());
                }
            }
            case 4 -> {
                if (store.availableVideoGames().size() == 0) {
                    inout.showText("Actualmente no tenemos Videojuegos en nuestra tienda.");
                } else if (store.availableVideoGames().size() == 1) {
                    inout.showText("Videojuego disponible Actualmente:");
                    inout.showListVideoGames(store.availableVideoGames());
                } else {
                    inout.showText("Videojuegos disponibles Actualmente:");
                    inout.showListVideoGames(store.availableVideoGames());
                }
            }
            case 5 -> {
                if (store.registeredCustomers().size() == 0) {
                    inout.showText("Actualmente no tenemos clientes registrados.");
                } else if (store.registeredCustomers().size() == 1) {
                    inout.showText("Cliente Registrado:");
                    inout.showListCustomer(store.registeredCustomers());
                } else {
                    inout.showText("Clientes Registrados:");
                    inout.showListCustomer(store.registeredCustomers());
                }
            }
            case 6 -> {
                if (store.ordersStore().size() == 0) {
                    inout.showText("Actualmente no hemos hecho ventas.");
                } else if (store.ordersStore().size() == 1) {
                    inout.showText("Orden de la tienda:");
                    inout.showListOrder(store.ordersStore());
                } else {
                    inout.showText("Ordenes de la tienda:");
                    inout.showListOrder(store.ordersStore());
                }
            }
            case 7 -> option = 7;
            default -> inout.showText("Opción no válida");
        }
    }

    private static void showOptionCSV() {
        inout.showText(
                "        OPCIONES DE GUARDAR EN CSV     \n(1) Guardar Videojuegos, Clientes y Ordenes.\n(2) Guardar Video Juegos.\n(3) Guardar clientes.\n(4) Guardar ordenes.\n(5) Salir.");
    }

    private static void optionCsv(int option) {
        switch (option) {
            case 1 -> {
                exportVideoGamesCsv();
                exportCustomerCsv();
                exportOrderCsv();
            }
            case 2 -> exportVideoGamesCsv();
            case 3 -> exportCustomerCsv();
            case 4 -> exportOrderCsv();
            case 5 -> option = 7;
            default -> inout.showText("Opción no válida");
        }
    }

    private static CSVEncoder<VideoGame> createVideoGamesEncoder() {
        return new CSVEncoder<VideoGame>() {
            @Override
            public String[] getValues(VideoGame game) {
                return new String[] {
                        game.getTitle(),
                        game.getGenre(),
                        String.valueOf(game.getPrice())
                };
            }

            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "Titulo",
                        "Genero",
                        "Precio"
                };
            }

            @Override
            public String getListName() {
                return "Video Games";
            };
        };
    }

    private static void exportVideoGamesCsv() {
        CSVEncoder<VideoGame> encoder = createVideoGamesEncoder();
        List<VideoGame> games = store.availableVideoGames();
        String csv = encoder.encode(games);
        String filePath = "src/data/VideoGames.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(csv);
            inout.showText("Archivo CSV guardado en: " + filePath);
        } catch (IOException e) {
            inout.showText("Error al guardar CSV: " + e.getMessage());
        }
    }

    private static CSVEncoder<Customer> createCustomeEncoder() {
        return new CSVEncoder<Customer>() {
            @Override
            public String[] getValues(Customer customers) {
                return new String[] {
                        customers.getName(),
                        String.valueOf(customers.getCC()),
                        String.valueOf(customers.getNumber())
                };
            }

            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "Nombre",
                        "Cedula",
                        "Celular"
                };
            }

            @Override
            public String getListName() {
                return "Clientes";
            };
        };
    }

    private static void exportCustomerCsv() {
        CSVEncoder<Customer> encoder = createCustomeEncoder();
        List<Customer> customers = store.registeredCustomers();
        String csv = encoder.encode(customers);
        String filePath = "src/data/Customers.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(csv);
            inout.showText("Archivo CSV guardado en: " + filePath);
        } catch (IOException e) {
            inout.showText("Error al guardar CSV: " + e.getMessage());
        }
    }

    private static CSVEncoder<Order> createOrdersEncoder() {
        return new CSVEncoder<Order>() {
            @Override
            public String[] getValues(Order orders) {
                return new String[] {
                        String.valueOf(orders.getId()),
                        String.valueOf(orders.getTotalPrice()),
                        String.valueOf(orders.getVideoGameCount())
                };
            }

            @Override
            public String[] getFieldNames() {
                return new String[] {
                        "ID",
                        "Costo",
                        "Cantidad de Video Juegos"
                };
            }

            @Override
            public String getListName() {
                return "Ordenes";
            };
        };
    }

    private static void exportOrderCsv() {
        CSVEncoder<Order> encoder = createOrdersEncoder();
        List<Order> orders = store.ordersStore();
        String csv = encoder.encode(orders);
        String filePath = "src/data/Order.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(csv);
            inout.showText("Archivo CSV guardado en: " + filePath);
        } catch (IOException e) {
            inout.showText("Error al guardar CSV: " + e.getMessage());
        }
    }

    private static void optionMenuPrimary(int option) {
        switch (option) {
            case 1 -> showAboutUs();
            case 2 -> {
                int answerCustomer;
                do {
                    showOptionsCustomer();
                    answerCustomer = inout.inputInt("Seleccione una opción:");
                    optionCustomer(answerCustomer);
                } while (answerCustomer != 4);
            }
            case 3 -> {
                int answerVideoGames;
                do {
                    showOptionsVideoGames();
                    answerVideoGames = inout.inputInt("Seleccione una opción:");
                    optionVideoGames(answerVideoGames);
                } while (answerVideoGames != 2);
            }
            case 4 -> {
                int answerOrder;
                do {
                    showOptionOrderCustomer();
                    answerOrder = inout.inputInt("Seleccione una opción:");
                    optionOrderCustomer(answerOrder);
                } while (answerOrder != 3);
            }
            case 5 -> {
                int answerStore;
                do {
                    showOptionStore();
                    answerStore = inout.inputInt("Seleccione una opción:");
                    optionStore(answerStore);
                } while (answerStore != 7);
            }
            case 6 -> {
                int answerCsv;
                do {
                    showOptionCSV();
                    answerCsv = inout.inputInt("Seleccione una opción:");
                    optionCsv(answerCsv);
                } while (answerCsv != 5);
            }
            case 7 -> option = 7;
            default -> inout.showText("Opción invalida");
        }
    }

    public static void main(String[] args) {
        loadStore();
        store.setName("La Cucha");
        System.out.println(
                "Hola.\nPulse (1) si quiere que la información se muestre por consola. \nPulse (2) si quiere que la información se muestre por ventanas.");
        showInformation();
        inout.showText("BIENVENIDO A LA TIENDA " + store.getName());
        int menuPrimary;
        do {
            showMenuPrimary();
            menuPrimary = inout.inputInt("Seleccione una opción:");
            optionMenuPrimary(menuPrimary);
        } while (menuPrimary != 7);
        saveStore();
    }
}