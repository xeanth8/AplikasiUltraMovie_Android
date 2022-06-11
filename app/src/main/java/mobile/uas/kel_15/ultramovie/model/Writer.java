package mobile.uas.kel_15.ultramovie.model;

public class Writer {
    private String id;
    private String name;
    private String email;
    private String telepon;

    private String[] movies;

    public Writer() {    }

    public Writer(String id, String name, String email, String telepon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telepon = telepon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String[] getMovies() { return movies; }

    public void setMovies(String[] movies) { this.movies = movies; }

    @Override
    public String toString() {
        return name;
    }
}
