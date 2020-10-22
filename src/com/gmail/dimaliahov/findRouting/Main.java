package com.gmail.dimaliahov.findRouting;

import java.util.Stack;

public class Main {

	public static boolean isConnected (Graph graph, int src, int dest,
									   boolean[] discovered, Stack<Integer> path) {
		discovered[src] = true;
		path.add(src);
		if (src == dest) {
			return true;
		}
		for (int i : graph.adjList.get(src)) {
			if (!discovered[i]) {
				if (isConnected(graph, i, dest, discovered, path)) {
					return true;
				}
			}
		}
		path.pop();
		return false;
	}
}
