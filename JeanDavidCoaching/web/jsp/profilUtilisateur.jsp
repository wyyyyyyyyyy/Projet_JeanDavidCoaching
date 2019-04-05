<%-- 
    Document   : profilUtilisateur
    Created on : 3 avr. 2019, 17:32:02
    Author     : Marck
--%>

<%@page import="db.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Espace Utilisateur</title>
    </head>
    <body>


        <%

            Client client = (Client) request.getAttribute("client");
            if (client != null) {
                String nom = (String) client.getNomcli() + " " + (String) client.getPrenomcli(); %>
        <h1>Bonjour <% out.println(nom);%></h1>
   

        <%} else {%>

        <h1>Vous avez saisi les mauvais identifiants</h1>
        <%}%>
        
        <p><a href="deconnexion">Se déconnecter</a></p>

    </body>
</html>
