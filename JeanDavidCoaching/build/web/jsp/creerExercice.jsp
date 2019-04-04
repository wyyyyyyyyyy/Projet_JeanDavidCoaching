

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <title>JExercice</title>
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

        <div class="container">
            <div class="py-5 text-center">
                <h1>Création d'exercice</h1>
            </div>
            <div class="row">
                <div class ="col-md-6">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            <h2>Créer un exercice : </h2>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="nomExo">Nom :</label>
                                <input id="nomExo" type="text" class="form-control" />
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
                            <div>
                                <img id="preview" width="200" style="display:none"/>
                                <button id="btnP" class="btn btn-outline-warning" type="button" onclick="imgPreview()">Visualiser image</button>
                                <hr/>
                            </div>
                            <button id="btn" class="btn btn-outline-warning" type="button">Ajouter</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            <h2>Exercices créés : </h2>
                        </div>
                        <div class="card-body">
                            <!--<div id="exoCre"></div>-->
                            <h5>Choisir un exercice</h5>
                            <select onchange="selectEvent()" class="custom-select d-block w-100" id="lexo"><option>-----</option></select>
                            <hr/>
                            <div class="form-group">
                                <h6>Numéro d'exercice:</h6>
                                <div id="numExo2"></div>
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
                            <div>
                                <img id="preview2" width="200" style="display:none"/>
                                <button id="btnP2" class="btn btn-outline-warning" type="button" onclick="imgPreview2()">Visualiser image</button>
                                <hr/>
                            </div>
                            <button id="btnM" class="btn btn-outline-warning" type="button">Modifier</button>
                            <button id="btnS" class="btn btn-outline-warning" type="button">Supprimer</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script type="text/JavaScript" src="js/creerExo.js"></script>
    </body>
</html>
