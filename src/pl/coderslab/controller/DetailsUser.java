package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;

@WebServlet("/DetailsUser")
public class DetailsUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailsUser() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("user", User.loadUserById(Integer.parseInt(request.getParameter("id"))));
			getServletContext().getRequestDispatcher("/META-INF/DetailsUser.jsp").forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}
