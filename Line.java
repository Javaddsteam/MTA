
import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private String[] stops;

    public Line(String name, String[] stops) {
        this.name = name;
        this.stops = stops;
    }

    public String getName() {
        return name;
    }


    public String[] getStops() {
        return stops;
    }

    public int getStopIndex(String stopName) {
        for (int i = 0; i < stops.length; i++) {
            if (stops[i].equals(stopName)) {
                return i;
            }
        }
        return -1;
    }

    public List<String> getStops(int startIndex, int endIndex) {
        List<String> stopsBetween = new ArrayList<>();

        if (startIndex == -1 || endIndex == -1) { // Invalid index
            return null;
        }
        // Forward way
        if (startIndex < endIndex) {
            for (int i = startIndex + 1; i <= endIndex; i++) {
                stopsBetween.add(stops[i]);
            }
        } else { // Reverse way
            for (int i = startIndex - 1; i >= endIndex; i--) {
                stopsBetween.add(stops[i]);
            }

        }
        return stopsBetween;
    }

}
