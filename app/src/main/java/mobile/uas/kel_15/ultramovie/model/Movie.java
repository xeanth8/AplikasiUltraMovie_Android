package mobile.uas.kel_15.ultramovie.model;

public class Movie {
    private String title;
    private String[] genres;
    private String[] writers;

    public Movie(String title, String[] genres, String[] writers) {
        this.title = title;
        this.genres = genres;
        this.writers = writers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }
}
