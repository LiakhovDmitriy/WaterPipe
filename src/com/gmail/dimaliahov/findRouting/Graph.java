package com.gmail.dimaliahov.findRouting;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	// A List of Lists to represent an adjacency list

	public Graph () {
	}

	List<List<Integer>> adjList = null;

	// Constructor
	public Graph (List<Edge> edges, int N) {
		adjList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge: edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}
