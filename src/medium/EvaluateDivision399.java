package medium;

import java.util.*;

public class EvaluateDivision399 {
    private class GraphNode {
        String destination;
        double weight;
        public GraphNode(String destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    /**
     * Approach:
     * 1. Convert to a graph with weight as a/b(2.0) value a -> [b, 2.0]
     * 2. Start from a and reach to b by BFS
     * 3. if b found then answer would be product of weight of all the paths covered. Otherwise -1
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ans = new double[queries.size()];
        Map<String, List<GraphNode>> graph = translateToGraph(equations, values);
        for (int i = 0; i < queries.size(); i++) {
            String source = queries.get(i).get(0);
            String destination = queries.get(i).get(1);
            if (!graph.containsKey(source) || !graph.containsKey(destination)) {
                ans[i] = -1;
            } else {
                ans[i] = calculate(source, destination, 1, graph, new HashSet<>());
            }
        }

        return ans;
    }

    private double calculate(String source, String destination, double resultSoFar, Map<String, List<GraphNode>> graph, Set<String> visited) {
        if (source.equals(destination)) {
            return resultSoFar;
        }
        if (!graph.containsKey(source)) {
            return -1;
        }

        if (visited.contains(source)) {
            return -1;
        }

        visited.add(source);
        for (GraphNode node : graph.get(source)) {
            double weight = node.weight;
            double result = calculate(node.destination, destination, resultSoFar * weight, graph, visited);
            if (result != -1) {
                return result;
            }
        }

        return -1;
    }

    private Map<String, List<GraphNode>> translateToGraph(List<List<String>> equations, double[] values) {
        Map<String, List<GraphNode>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String source = equation.get(0);
            String destination = equation.get(1);
            graph.putIfAbsent(source, new ArrayList<>());
            double weight = values[i];
            graph.get(source).add(new GraphNode(destination, weight));
            graph.putIfAbsent(destination, new ArrayList<>());
            // We should use the same GraphNode for a in the reverse order. With this implementation, it creates a new node
            //
            graph.get(destination).add(new GraphNode(source, 1/weight));
        }

        return graph;
    }

    public static void main(String[] args) {
        EvaluateDivision399 evaluateDivision399 = new EvaluateDivision399();

        List<List<String>> equations = List.of(List.of("a","b"), List.of("b","c"));
        double[] values = {2.0,3.0};
        List<List<String>> queries = List.of(List.of("a","c"), List.of("b","a"), List.of("a","e"), List.of("a","a"), List.of("x","x"));
        System.out.println(Arrays.toString(evaluateDivision399.calcEquation(equations, values, queries)));

        List<List<String>> equations2 = List.of(List.of("a","b"), List.of("b","c"), List.of("bc","cd"));
        double[] values2 = {1.5,2.5,5.0};
        List<List<String>> queries2 = List.of(List.of("a","c"), List.of("c","b"), List.of("bc","cd"), List.of("cd","bc"));
        System.out.println(Arrays.toString(evaluateDivision399.calcEquation(equations2, values2, queries2)));

        List<List<String>> equations3 = List.of(List.of("a","b"));
        double[] values3 = {0.5};
        List<List<String>> queries3 = List.of(List.of("a","b"), List.of("b","a"), List.of("a","c"), List.of("x","y"));
        System.out.println(Arrays.toString(evaluateDivision399.calcEquation(equations3, values3, queries3)));
    }
}


