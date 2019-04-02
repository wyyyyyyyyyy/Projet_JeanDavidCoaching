/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 21405117
 */
public class ServletListeSeance extends HttpServlet {

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        try (PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\"?>");

            int codecli = Integer.parseInt(request.getParameter("codecli"));
            int numSem = Integer.parseInt(request.getParameter("numSem"));
            // get list exo
            String sql_getLstExo = "from Exercice e "
                    + "where e.seance.semaines = " + numSem + " "
                    + "and e.seance.programme.client.codecli=" + codecli + " "
                    + "order by e.seance.ordres,e.ordre";
            List<Exercice> lstExo = (List<Exercice>) session.createQuery(sql_getLstExo).list();

            // get list seance
            String sql_getLstSeance = "from Seance s "
                    + "where s.programme.client.codecli =" + codecli + " "
                    + "and s.semaines = " + numSem + " "
                    + "order by s.semaines,s.ordres asc";
            List<Seance> lstSeance = (List<Seance>) session.createQuery(sql_getLstSeance).list();
            out.println("<semaine>");

            for (Seance s : lstSeance) {
                out.print("<seance>");
                out.print("<ordreSeance>" + s.getOrdres() + "</ordreSeance>");
                out.print("<nomSeance>" + s.getSeancetype().getNoms() + "</nomSeance>");

                for (Exercice exo : lstExo) {
                    if (s.equals(exo.getSeance())) {
                        out.print("<exercice>");

                        out.print("<ordreExercie>" + exo.getOrdre() + "</ordreExercie>");
                        out.print("<nomExercice>" + exo.getExercicetype().getNomet() + "</nomExercice>");

                        out.print("</exercice>");
                    }
                }
                out.print("</seance>");
            }
            out.print("</semaine>");
        }
//        t.commit();
        session.close();
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
