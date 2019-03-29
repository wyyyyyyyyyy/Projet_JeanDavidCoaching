<%-- 
    Document   : creerSeance
    Created on : 27 mars 2019, 18:19:14
    Author     : xiyue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Création d'une séance</h1>
        <!--<form action="/ServletEnrgSeanceType">-->
            Nom:
            <input id="nomSeance" type="text">
            Description :
            <input id="desc" type="text">
            <input type="submit" value="créer" id="creerSeance">
            
            
            <div class="exoBox">
                <input id="saisieExo" type="text" placeholder="Saisissez le nom d'exercice"/>
                <span class="listExo"></span>
                </br>

                Nombre de répétition d'exercice :                
                <input id="nbrep" type="number" min="1">
                </br>

                Temps de chaque exercice :
                <input id="tempsexo" type="number" min="1">
                </br>

                Temps de repos entre série :
                <input id="tempsreposserie" type="number" step="5" min="0">
                </br>

                Nombre de série :
                <input id="nbserie" type="number" min="0">
                </br>

                Temps de repos entre exercice :
                <input id="tempsreposexo" type="number" step="5" min="0">
                </br>
            </div>
            <input type="submit" value="Ajouter" id="ajouter">
            <!--<input type="submit" value="Valider" id="valider">-->
        </form>
        
        <script type="text/JavaScript" src="js/jsCreerSeance.js"></script>
        <a href ="ServletAjouterExoType?nomSeance=test2&codeET=2&ordre=2&nbrep=1&nbserie=1&tempsexo=1&tempsreposserie=1&tempsreposexo=1">Test</a>
    </body>
</html>
