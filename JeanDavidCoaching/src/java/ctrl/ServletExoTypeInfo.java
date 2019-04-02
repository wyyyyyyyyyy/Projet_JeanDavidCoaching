/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Bd;
import db.Exercicetype;
import db.HibernateUtil;
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
 * @author 21205992
 */
public class ServletExoTypeInfo extends HttpServlet {

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
        /*----- Type de la réponse -----*/
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            
            String code = request.getParameter("codeExo");

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            
            String hql = "from Exercicetype et where et.codeet='" + code + "'";
            List<Exercicetype> l_exType = (List<Exercicetype>) session.createQuery(hql).list();
            
            /*----- Ecriture de la page XML -----*/
            out.println("<?xml version=\"1.0\"?>");
            out.print("<liste_exo>");           
            
//            List<Exercicetype> l_Exo = Bd.ETInfoByID(code);
            for (Exercicetype exo : l_exType) {
                out.print("<Exercice>");
                out.print("<code>" + exo.getCodeet() + "</code>");
                out.print("<nom>" + exo.getNomet() + "</nom>");
                out.print("<objectif>" + exo.getObjectif() + "</objectif>");
                out.print("<description>" + exo.getDescriptione() + "</description>");
                out.print("<tipsrep>" + exo.getTipsrep() + "</tipsrep>");
                out.print("<tips>" + exo.getTipsexo() + "</tips>");
                out.print("<materiel>" + exo.getMateriel() + "</materiel>");
                out.print("<media>" + exo.getLienmedia() + "</media>");
                out.print("</Exercice>");
            }

            out.print("</liste_exo>");
            t.commit();
            session.close();
        }
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
        doGet(request, response);
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
