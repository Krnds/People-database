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

@WebServlet("/add")
public class AddPerson extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ID is put automatically, so we don't have to worry about the actual number
		int id = 0;
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));
		String adress = request.getParameter("adress");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String phoneNumber = request.getParameter("phoneNumber");
		Person personToAdd = new Person(id, firstname, lastname, birthdate, adress, postalCode, city, phoneNumber);

		Dao dao = MainServlet.getDao();
		dao.addPerson(personToAdd);
		response.sendRedirect("database");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
