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
        <title>JExercice</title>
    </head>
    <body>
        <h1>Créer exercice</h1>
        <div>
            <h2>Exercices créés</h2>
            <div></div>
        </div>
        <div>
            <table>
                <tr>
                    <td><b>Nom : </b></td>
                    <td><input id="nomExo" type="text" size="50" maxlength="50" /></td>
                </tr>
                <tr>
                    <td><b>Objectif : </b></td>
                    <td><input id="objectifExo" type="text" size="50" maxlength="50" /></td>
                </tr>
                <tr>
                    <td><b>Description : </b></td>
                    <td><textarea id="descriptionExo" rows="10" cols="30"></textarea></td>
                </tr>
                <tr>
                    <td><b>Tips Répétition : </b></td>
                    <td><input id="tipRepExo" type="text" size="50" maxlength="50" /></td>
                </tr>
                <tr>
                    <td><b>Tips Exercice : </b></td>
                    <td><input id="tipExo" type="text" size="50" maxlength="50" /></td>
                </tr>
                <tr>
                    <td><b>Matériel : </b></td>
                    <td><input id="materielExo" type="text" size="50" maxlength="50" /></td>
                </tr>
                <tr>
                    <td><b>Média descriptif : </b></td>
                    <td><input id="mediaExo" type="text" size="50" maxlength="50" /></td>
                </tr>
            </table>
            <input id="btn" type="button" value="Ajouter" />
        </div>
        <a href="../index.html">Retourner à la page d'accueil</a>
        <script type="text/JavaScript">
            /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function creerExer ()
{
    var nom = encodeURIComponent(document.getElementById("nomExo").value);
    var objectif = encodeURIComponent(document.getElementById("objectifExo").value);
    var description = encodeURIComponent(document.getElementById("descriptionExo").value);
    var tipRep = encodeURIComponent(document.getElementById("tipRepExo").value);
    var tip = encodeURIComponent(document.getElementById("tipExo").value);
    var materiel = encodeURIComponent(document.getElementById("materielExo").value);
    var media = encodeURIComponent(document.getElementById("mediaExo").value);      
    
    var xhr = new XMLHttpRequest();
    
    var url = "ServletCreExo?nom="+nom+"&objectif="+objectif+"&description="+description
            +"&tipRep="+tipRep+"&tip="+tip+"&materiel="+materiel+"&media="+media;
    xhr.open("GET",url,true);
    alert(url);

    alert(nom+objectif+description+tipRep+tip+materiel+media);
    //alert("hello");
    
    xhr.onload = function()
        {
            if (xhr.status ===200)
            {
                alert("ok");
            }
        };
    xhr.send();
}    
    document.addEventListener("DOMContentLoaded", () => {
	document.getElementById("btn").addEventListener("click",creerExer);
    });


        </script>
    </body>
</html>
