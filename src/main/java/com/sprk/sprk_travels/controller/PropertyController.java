package com.sprk.sprk_travels.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import com.sprk.sprk_travels.entity.Hotel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/property")
public class PropertyController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// READ ALL FIELDS
		String propertyName = request.getParameter("property_name");
		String propertyUrl = request.getParameter("property_url");
		String propertyPrice = request.getParameter("property_price");
		String propertyDescription = request.getParameter("property_description");
		int errorCount = 0;
		Hotel hotel = new Hotel();
		hotel.setPropertyName(propertyName);
		hotel.setPropertyDescription(propertyDescription);
		hotel.setPropertyPrice(propertyPrice);
		hotel.setPropertyUrl(propertyUrl);
		if(propertyName == null || propertyName.isBlank()) {
			request.setAttribute("nameError","Name Cannot Be Empty");
			errorCount++;
		}
		if(propertyUrl == null || propertyUrl.isBlank()) {
			request.setAttribute("urlError","Image url cannot be empty");
			errorCount++;
		}
		if(propertyPrice == null || propertyPrice.isBlank()) {
			if(!Pattern.matches("^\\d+$", propertyPrice)) {
				
				request.setAttribute("priceError","Price can only be number only");
			}else {
				request.setAttribute("priceError","Price cannot be empty");
				
			}
			errorCount++;
		}
		if(propertyDescription == null || propertyDescription.isBlank()) {
			request.setAttribute("descError","Desc Cannot Be Empty");
			errorCount++;
		}
		
		if(errorCount > 0) {
			// AGAIN OPEN FORM WITH ALL MSGS
			request.setAttribute("hotel", hotel);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			
		}else {
			// BEGIn SAVING DATA
		}
	}

}
