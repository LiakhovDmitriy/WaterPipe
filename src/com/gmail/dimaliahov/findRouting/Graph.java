package com.gmail.dimaliahov.findRouting;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	List<List<Integer>> adjList = null;

	public Graph () {
	}

	public Graph (List<Edge> edges, int N) {
		adjList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (Edge edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}
