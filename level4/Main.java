package ro.fasttrackit.curs14.catcoder.privateContest.level4;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.*;
import ro.fasttrackit.curs14.catcoder.privateContest.level2.*;
import ro.fasttrackit.curs14.catcoder.privateContest.level3.*;


import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level4\\level4-4.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level4\\results"));

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

//        System.out.println(journeys);
        printSeparator();

        int numberOfProposedLines = scanner.nextInt();
//        System.out.println(numberOfProposedLines);
        printSeparator();

        BuildingRoute routeBuilder = new BuildingRoute(locations, journeys, numberOfProposedLines);

//        Map<String, List<Location>> possibleStations = routeBuilder.possibleStations();
//        for (String key : possibleStations.keySet()) {
//            System.out.println(key + " == " + possibleStations.get(key));
//        }

        printSeparator();

        System.out.println(routeBuilder.returnPossibleRoute());

        printSeparator();


        Location test1 = new Location("Budapest", 318860, 0);
        Location test2 = new Location("Bratislava", 183680, 71710);

//        List<Location> loopStations = new ArrayList<>();
//        loopStations.add(test1);
//        loopStations.add(test2);
//
//        Journey journey = journeys.get(2);
//        System.out.println(journey);
//
//        RouteEvaluator evaluator = new RouteEvaluator(locations, journeys, loopStations);
//
//        System.out.println(evaluator.journeyTimeWithHyperLoop(journey));

    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
