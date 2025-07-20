package com.sprk.sprk_travels.repository;

import javax.sql.DataSource;

public class HotelRepository {
	
	private DataSource dataSource;

	public HotelRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

}
