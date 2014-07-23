package by.filippov.library.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import by.filippov.library.commands.Command;
import by.filippov.library.commands.CommandFactory;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/library")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		try (InputStream config = new FileInputStream(new File(
				getServletContext().getRealPath(
						"/resources/logconfig.properties")))) {
			PropertyConfigurator.configure(config);
		} catch (IOException e) {
			getServletContext().log("Cant configure logger");
		}
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		CommandFactory factory = new CommandFactory();
		RequestContent content = new RequestContent(request);
		Command command = factory.defineCommand(content);
		page = command.execute(content);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
