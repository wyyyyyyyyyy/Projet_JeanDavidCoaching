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
 * @author hugog
 */
public class Bd {
    
    //Données
    //Connexion
    private static Connection cx = null;
    
    //Données de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/db_21405117_2";
    private static final String LOGIN = "21405117";
    private static final String PASSWORD = "P00M37";
    
    //Méthodes
    
    //Méthode de connexion avec la base de données
    private static void connexion() throws ClassNotFoundException, SQLException
    {
        //Chargement du pilote pour la BD
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            throw new ClassNotFoundException("connexion() : Pilote pour se connecter à MySql introuvable - " + ex.getMessage());
        }
        
        //Ouverture de la connexion
        try {
            Bd.cx = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        }
        catch (SQLException ex)
        {
            throw new SQLException("connexion() : Problème de connexion à la base de données - " + ex.getMessage());
        }
    }
    
    public static HashMap<Integer, String> chercherExercice(String nomExercice) throws ClassNotFoundException, SQLException 
    {
        //Création de la connexion à la base de données
        if (Bd.cx == null)
            Bd.connexion();
        
        //Espace de requête
        PreparedStatement st;
        
        //Requête SQL
        String sql = "SELECT CODEET,NOMET FROM EXERCICETYPE WHERE NOMET LIKE ?";
        
        //Ouverture de l'espace de requête
        try {
            st = Bd.cx.prepareStatement(sql);
        }
        catch (SQLException ex)
        {
            throw new SQLException("lireMot() : Problème d'ouverture de l'espace de requête - " + ex.getMessage());
        }
        
        //Interrogation de la base
        List<Exercice> listeexo = new ArrayList<Exercice>();
        
        try {
            //Exécution de la requête
            st.setString(1, "%"+nomExercice+"%");
            ResultSet rs = st.executeQuery();
            
            //Lecture du contenu de ResulSet
            while (rs.next())
            {
                int num = rs.getInt("CODEET");
                String nom = rs.getString("NOMET");
                listeexo.add(new Exercice());
            }
            
            //Lecture 
        }
        catch(SQLException ex){
            
        }
        return null;
    }
    
    public static List<Exercicetype> lireExerciceType(String nom) throws SQLException, ClassNotFoundException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Exercicetype> l = (List<Exercicetype>)session.createQuery(
                                    "from Exercicetype "
                                    + "where NomET like '%"+nom+"%'").list();
        return l;     
    }
    
    public static List<Client> lireClient () throws SQLException, ClassNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Client> lclient = (List<Client>)session.createQuery("from Client ").list();
        return lclient;
    }
    
    public static ArrayList<String> lireObjectifs (String nomClient) throws ClassNotFoundException, SQLException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<String> lobjectif = (ArrayList<String>)session.createQuery( "select c.objectif "
                                                                            + "from Client as c "
                                                                            + "where c.nomcli='" +nomClient+ "'").list();
        return lobjectif;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        ArrayList<String> l = lireObjectifs("AAA");
        for(int i=0; i<l.size(); i++){
           System.out.println(l.get(i));
        }
    }
}
