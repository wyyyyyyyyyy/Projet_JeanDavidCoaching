/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Exercicetype;
import db.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 21205992
 */
public class ServletModExo extends HttpServlet {

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
            /*----- Ecriture de la page XML -----*/
            out.println("<?xml version=\"1.0\"?>");

            /*----- Récupération des paramètres -----*/
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String objectif = request.getParameter("objectif");
            String description = request.getParameter("description");
            String tipRep = request.getParameter("tipRep");
            String tip = request.getParameter("tip");
            String materiel = request.getParameter("materiel");
            String media = request.getParameter("media");

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
//
//            String hql = "update Exercicetype et set et.nomet='" + nom
//                    + "', et.descriptione='" + description + "', et.lienmedia='"
//                    + media + "', et.tipsrep='" + tipRep + "', et.tipsexo='" 
//                    + tip + "', et.materiel='" + materiel + "', et.objectif='" 
//                    + objectif + "' where et.codeet='" + id + "'";
//            Query queryupdate = session.createQuery(hql);
//            queryupdate.executeUpdate();
            Exercicetype et = (Exercicetype) session.get(Exercicetype.class, id);
            et.setNomet(nom);
            et.setDescriptione(description);
            et.setLienmedia(media);
            et.setTipsrep(tipRep);
            et.setTipsexo(tip);
            et.setMateriel(materiel);
            et.setObjectif(objectif);

            session.update(et);
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
