package com.sprk.sprk_travels.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import com.sprk.sprk_travels.entity.Hotel;
import com.sprk.sprk_travels.repository.HotelRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/property")
public class PropertyController extends HttpServlet {
	
	@Resource(name = "db_resource")
	private DataSource dataSource;
	
	private HotelRepository hotelRepository;
	
	@Override
	public void init() throws ServletException {
		hotelRepository = new HotelRepository(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// READ ALL FIELDS
		String propertyName = request.getParameter("property_name");
		String propertyUrl = request.getParameter("property_url");
		String propertyPrice = request.getParameter("property_price");
		String propertyDescription = request.getParameter("property_description");
		int errorCount = 0;

		if (propertyName == null || propertyName.isBlank()) {
			request.setAttribute("nameError", "Name Cannot Be Empty");
			errorCount++;
		}
		if (propertyUrl == null || propertyUrl.isBlank()) {
			request.setAttribute("urlError", "Image url cannot be empty");
			errorCount++;
		}
		if (propertyPrice == null || propertyPrice.isBlank()) {
			request.setAttribute("priceError", "Price cannot be empty");

			errorCount++;
		}
		if (propertyDescription == null || propertyDescription.isBlank()) {
			request.setAttribute("descError", "Desc Cannot Be Empty");
			errorCount++;
		}
		Hotel hotel = new Hotel();
		hotel.setPropertyName(propertyName);
		hotel.setPropertyDescription(propertyDescription);
		if (Pattern.matches("^\\d+(\\.\\d+)?$", propertyPrice)) {
			hotel.setPropertyPrice(Double.parseDouble(propertyPrice));

		} else {

			request.setAttribute("priceError","Price can only be number only");
			hotel.setPropertyPrice(0);
		}
		hotel.setPropertyUrl(propertyUrl);
		// Check if hotel already exists with same name then display error msg
		Hotel existingHotel = null;
		try {
			existingHotel =  hotelRepository.findByPropertyName(propertyName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(existingHotel != null)
		{
			// already exists
			request.setAttribute("nameError", "Name already exists try another name");
			errorCount++;
		}
		if (errorCount > 0) {
			// AGAIN OPEN FORM WITH ALL MSGS
			request.setAttribute("hotel", hotel);
			request.getRequestDispatcher("register.jsp").forward(request, response);

		} else {
			// BEGIN SAVING DATA
			int result = 0;
			try {
				result = hotelRepository.saveHotel(hotel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			if(result>0) {
				// SET MSG
				session.setAttribute("successMsg", "Hotel saved successfully!!");
			}else {
				// SET MSG
				session.setAttribute("errorMsg", "Some bad happen on server");
			}
			
			// REDIRECT TO HOME PAGE
			response.sendRedirect("home");
			
			
		}
	}

}
