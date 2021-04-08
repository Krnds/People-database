package com.karinedias.servlets;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karinedias.dao.Dao;
import com.karinedias.model.Person;

@WebServlet("/get")
public class GetPerson extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Dao dao = MainServlet.getDao();
		int id = Integer.parseInt(request.getParameter("id"));
		Optional<Person> getPerson = dao.getPerson(id);
		request.setAttribute("getPerson", getPerson.get());
		RequestDispatcher dispatcher = request.getRequestDispatcher("getPerson.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
