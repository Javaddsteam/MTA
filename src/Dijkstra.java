
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra<T> {

    public void calculateShortestPath(Node<T> source) {
        // the time complexity of Dijkstra is O(V^2), unless when you use priority queue it drops to O(V+ElogV)
        source.setDistance(0);
        // settled nodes are the nodes expanded, which means are all the adjacent nodes listed (added to the unsettled list)
        Set<Node<T>> settledNodes = new HashSet<>();
        // the nodes that are not fully explored yet
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            //gets the list head
            Node<T> currentNode = unsettledNodes.poll();
            //explore all adjacent nodes
            currentNode.getAdjacentNodes()
                    .entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node<T> adjacentNode, Integer edgeWeight, Node<T> sourceNode) {
        // does this distance
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        // if new distance is lower set as new shortest path to this adjacent, if not, drop it
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList());
        }
    }

    public void printPaths(List<Node<T>> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getName).map(Objects::toString)
                    .collect(Collectors.joining(" -> "));
            System.out.println((path.isBlank()
                    ? "%s : %s".formatted(node.getName(), node.getDistance())
                    : "%s -> %s ".formatted(path, node.getName()))
            );
            System.out.println("That will be %s Stops in total".formatted(node.getDistance()));

        });
    }

}