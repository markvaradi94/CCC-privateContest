package ro.fasttrackit.curs14.catcoder.privateContest.level1;

import ro.fasttrackit.curs14.catcoder.privateContest.level3.Journey;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculator {
    private final List<Location> locations;

    public RouteCalculator(List<Location> locations) {
        this.locations = new ArrayList<>(locations == null ? new ArrayList<>() : locations);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location getLocationByName(String name) {
        for (Location location : this.locations) {
            if (location.getName().toLowerCase().equals(name.toLowerCase())) {
                return location;
            }
        }
        return null;
    }

    public double calculateDistanceBetweenLocations(Location location1, Location location2) {
        double distance = Math.sqrt(Math.pow(location2.getX() - location1.getX(), 2) + Math.pow(location2.getY() - location1.getY(), 2));
        return Math.round(distance * 10) / 10.0;
    }

    public double travelTime(Location location1, Location location2) {
        double distance = calculateDistanceBetweenLocations(location1, location2);
        double travelTime = distance / 250.0 + 200;
        return Math.round(travelTime * 10) / 10.0;
    }

    public Location closestLocationToStart(Location journeyStart, Location loopStart, Location loopEnd) {
        if (calculateDistanceBetweenLocations(journeyStart, loopStart) < calculateDistanceBetweenLocations(journeyStart, loopEnd)) {
            return loopStart;
        } else {
            return loopEnd;
        }
    }

    public Location closestLocationToEnd(Location journeyEnd, Location loopStart, Location loopEnd) {
        if (calculateDistanceBetweenLocations(journeyEnd, loopStart) < calculateDistanceBetweenLocations(journeyEnd, loopEnd)) {
            return loopStart;
        } else {
            return loopEnd;
        }
    }

    public Location closestToStartFromListOfLocations(Journey journey, List<Location> loopstations) {
        String name = "";
        int minDistance = Integer.MAX_VALUE;
        for (Location location : loopstations) {
            if (calculateDistanceBetweenLocations(journey.getStartLocation(), location) < minDistance && !location.equals(journey.getStartLocation())) {
                minDistance = (int) calculateDistanceBetweenLocations(journey.getStartLocation(), location);
                name = location.getName();
            }
        }
        return getLocationByName(name);
    }

    public Location closestToEndFromListOfLocations(Journey journey, List<Location> loopStations) {
        String name = "";
        int minDistance = Integer.MAX_VALUE;
        for (Location location : loopStations) {
            if (calculateDistanceBetweenLocations(journey.getEndLocation(), location) < minDistance) {
                minDistance = (int) calculateDistanceBetweenLocations(journey.getEndLocation(), location);
                name = location.getName();
            }
        }
        return getLocationByName(name);
    }

    public double calculateRouteWithStops(Journey journey, List<Location> loopStations) {
        double drivingTimeToStart = drivingTime(journey.getStartLocation(), closestToStartFromListOfLocations(journey, loopStations));
        int endPosition = loopStations.indexOf(closestToEndFromListOfLocations(journey, loopStations));
        int startPosition = loopStations.indexOf(closestToStartFromListOfLocations(journey, loopStations));
        double sum = 0;

        System.out.println(endPosition);
        System.out.println(startPosition);

        if (endPosition < startPosition) {
            for (int i = startPosition; i > endPosition; i--) {
                double time = calculateDistanceBetweenLocations(loopStations.get(i), loopStations.get(i - 1)) / 250 + 200;
                sum += time;
            }
        } else {
            for (int i = startPosition; i < endPosition; i++) {
                double time = calculateDistanceBetweenLocations(loopStations.get(i), loopStations.get(i + 1)) / 250 + 200;
                sum += time;
            }
        }


        System.out.println("drivingtime = " + drivingTimeToStart);
        System.out.println("hypertime = " + Math.round(sum * 10) / 10.0);

        double driveToEnd = drivingTime(journey.getEndLocation(), closestToEndFromListOfLocations(journey, loopStations));
        int journeyTime = (int) Math.round(driveToEnd + sum + drivingTimeToStart);

        System.out.println("jtime = " + journeyTime);
        return journeyTime;
    }

    public int positionOfDestinationInList(List<Location> hyperLocations, Journey journey) {
        Location endStation = closestToEndFromListOfLocations(journey, hyperLocations);
        return hyperLocations.indexOf(endStation);
    }


    public double drivingTime(Location location1, Location location2) {
        double distance = calculateDistanceBetweenLocations(location1, location2);
        double travelTime = distance / 15.0;
        return Math.round(travelTime * 10) / 10.0;
    }

    public long journeyTime(String[] journey, String[] loop) {
        Location journeyStart = getJourneyStart(journey);
        Location journeyEnd = getJourneyEnd(journey);
        Location loopStart = getLoopStart(loop);
        Location loopEnd = getLoopEnd(loop);

        Location closestToStart = closestLocationToStart(journeyStart, loopStart, loopEnd);
        Location closestToEnd = closestLocationToEnd(journeyEnd, loopStart, loopEnd);

        double driveToStart = drivingTime(journeyStart, closestToStart);
        double hyperloopTime = travelTime(loopStart, loopEnd);
        double driveToEnd = drivingTime(closestToEnd, journeyEnd);
        double totalTime = driveToStart + hyperloopTime + driveToEnd;
        return Math.round(totalTime);
    }

    private List<Location> journeyEndAndStart(String[] journey) {
        List<Location> result = new ArrayList<>();
        result.add(getLocationByName(journey[0]));
        result.add(getLocationByName(journey[1]));
        return result;
    }

    private List<Location> loopStartAndEnd(String[] loop) {
        List<Location> result = new ArrayList<>();
        result.add(getLocationByName(loop[0]));
        result.add(getLocationByName(loop[1]));
        return result;
    }

    public Location getJourneyStart(String[] journey) {
        return journeyEndAndStart(journey).get(0);
    }

    public Location getJourneyEnd(String[] journey) {
        return journeyEndAndStart(journey).get(1);
    }

    public Location getLoopStart(String[] loop) {
        return loopStartAndEnd(loop).get(0);
    }

    public Location getLoopEnd(String[] loop) {
        return loopStartAndEnd(loop).get(1);
    }

}
