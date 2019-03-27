/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import org.hibernate.*;


/**
 *
 * @author 21405117
 */
public class TestHibernate {
    public static void main(String[] args)
	{

	/*----- Ouverture de la session et de la transaction -----*/
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction t = session.beginTransaction();
        
        Exercicetype exot1= new Exercicetype("hugo","","","","","","try for test");
        session.save(exot1);
        t.commit();
        }
    
}
