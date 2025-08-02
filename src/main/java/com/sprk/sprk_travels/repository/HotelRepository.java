package com.sprk.sprk_travels.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

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

	public List<Hotel> allHotels() throws Exception {

		// STEP 1: Create Connection
		Connection conn = dataSource.getConnection();

		// STEP 2: Create SQL Statement
		String sql = "select * from hotel";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		List<Hotel> hotels = new LinkedList<>();
		// STEP 3: Execute Statement
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Hotel hotel = new Hotel();
			hotel.setPropertyId(rs.getInt("property_id"));
			hotel.setPropertyName(rs.getString("property_name"));
			hotel.setPropertyDescription(rs.getString(4));
			hotel.setPropertyPrice(rs.getDouble(3));
			hotel.setPropertyUrl(rs.getString(5));
			
			hotels.add(hotel);
		}

		// STEP 4: Close all
		closeAll(ps, conn, rs);

		return hotels;
	}

	private void closeAll(PreparedStatement ps, Connection conn, ResultSet rs) throws Exception {
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public Hotel findByPropertyName(String propertyName) throws Exception {
		// STEP 1: Create Connection
				Connection conn = dataSource.getConnection();

				// STEP 2: Create SQL Statement
				String sql = "select * from hotel where property_name = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, propertyName);
				Hotel hotel = null;
				// STEP 3: Execute Statement
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					hotel = new Hotel();
					hotel.setPropertyId(rs.getInt("property_id"));
					hotel.setPropertyName(rs.getString("property_name"));
					hotel.setPropertyDescription(rs.getString(4));
					hotel.setPropertyPrice(rs.getDouble(3));
					hotel.setPropertyUrl(rs.getString(5));
					
				}

				// STEP 4: Close all
				closeAll(ps, conn, rs);

				return hotel;
		
	}

	public Hotel findByPropertyId(int hotel_id) throws Exception {
		// STEP 1: Create Connection
		Connection conn = dataSource.getConnection();

		// STEP 2: Create SQL Statement
		String sql = "select * from hotel where property_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, hotel_id);
		Hotel hotel = null;
		// STEP 3: Execute Statement
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			hotel = new Hotel();
			hotel.setPropertyId(rs.getInt("property_id"));
			hotel.setPropertyName(rs.getString("property_name"));
			hotel.setPropertyDescription(rs.getString(4));
			hotel.setPropertyPrice(rs.getDouble(3));
			hotel.setPropertyUrl(rs.getString(5));
			
		}

		// STEP 4: Close all
		closeAll(ps, conn, rs);

		return hotel;
	}

}
