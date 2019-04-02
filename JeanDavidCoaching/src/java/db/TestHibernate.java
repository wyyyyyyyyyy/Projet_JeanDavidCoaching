/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import org.hibernate.*;


/**
 *
 * @author 21405117
 */
public class TestHibernate {
    public static void main(String[] args) throws Exception
	{

	/*----- Ouverture de la session et de la transaction -----*/
//	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	Transaction t = session.beginTransaction();
//        
//        Exercicetype exot1= new Exercicetype("exoalae","","","","","","try for test");
//        session.save(exot1);
//        t.commit();
        
//        Bd.creerExType("a", "b", "c", "d", "e", "f", "g");
        
//        List<Exercicetype> l = Bd.listeExType();
//        for (Exercicetype e : l)
//        {
//            System.out.println(e.toString());
//        }
//        
//        System.exit(0);
        
            Integer id = 50;
            String nom = "a";
            String objectif = "a";
            String description = "a";
            String tipRep = "a";
            String tip = "a";
            String materiel = "a";
            String media = "a";
            
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();

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
