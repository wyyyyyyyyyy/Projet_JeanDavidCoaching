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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>Affectation Programme Client</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-md bg-dark navbar-dark">
            <a class="navbar-brand" href="/JeanDavidCoaching">Jean David Coaching</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="creerSeance">Créer une séance</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="creerExercice">Créer un exercice</a>
                    </li>
                </ul>
            </div>
        </nav>
        <br>
        <div>
            <h1>Affectation d'un programme à un client</h1><br/>
            <h2>Liste des clients :</h2>
            <div class="input-group"><select id="nomClient" class="custom-select"><option>----------------</option></select>
                <!--input id="bt_clients" class="btn btn-outline-secondary" type="submit" value="Afficher les clients" /--></div>
            <br>
            <div class="p-3 mb-2 bg-light text-dark"><h3>Objectifs</h3>
                <blockquote class="blockquote" id="objectifs"></blockquote>
                <br/></div>
            <h2>Liste des programmes :</h2>
            <div class="input-group"><select id="nomProgrammetype" class="custom-select"><option>----------</option></select>
                <!--input id="bt_programmetype" class="btn btn-outline-secondary" type="submit" value="Afficher les programmes"/--></div>
            <br/>
            <input id="bt_affectation" type="submit" class="btn btn-outline-warning btn-lg col-md-2 center-block" value="Affecter"/>
            <!--a href ="ServletClient">Test</a-->
        </div>
        <script type="text/JavaScript" src="js/affecterProCliJS.js"></script>

    </body>
</html>
