<%-- 
    Document   : creerSeance
    Created on : 27 mars 2019, 18:19:14
    Author     : hugog
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
        <p>Nom:
        <input id="nom" type="text">
        </p>
        
        <p>Description :
        <input id="desc" type="text">
        </p>
        
        <input id="saisieExo" type="text" placeholder="Saisissez le nom d'exercice"/>
        <div id="listExo"></div>
        
        <script type="text/JavaScript" src="../js/fctxml.js"></script>
        <a href ="ServletRecherche?nomExo=b">Test</a>
    </body>
</html>
