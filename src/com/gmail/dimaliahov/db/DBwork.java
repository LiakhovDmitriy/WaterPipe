package com.gmail.dimaliahov.db;

import com.gmail.dimaliahov.model.Rout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBwork {

	public static void main (String[] args) {
		DBwork dbWork = new DBwork();
		System.out.println(dbWork);
	}

	private Connection getConnection () {
		Connection conn = null;
		try {
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:h2:~/test", "", "");
		} catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public List<Rout> getAllFromRouting () {
		Connection conn = getConnection();
		Statement s = null;
		List<Rout> retRout = new ArrayList<>();
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM ROUTING");
			while (r.next()) {
				int id = r.getInt("id");
				int a = r.getInt("pointA");
				int b = r.getInt("pointB");
				int lengt = r.getInt("length");
				Rout rout = new Rout();
				rout.setId(id).setPointA(a).setPointB(b).setLength(lengt);
				retRout.add(rout);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return retRout;
	}

	public StringBuilder getAllFromCheckRouting () {
		StringBuilder str = new StringBuilder();
		Connection conn = getConnection();
		Statement s = null;
		try {
			s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM CHECK_ROUTING");
			while (r.next()) {
				int id = r.getInt("id");
				int a = r.getInt("pointA");
				int b = r.getInt("pointB");
				Rout rout = new Rout();
				rout.setId(id).setPointA(a).setPointB(b);
				str.append(rout.toString());
			}
//			System.out.println(str.toString());
		} catch (SQLException e){
			e.printStackTrace();
		}
		return str;
	}

	public Rout getRoutingById (int idRout) {
		Connection con = getConnection();
		Rout r = new Rout();
		String GET_ROUT_BY_ID = "SELECT id, pointA, pointB, length FROM ROUTING WHERE id = ?;";
		try (PreparedStatement ps = con.prepareStatement(GET_ROUT_BY_ID);) {
			ps.setString(1, String.valueOf(idRout));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				r
						.setId(Integer.parseInt(rs.getString("id")))
						.setPointA(Integer.parseInt(rs.getString("pointA")))
						.setPointB(Integer.parseInt(rs.getString("pointB")))
						.setLength(Integer.parseInt(rs.getString("length")));
			}
			rs.close();
		} catch (SQLException throwables){
			throwables.printStackTrace();
		}
		return r;
	}

	public boolean cleaneRouting(){
		Connection con = getConnection();
		String CLEAN = "TRUNCATE TABLE ROUTING";
		Statement s = null;
		try {
			s = con.createStatement();
			int r = s.executeUpdate(CLEAN);
			s.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return true;
	}

	public boolean setRoutToDB (Rout rout) {
		Connection con = getConnection();
		String SET_ROUTING = "INSERT INTO ROUTING (POINTA, POINTB, LENGTH) VALUES (?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(SET_ROUTING);) {
			ps.setString(1, String.valueOf(rout.getPointA()));
			ps.setString(2, String.valueOf(rout.getPointB()));
			ps.setString(3, String.valueOf(rout.getLength()));
			int rows = ps.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return true;
	}

	public Rout getAllWherePointA(int pointA, int pointB){
		Connection con = getConnection();
		String GET_ROUT_BY_ID = "SELECT id, pointA, pointB, length FROM ROUTING WHERE pointA = ? AND POINTB = ?;";
		Rout r = new Rout();
		try (PreparedStatement ps = con.prepareStatement(GET_ROUT_BY_ID);) {
			ps.setString(1, String.valueOf(pointA));
			ps.setString(2, String.valueOf(pointB));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r
						.setId(Integer.parseInt(rs.getString("id")))
						.setPointA(Integer.parseInt(rs.getString("pointA")))
						.setPointB(Integer.parseInt(rs.getString("pointB")))
						.setLength(Integer.parseInt(rs.getString("length")));

			}
			rs.close();
		} catch (SQLException throwables){
			throwables.printStackTrace();
		}
		return r;
	};

	public List<Rout> getAllWherePointB(int pointA, int pointB){
		Connection con = getConnection();
		String GET_ROUT_BY_ID = "SELECT id, pointA, pointB, length FROM ROUTING WHERE pointB = ?;";
		Rout r = new Rout();
		List<Rout> retRout = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(GET_ROUT_BY_ID);) {
			ps.setString(1, String.valueOf(pointB));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r
						.setId(Integer.parseInt(rs.getString("id")))
						.setPointA(Integer.parseInt(rs.getString("pointA")))
						.setPointB(Integer.parseInt(rs.getString("pointB")))
						.setLength(Integer.parseInt(rs.getString("length")));
				retRout.add(r);
			}
			rs.close();
		} catch (SQLException throwables){
			throwables.printStackTrace();
		}
		return retRout;
	};

}
