package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.model.Solution;

@WebServlet("/Szczegoly")
public class Szczegoly extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Szczegoly() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("solutionById", Solution.loadSolutionById(Integer.parseInt(request.getParameter("id"))));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/META-INF/szczegoly.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
