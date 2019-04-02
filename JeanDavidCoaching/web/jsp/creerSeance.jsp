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
    </head>
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
                    <a class="nav-link" href="creerExercice">Créer un exercice</a>
                </li> 
            </ul>
        </div> 
    </nav> 
    <body>
        <div id="erreur"></div>   
        <h1>Création d'une séance</h1>
        <div class="form-group">
            <label for="nomSeance">Nom :</label>
            <input id="nomSeance" type="text" class="form-control">
            <label for="desc">Description :</label>
            <input id="desc" type="text" class="form-control">
            <button type="submit" id="creerSeance" class="btn btn-outline-secondary">créer</button>
        </div>
        <br><br>
        
        

        <div class="exoBox">
            <input type="text" placeholder="Saisissez le nom d'exercice"/>
            <div class="listExo"></div>
            </br>

            Type d'exercice :
            <select>
                <option>nombre de répétition</option>
                <option>temps d'exercice</option>
            </select>
            <input type="number" min="1">
            </br>

            Temps de repos entre série :
            <input type="number" step="5" min="0">
            </br>

            Nombre de série :
            <input type="number" min="0">
            </br>

            Temps de repos entre exercice :
            <input type="number" step="5" min="0">
            </br>
        </div>
        <input type="submit" value="Ajouter" id="ajouter">
        <!--<input type="submit" value="Valider" id="valider">-->

        <!-- Scripts -->
        <script type="text/JavaScript" src="js/jsCreerSeance.js"></script>
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/typewriter.js"></script>
        <script src="js/jquery.onepagenav.js"></script>
        <script src="js/main.js"></script>
        <a href ="ServletAjouterExoType?nomSeance=test2&codeET=2&ordre=2&nbrep=1&nbserie=1&tempsexo=1&tempsreposserie=1&tempsreposexo=1">Test</a>
    </body>
</html>
