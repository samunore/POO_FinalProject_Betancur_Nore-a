package data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.Store;

public class StoreStorage {

    public static void save(Store store, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(store);
            System.out.println("Datos de la tienda guardados correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar los datos de la tienda: " + e.getMessage());
        }
    }

    public static Store load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Store) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos de la tienda: " + e.getMessage());
            return null;
        }
    }
}