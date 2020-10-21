package com.gmail.dimaliahov.findRouting;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
	public static boolean isConnected(Graph graph, int src, int dest,
									  boolean[] discovered, Stack<Integer> path)
	{
		discovered[src] = true;
		path.add(src);
		if (src == dest) {
			return true;
		}
		for (int i: graph.adjList.get(src))
		{
			if (!discovered[i])
			{
				if (isConnected(graph, i, dest, discovered, path)) {
					return true;
				}
			}
		}
		path.pop();
		return false;
	}

	public static void main(String[] args)
	{

		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(
				Edge.of(1, 3),
				Edge.of(1, 0),
				Edge.of(1, 2),
				Edge.of(1, 4),
				Edge.of(1, 7),
				Edge.of(3, 4),
				Edge.of(3, 5),
				Edge.of(3, 3),
				Edge.of(4, 6),
				Edge.of(5, 6),
				Edge.of(6, 7));

		// Number of nodes in the graph (labelled from 0 to N-1)
		int N = 8;

		// create a graph from given edges
		Graph graph = new Graph(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// source and destination vertex
		int src = 2, dest = 7;

		// To store the complete path between source and destination
		Stack<Integer> path = new Stack<>();

		// perform DFS traversal from the source vertex to check the connectivity
		// and store path from the source vertex to the destination vertex
		if (isConnected(graph, src, dest, discovered, path))
		{
			System.out.println("Path exists from vertex " + src + " to vertex " + dest);
			System.out.println("The complete path is: " + path);
		}
		else {
			System.out.println("No path exists between vertices " + src + " and " + dest);
		}
	}
}
