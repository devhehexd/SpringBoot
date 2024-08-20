package com.example.demo.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import conn.MysqlConnect;

import javax.xml.transform.Result;

@Repository
public class CityDao {
	private MysqlConnect dbconn;
	
	public CityDao() {
		dbconn = MysqlConnect.getInstance();
	}
	
	public City select(int id) {
		Connection conn = dbconn.getConn();
		String sql = "SELECT * FROM city WHERE id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new City(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4),  rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void insert(City city) {
		// 도시 하나 추가하는 테이블에 한줄 추가하는 
		Connection conn = dbconn.getConn();
		String sql = "INSERT INTO city(name, countrycode, district, population) values(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city.getName());
			pstmt.setString(2, city.getCountryCode());
			pstmt.setString(3, city.getDistrict());
			pstmt.setInt(4, city.getPopulation());
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<City> selectAll() {

		ResultSet rs = null;
		ArrayList<City> list = new ArrayList<>();
		Connection conn = dbconn.getConn();

		String sql = "SELECT * FROM city ORDER BY id";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				list.add(new City(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5)));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void upadate(City city) {
		Connection conn = dbconn.getConn();
		String sql = "UPDATE city SET population = ? WHERE id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, city.getPopulation());
			pstmt.setInt(2, city.getId());
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(int id) {
		Connection conn = dbconn.getConn();
		String sql = "DELETE FROM city WHERE id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

