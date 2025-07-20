package com.sprk.sprk_travels.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.sprk.sprk_travels.entity.Hotel;

public class HotelRepository {
	
	private DataSource dataSource;

	public HotelRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public int saveHotel(Hotel hotel) throws Exception {
		// STEP 1: Create Connection
		Connection conn = dataSource.getConnection();
		
		// STEP 2: Create SQL Statement
		String sql = "insert into hotel(property_name, property_price, property_description, property_url) values (?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, hotel.getPropertyName());
		ps.setDouble(2, hotel.getPropertyPrice());
		ps.setString(3, hotel.getPropertyDescription());
		ps.setString(4, hotel.getPropertyUrl());
		
		// STEP 3: Execute Statement
		int result = ps.executeUpdate();
		
		// STEP 4: Close all
		closeAll(ps, conn, null);
		
		return result;
	}
	
	private void closeAll(PreparedStatement ps, Connection conn, ResultSet rs) throws Exception {
		if(ps != null) {
			ps.close();
		}
		if(rs != null) {
			rs.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
	

}
