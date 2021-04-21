package test.Store;

import test.model.Color;

import java.util.List;

public interface Store {
    List<Color> getAll();

    Color getColorById(int id);

    String getValue(String colorId);
}
