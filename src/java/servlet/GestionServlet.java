/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fcd
 */
public class GestionServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationContextPath = request.getContextPath();
        if (request.getRequestURI().equals(applicationContextPath + "/")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/accueil.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/quizz")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/quizz.jsp");
			dispatcher.forward(request, response);


    }
 }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
