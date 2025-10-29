package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {
    private String name = "";
    private int saleRecord = 0;
    private Double incomeReport = 0.0;
    private ArrayList<VideoGame> videoGames = new ArrayList<>();

    public String getName() {
        return name;
    }
    
    public int getSaleRecord() {
        return saleRecord;
    }

    public double getIncomeReport() {
        return incomeReport;
    }

    public void registerSale(double totalAmount) {
    saleRecord++;
    incomeReport += totalAmount;
    }
}
