<%-- 
    Document   : affecterProgrammeClient
    Created on : 28 mars 2019, 09:29:10
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
        <h1>Affectation d'un programme à un client</h1><br/>
        <h2>Liste des clients :</h2>
        <select id="nomClient"><option>----------------</option></select>
        <input id="bt_clients" type="submit" value="Afficher les clients" /><br/>
        <h2>Objectifs du client</h2>
        <div id="objectifs"></div><br/>
        <h2>Liste des programmes :</h2>
        <select id="nomProgrammetype"><option>----------</option></select>
        <input id="bt_programmetype" type="submit" value="Afficher les programmes"/><br/>
        <br/>
        <input id="bt_affectation" type="submit" value="Affecter"/>
        <a href ="ServletClient">Test</a>
        <script type="text/JavaScript" src="js/fctxml.js"></script>
        
    </body>
</html>
