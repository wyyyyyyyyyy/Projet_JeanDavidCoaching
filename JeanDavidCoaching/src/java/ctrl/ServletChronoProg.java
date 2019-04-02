/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Client;
import db.Exercice;
import db.HibernateUtil;
import db.Resultatexo;
import db.Seance;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class ServletChronoProg extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        /*--get codeP--*/
        Session session = (Session) HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t1 = session.beginTransaction();
        int codecli = Integer.parseInt(request.getParameter("idClient"));

//        Client information
        List<Client> listeClients = (List<Client>) session.createQuery("from Client c order by c.nomcli asc").list();
        for (Client c : listeClients) {
            if (c.getCodecli() == codecli) {
                System.out.println("nomClient" + c.getNomcli());

                request.setAttribute("nomClient", c.getNomcli());
                request.setAttribute("prenomClient", c.getPrenomcli());
                request.setAttribute("codeCli", c.getCodecli());
                request.setAttribute("ageCli", c.getAge());
            }
        }

//          Client resultat exercice 
        String sql_checkDo = "from Resultatexo r "
                + "where r.exercice.seance.programme.client.codecli = " + codecli + " "
                + "order by r.exercice.seance.semaines,r.exercice.seance.ordres,r.exercice.ordre asc ";
        List<Resultatexo> lstResExo = (List<Resultatexo>) session.createQuery(sql_checkDo).list();
        request.setAttribute("listeExoFinis", lstResExo);
        ArrayList<Seance> lSS = new ArrayList<Seance>();
        ArrayList<Integer> lsemaine = new ArrayList<Integer>();
//            ArrayList<Integer> lSmm = ArrayList < Integer > ();
        if (!lstResExo.isEmpty()) {
            for (Resultatexo rExo : lstResExo) {
                System.out.println(rExo.getExercice().getCodee());
                if (!lSS.contains(rExo.getExercice().getSeance())) {
                    lSS.add(rExo.getExercice().getSeance());
                }
                if (!lsemaine.contains(rExo.getExercice().getSeance().getSemaines())) {
                    lsemaine.add(rExo.getExercice().getSeance().getSemaines());
                }
            }
        }
        request.setAttribute("listeSeanceFinis", lSS);
        request.setAttribute("listeSemaines", lsemaine);

        request.setAttribute("codecli", codecli);

        String sql = "select p1.codep "
                + "from Programme p1, Programmetype pt "
                + "where p1.datedebut = (select max(p2.datedebut) "
                + "from Programme p2 "
                + "where p2.client.codecli = " + codecli + ") ";
        List<Integer> codeps = (List<Integer>) session.createQuery(sql).list();
        Integer codep = null;
        if (!codeps.isEmpty()) {
            codep = codeps.get(0);
        }
//
        String sql_getLstSeance = "from Seance s where s.programme.codep =" + codep + " order by s.semaines,s.ordres asc";
        List<Seance> lstSeance = (List<Seance>) session.createQuery(sql_getLstSeance).list();
        request.setAttribute("listeSeances", lstSeance);

        String sql_getLstExo = "from Exercice e "
                + "where e.seance.programme.client.codecli=" + codecli + " "
                + "order by e.seance.ordres,e.ordre";
        List<Exercice> lstExo = (List<Exercice>) session.createQuery(sql_getLstExo).list();
        request.setAttribute("listeExos", lstExo);
        
        RequestDispatcher rd = request.getRequestDispatcher("chronoProg");
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
