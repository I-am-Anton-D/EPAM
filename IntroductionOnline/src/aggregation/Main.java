package aggregation;

import aggregation.automobile.Automobile;
import aggregation.automobile.Engine;
import aggregation.automobile.Wheel;
import aggregation.state.Area;
import aggregation.state.City;
import aggregation.state.Region;
import aggregation.state.State;
import aggregation.text.Sentence;
import aggregation.text.Text;

public class Main {

    public static void main(String[] args) {
        testText();
        testAutomobile();
        testState();
    }

    /**
     * Task 1. Work with text class
     */

    public static void testText() {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Work with text class");
        Text text = new Text("Header of text");
        text.addSentence(new Sentence("This is my new sentence."));
        text.addSentence(new Sentence("Hi! Pepsi, cola and beer, please."));
        text.addSentence(new Sentence("Bill, can you give me Windows?"));
        text.printHeader();
        text.printText();
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Automobile test
     */

    public static void testAutomobile() {
        System.out.println("[Task 2] Work with automobile class");
        Automobile automobile = new Automobile("BMW", new Engine(),
            new Wheel[]{new Wheel(), new Wheel(), new Wheel(), new Wheel()});
        automobile.printModel();
        automobile.move();
        automobile.changeWheel(2,new Wheel());
        automobile.refill();
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. State, regions, areas...
     */

    public static void testState() {
        State state = new State("Wonderful state", new City("New Minsk"));
        Area area1 = new Area("Area 1 Region 1",100, new City("Center area 1 region 1"));
        Area area2 = new Area("Area 2 Region 1",200, new City("Center area 2 region 1"));
        Area area3 = new Area("Area 3 Region 1",300, new City("Center area 3 region 1"));
        Region region1 = new Region("Region 1");
        region1.addArea(area1);
        region1.addArea(area2);
        region1.addArea(area3);

        Area area4 = new Area("Area 4 Region 2",10, new City("Center area 4 region 2"));
        Area area5 = new Area("Area 5 Region 2",20, new City("Center area 5 region 2"));
        Area area6 = new Area("Area 6 Region 2",30, new City("Center area 6 region 2"));
        Region region2 = new Region("Region 2");
        region2.addArea(area4);
        region2.addArea(area5);
        region2.addArea(area6);

        state.addRegion(region1);
        state.addRegion(region2);

        System.out.println("[Task 3] Work with state class");
        state.printCapital();
        state.printCountOfRegions();
        state.printSquare();
        state.printCentersOfAreas();
        System.out.println("-----------------------------------------------");

    }
}
