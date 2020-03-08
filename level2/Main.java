package ro.fasttrackit.curs14.catcoder.privateContest.level2;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level2\\level2-4.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level2\\results"));

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


        scanner.nextLine();
        String journeyLine = scanner.nextLine();
        String loopLine = scanner.nextLine();

        String[] journey = journeyLine.split(" ");
        String journeyStart = journey[0];
        String journeyEnd = journey[1];

        Location startLocationJourney = routeCalculator.getLocationByName(journeyStart);
        Location endLocationJourney = routeCalculator.getLocationByName(journeyEnd);


        System.out.println(journeyStart);
        System.out.println(journeyEnd);
        printSeparator();

        String[] loop = loopLine.split(" ");
        String loopStart = loop[0];
        String loopEnd = loop[1];

        Location startLoopLocation = routeCalculator.getLocationByName(loopStart);
        Location endLoopLocation = routeCalculator.getLocationByName(loopEnd);

        System.out.println(loopStart);
        System.out.println(loopEnd);
        printSeparator();


        Location prague = new Location("Prague", 0, 286100);
        Location bratislava = new Location("Bratislava", 183680, 71710);
        Location brno = new Location("Brno", 152440, 194430);


        System.out.println(routeCalculator.journeyTime(journey, loop));

    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
