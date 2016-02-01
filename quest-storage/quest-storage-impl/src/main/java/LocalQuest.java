import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by al on 30.12.2015.
 */
@ManagedBean
@ViewScoped
public class LocalQuest {

    private String name;
    private String genre;
    private double rating;
    private QuestDifficulty difficulty;


    public LocalQuest() {
    }

    public LocalQuest(String name, String genre, double rating, QuestDifficulty difficulty) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.difficulty = difficulty;
    }

    public QuestDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(QuestDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
