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

				url = request.getContextPath()+"/update.jsp";
				System.out.println("Inside Else");

				RequestDispatcher rd = request.getRequestDispatcher(url);

				rd.forward(request, response);

			}
		}

	}

}
