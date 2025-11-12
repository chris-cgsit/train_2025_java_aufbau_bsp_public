package at.cgsit.train.java.filestream;

public class Buch {
    private final int id;
    private final String name;

    public Buch(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Buch{id=" + id + ", name='" + name + "'}";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
