package edu.hsutx;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class WeightedDirectedGraph {
    //Adjacency list - list 0 = vertex 1, etc
    List<Edge> [] vertices;

    /***
     *
     * @param vertexQuantity: Total number of vertices, as an int.  We will start counting at vertex 1, not 0.
     * @param edgeList: an List of Edges containing start and end vertex # and weight.
     ***/
    public WeightedDirectedGraph(int vertexQuantity, List<Edge> edgeList) {
        //Leave vertices[0] as empty and unused, so that when accessing the graph the vertex number matches the index of vertices
        vertices = new ArrayList [vertexQuantity+1];
        for (Edge e : edgeList) {
            if (vertices[e.getStart()]== null) {
                vertices[e.getStart()] = new ArrayList<Edge>();
                vertices[e.getStart()].add(e);
            }
            else if (!vertices[e.getStart()].contains(e)) {
                vertices[e.getStart()].add(e);
            }
        }
    }

    /***
     * returns true if vertex[start] has an edge to vertex[end], otherwise returns false
     * @param start
     * @param end
     */
    public boolean isAdjacent(int start, int end) {
        for (Edge e : vertices[start]) {
            if (e.getEnd() == end) return true;
        }
        return false;
    }

    /***
     * returns a 2d matrix of adjacency weights, with 0 values for non-adjacent vertices.
     * @return matrix of doubles representing adjacent edge weights
     */
    public double[][] adjacencyMatrix() {
        double [][] adjacencyMatrix = new double[vertices.length][vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            for (int j=0; j<vertices.length;j++) {
                adjacencyMatrix[i][j] = 0.0;
            }
            if (vertices[i] != null) {
                for (Edge e : vertices[i]) adjacencyMatrix[e.getStart()][e.getEnd()] = e.getWeight();
            }
        }
        return adjacencyMatrix;
    }

    /***
     * Conducts a Breadth First Search and returns the path from start to end, or null if not connected.
     * For accurate testing reproduction, add new vertices to the queue from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public int[] getBFSPath(int start, int end) {
        // TODO - Implement
        Deque<Edge> q = new ArrayDeque<>();
        boolean [] visited = new boolean [vertices.length+1];
        int [] parent = new int [vertices.length+1];
        q.addAll(vertices[start]);
        visited[start] = true;
        parent[start] = -1;
        while (!q.isEmpty()) {
            Edge e = q.pollFirst();
            if (!visited[e.getEnd()]) {
                if (e.getEnd() == end) {
                    int [] path = new int[vertices.length];
                    path[0]=e.getEnd();
                    int j = e.getEnd();
                    int i = 1;
                    while (parent[j]!=-1) {
                        path[i] = parent[j];
                        j = parent[j];
                        i++;
                    }
                    return path;
                }
                q.addAll(vertices[e.getEnd()]);
                visited[e.getEnd()] = true;
                parent[e.getEnd()] = e.getStart();
            }
        }

        return null;
    }

    /***
     * Conducts a Depth First Search, and returns the path from start to end, or null if not connected.
     * Again, for accurate testing reproduction, add new vertices to the stack from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public static int[] getDFSPath(int start, int end) {
        // TODO - Implement
        return null;
    }

}

