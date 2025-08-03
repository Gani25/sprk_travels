package com.sprk.sprk_travels.controller;

import java.io.IOException;
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

@WebServlet("/hotel/remove")
public class DeleteController extends HttpServlet {

	@Resource(name = "db_resource")
	private DataSource dataSource;

	private HotelRepository hotelRepository;

	@Override
	public void init() throws ServletException {
		hotelRepository = new HotelRepository(dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
			// LOGIC FOR DELETE
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
				// HOTEL FOUNDED AND WE WILL DELETE THAT HOTEL
				int result = 0;

				try {
					result = hotelRepository.deleteHotel(hotel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (result > 0) {
					// Set MSG
					session.setAttribute("successMsg", "Hotel with id = " + hotel_id + " deleted successfully!");

					// REDIRECT TO HOME PAGE
					url = project_path + "/" + "home";
					response.sendRedirect(url);
				} else {
					// SET MSG
					session.setAttribute("errorMsg", "Something Bad Happens!!");

					// REDIRECT TO HOME PAGE
					url = project_path + "/" + "home";
					response.sendRedirect(url);
				}
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Show Error Msg on Homepage
		String project_path = request.getContextPath();
		HttpSession session = request.getSession();
		String url = null;
		// SET MSG
		session.setAttribute("errorMsg", "Link Not Allowed In URL");

		// REDIRECT TO HOME PAGE
		url = project_path + "/" + "home";
		response.sendRedirect(url);

	}

}
