package com.sprk.sprk_travels.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.sprk.sprk_travels.entity.Hotel;
import com.sprk.sprk_travels.repository.HotelRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	@Resource(name = "db_resource")
	private DataSource dataSource;
	
	private HotelRepository hotelRepository;
	
	@Override
	public void init() throws ServletException {
		hotelRepository = new HotelRepository(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Hotel> hotels = null;
		try {
			hotels = hotelRepository.allHotels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		hotels.forEach(hotel->System.out.println(hotel));
		request.setAttribute("hotels", hotels);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
