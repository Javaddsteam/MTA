import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //for brute force solution
    public static List<String> bruteForceLineN;
    public static List<String> bruteForceLineL;
    public static List<String> bruteForceLine6;
    public static String unionSquare = "Union Square";

    //for shortest path algorithm Dijkstra
    //Line N
    public static List<Node<String>> dijkstraLineN;
    public static Node<String> nodeTSLineN;
    public static Node<String> node34LineN;
    public static Node<String> node28LineN;
    public static Node<String> node23LineN;
    public static Node<String> node8LineN;
    //Line L
    public static List<Node<String>> dijkstraLineL;
    public static Node<String> node8LineL;
    public static Node<String> node6LineL;
    public static Node<String> node3LineL;
    public static Node<String> node1LineL;
    //Line 6
    public static List<Node<String>> dijkstraLine6;
    public static Node<String> nodeGCLine6;
    public static Node<String> node33Line6;
    public static Node<String> node28Line6;
    public static Node<String> node23Line6;
    public static Node<String> nodeAPLine6;
    //Intersection Station
    public static Node<String> nodeUnionSquare;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //System.in is a standard input stream.
//        The N line has the following stops: Times Square, 34th, 28th, 23rd, Union Square, and 8th
//        The L line has the following stops: 8th, 6th, Union Square, 3rd, and 1st
//        The 6 line has the following stops: Grand Central, 33rd, 28th, 23rd, Union Square, and Astor Place.

        fillBruteForce();
        fillDijkstra();


        System.out.println("Welcome to The Subway System!" +
                "We have 3 main Lines" +
                "\n Line N: " + bruteForceLineN +
                "\n Line L: " + bruteForceLineL +
                "\n Line 6: " + bruteForceLine6 +
                "\n Kindly, would you provide me in which Line you are located? \n A - Line N \n B - Line L \n C - Line 6 \n ");
        char currentLine = scanner.next().charAt(0);
        currentLine = Character.toUpperCase(currentLine);
        System.out.println("Please provide me with your current station:");
        printStations(getLine(currentLine));
        int currentStation = scanner.nextInt();

        System.out.println("What is your destination line? \n A - Line N \n B - Line L \n C - Line 6 \n ");
        char destinationLine = scanner.next().charAt(0);
        destinationLine = Character.toUpperCase(destinationLine);

        System.out.println("What is your destination station?");
        printStations(getLine(destinationLine));
        int destinationStation = scanner.nextInt();
        System.out.println("====================================================\n \t \t \t \t Brute Force Solution \t \t \t\t \n====================================================");
        getDirections(currentStation, currentLine, destinationStation, destinationLine);

        Node<String> source = getStationNode(currentLine, getLine(currentLine).get(currentStation - 1));
        Node<String> dist = getStationNode(destinationLine, getLine(destinationLine).get(destinationStation - 1));
        System.out.println("====================================================\n \t \t \t \t Dijkstra Solution \t \t \t\t \n====================================================");
        Dijkstra<String> dijkstra = new Dijkstra<>();
        dijkstra.calculateShortestPath(source);
        dijkstra.printPaths(List.of(dist));
    }

    private static void fillBruteForce() {
        bruteForceLineN = Arrays.asList("Times Square Line N", "34th Line N", "28th Line N", "23rd Line N", "Union Square", "8th Line N");
        bruteForceLineL = Arrays.asList("8th Line L", "6th Line L", "Union Square", "3rd Line L", "1st Line L");
        bruteForceLine6 = Arrays.asList("Grand Central Line 6", "33rd Line 6", "28th Line 6", "23rd Line 6", "Union Square", "Astor Place Line 6");
    }

    private static void fillDijkstra() {

        //Line N
        nodeTSLineN = new Node<>("Time Square Line N");
        node34LineN = new Node<>("34th Line N");
        node28LineN = new Node<>("28th Line N");
        node23LineN = new Node<>("23rd Line N");
        node8LineN = new Node<>("8th Line N");
        //Line L
        node8LineL = new Node<>("8th Line L");
        node6LineL = new Node<>("6th Line L");
        node3LineL = new Node<>("3rd Line L");
        node1LineL = new Node<>("1st Line L");

        //Line 6
        nodeGCLine6 = new Node<>("Grand Central Line 6");
        node33Line6 = new Node<>("33rd Line 6");
        node28Line6 = new Node<>("28th Line 6");
        node23Line6 = new Node<>("23rd Line 6");
        nodeAPLine6 = new Node<>("Astor Place Line 6");

        //Intersection Station
        nodeUnionSquare = new Node<>("Union Square");

        nodeTSLineN.addAdjacentNode(node34LineN, 1);
        node34LineN.addAdjacentNode(node28LineN, 1);
        node28LineN.addAdjacentNode(node23LineN, 1);
        node23LineN.addAdjacentNode(nodeUnionSquare, 1);
        nodeUnionSquare.addAdjacentNode(node8LineN, 1);
        node8LineN.addAdjacentNode(nodeTSLineN, 1);


        node8LineL.addAdjacentNode(node6LineL, 1);
        node6LineL.addAdjacentNode(nodeUnionSquare, 1);
        nodeUnionSquare.addAdjacentNode(node3LineL, 1);
        node3LineL.addAdjacentNode(node1LineL, 1);
        node1LineL.addAdjacentNode(node8LineL, 1);

        nodeGCLine6.addAdjacentNode(node33Line6, 1);
        node33Line6.addAdjacentNode(node28Line6, 1);
        node28Line6.addAdjacentNode(node23Line6, 1);
        node23Line6.addAdjacentNode(nodeUnionSquare, 1);
        nodeUnionSquare.addAdjacentNode(nodeAPLine6, 1);
        nodeAPLine6.addAdjacentNode(nodeGCLine6, 1);
        dijkstraLineN = Arrays.asList(nodeTSLineN,
                node34LineN,
                node28LineN,
                node23LineN,
                nodeUnionSquare,
                node8LineN);
        dijkstraLineL = Arrays.asList(node8LineL,
                node6LineL,
                nodeUnionSquare,
                node3LineL,
                node1LineL);
        dijkstraLine6 = Arrays.asList(nodeGCLine6,
                node33Line6,
                node28Line6,
                node23Line6,
                nodeUnionSquare,
                nodeAPLine6);
    }

    public static void getDirections(int currentStation, char currentLine, int destinationStation, char destinationLine) {
        List<String> mapList = new ArrayList<>();
        int firstLineSteps = 0;
        int lastLineSteps = 0;
        int currentLineSize = getLine(currentLine).size();
        int destinationLineSize = getLine(destinationLine).size();
        List<String> currentLineList = getLine(currentLine);
        List<String> distenationLineList = getLine(destinationLine);

        if (currentLine == destinationLine) {
            firstLineSteps = getDistance(destinationStation - 1, currentStation - 1, currentLineSize);
            int temp = currentStation - 1;
            for (int i = 0; i < firstLineSteps; i++) {
                mapList.add(currentLineList.get(temp));
                if (temp == currentLineSize - 1) {
                    temp = 0;
                } else {
                    temp++;
                }
            }
        } else {
            firstLineSteps = getDistance(getLine(currentLine).indexOf(unionSquare), currentStation - 1, currentLineSize);
            lastLineSteps = getDistance(destinationStation - 1, getLine(destinationLine).indexOf(unionSquare), destinationLineSize);
            int temp = currentStation - 1;
            for (int i = 0; i < firstLineSteps; i++) {
                mapList.add(currentLineList.get(temp));
                if (temp == currentLineSize - 1) {
                    temp = 0;
                } else {
                    temp++;
                }
            }
            temp = getLine(destinationLine).indexOf(unionSquare);
            for (int i = 0; i <= lastLineSteps; i++) {
                mapList.add(distenationLineList.get(temp));
                if (temp == destinationLineSize - 1) {
                    temp = 0;
                } else {
                    temp++;
                }
            }
        }
        System.out.println(mapList);
        System.out.println("That will be %s Stops in total".formatted(firstLineSteps + lastLineSteps));

    }


    public static int getDistance(int toIndex, int fromIndex, int length) {
        return ((toIndex - fromIndex) + length) % length;
    }

    public static Node<String> getStationNode(char chosen, String chosenStation) {

        switch (chosen) {
            case 'A':
                return dijkstraLineN.stream()
                        .filter(station -> chosenStation.equals(station.getName()))
                        .findAny()
                        .orElse(null);
            case 'B':
                return dijkstraLineL.stream()
                        .filter(station -> chosenStation.equals(station.getName()))
                        .findAny()
                        .orElse(null);
            case 'C':
                return dijkstraLine6.stream()
                        .filter(station -> chosenStation.equals(station.getName()))
                        .findAny()
                        .orElse(null);
        }
        return null;
    }

    public static List<String> getLine(char chosen) {
        switch (chosen) {
            case 'A':
                return bruteForceLineN;
            case 'B':
                return bruteForceLineL;
            case 'C':
                return bruteForceLine6;
        }
        return null;
    }

    public static void printStations(List<String> currentLineStations) {
        for (int i = 0; i < currentLineStations.size(); i++) {
            System.out.print("[" + (i + 1) + " - " + currentLineStations.get(i) + "]");
        }

    }

}