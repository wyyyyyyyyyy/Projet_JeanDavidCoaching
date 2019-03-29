<%-- 
    Document   : index
    Created on : 27 mars 2019, 10:51:46
    Author     : Marck
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>JExercice</title>
    </head>
    <body>
        <div class="container">
            <div class="py-5 text-center">
                <h1>Création d'exercice</h1>
            </div>
            <div class="row">
                <div class="col-md-4 order-md-2 mb-4">
                        <h2>Exercices créés : </h2>
                        <!--<div id="exoCre"></div>-->
                        <select onchange="selectEvent()" id="lexo"><option>-----</option></select>
                        <div>
                            <label for="nomExo2">Nom :</label>
                                <input id="nomExo2" type="text" class="form-control"/>
                            <label for="objectifExo2">Objectif :</label>
                                <input id="objectifExo2" type="text" class="form-control" />
                            <label for="descriptionExo2">Description :</label>
                                <textarea id="descriptionExo2" class="form-control"></textarea>
                            <label for="tipRepExo2">Tips Répétition :</label>
                                <input id="tipRepExo2" type="text" class="form-control" />
                            <label for="tipExo2">Tips Exercice :</label>
                                <input id="tipExo2" type="text" class="form-control" />
                            <label for="materielExo2">Matériel :</label>
                                <input id="materielExo2" type="text" class="form-control" />
                            <label for="mediaExo2">Média descriptif :</label>
                                <input id="mediaExo2" type="text" class="form-control" />  
                        </div>
                        <button id="btnM" class="btn btn-success" type="button">Modifier</button>
                        <button id="btnS" class="btn btn-success" type="button">Supprimer</button>
                </div>
                <div class ="col-md-8 order-md-1">
                    <h2>Créer un exercice : </h2>
                    <div class="form-group">
                        <label for="nomExo">Nom :</label>
                        <input id="nomExo" type="text" class="form-control"/>
                        <label for="objectifExo">Objectif :</label>
                            <input id="objectifExo" type="text" class="form-control" />
                        <label for="descriptionExo">Description :</label>
                            <textarea id="descriptionExo" class="form-control"></textarea>
                        <label for="tipRepExo">Tips Répétition :</label>
                            <input id="tipRepExo" type="text" class="form-control" />
                        <label for="tipExo">Tips Exercice :</label>
                            <input id="tipExo" type="text" class="form-control" />
                        <label for="materielExo">Matériel :</label>
                            <input id="materielExo" type="text" class="form-control" />
                        <label for="mediaExo">Média descriptif :</label>
                            <input id="mediaExo" type="text" class="form-control" />                           
                    </div>
                    <button id="btn" class="btn btn-success" type="button">Ajouter</button>
                </div>
            </div>
            <a href="index.html">Retourner à la page d'accueil</a>
        </div>
        <script type="text/JavaScript" src="js/creerExo.js"></script>
    </body>
</html>
