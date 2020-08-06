package aggregation.state;

import java.util.ArrayList;

/**
 * Class for area
 */

public class Area {
    private String name;
    private int square;
    private City center;
    private ArrayList<City> cities = new ArrayList<>();

    public Area(String name, int square, City center) {
        this.square = square;
        this.center = center;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public City getCenter() {
        return center;
    }

    public void setCenter(City center) {
        this.center = center;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
