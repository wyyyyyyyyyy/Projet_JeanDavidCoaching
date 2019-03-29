/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.Bd;
import db.Exercicetype;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugog
 */
public class ServletRecherche extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            /*---Ecriture de la page XML---*/
            out.println("<?xml version=\"1.0\"?>");
            out.println("<liste_exo>");
            /*---Récupérer des paramères---*/
            String nomExo = request.getParameter("nomExo");
            
                try {
                    List<Exercicetype> l_Exo = Bd.lireExerciceType(nomExo);
                    for(Exercicetype exo : l_Exo){
                        out.println("<Exercice>");
                        out.println("<codeExo>" + exo.getCodeet() + "</codeExo>"); 
                        out.println("<nomExo>"+ exo.getNomet() + "</nomExo>");
                        out.println("</Exercice>");
                    }
                }
                catch(ClassNotFoundException | SQLException ex){
                     out.println("<Exercice>Erreur - " + ex.getMessage() + "</Exercice>");  
                }
            out.println("</liste_exo>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
