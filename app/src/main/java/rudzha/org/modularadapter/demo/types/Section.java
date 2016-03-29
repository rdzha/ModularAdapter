package rudzha.org.modularadapter.demo.types;

public class Section {
    public final String name;

    public Section(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
