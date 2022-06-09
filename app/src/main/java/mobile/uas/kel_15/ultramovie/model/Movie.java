package mobile.uas.kel_15.ultramovie.model;

public class Movie {
    private String id;
    private String title;
    private String[] genres;
    private String[] writers;
    private String director;
    private String[] stars;
    private String synopsis;

    public Movie() {}

    public Movie(String id, String title, String[] genres, String[] writers, String director, String synopsis) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.writers = writers;
        this.director = director;
        this.synopsis = synopsis;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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

    public String getDirector() { return director; }

    public void setDirector(String director) { this.director = director; }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getStars() { return stars; }

    public void setStars(String[] stars) { this.stars = stars; }

    public String getSynopsis() { return synopsis; }

    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
}
