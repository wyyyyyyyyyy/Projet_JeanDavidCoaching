/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 21205992
 */
public class Bd {
    
    
    public static void creerExType (String nom, String description, String media, String tipRep, String tip, String materiel, String objectif) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction t = session.beginTransaction();
        
        Exercicetype exo= new Exercicetype(nom,description,media,tipRep,tip,materiel,objectif);
        session.save(exo);
        t.commit();
    }

    public static List listeExType () {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction t = session.beginTransaction();
        
        String hql = "select et.nomet from Exercicetype et";
        List l_exType = session.createQuery(hql).list();
        return l_exType;
    }
    
    public static List<Exercicetype> ETInfo(String s) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction t = session.beginTransaction();
        
        String hql = "from Exercicetype et where et.nomet='"+s+"'";
        List<Exercicetype> l_exType = (List<Exercicetype>)session.createQuery(hql).list();
        return l_exType;
    }
}
