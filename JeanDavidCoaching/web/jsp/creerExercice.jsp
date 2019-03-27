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
            <input id="bt_add" type="button" value="Ajouter" disabled="disabled"/>
        </div>
        <a href="">Retourner à la page d'accueil</a>
        <script type="text/JavaScript" src="js/fctxml.js"></script>
    </body>
</html>
