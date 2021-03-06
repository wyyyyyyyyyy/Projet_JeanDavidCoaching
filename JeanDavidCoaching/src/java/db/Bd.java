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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
        t.commit();
        return l_exType;
    }

    /**
     * permet de chercher un exercice by nom (plusieurs résultats possibles)
     *
     * @param s
     * @return
     */
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
                + "where NomET like '%" + nom + "%' "
                + "order by NomET").list();
        t.commit();
        return l;
    }

    public static int creerSeanceType(String nomSeance, String desc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Seancetype s = new Seancetype(nomSeance, desc);
        session.save(s);
        t.commit();
        return s.getCodest();
    }

    public static void ajouterExoType(int codeSeance, int codeET, int ordre,
            int nbrep, int nbserie, int tempsexo,
            int tempsreposserie, int tempsreposexo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Seancetype> listS = session.createQuery("from Seancetype "
                + "where CODEST ='" + codeSeance + "'").list();
        Seancetype seance = listS.get(0);
        Exercicetype exo = (Exercicetype) session.createQuery("from Exercicetype "
                + "where codeET ='" + codeET + "'").list().get(0);

        PredefinirexoId preId = new PredefinirexoId(codeET, codeSeance, ordre);
        Predefinirexo pre = new Predefinirexo(preId, exo, seance, nbrep, nbserie,
                tempsexo, tempsreposserie, tempsreposexo);
        session.save(pre);
        exo.getPredefinirexos().add(pre);
        seance.getPredefinirexos().add(pre);
        t.commit();
    }

    // inserer un nouveau programme type 
    public static int creerProgType(String nomp, String desp) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Programmetype newpt = new Programmetype(nomp, desp);
        session.save(newpt);
        t.commit();
        return newpt.getCodept();
    }

// inserer les donnes dans la table "PredifinirSeance".    
    public static void ajouterPredefiniProg(int numsem, int codest, int i, int codept) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        // get object Programme type
        String sqlProg = "from Programmetype p where p.codept =" + codept + " ";
        List<Programmetype> l = session.createQuery(sqlProg).list();
        Programmetype pt = (Programmetype) session.get(Programmetype.class, l.size());
        // get object Seancetype
        String sql = "from Seancetype s where s.codest = " + codest + " ";
        List<Seancetype> listS = session.createQuery(sql).list();
        Seancetype seancetype = listS.get(0);
        // create a object PredefinirseanceId
        int ordre = i + 1;
        PredefinirseanceId id = new PredefinirseanceId(codept, codest, ordre, numsem);
        // create a object Predefinirseance
        Predefinirseance defini = new Predefinirseance(id, pt, seancetype);
        session.save(defini);
        // create the relationship
        pt.getPredefinirseances().add(defini);
        seancetype.getPredefinirseances().add(defini);

        t.commit();
    }

    public static List<Client> lireClient() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Client> lclient = (List<Client>) session.createQuery("from Client ").list();
        session.close();
        return lclient;
    }

	public static ArrayList<String> lireObjectifs(int codeclient) throws ClassNotFoundException, SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        ArrayList<String> lobjectif = (ArrayList<String>) session.createQuery("select c.objectif "
                + "from Client as c "
                + "where c.codecli='" + codeclient + "'").list();
        session.close();
        return lobjectif;
    }

    public static List<Programmetype> lireProgrammeType() throws ClassNotFoundException, SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Programmetype> lprogrammetype = (List<Programmetype>) session.createQuery("from Programmetype ").list();
        t.commit();
        return lprogrammetype;
    }

    public static Integer affecterProgrammeClient(int codecli, int codept) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        List<Client> client = (List<Client>) session.createQuery("from Client "
                + "where codecli=" + codecli + "").list();

        List<Programmetype> programmetype = (List<Programmetype>) session.createQuery("from Programmetype p where p.codept=" + codept + "").list();

        Programme prog = new Programme(client.get(0), programmetype.get(0));
        System.out.println(client.get(0).getNomcli());
        client.get(0).addProgramme(prog);
        programmetype.get(0).addProgramme(prog);

        session.save(prog);
        t.commit();
        session.close();
        return prog.getCodep();
    }

	    /**
     * fonction pour vérifier que le mail et le mdp appartiennent à un
     * utilisateur
     *
     * @param mail
     * @param mdp
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Client VerifConnexionUtilisateur(String mail, String mdp) throws ClassNotFoundException, SQLException {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Client> client = (List<Client>) session.createQuery("from Client "
                + "where mailcli='" + mail + "' and mdpcli='" + mdp + "'").list();
        t.commit();
        if (!client.isEmpty()) {
           // System.out.println("Connexion ok");
            return client.get(0);
        } else {
            //System.out.println("Connexion pas ok");
            return null;
        }

    }
    public static List<Client> clientNoProgramme() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        //initialiser les list des variables
        ArrayList<Integer> codeS = new ArrayList<Integer>();
        ArrayList<Integer> codeCli = new ArrayList<Integer>();
        ArrayList<Client> l_cli = new ArrayList<Client>();
        //select seance(en cours) in resultatSeance
        codeS = (ArrayList<Integer>) session.createQuery("select r.seance.codes from Resultatseance r ").list();
        //select code client en cours
        Query q_prog = session.createQuery("select distinct p.client.codecli "
                + "from Programme p, Seance s "
                + "where p.codep = s.programme.codep "
                + "and s.codes not in(:codes)");
        q_prog.setParameterList("codes", codeS);
        codeCli = (ArrayList<Integer>) q_prog.list();

        //select client ayant pas programme en cours
        Query q_cli = session.createQuery("from Client c "
                + "where c.codecli not in(:codecli)");
        q_cli.setParameterList("codecli", codeCli);
        l_cli = (ArrayList<Client>) q_cli.list();
        t.commit();

        return l_cli;
    }
    
    public static List<Resultatseance> seanceBilan(int codecli) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        
        List<Resultatseance> l_seance = new ArrayList<Resultatseance>();
        Query q_seanceBilan= session.createQuery("select res from Programme p, Seance s, Resultatseance res, Seancetype st "
                + "where p.client.codecli = " + codecli
                + " and p.codep = s.programme.codep "
                + "and s.seancetype.codest = st.codest "
                + "and st.noms = '" + "Seance Bilan' "
                + "order by s.ordres");
        l_seance = (ArrayList<Resultatseance>) q_seanceBilan.list();
        t.commit();

        return l_seance;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<Client> l_cli = clientNoProgramme();
        for(Client c :l_cli){
            System.out.println(c.getNomcli());
        }
        
        List<Resultatseance> l_seance = seanceBilan(1);
        System.out.println(l_seance.size());
        for(int i = 0; i<l_seance.size();i++){
            System.out.println(l_seance.get(i).getCoderesseance());
        }
    }
}
