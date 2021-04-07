package com.karinedias.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karinedias.dao.Dao;

@WebServlet("/delete")
public class DeletePerson extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Dao dao = MainServlet.getDao();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("personToDelete", dao.getPerson(id));
		dao.deletePerson(id);
		// Redirect to main page and reload database after delete
		response.sendRedirect("database");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
