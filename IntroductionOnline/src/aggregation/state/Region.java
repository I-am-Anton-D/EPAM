package aggregation.state;

import java.util.ArrayList;

/**
 * Class for region
 */
public class Region {
    private ArrayList<Area> areas = new ArrayList<>();
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public int getSquare() {
        return areas.stream().mapToInt(Area::getSquare).sum();
    }

    public void addArea(Area area) {
        areas.add(area);
    }

    public String getName() {
        return name;
    }
}
