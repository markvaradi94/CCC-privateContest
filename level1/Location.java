package ro.fasttrackit.curs14.catcoder.privateContest.level1;

import java.util.Objects;

public class Location {
    private final String name;
    private final int x;
    private final int y;

    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name = " + name +
                ", x = " + x +
                ", y = " + y +
                '}';
    }
}
