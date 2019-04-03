<%-- 
    Document   : voirSeanceType
    Author     : yiyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Visualisation Seance Type</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">       
    </head>
    <body>
        <!--Zone for Nav Bar-->
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
                        <a class="nav-link" href="creerSeance">Créer une seance</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="creerExercice">Créer un exercice</a>
                    </li> 
                </ul>
            </div> 
        </nav>
        <!--END zone for Nav Bar-->
        
        <!--Zone for choose a seance type-->
            <div class="input-group">
                <select id="zoneST" class="custom-select"></select>       
                <button id ="btn_findST" class="btn btn-warning" type="button">
                    Choisir une séance
                </button>
            </div> 
        <!--END Zone for choose a seance type-->
 
        <!--Zone for display the information of a seance type -->
        <div id="zoneInfoST"></div>
        <!--END Zone for display the information of a seance type -->        
        
        <!--Zone for display the information of exercise types -->
        <div id="zoneExoType"></div>
        <!--END Zone for display the information of exercise types -->
        <li><a href="ServletFindETbySTCode?codest=1">xml</a></li>
    </body>
</html>
<!--script: js page -->
<script type="text/JavaScript" src="js/jsVoirSeanceType.js"></script>
