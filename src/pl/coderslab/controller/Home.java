package pl.coderslab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;

@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Solution[] solutions = Solution.loadAllSolution(Integer.parseInt(getServletContext().getInitParameter("number-solutions")));
			request.setAttribute("SolutionGetLimited", solutions);
			
			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		doGet(request, response);
	}

	/*
    Utwórz pliki header.jsp, footer.jsp oraz plik index.jsp, który będzie je załączał – w ten sposób stworzymy szablon naszej aplikacji.
    W pliku header.jsp umieścimy linki nawigacyjne naszej aplikacji.
    W pliku footer.jsp umieścimy stopkę informacyjną.
    W metodzie doGet pierwszego servletu pobierz utworzony wcześniej parametr określający liczbę wyświetlanych rozwiązań na stronie startowej.
    Następnie wywołaj metodę loadAll(int) na obiekcie klasy Solution wykorzystując uprzednio pobraną wartość parametru.
    Przekaż pobraną listę do widoku index.jsp, a następnie wyświetl szczegóły rozwiązań w wierszach tabeli html.
	 */
}
