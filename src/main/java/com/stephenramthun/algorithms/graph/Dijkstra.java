package com.stephenramthun.algorithms.graph;

import com.stephenramthun.datastructures.Graph;
import com.stephenramthun.datastructures.Vertex;
import com.stephenramthun.datastructures.PriorityQueue;

import java.util.HashSet;
import java.util.Map;

/**
 * Single source shortest path algorithm for graphs with non-negative weights,
 * based on Dijkstra's algorithm. Uses a priority queue implementation to keep
 * track of the currently considered edges with smallest weights at each step
 * of the algorithm.
 *
 * @author Stephen Ramthun
 * @see <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's Algorithm<a/>
 */

public class Dijkstra {

    /**
     * Finds the shortest paths from a source vertex to all other vertices in
     * the graph.
     * @param graph     Graph to use as basis for search.
     * @param source    Start vertex from where the distances are
     *                  calculated.
     */
    @SuppressWarnings("unchecked")
    public static void run(Graph graph, Vertex source) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        HashSet<Vertex> inQueue = new HashSet<>();

        for (Object o : graph.getVertices()) {
            Vertex v = (Vertex)o;
            v.setDistance(Double.MAX_VALUE);
            v.setPrevious(null);
        }

        source.setDistance(0);
        queue.add(source);
        inQueue.add(source);

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();

            Map map = v.getEdges();

            for (Object o : map.entrySet()) {
                Map.Entry<Vertex, Double> edge = (Map.Entry<Vertex, Double>)o;
                Vertex vertex = edge.getKey();
                double weight = edge.getValue();

                double distance = v.getDistance() + weight;

                if (distance < vertex.getDistance()) {
                    vertex.setDistance(distance);
                    vertex.setPrevious(v);

                    if (!inQueue.contains(vertex)) {
                        queue.add(vertex);
                        inQueue.add(vertex);
                    }
                }
            }
        }
    }
}
