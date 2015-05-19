/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Questionnaire;

/**
 *
 * @author fcd
 */
public class QuizzServlet extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String vue = "/WEB-INF/quizz.jsp";
        String pQuestion = request.getParameter("idQuiz");
        
        try {
            int id = Integer.parseInt(pQuestion);
            Questionnaire questionnaire = Questionnaire.getById(id);
            request.setAttribute("questionnaire", questionnaire);
        }catch (SQLException ex) {
            request.setAttribute("message", "Problème de base de données. Code : " + ex.getErrorCode());
            vue = "/WEB-INF/message.jsp";
        } catch (NumberFormatException exc) {
            if (request.getParameter("num") != null) {
                request.setAttribute("msgNum", " doit être entier");
            }
        }
        
        request.getRequestDispatcher(vue).forward(request, response);
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
