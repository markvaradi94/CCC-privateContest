package ro.fasttrackit.curs14.catcoder.privateContest.level4;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.Location;
import ro.fasttrackit.curs14.catcoder.privateContest.level3.Journey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingRoute {
    private final List<Location> locations;
    private final List<Journey> journeys;
    private final int numberOfFasterJourneys;

    public BuildingRoute(List<Location> locations, List<Journey> journeys, int numberOfFasterJourneys) {
        this.locations = new ArrayList<>(locations == null ? new ArrayList<>() : locations);
        this.journeys = new ArrayList<>(journeys == null ? new ArrayList<>() : journeys);
        this.numberOfFasterJourneys = numberOfFasterJourneys;
    }

    public Map<String, List<Location>> possibleStations() {
        List<List<Location>> result = new ArrayList<>();
        Map<String, List<Location>> resultMap = new HashMap<>();
        for (int i = 0; i < this.locations.size() - 1; i++) {
            if (!resultMap.containsKey(locations.get(i).getName())) {
                resultMap.put(locations.get(i).getName(), new ArrayList<>());
            }
            for (int j = i + 1; j < this.locations.size(); j++) {
                resultMap.get(locations.get(i).getName()).add(locations.get(j));
            }
        }
        return resultMap;
    }

    public List<Location> loopStations(Location location1, Location location2) {
        List<Location> result = new ArrayList<>();
        result.add(location1);
        result.add(location2);
        return result;
    }

    public Location getLocationByName(String name) {
        for (Location location : this.locations) {
            if (location.getName().toLowerCase().equals(name.toLowerCase())) {
                return location;
            }
        }
        return null;
    }

    public String returnPossibleRoute() {
        Map<String, List<Location>> possibleStations = new HashMap<>(possibleStations());
        List<Journey> journeyList = new ArrayList<>(this.journeys);
        int fasterRouteCount = 0;
        StringBuilder result = new StringBuilder();
        for (String key : possibleStations.keySet()) {
            for (int i = 0; i < possibleStations.get(key).size(); i++) {
                Location location1 = getLocationByName(key);
                System.out.println("location 1 = " + location1);
                Location location2 = getLocationByName(possibleStations.get(key).get(i).getName());
                System.out.println("location 2 = " + location2);
                for (Journey journey : journeyList) {
                    if (journey.getStartLocation().equals(location1) && journey.getEndLocation().equals(location2) || journey.getStartLocation().equals(location2) && journey.getEndLocation().equals(location1)) {
                        continue;
                    }
                    if (journeyTimeWithHyperLoop(journey, location1, location2) < journey.getCurrentTime()) {
                        System.out.println("minimum faster routes = " + numberOfFasterJourneys);
                        System.out.println("routeCount = " + fasterRouteCount);
                        System.out.println("journey = " + journey);
                        System.out.println("journey currentTime = " + journey.getCurrentTime());
                        System.out.println("With hyperloop = " + journeyTimeWithHyperLoop(journey, location1, location2));
                        fasterRouteCount++;
                        System.out.println("routeCount after increment = " + fasterRouteCount);
                    }
                }
                if (fasterRouteCount >= numberOfFasterJourneys) {
                    result.append(location1.getName()).append(" ").append(location2.getName());
                    return result.toString();
                }
                fasterRouteCount = 0;
            }
        }
        return result.toString();
    }

    public long journeyTimeWithHyperLoop(Journey journey, Location location1, Location location2) {
        List<Location> loopStations = loopStations(location1, location2);
        Location start = journey.getStartLocation();
        Location end = journey.getEndLocation();
        Location loopStart = loopStations.get(0);
        Location loopEnd = loopStations.get(1);

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
}
