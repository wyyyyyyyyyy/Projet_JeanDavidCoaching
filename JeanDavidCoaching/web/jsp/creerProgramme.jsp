<%-- 
    Document   : creerProgramme
    Author     : yiyang
--%>

<%@page import="db.Seancetype"%>
<%@page import="java.util.List"%>
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
        <title>Creation de Programme</title>
    </head>
    <body>
        <div  id="body" class="container">
            <div id="erreur"></div>
            <h1>Créer un programme : </h1>
            <div id = "zoneALL" class="form-group" >
                <label for="nomProg"><strong>Nom :</strong></label>
                <input id="nomProg" type="text" placeholder="Saisissez le nom de programme" class="form-control"/></br>
                <label for="descriptionProg"><strong>Description :</strong></label>
                <textarea id="descriptionProg" placeholder="Saisissez la description de programme:objectif, profils......" class="form-control"></textarea>
                </br>

                <p><strong>Ajoutez les séances :</strong><p/>            
                <%--Zone for add the seance --%>                      
                <div class="shadow-none p-4 mb-4 bg-light">                   
                    Numero de semaine :
                    <input type="number" min="1" /> 
                    Choisissez une séance :
                    <select class="col-md-2"><option></option></select>
                    <input type="button" class="btn btn-outline-warning"  value="Chercher"/>
                    </br>
                    <div></div>
                    <input type="button" class="btn btn-outline-warning"  value="Ajouter une séance"/>
                    <input type="button" class="btn btn-outline-danger"  value="Supprimer cette séance"/>
                </div>                       
                <%-- Fin Zone for add the seance --%>                
            </div>
            <button id="btn_addProg" class="btn btn-outline-warning" type="button">Ajouter Programme </button>
            </br></br><a href="index.html">Retourner à la page d'accueil</a>
        </div>
        <%--confirmation box --%> 
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Demande de confirmation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                        <div class="modal-body" id="zonetext"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="button" class="btn btn-primary" id="verifier">Valider</button>
                        </div>
                </div>
            </div>
        </div>            
        <!-- Scripts -->
        <script type="text/JavaScript" src="js/jsCreerProg.js"></script>

    </body>
</html>
