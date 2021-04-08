package com.karinedias.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karinedias.dao.Dao;
import com.karinedias.dao.DaoJDBC;
import com.karinedias.dao.DatabaseType;
import com.karinedias.dao.MemoryDao;

@WebServlet("/chooseDao")
public class ChooseDAO extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Dao dao;

	public static Dao getDao() {
		return dao;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String choice = request.getParameter("daoChoice");
		chooseStrategy(choice);
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("database");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public Dao chooseStrategy(String dao) {
		switch (dao) {
		case "JDBC":
			ChooseDAO.dao = new DaoJDBC(DatabaseType.POSTGRESQL);
			return ChooseDAO.dao;
		case "Memory":
			ChooseDAO.dao = new MemoryDao();
			return ChooseDAO.dao;
		}
		return null;
	}

}
