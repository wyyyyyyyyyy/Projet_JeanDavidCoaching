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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" src="css/bootstrap-select.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="js/bootstrap-select.min.js"></script>
        <style>
            .champs_erreur{font-size: 1em; color: #a94442;}
            .champs_seance{margin: 30px;}
            .listexo{border: 1px solid #f0ad4e; width: 250px;list-style-type: none;display: none;}
            .listexo li:hover{background-color:#f0ad4e;}
            .listexo li{margin: 0; padding: 0;}
        </style>
        <title>Création d'une séance</title>
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
        <div id="erreur"></div>
        <div class="container">
            <h1>Création d'une séance</h1>
            <div class="champs_seance">
                Nom:
                <input id="nomSeance" class="form-control" type="text">
                Description :
                <input id="desc" class="form-control" type="text">
            </div>

            <div class="shadow-none p-4 mb-4 bg-light">
                <div class="exoBox">
                    <input type="text" placeholder="Saisissez le nom d'exercice"/>
                    <div class="champs_erreur"></div>
                    <ul class="listexo">
                    </ul>

                    <br/>

                    Type d'exercice :
                    <select>
                        <option>nombre de répétition</option>
                        <option>temps d'exercice</option>
                    </select>
                    <input type="number" min="1">
                    <div class="champs_erreur"></div>
                    <br/>

                    Temps de repos entre série :
                    <input type="number" step="5" min="0">
                    <div class="champs_erreur"></div>
                    <br/>

                    Nombre de série :
                    <input type="number" min="0">
                    <div class="champs_erreur"></div>
                    <br/>

                    Temps de repos entre exercice :
                    <input type="number" step="5" min="0">
                    <div class="champs_erreur"></div>
                    <br/>
                    <input type="submit" value="Ajouter" class="btn_ajouter btn btn-outline-warning">
                    <input type="submit" value="Supprimer" class="btn_supprimer btn btn-outline-danger">
                </div>
            </div>
            <button type="button" id="btn_valider" class="btn btn-outline-warning">
                Valider
            </button>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Valider</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="modal">
                        Voulez-vous enregistrer cette séance maintenant ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Retour</button>
                        <button type="button" class="btn btn-primary" id="verifier">Oui</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Scripts -->
        <script type="text/JavaScript" src="js/jsCreerSeance.js"></script>
    </body>
</html>
