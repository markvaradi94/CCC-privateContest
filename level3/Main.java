package ro.fasttrackit.curs14.catcoder.privateContest.level3;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level3\\level3-4.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level3\\results"));

        int numberOfLocations = scanner.nextInt();
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < numberOfLocations; i++) {
            String name = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            locations.add(new Location(name, x, y));
        }

        RouteCalculator routeCalculator = new RouteCalculator(locations);

        printSeparator();
        System.out.println(locations);
        printSeparator();

        int numberOfJourneys = scanner.nextInt();
        List<Journey> journeys = new ArrayList<>();
        for (int i = 0; i < numberOfJourneys; i++) {
            String firstLocation = scanner.next();
            String secondLocation = scanner.next();
            int currentTime = scanner.nextInt();
            journeys.add(new Journey(routeCalculator.getLocationByName(firstLocation), routeCalculator.getLocationByName(secondLocation), currentTime));
        }

        System.out.println(journeys);
        printSeparator();

        List<Location> loopStations = new ArrayList<>();

        String loopLocation1 = scanner.next();
        String loopLocation2 = scanner.next();

        System.out.println(loopLocation1);
        System.out.println(loopLocation2);
        printSeparator();

        Location loopStart = routeCalculator.getLocationByName(loopLocation1);
        Location loopEnd = routeCalculator.getLocationByName(loopLocation2);

        loopStations.add(loopStart);
        loopStations.add(loopEnd);

        System.out.println(loopStart);
        System.out.println(loopEnd);
        printSeparator();

        RouteEvaluator evaluator = new RouteEvaluator(locations, journeys, loopStations);
        System.out.println(evaluator.getLoopStations());

        printSeparator();
        System.out.println(evaluator.getJourneys());

        Location prague = evaluator.getLocationByName("Prague");
        Location bratislava = evaluator.getLocationByName("Bratislava");
        printSeparator();

        System.out.println(evaluator.numberOfJourneysFasterWithHyperLoop());
    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
