package mobile.uas.kel_15.ultramovie.model;

public class Genre {
    private String id;
    private String name;
    private String[] movies;

    public Genre() { }

    public Genre(String id, String name, String[] movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String[] getMovies() {
        return movies;
    }

    public void setMovies(String[] movies) {
        this.movies = movies;
    }
}
