package test.model;

public class Color {
    private final int id;
    private String colorNumber;
    private String colorName;

    public Color(int id, String colorNumber, String colorName) {
        this.id = id;
        this.colorNumber = colorNumber;
        this.colorName = colorName;
    }

    public int getId() {
        return id;
    }

    public String getColorNumber() {
        return colorNumber;
    }

    public String getColorName() {
        return colorName;
    }

}
