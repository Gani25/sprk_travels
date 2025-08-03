package com.sprk.sprk_travels.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import com.sprk.sprk_travels.entity.Hotel;
import com.sprk.sprk_travels.repository.HotelRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/hotel/edit")
public class UpdateController extends HttpServlet {

	@Resource(name = "db_resource")
	private DataSource dataSource;

	private HotelRepository hotelRepository;

	@Override
	public void init() throws ServletException {
		hotelRepository = new HotelRepository(dataSource);
	}

	// Show Form For Update
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get Hotel Id
		String hotel_id_str = request.getParameter("hotel_id");
		HttpSession session = request.getSession();
		String project_path = request.getContextPath();
		String url = null;
		if (!Pattern.matches("^(\\d)+$", hotel_id_str)) {
			// SET MSG
			session.setAttribute("errorMsg", "Hotel id can only be number only");

			// REDIRECT TO HOME PAGE
			url = project_path + "/" + "home";
			response.sendRedirect(url);
		} else {
			// LOGIC FOR UPDATE
			int hotel_id = Integer.parseInt(hotel_id_str);

			// Check if this hotel exists or not?
			Hotel hotel = null;
			try {
				hotel = hotelRepository.findByPropertyId(hotel_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (hotel == null) {

				// SET MSG
				session.setAttribute("errorMsg", "Hotel with id = " + hotel_id + " not found in our records!");

				// REDIRECT TO HOME PAGE
				url = project_path + "/" + "home";
				response.sendRedirect(url);
			} else {

				// SHOW UPDATE FORM WITH PREDEFINED FIELDS
				// HERE WE WON'T USE REDIRECT

				request.setAttribute("hotel", hotel);

				// request.getContextPath()+"/update.jsp";
				// -/sprk_travels/sprk_travels/update.jsp
				url = "/update.jsp";

				RequestDispatcher rd = request.getRequestDispatcher(url);

				rd.forward(request, response);

			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// READ ALL FIELDS
		String propertyName = request.getParameter("property_name");
		String propertyUrl = request.getParameter("property_url");
		String propertyPrice = request.getParameter("property_price");
		String propertyDescription = request.getParameter("property_description");
		String hotel_id_str = request.getParameter("hotel_id");

		String project_path = request.getContextPath();
		HttpSession session = request.getSession();
		String url = null;
		if (!Pattern.matches("^(\\d)+$", hotel_id_str)) {
			// SET MSG
			session.setAttribute("errorMsg", "Hotel id can only be number only");

			// REDIRECT TO HOME PAGE
			url = project_path + "/" + "home";
			response.sendRedirect(url);
		} else {
			// LOGIC FOR UPDATE
			int hotel_id = Integer.parseInt(hotel_id_str);

			// Check if this hotel exists or not?
			Hotel dbHotel = null;
			try {
				dbHotel = hotelRepository.findByPropertyId(hotel_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (dbHotel == null) {

				// SET MSG
				session.setAttribute("errorMsg", "Hotel with id = " + hotel_id + " not found in our records!");

				// REDIRECT TO HOME PAGE
				url = project_path + "/" + "home";
				response.sendRedirect(url);
			} else {

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

					request.setAttribute("priceError", "Price can only be number only");
					hotel.setPropertyPrice(0);
				}
				hotel.setPropertyUrl(propertyUrl);
				hotel.setPropertyId(hotel_id);

				if (errorCount > 0) {
					// AGAIN OPEN FORM WITH ALL MSGS
					request.setAttribute("hotel", hotel);
					request.getRequestDispatcher("/update.jsp").forward(request, response);

				} else {
					// BEGIN Updating DATA
					int result = 0;
					try {
						result = hotelRepository.updateHotel(hotel);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (result > 0) {
						// SET MSG
						session.setAttribute("successMsg", "Hotel updated successfully!!");
					} else {
						// SET MSG
						session.setAttribute("errorMsg", "Some bad happen on server");
					}

					// REDIRECT TO HOME PAGE

					response.sendRedirect(request.getContextPath() + "/home");

				}
			}
		}
	}
}