package rudzha.org.modularadapter.demo.types;

public class ArtStyle {
    public final String name;

    public ArtStyle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
