/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author 21405117
 */
public class TestHibernate {

    public static List<Client> clientNoProgramme() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        //initialiser les list des variables
        ArrayList<Integer> codeS = new ArrayList<Integer>();
        ArrayList<Integer> codeCli = new ArrayList<Integer>();
        ArrayList<Client> l_cli = new ArrayList<Client>();
        //select seance(en cours) in resultatSeance
        codeS = (ArrayList<Integer>)session.createQuery("select r.seance.codes from Resultatseance r ").list();
        //select code client en cours
        Query q_prog = session.createQuery("select distinct p.client.codecli "
                + "from Programme p, Seance s "
                + "where p.codep = s.programme.codep "
                + "and s.codes not in(:codes)");
        q_prog.setParameterList("codes", codeS);
        codeCli = (ArrayList<Integer>)q_prog.list();
        
        //select client ayant pas programme en cours
        Query q_cli = session.createQuery("from Client c "
                + "where c.codecli not in(:codecli)");
        q_cli.setParameterList("codecli", codeCli);
        l_cli = (ArrayList<Client>)q_cli.list();
        
        return l_cli;
    }
    
    
    public static void main(String[] args) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        //initialiser les list des variables
        ArrayList<Integer> codeS = new ArrayList<Integer>();
        ArrayList<Integer> codeCli = new ArrayList<Integer>();
        ArrayList<Client> l_cli = new ArrayList<Client>();
        //select seance(en cours) in resultatSeance
        codeS = (ArrayList<Integer>)session.createQuery("select r.seance.codes from Resultatseance r ").list();
        //select code client en cours
        Query q_prog = session.createQuery("select distinct p.client.codecli "
                + "from Programme p, Seance s "
                + "where p.codep = s.programme.codep "
                + "and s.codes not in(:codes)");
        q_prog.setParameterList("codes", codeS);
        codeCli = (ArrayList<Integer>)q_prog.list();
        
        //select client ayant pas programme en cours
        Query q_cli = session.createQuery("from Client c "
                + "where c.codecli not in(:codecli)");
        q_cli.setParameterList("codecli", codeCli);
        l_cli = (ArrayList<Client>)q_cli.list();
        
        
        //outprint
        for(int codes : codeS){
            System.out.println("codeS fini : " + codes);
        }
        for(int codeC : codeCli){
            System.out.println("codeC en cours : " + codeC);
        }
        for(Client cli : l_cli){
            System.out.println(cli.getNomcli());
        }
    }

}
