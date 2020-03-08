package ro.fasttrackit.curs14.catcoder.privateContest.level5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.Location;
import ro.fasttrackit.curs14.catcoder.privateContest.level1.RouteCalculator;
import ro.fasttrackit.curs14.catcoder.privateContest.level3.Journey;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level5\\level5-3.txt"));

        int numberOfLocations = scanner.nextInt();
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < numberOfLocations; i++) {
            String name = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            locations.add(new Location(name, x, y));
        }

        printSeparator();
        System.out.println("locations = " + locations);
        printSeparator();

        scanner.nextLine();
        RouteCalculator routeCalculator = new RouteCalculator(locations);
        String journeyLine = scanner.nextLine();
        String[] journeyArr = journeyLine.split(" ");
        Location journeyStart = routeCalculator.getLocationByName(journeyArr[0]);
        Location journeyEnd = routeCalculator.getLocationByName(journeyArr[1]);

        Journey journey = new Journey(journeyStart, journeyEnd);

        System.out.println("journeyStart = " + journeyStart);
        printSeparator();
        System.out.println("journeyEnd = " + journeyEnd);
        printSeparator();

        List<Location> hyperLoopLocations = new ArrayList<>();

        int numberOfHyperLoopLocations = scanner.nextInt();
        System.out.println("loopLocationNR= " + numberOfHyperLoopLocations);
        printSeparator();

        for (int i = 0; i < numberOfHyperLoopLocations; i++) {
            String name = scanner.next();
            hyperLoopLocations.add(routeCalculator.getLocationByName(name));
        }

        System.out.println("loopLocations = " + hyperLoopLocations);
        printSeparator();


//        System.out.println(routeCalculator.closestToStartFromListOfLocations(journey));
        printSeparator();
//        System.out.println(routeCalculator.closestToEndFromListOfLocations(journey));
        printSeparator();

        Location vienna = new Location("Vienna", 126350, 78010);
        Location prague = new Location("Prague", 0, 286100);
        Location bratislava = new Location("Bratislava", 183680, 71710);
        Location budapest = new Location("Budapest", 318860, 0);

        printSeparator();
        System.out.println(routeCalculator.calculateDistanceBetweenLocations(vienna, bratislava));
        printSeparator();
        System.out.println(routeCalculator.calculateDistanceBetweenLocations(vienna, budapest));
        printSeparator();
        System.out.println(routeCalculator.calculateDistanceBetweenLocations(vienna, prague));

        printSeparator();
        System.out.println(routeCalculator.calculateRouteWithStops(journey, hyperLoopLocations));


    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
