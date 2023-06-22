
import java.util.ArrayList;
import java.util.List;

public class System {
    private List<Line> Lines;

    public System() {
        this.Lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        Lines.add(line);
    }


    private Line getLineByName(String lineName) {
        for (Line line : Lines) {
            if (line.getName().equals(lineName)) {
                return line;
            }
        }
        return null;
    }
    public void planTrip(String startLineName, String startStopName, String endLineName, String endStopName) {

        Line startLine = getLineByName(startLineName);
        Line endLine = getLineByName(endLineName);

        if (startLine == null || endLine == null) { // Check if invalid
            java.lang.System.out.println("Invalid line.");
            return;
        }

        int startIndex = startLine.getStopIndex(startStopName);
        int endIndex = endLine.getStopIndex(endStopName);

        if (startIndex == -1 || endIndex == -1) { // Check if invalid
            java.lang.System.out.println("Invalid stop.");
            return;
        }

        if (startLine.equals(endLine)) { // Same line
            List<String> tripStops = startLine.getStops(startIndex, endIndex);

            java.lang.System.out.println("You must travel through the following stops on the " + startLine.getName() + " line: " + tripStops);
            java.lang.System.out.println(tripStops.size() + " stops in total.");

        } else { // Trip need to change line
            int unionSquareStartIndex = startLine.getStopIndex("Union Square");
            int unionSquareEndIndex = endLine.getStopIndex("Union Square");

            List<String> startTripStops = startLine.getStops(startIndex, unionSquareStartIndex);
            List<String> endTripStops = endLine.getStops(unionSquareEndIndex, endIndex);

            java.lang.System.out.println("You must travel through the following stops on the " + startLine.getName() + " line: " + startTripStops);
            java.lang.System.out.println("Change at Union Square.");
            java.lang.System.out.println("Your journey continues through the following stops on the " + endLine.getName() + " line: " + endTripStops);
            java.lang.System.out.println((startTripStops.size() + endTripStops.size()) + " stops in total.");
        }
    }


}
