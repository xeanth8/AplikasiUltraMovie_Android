package mobile.uas.kel_15.ultramovie.model;

public class Genre {
    private String id;
    private String name;

    public Genre() { }

    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
