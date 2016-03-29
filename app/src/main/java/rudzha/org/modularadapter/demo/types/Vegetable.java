package rudzha.org.modularadapter.demo.types;

public class Vegetable {
    public final String name;

    public Vegetable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
