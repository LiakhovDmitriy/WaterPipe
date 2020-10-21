package com.gmail.dimaliahov.model;

import java.util.Objects;

public class Rout {
	private int id;
	private int pointA;
	private int pointB;
	private int length;
	private boolean routingBol;



	public Rout () {
		super();
	}

	public Rout (int id, int pointA, int pointB, int length) {
		this.id = id;
		this.pointA = pointA;
		this.pointB = pointB;
		this.length = length;
	}

	public boolean isRoutingBol () {
		return routingBol;
	}

	public Rout setRoutingBol (boolean routingBol) {
		this.routingBol = routingBol;
		return this;
	}

	public int getId () {
		return id;
	}

	public Rout setId (int id) {
		this.id = id;
		return this;
	}

	public int getPointA () {
		return pointA;
	}

	public Rout setPointA (int pointA) {
		this.pointA = pointA;
		return this;
	}

	public int getPointB () {
		return pointB;
	}

	public Rout setPointB (int pointB) {
		this.pointB = pointB;
		return this;
	}

	public int getLength () {
		return length;
	}


	public Rout setLength (int length) {
		this.length = length;
		return this;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rout rout = (Rout) o;
		return id == rout.id &&
				pointA == rout.pointA &&
				pointB == rout.pointB &&
				length == rout.length &&
				routingBol == rout.routingBol;
	}

	@Override
	public int hashCode () {
		return Objects.hash(id, pointA, pointB, length);
	}

	@Override
	public String toString () {
		return "Rout { " +
				"id = " + id +
				", pointA = " + pointA +
				", pointB = " + pointB +
				", length = " + length +
				", routingBol = " + routingBol +
				"} \n";
	}
}
