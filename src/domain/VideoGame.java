package domain;

import java.io.Serializable;

public class VideoGame implements Serializable {
    private String genre = "", title = "";
    private Double price = 0.0;
    private int amount = 0;

    public VideoGame() {
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public void decreaseAmount(int amount){
        if (this.amount>=amount) {
            this.amount-=amount;
        }
    }

    public int getAmount() {
        return amount;
    }
}