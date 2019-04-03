/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Exercicetype;
import db.HibernateUtil;
import db.Predefinirexo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author GS63VR
 */
public class ServletFindETbySTCode extends HttpServlet {

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
            out.println("<title>Servlet ServletFindETbySTCode</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletFindETbySTCode at " + request.getContextPath() + "</h1>");
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
            out.println("<?xml version=\"1.0\"?>");
            out.println("<SeanceType>");
       
            int codest = Integer.parseInt(request.getParameter("codest"));

            /*----- Hibernate -----*/
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.getCurrentSession();
            Transaction t = session.beginTransaction();
               out.println("SeanceType");
                    String sql_getLstExo = "select et from Exercicetype et, Predefinirexo pe where pe.exercicetype.codeet = et.codeet and pe.seancetype.codest =" + codest;
                    List<Exercicetype> lst_Exo = (List<Exercicetype>) session.createQuery(sql_getLstExo).list();
                    for (Exercicetype et : lst_Exo) {

                        out.println("<ExerciceType>");

                        out.println("<CodeExerciceType>" + et.getCodeet()+ "</CodeExerciceType>");

                        out.println("<NomExerciceType>");
                        out.println(et.getNomet());
                        out.println("</NomExerciceType>");

                        out.println("<DescriptionExerciceType>" + et.getDescriptione() + "</DescriptionExerciceType>");

                        out.print("<ObjectifExerciceType>");
                        out.print(et.getObjectif());
                        out.print("</ObjectifExerciceType>");

                        out.print("<TipsExerciceType>");
                        out.print(et.getTipsexo());
                        out.print("</TipsExerciceType>");

                        out.print("<TipsrepExerciceType>");
                        out.print(et.getTipsrep());
                        out.print("</TipsrepExerciceType>");

                        out.print("<MaterielExerciceType>");
                        out.print(et.getMateriel());
                        out.print("</MaterielExerciceType>");

                        String sql_getLstPExo = "select pe from Predefinirexo pe where pe.exercicetype.codeet =" + et.getCodeet() + " and pe.seancetype.codest =" + codest+ " order by pe.id.ordree asc";
                        List<Predefinirexo> lst_pExo = (List<Predefinirexo>) session.createQuery(sql_getLstPExo).list();

                        out.print("<OrdreExo>");
                        out.print(lst_pExo.get(0).getId().getOrdree());
                        out.print("</OrdreExo>");
                        
                        out.print("<NombreDeRep>");
                        out.print(lst_pExo.get(0).getNbrep());
                        out.print("</NombreDeRep>");

                        out.print("<Nbserie>");
                        out.print(lst_pExo.get(0).getNbserie());
                        out.print("</Nbserie>");
                        
                        out.print("<Tempsexo>");
                        out.print(lst_pExo.get(0).getTempsexo());
                        out.print("</Tempsexo>");
                        
                        out.print("<TempsreposSerie>");
                        out.print(lst_pExo.get(0).getTempsreposserie());
                        out.print("</TempsreposSerie>");
                        
                        
                        out.print("<TempsReposExo>");
                        out.print(lst_pExo.get(0).getTempsreposexo());
                        out.print("</TempsReposExo>");

                        out.print("</ExerciceType>");
                    }

                    out.println("</SeanceType>");
                    t.commit();
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
