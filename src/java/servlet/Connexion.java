/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.User;

/**
 *
 * @author plasse
 */
public class Connexion extends HttpServlet {

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);

  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    String vue = "WEB-INF/user.jsp";
    try {
      // Recuperer la session http
      HttpSession session = request.getSession(true);
      if ("deconnecter".equals(request.getParameter("action"))) {
        // Oublier le user
        session.removeAttribute("user");
      }
      else {
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        User user = User.getByLoginPwd(login, pwd);
        if (user != null) {
          session.setAttribute("user", user);
        }
        else {
          request.setAttribute("msg", "Utilisateur inconnu");
        }
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
      request.setAttribute("message", ex.getMessage());
      vue = "WEB-INF/erreur";
    }
    request.getRequestDispatcher(vue).forward(request, response);
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
