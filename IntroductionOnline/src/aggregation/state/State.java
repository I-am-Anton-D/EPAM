package aggregation.state;

import java.util.ArrayList;

/**
 * Class for state
 */

public class State {
    private String name;
    private final City capital;
    private final ArrayList<Region> regions = new ArrayList<>();

    public State(String name, City capital) {
        this.capital = capital;
        this.name = name;
    }

    public void addRegion(Region region) {
        regions.add(region);
    }

    public void printCapital() {
        System.out.println("Capital of state: " + capital.name);
    }

    public void printCountOfRegions() {
        System.out.println("Count of regions = " + regions.size());
    }

    public void printSquare() {
        System.out.println("Square of state = " + regions.stream().mapToInt(Region::getSquare).sum());
    }

    public void printCentersOfAreas() {
        for (Region r: regions) {
            for (Area a: r.getAreas()) {
                System.out.println("Center of " + a.getName() + " is " + a.getCenter().getName());
            }
        }
    }

}
