package utils;
import java.util.ArrayList;

public interface IOStore {
    public abstract String inputText(String title);
    public abstract int inputInt(String title);
    public abstract double inputDouble(String title);
    public abstract void showText(String message);
    public abstract void showList(ArrayList<?> list);
}