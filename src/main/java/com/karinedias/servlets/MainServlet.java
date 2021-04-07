package com.karinedias.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karinedias.PopulateMemoryDatabase;
import com.karinedias.dao.Dao;
import com.karinedias.dao.MemoryDao;
import com.karinedias.model.Person;

@WebServlet("/database")
public class MainServlet extends HttpServlet {

	private static final Dao DAO = ChooseDAO.getDao();
	private static final long serialVersionUID = 1L;

	public static Dao getDao() {
		return DAO;
	}

	public MainServlet() {
		if (DAO.getClass() == MemoryDao.class) {
			PopulateMemoryDatabase.main(null);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Person> allPersons = DAO.getAllPersons();

		request.setAttribute("allPersons", allPersons);
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("getAllPersons.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
