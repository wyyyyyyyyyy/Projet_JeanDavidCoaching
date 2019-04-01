/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Bd;
import db.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugog
 */
public class ServletClient extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

            //Ecriture de la page XML
            out.println("<?xml version=\"1.0\"?>");
            
            out.println("<liste_client>");
            try {
                List<Client> l_client = Bd.lireClient();
                for (int i = 0; i < l_client.size(); i++) {
                    
                    out.println("<client>");
                    out.println("<codeClient>"+l_client.get(i).getCodecli()+"</codeClient>");
                    out.println("<nomClient>" + l_client.get(i).getNomcli() + "</nomClient>");
                    out.println("</client>");     
                }
            } catch (ClassNotFoundException | SQLException ex) {
                out.println("<client>");
                out.print("<codeClient>"+ ex.getMessage()+"</codeClient>");
                out.print("<nomClient>Erreur - " + ex.getMessage() + "</nomClient>");
                out.println("</client>");
            }
            out.println("</liste_client>");
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
