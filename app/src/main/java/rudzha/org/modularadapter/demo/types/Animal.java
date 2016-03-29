package rudzha.org.modularadapter.demo.types;

public class Animal {
    public final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
