/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import db.Client;
import db.Exercice;
import db.HibernateUtil;
import db.Profilsportif;
import db.Programme;
import db.Seance;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ELITEBOOK
 */
public class ServletvoirPrgClient extends HttpServlet {

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
        
         /*--get codeP--*/
        
        
        Session session = (Session) HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t1 = session.beginTransaction();
        List<Client> listeClients = (List<Client>) session.createQuery("from Client c order by c.nomcli asc").list();
        request.setAttribute("listeClients", listeClients);
              

       String codec = request.getParameter("idClient");
        System.out.println("test code client : "+request.getParameter("idClient"));
        
  
        String sql = "select p1.codep "
                   + "from Programme p1, Programmetype pt "
                   + "where p1.datedebut = (select max(p2.datedebut) "
                                         + "from Programme p2 "
                                         + "where p2.client.codecli = "+codec+") ";
            List<Integer> codeps = (List<Integer>)session.createQuery(sql).list();
            Integer codep = null;
        if(!codeps.isEmpty()){
           codep= codeps.get(0); 
        }
                for(Client c :listeClients){
                    if(c.getCodecli()==Integer.parseInt(codec)){
                        System.out.println("nomClient" + c.getNomcli());
                        
                        request.setAttribute("nomClient", c.getNomcli());
                        request.setAttribute("prenomClient", c.getPrenomcli());
                        request.setAttribute("codeCli", c.getCodecli());
                        request.setAttribute("ageCli", c.getAge());
                        
                        String sqlx = "from Profilsportif p "
                                + "where p.client.codecli ="+codec+" ";
                        List<Profilsportif> lstPS = (List<Profilsportif>)session.createQuery(sqlx).list();

                        for(Profilsportif ps:lstPS){
                            request.setAttribute("Poids",ps.getPoids());
                            request.setAttribute("Handicap",ps.getHandicap());
                            request.setAttribute("Taille",ps.getTaille());
                            request.setAttribute("Poitrine",ps.getPoitrine());
                            request.setAttribute("Bras",ps.getBras());
                            request.setAttribute("Cuisses",ps.getCuisses());
                            request.setAttribute("Hanches",ps.getHanches());
                            
                        }
                    }
                }
                System.out.println("test code programme : "+codep);
                /*--get list seance--*/
                String sql_getLstSeance = "from Seance s where s.programme.codep ="+codep+" order by s.semaines,s.ordres asc";
                List<Seance> lstSeance = (List<Seance>)session.createQuery(sql_getLstSeance).list();
                request.setAttribute("listeSeances", lstSeance);

                      /*--get list exo--*/
                String sql_getLstExo = "from Exercice e "
                        + "where e.seance.programme.codep = codep "
                        + "order by e.seance.ordres,e.ordre";
                List<Exercice> lstExo = (List<Exercice>)session.createQuery(sql_getLstExo).list();
                request.setAttribute("listeExos", lstExo);

        
        
//        t1.commit(); 
        RequestDispatcher rd = request.getRequestDispatcher("TimeLineProg"); //importer requestdispatcher
        rd.forward(request,response);
        
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
