package aggregation;

import aggregation.automobile.Automobile;
import aggregation.automobile.Engine;
import aggregation.automobile.Wheel;
import aggregation.bank.Account;
import aggregation.bank.Bank;
import aggregation.bank.Client;
import aggregation.state.Area;
import aggregation.state.City;
import aggregation.state.Region;
import aggregation.state.State;
import aggregation.text.Sentence;
import aggregation.text.Text;
import aggregation.travel.Trip;
import aggregation.travel.TripsHolder;
import aggregation.travel.TypeOfNutrition;
import aggregation.travel.TypeOfTransport;
import aggregation.travel.TypeOfTrip;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        testText();
        testAutomobile();
        testState();
        testBank();
        testTrips();
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
        automobile.changeWheel(2, new Wheel());
        automobile.refill();
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. State, regions, areas...
     */

    public static void testState() {
        State state = new State("Wonderful state", new City("New Minsk"));
        Area area1 = new Area("Area 1 Region 1", 100, new City("Center area 1 region 1"));
        Area area2 = new Area("Area 2 Region 1", 200, new City("Center area 2 region 1"));
        Area area3 = new Area("Area 3 Region 1", 300, new City("Center area 3 region 1"));
        Region region1 = new Region("Region 1");
        region1.addArea(area1);
        region1.addArea(area2);
        region1.addArea(area3);

        Area area4 = new Area("Area 4 Region 2", 10, new City("Center area 4 region 2"));
        Area area5 = new Area("Area 5 Region 2", 20, new City("Center area 5 region 2"));
        Area area6 = new Area("Area 6 Region 2", 30, new City("Center area 6 region 2"));
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

    /**
     * Task 4. Banks account
     */

    public static void testBank() {
        Bank bank = new Bank("Golden mountain bank");
        Client clientBill = new Client("Bill");
        clientBill.addAccount(new Account("Common", 100, false));
        clientBill.addAccount(new Account("My", 1000, false));
        clientBill.addAccount(new Account("For wife", -100, false));
        clientBill.addAccount(new Account("On new computer", 5, false));
        clientBill.addAccount(new Account("On beer", -200, false));
        bank.addClient(clientBill);

        Client clientVlad = new Client("Vlad");
        clientVlad.addAccount(new Account("Common", 2, false));
        clientVlad.addAccount(new Account("Credit", -100, true));
        bank.addClient(clientVlad);

        System.out.println("[Task 4] Work with banks account");
        Client abstractClient = bank.findClient("Bill");
        abstractClient.sortAntPrintAccounts();
        abstractClient.getAndPrintAllSum();
        abstractClient.getAndPrintPositiveSum();
        abstractClient.getAntPrintNegativeSum();

        abstractClient = bank.findClient("Vlad");
        abstractClient.sortAntPrintAccounts();
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Trips
     */

    public static void testTrips() {
        Random random = new Random();
        TripsHolder tripsHolder = new TripsHolder();
        for (int i = 0; i < 100; i++) {
            tripsHolder.addTrip(new Trip(3+random.nextInt(10),
                TypeOfNutrition.values()[random.nextInt(TypeOfNutrition.values().length)],
                TypeOfTransport.values()[random.nextInt(TypeOfTransport.values().length)],
                TypeOfTrip.values()[random.nextInt(TypeOfTrip.values().length)], "Trip" + i,
                random.nextInt(2000)));
        }
        System.out.println("[Task 5] Work with trips");

        ArrayList<TypeOfNutrition> nutritious = new ArrayList<>();
        nutritious.add(TypeOfNutrition.NONE);
        nutritious.add(TypeOfNutrition.BREAKFAST);

        ArrayList<TypeOfTransport> transports = new ArrayList<>();
        transports.add(TypeOfTransport.AIRPLANE);

        ArrayList<TypeOfTrip> typeOfTrips = new ArrayList<>();
        typeOfTrips.add(TypeOfTrip.RELAX);
        typeOfTrips.add(TypeOfTrip.HEALTH);
        System.out.println("List of trips after filter: ");
        tripsHolder.filterAndPrintTrips(nutritious,transports,typeOfTrips,2,10);
        System.out.println("-----------------------------------------------");
    }
}
