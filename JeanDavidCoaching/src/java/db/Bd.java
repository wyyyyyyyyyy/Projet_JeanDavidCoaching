/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 21205992
 */
public class Bd {

    /**
     * permet de créer un exercice type.
     *
     * @param nom
     * @param description
     * @param media
     * @param tipRep
     * @param tip
     * @param materiel
     * @param objectif
     * @throws Exception
     */
    //Données
    //Connexion
    private static Connection cx = null;

    //Données de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/db_21405117_2";
    private static final String LOGIN = "21405117";
    private static final String PASSWORD = "P00M37";

    public static void creerExType(String nom, String description, String media, String tipRep, String tip, String materiel, String objectif) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        Exercicetype exo = new Exercicetype(nom, description, media, tipRep, tip, materiel, objectif);
        session.save(exo);
        t.commit();
    }

    /**
     * Permet d'afficher tous les exercices types.
     *
     * @return
     */
    public static List listeExType() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        String hql = "select et.nomet from Exercicetype et";
        List l_exType = session.createQuery(hql).list();
        return l_exType;
    }

    /**
     * permet de chercher un exercice by nom (plusieurs résultats possibles)
     *
     * @param s
     * @return
     */
    public static List<Exercicetype> ETInfo(String s) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        String hql = "from Exercicetype et where et.nomet='" + s + "'";
        List<Exercicetype> l_exType = (List<Exercicetype>) session.createQuery(hql).list();
        return l_exType;
    }

    //Méthodes
    //Méthode de connexion avec la base de données
    private static void connexion() throws ClassNotFoundException, SQLException {
        //Chargement du pilote pour la BD
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("connexion() : Pilote pour se connecter à MySql introuvable - " + ex.getMessage());
        }

        //Ouverture de la connexion
        try {
            Bd.cx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException ex) {
            throw new SQLException("connexion() : Problème de connexion à la base de données - " + ex.getMessage());
        }
    }

    public static List<Exercicetype> lireExerciceType(String nom) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Exercicetype> l = (List<Exercicetype>) session.createQuery(
                "from Exercicetype "
                + "where NomET like '%" + nom + "%'").list();
        session.close();
        return l;

    }

    public static void creerSeanceType(String nomSeance, String desc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Seancetype s = new Seancetype(nomSeance, desc);
        session.save(s);
        t.commit();
        session.close();
    }

    public static void ajouterExoType(String nomSeance, int codeET, int ordre,
            int nbrep, int nbserie, int tempsexo,
            int tempsreposserie, int tempsreposexo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Seancetype> listS = session.createQuery("from Seancetype "
                + "where NomS ='" + nomSeance + "'").list();
        Seancetype seance = listS.get(0);
        Exercicetype exo = (Exercicetype) session.get(Exercicetype.class, 1);
//        Exercicetype  exo= (Exercicetype)session.createQuery("from Exercicetype "
//                                        + "where codeET ='" + codeET + "'");

        PredefinirexoId preId = new PredefinirexoId(codeET, seance.getCodest(), ordre);
        Predefinirexo pre = new Predefinirexo(preId, exo, seance, nbrep, nbserie,
                tempsexo, tempsreposserie, tempsreposexo);
        session.save(pre);
        exo.getPredefinirexos().add(pre);
        seance.getPredefinirexos().add(pre);
        t.commit();
    }

    public static List<Exercice> getListExo(Integer numSem, Integer codecli) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql_getLstExo = "from Exercice e "
                + "where e.seance.semaines = " + numSem + " "
                + "and e.seance.programme.client.codecli=" + codecli + " "
                + "order by e.seance.ordres,e.ordre";
        List<Exercice> lstExo = (List<Exercice>) session.createQuery(sql_getLstExo).list();
        session.close();
        return lstExo;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
                   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
                  String sql_checkDo = "from Resultatexo r "
                    + "where r.exercice.seance.programme.client.codecli = 1 "
                    + "order by r.exercice.seance.semaines,r.exercice.seance.ordres,r.exercice.ordre asc ";
            List<Resultatexo> lstResExo = (List<Resultatexo>) session.createQuery(sql_checkDo).list();
            ArrayList<Seance> lSS = new ArrayList<Seance>();
//            ArrayList<Integer> lSmm = ArrayList < Integer > ();
            if (!lstResExo.isEmpty()) {
                for (Resultatexo rExo : lstResExo) {
                    if (lSS.contains(rExo.getExercice().getSeance())) {
                        lSS.add(rExo.getExercice().getSeance());
                    }
                }
            }
            if (!lSS.isEmpty()) {
                for (Seance s : lSS) {
                    System.out.println("<seance>");
                    System.out.println("<ordreSeance>" + s.getOrdres() + "</ordreSeance>");
                    System.out.println("<nomSeance>" + s.getSeancetype().getNoms() + "</nomSeance>");
                    for (Resultatexo rExo : lstResExo) {
                        if(rExo.getExercice().equals(s)){
                        System.out.println("<exercice>");
                        System.out.println("<ordreExercie>" + rExo.getExercice().getOrdre() + "</ordreExercie>");
                        System.out.println("<nomExercice>" + rExo.getExercice().getExercicetype().getNomet() + "</nomExercice>");
                        System.out.println("</exercice>");    
                        }
                    }
                }
                System.out.println("</seance>");
            }
    }
    
    

}
