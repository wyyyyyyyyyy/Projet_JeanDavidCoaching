

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

        <title>JExercice</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-md bg-dark navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="/JeanDavidCoaching">Jean David Coaching</a>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="ListCtrlPrg">Programme Client</a>
                    </li> 
                    <li class="nav-item">
                        <a class="nav-link" href="creerSeance">Créer une seance</a>
                    </li>
                </ul>
            </div> 
        </nav> 
        <div class="container">
            <div class="py-5 text-center">
                <h1>Création d'exercice</h1>
            </div>
            <div class="row">
                <div class="col-md-5 order-md-2 mb-4">
                        <h2>Exercices créés : </h2>
                        <div id="exoCre"></div>
                </div>
                <div class ="col-md-7 order-md-1">
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
                    <button id="btn" class="btn btn-success" type="button">Ajouter </button>
                </div>
            </div>
            <a href="index.html">Retourner à la page d'accueil</a>
        </div>
        <script type="text/JavaScript" src="js/creerExo.js"></script>
    </body>
</html>
