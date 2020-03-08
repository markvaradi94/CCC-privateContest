package ro.fasttrackit.curs14.catcoder.privateContest.level3;

import ro.fasttrackit.curs14.catcoder.privateContest.level1.Location;

import java.util.Objects;

public class Journey {
    private final Location startLocation;
    private final Location endLocation;
    private final int currentTime;

    public Journey(Location startLocation, Location endLocation, int currentTime) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.currentTime = currentTime;
    }

    public Journey(Location startLocation, Location endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.currentTime = 0;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return currentTime == journey.currentTime &&
                Objects.equals(startLocation, journey.startLocation) &&
                Objects.equals(endLocation, journey.endLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startLocation, endLocation, currentTime);
    }

    @Override
    public String toString() {
        return "Journey{" +
                "startLocation = " + startLocation +
                ", endLocation = " + endLocation +
                ", currentTime = " + currentTime +
                '}';
    }
}
