package domain;
import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private Double total = 0.0;
    private ArrayList<VideoGame> videogames = new ArrayList<>();

    public Order(int id) {
    }

    public void addVideoGameOrder(VideoGame game) {
        for (VideoGame videoGame : videogames) {
            if (videoGame.getTitle().toLowerCase().equals(game.getTitle().toLowerCase())) {
                return;
            }
        }
        if (game != null) {
            videogames.add(game);
        }
    }

    /*
     * public void removeVideoGameOrder(int position) {
     * if (position >= 0 && position < videogames.size()) {
     * videogames.remove(position);
     * }
     * }
     */

    public double getTotalPrice() {
        total = 0.0;
        for (VideoGame game : videogames) {
            total += game.getPrice();
        }
        return total;
    }

    public int getVideoGameCount() {
        return videogames.size();
    }

    /*
     * public void clearOrder() {
     * videogames.clear();
     * }
     */

    public ArrayList<VideoGame> getVideoGames() {
        return videogames;
    }
}