/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author GS63VR
 */
public class ServletAddProgType extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAddProgType</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletAddProgType at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("<?xml version=\"1.0\"?>");
            out.print("<message>");
            
            /*----- get parameters -----*/
            String nom = request.getParameter("nom");
            String des = request.getParameter("des");
            String[] listSemaineString = request.getParameter("listSemaine").split(",");
            String[] listSeanceString = request.getParameter("listSeance").split(",");

            /*----- Creation d'un programme type -----*/
            int codept = Bd.creerProgType(nom, des);

            /*----- Association -----*/
            if (listSemaineString.length == listSeanceString.length) {
                for (int i = 0; i < listSemaineString.length; i++) {
                    int numSem =  Integer.parseInt(listSemaineString[i]);
                    int codest = Integer.parseInt(listSeanceString[i]);
                    Bd.ajouterPredefiniProg(numSem, codest, i, codept);
                    out.print("Vous avez bien créé un nouveau programme");
                }
            }
            /*----- Fin Hibernate -----*/
//            out.print("<msg>ok</msg>");
            out.print("</message>");
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.close();
        session.clear();          
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
