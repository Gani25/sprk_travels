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

@WebServlet("/hotel")
public class HotelController extends HttpServlet {

	@Resource(name = "db_resource")
	private DataSource dataSource;

	private HotelRepository hotelRepository;

	@Override
	public void init() throws ServletException {
		hotelRepository = new HotelRepository(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hotel_id_str = req.getParameter("hotel_id");
		HttpSession session = req.getSession();

		if (!Pattern.matches("^(\\d)+$",hotel_id_str)) {
			// SET MSG
			session.setAttribute("errorMsg", "Hotel id can only be number only");

			// REDIRECT TO HOME PAGE
			resp.sendRedirect("home");
		} else {
			int hotel_id = Integer.parseInt(hotel_id_str);

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
				resp.sendRedirect("home");
			} else {
				req.setAttribute("hotel", hotel);
				req.getRequestDispatcher("show_hotel.jsp").forward(req, resp);
			}
		}

	}

}
