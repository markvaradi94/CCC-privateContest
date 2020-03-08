package ro.fasttrackit.curs14.catcoder.privateContest.level3;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.Location;

import java.util.ArrayList;
import java.util.List;


public class RouteEvaluator {

    private final List<Location> locations;
    private final List<Journey> journeys;
    private final List<Location> loopStations;

    public RouteEvaluator(List<Location> locations, List<Journey> journeys, List<Location> loopStations) {
        this.locations = new ArrayList<>(locations == null ? new ArrayList<>() : locations);
        this.journeys = new ArrayList<>(journeys == null ? new ArrayList<>() : journeys);
        this.loopStations = new ArrayList<>(loopStations == null ? new ArrayList<>() : loopStations);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public List<Location> getLoopStations() {
        return loopStations;
    }

    public long journeyTimeWithHyperLoop(Journey journey) {
        Location start = journey.getStartLocation();
        Location end = journey.getEndLocation();
        Location loopStart = this.loopStations.get(0);
        Location loopEnd = this.loopStations.get(1);

        Location closestToStart = closestLocationToStart(start, loopStart, loopEnd);
        Location closestToEnd = closestLocationToEnd(end, loopStart, loopEnd);

        double driveToStart = drivingTime(start, closestToStart);
        double hyperloopTime = hyperloopTime(loopStart, loopEnd);
        double driveToEnd = drivingTime(closestToEnd, end);
        double totalTime = driveToStart + hyperloopTime + driveToEnd;
        return Math.round(totalTime);
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

    public double calculateDistanceBetweenLocations(Location location1, Location location2) {
        double distance = Math.sqrt(Math.pow(location2.getX() - location1.getX(), 2) + Math.pow(location2.getY() - location1.getY(), 2));
        return Math.round(distance * 10) / 10.0;
    }

    public double drivingTime(Location location1, Location location2) {
        double distance = calculateDistanceBetweenLocations(location1, location2);
        double travelTime = distance / 15.0;
        return Math.round(travelTime * 10) / 10.0;
    }

    public double hyperloopTime(Location location1, Location location2) {
        double distance = calculateDistanceBetweenLocations(location1, location2);
        double travelTime = distance / 250.0 + 200;
        return Math.round(travelTime * 10) / 10.0;
    }

    public Location getLocationByName(String name) {
        for (Location location : this.locations) {
            if (location.getName().toLowerCase().equals(name.toLowerCase())) {
                return location;
            }
        }
        return null;
    }

    public int numberOfJourneysFasterWithHyperLoop() {
        List<Journey> journeyList = new ArrayList<>(this.journeys);
        int count = 0;
        for (Journey journey : journeyList) {
            if (journeyTimeWithHyperLoop(journey) < journey.getCurrentTime()) {
                count++;
            }
        }
        return count;
    }


}
