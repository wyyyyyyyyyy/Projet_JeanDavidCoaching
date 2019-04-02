/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Bd;
import db.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author liaox
 */
public class ServletAjouterExoType extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codeSeance = Integer.valueOf(request.getParameter("codeSeance"));
        int codeET = Integer.valueOf(request.getParameter("codeET"));
        int ordre = Integer.valueOf(request.getParameter("ordre"));
        int nbrep = Integer.valueOf(request.getParameter("nbrep"));
        int nbserie = Integer.valueOf(request.getParameter("nbserie"));
        int tempsexo = Integer.valueOf(request.getParameter("tempsexo"));
        int tempsreposserie = Integer.valueOf(request.getParameter("tempsreposserie"));
        int tempsreposexo = Integer.valueOf(request.getParameter("tempsreposexo"));
        Bd.ajouterExoType(codeSeance, codeET, ordre, nbrep, nbserie, tempsexo, tempsreposserie, tempsreposexo);
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
