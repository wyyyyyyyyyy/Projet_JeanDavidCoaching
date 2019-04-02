/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Exercicetype;
import db.HibernateUtil;
import db.Predefinirexo;
import db.Programmetype;
import db.Seancetype;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ELITEBOOK
 */
public class ServletVoirDetailsProgType1 extends HttpServlet {

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
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Session session = (Session) HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t1 = session.beginTransaction();
        int codept = Integer.parseInt(request.getParameter("codeprog"));
        String sql_getProgrammeType = "from Programmetype pt where pt.codept =" + codept;
        List<Programmetype> lst_Programmetype = (List<Programmetype>) session.createQuery(sql_getProgrammeType).list();
        Programmetype pt = null;
        if (!lst_Programmetype.isEmpty()) {
            pt = lst_Programmetype.get(0);
        }
        String sql_getLstSeance = "select st from Seancetype st, Predefinirseance pt where pt.seancetype.codest = st.codest and pt.programmetype.codept =" + codept + " order by pt.id.ordreexo asc";
        List<Seancetype> lst_Seance = (List<Seancetype>) session.createQuery(sql_getLstSeance).list();
        try (PrintWriter out = response.getWriter()) {
            out.print("<?xml version=\"1.0\"?>");

            if (pt != null) {
                out.print("<ProgrammeType>");

                out.print("<Code>");
                out.print(pt.getCodept());
                out.print("</Code>");

                out.print("<Nom>");
                out.print(pt.getNomp());
                out.print("</Nom>");

                out.print("<Description>");
                out.print(pt.getDescriptionp());
                out.print("</Description>");
                for (Seancetype st : lst_Seance) {
                    out.print("<SeanceType>");

                    out.print("<CodeSeanceType>");
                    out.print(st.getCodest());
                    out.print("</CodeSeanceType>");

                    out.print("<NomSeanceType>");
                    out.print(st.getNoms());
                    out.print("</NomSeanceType>");

                    out.print("<DescriptionSeanceType>");
                    out.print(st.getDescriptions());
                    out.print("</DescriptionSeanceType>");

                    String sql_getLstExo = "select et from Exercicetype et, Predefinirexo pe where pe.exercicetype.codeet = et.codeet and pe.seancetype.codest =" + st.getCodest();
                    List<Exercicetype> lst_Exo = (List<Exercicetype>) session.createQuery(sql_getLstExo).list();

                    for (Exercicetype et : lst_Exo) {

                        out.print("<ExerciceType>");

                        out.print("<CodeExerciceType>");
                        out.print(et.getCodeet());
                        out.print("</CodeExerciceType>");

                        out.print("<NomExerciceType>");
                        out.print(et.getNomet());
                        out.print("</NomExerciceType>");

                        out.print("<DescriptionExerciceType>");
                        out.print(et.getDescriptione());
                        out.print("</DescriptionExerciceType>");

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

                        String sql_getLstPExo = "select pe from Predefinirexo pe where pe.exercicetype.codeet =" + et.getCodeet() + " and pe.seancetype.codest =" + st.getCodest()+ " order by pe.id.ordree asc";
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

                    out.print("</SeanceType>");
                }
                out.print("</ProgrammeType>");
            }

        }
        t1.commit();
        session.close();

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
        processRequest(request, response);
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
