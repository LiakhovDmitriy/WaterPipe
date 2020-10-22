package com.gmail.dimaliahov.findRouting;

public class Edge {

	public final int source;

	public final int dest;

	public Edge (int source, int dest) {
		this.source = source;
		this.dest = dest;
	}

	public static Edge of (int a, int b) {
		return new Edge(a, b);
	}

	@Override
	public String toString () {
		return "Edge{" +
				"source=" + source +
				", dest=" + dest +
				'}';
	}
}
