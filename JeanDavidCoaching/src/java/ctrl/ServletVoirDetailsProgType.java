/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Exercicetype;
import db.HibernateUtil;
import db.Predefinirseance;
import db.Programmetype;
import db.Seance;
import db.Seancetype;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
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
public class ServletVoirDetailsProgType extends HttpServlet {

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

        String codept = request.getParameter("idProgt");

        Session session = (Session) HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t1 = session.beginTransaction();
        List<Programmetype> listeProgs = (List<Programmetype>) session.createQuery("from Programmetype pt order by pt.nomp").list();
        request.setAttribute("listeProgs", listeProgs);

//        Set<Predefinirseance> set_pred =  new HashSet<Predefinirseance>();
//        List<Predefinirseance> lst_Predefinir = new ArrayList<Predefinirseance>();
//        List<Seancetype> lst_Seance = new ArrayList<Seancetype>();
//        for(Programmetype prog : listeProgs){
//            if(prog.getCodept() == Integer.parseInt(codept)){
//                set_pred = (Set<Predefinirseance>)prog.getPredefinirseances();
//            }
//        }
//        for(Predefinirseance pred : lst_Predefinir){
//            lst_Seance.add(pred.getSeancetype());
//        }
        String nomProg = "";
        String sql_getProgrammeType = "from Programmetype pt where  pt.codept =" + codept;
        List<Programmetype> lst_Programmetype = (List<Programmetype>) session.createQuery(sql_getProgrammeType).list();
        if (!lst_Programmetype.isEmpty()) {
            request.setAttribute("programmechoisi", lst_Programmetype.get(0));
        }

        String sql_getLstSeance = "select st from Seancetype st, Predefinirseance pt where pt.seancetype.codest = st.codest and pt.programmetype.codept =" + codept + " order by pt.id.ordreexo asc";
        List<Seancetype> lst_Seance = (List<Seancetype>) session.createQuery(sql_getLstSeance).list();

        String sql_getLstExo = "select et from Exercicetype et, Predefinirexo pe where pe.exercicetype.codeet = et.codeet order by pe.id.ordree asc";
        List<Exercicetype> lst_Exo = (List<Exercicetype>) session.createQuery(sql_getLstExo).list();

        request.setAttribute("listeSeancest", lst_Seance);
        request.setAttribute("listeExo", lst_Exo);

//                    String sql_getLstSem = "from Predefinirseance pt where pt.programmetype.codept =" + codept + "";
//            ArrayList<Predefinirseance> lst_Sem = (ArrayList<Predefinirseance>) session.createQuery(sql_getLstSem).list();
//                    request.setAttribute("listeSem", lst_Sem);

        RequestDispatcher rd = request.getRequestDispatcher("voirProgType");
        rd.forward(request, response);
//        t1.commit();
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
