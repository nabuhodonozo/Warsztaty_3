package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Groups;

@WebServlet("/LoadGroups")
public class LoadGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadGroups() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("groups", Groups.loadAllGroups());
			getServletContext().getRequestDispatcher("/META-INF/groups.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
