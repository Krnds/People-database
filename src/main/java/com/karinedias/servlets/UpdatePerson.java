package com.karinedias.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karinedias.dao.Dao;
import com.karinedias.model.Person;

@WebServlet("/update")
public class UpdatePerson extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = MainServlet.getDao();

		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String adress = request.getParameter("adress");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String phoneNumber = request.getParameter("phoneNumber");
		Person personUpdated = new Person(id, firstname, lastname, LocalDate.of(1990, 01, 01), adress, postalCode, city,
				phoneNumber);
		request.setAttribute("personUpdated", personUpdated);
		dao.updatePerson(personUpdated);
		response.sendRedirect("database");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
