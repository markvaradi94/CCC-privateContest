package ro.fasttrackit.curs14.catcoder.privateContest.level1;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level1\\level1-4.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\fasttrack\\curs14\\curs14-code\\src\\ro\\fasttrackit\\curs14\\catcoder\\privateContest\\level1\\results4"));

        int numberOfCoordinates = scanner.nextInt();
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < numberOfCoordinates; i++) {
            String name = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            locations.add(new Location(name, x, y));
        }
        scanner.nextLine();
        printSeparator();
        System.out.println("locations = " + locations);
        printSeparator();
        String route = scanner.nextLine();
        System.out.println("route = " + route);
        String[] endAndStartOfRoute = route.split(" ");
        String start = endAndStartOfRoute[0];
        String end = endAndStartOfRoute[1];
        printSeparator();
        System.out.println("start = " + start);
        System.out.println("end = " + end);
        printSeparator();
        RouteCalculator calculator = new RouteCalculator(locations);

        Location startLocation = calculator.getLocationByName(start);
        System.out.println(startLocation);
        Location endLocation = calculator.getLocationByName(end);
        System.out.println(endLocation);
        printSeparator();

        System.out.println(calculator.travelTime(startLocation, endLocation));
        double travelTime = calculator.travelTime(startLocation,endLocation);
        writer.write(String.valueOf(travelTime));
        writer.close();

    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
