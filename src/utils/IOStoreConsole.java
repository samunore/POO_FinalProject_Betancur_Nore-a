package utils;

import java.util.ArrayList;
import java.util.Scanner;

public class IOStoreConsole implements IOStore {

    private static Scanner input = new Scanner(System.in);

    @Override
    public String inputText(String title) {
        System.out.println(title);

        return input.nextLine();
    }

    @Override
    public int inputInt(String title) {

        return Integer.parseInt(inputText(title));
    }

    @Override
    public double inputDouble(String title) {

        return Double.parseDouble(inputText(title));
    }

    @Override
    public void showText(String message) {
        System.out.println(message);
    }

    @Override
    public void showList(ArrayList<?> list) {
        
    }
}