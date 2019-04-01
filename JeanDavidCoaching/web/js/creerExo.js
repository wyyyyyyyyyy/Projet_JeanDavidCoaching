/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function creerExer()
{
    var nom = encodeURIComponent(document.getElementById("nomExo").value);
    var objectif = encodeURIComponent(document.getElementById("objectifExo").value);
    var description = encodeURIComponent(document.getElementById("descriptionExo").value);
    var tipRep = encodeURIComponent(document.getElementById("tipRepExo").value);
    var tip = encodeURIComponent(document.getElementById("tipExo").value);
    var materiel = encodeURIComponent(document.getElementById("materielExo").value);
    var media = encodeURIComponent(document.getElementById("mediaExo").value);

    var xhr = new XMLHttpRequest();

    var url = "ServletCreExo?nom=" + nom + "&objectif=" + objectif + "&description=" + description
            + "&tipRep=" + tipRep + "&tip=" + tip + "&materiel=" + materiel + "&media=" + media;
    //var test = "ServletCreExo?nom=a&objectif=b&description=c&tipRep=d&tip=e&materiel=f&media=g"
    xhr.open("GET", url, true);
    alert(url);

    //alert(nom+objectif+description+tipRep+tip+materiel+media);
    //alert("hello");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            alert("ok");
//            var texte = "<p class=\"choisir\">" + nom + "</p>";
//
//            //récupère le composant HTML à mettre à jour
//            var elt = document.getElementById("exoCre");
//
//            var e_div = document.createElement("div");
//            e_div.innerHTML = texte;
//            elt.insertBefore(e_div, elt.lastElementChild);
//            clean();
            document.getElementById("nomExo").value = "";
            document.getElementById("objectifExo").value = "";
            document.getElementById("descriptionExo").value = "";
            document.getElementById("tipRepExo").value = "";
            document.getElementById("tipExo").value = "";
            document.getElementById("materielExo").value = "";
            document.getElementById("mediaExo").value = "";

//            var choix = document.querySelectorAll(".choisir");
//            for (var i = 0; i < choix.length; i++)
//            {
//                choix[i].addEventListener("click", afficherInfo);
//            }
        }
    };
    xhr.send();
}

//function afficherInfo()
//{
//    var nom = this.firstChild.nodeValue;
//    //alert(nom);
//    var xhr = new XMLHttpRequest();
//    var url = "ServletExoTypeInfo?nom=" + nom;
//    xhr.open("GET", url, true);
//    xhr.onload = function ()
//    {
//        if (xhr.status === 200)
//        {
//            var l_ET = xhr.responseXML.getElementsByTagName("ET");
//            for (var i = 0; i < l_ET.length; i++)
//            {
//                alert(l_ET[i].firstChild.nodeValue);
//            }
//
//        }
//    };
//    xhr.send();
//}

function listeExo()
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletListeExo");
    xhr.onload = function () {
        if (xhr.status === 200) {
            var rep = xhr.responseXML.getElementsByTagName("liste_exo");
            var txt = "<option></option>";
            for (var i = 0; i < rep.length; i++) {
                var exercice = rep[i].children;
                var code = exercice[0].firstChild.nodeValue;
                var nom = exercice[1].firstChild.nodeValue;
                txt += "<option class=\"exo\" value=\"" + code + "\">" + code + " " + nom + "</option>";
            }
            var elt = document.getElementById("lexo");
            elt.innerHTML = txt;

//            var choix = document.getElementsByClassName("exo");
//            console.log(choix.length);
//            for (var i = 0; i < choix.length; i++)
//            {
//                choix[i].addEventListener("click", afficherInfo);
//            }
        }
    };
    xhr.send();
}


function selectEvent()
{
    codeExo = $('#lexo').val();
    alert(codeExo);

    var xhr = new XMLHttpRequest();
    var url = "ServletExoTypeInfo?codeExo=" + codeExo;
    xhr.open("GET", url, true);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var rep = xhr.responseXML.getElementsByTagName("liste_exo");
            for (var i = 0; i < rep.length; i++)
            {
                var exercice = rep[i].children;
                var code = exercice[0].firstChild.nodeValue;
                var nom = exercice[1].firstChild.nodeValue;
                var objectif = exercice[2].firstChild.nodeValue;
                var description = exercice[3].firstChild.nodeValue;
                var tipsrep = exercice[4].firstChild.nodeValue;
                var tips = exercice[5].firstChild.nodeValue;
                var materiel = exercice[6].firstChild.nodeValue;
                var media = exercice[7].firstChild.nodeValue;

                $("#numExo2").html(code);
//                document.getElementById("numExo2").style.display="none";

                document.getElementById("nomExo2").value = nom;
                document.getElementById("objectifExo2").value = objectif;
                document.getElementById("descriptionExo2").value = description;
                document.getElementById("tipRepExo2").value = tipsrep;
                document.getElementById("tipExo2").value = tips;
                document.getElementById("materielExo2").value = materiel;
                document.getElementById("mediaExo2").value = media;
            }
        }
    };
    xhr.send();

}

function modifierExer()
{
    var id = $("#numExo2").html();
    alert(id);
    var nom = encodeURIComponent(document.getElementById("nomExo2").value);
    var objectif = encodeURIComponent(document.getElementById("objectifExo2").value);
    var description = encodeURIComponent(document.getElementById("descriptionExo2").value);
    var tipRep = encodeURIComponent(document.getElementById("tipRepExo2").value);
    var tip = encodeURIComponent(document.getElementById("tipExo2").value);
    var materiel = encodeURIComponent(document.getElementById("materielExo2").value);
    var media = encodeURIComponent(document.getElementById("mediaExo2").value);

    var xhr = new XMLHttpRequest();
    var url = "ServletModExo?id=" + id + "&nom=" + nom + "&objectif=" + objectif
            + "&description=" + description + "&tipRep=" + tipRep + "&tip="
            + tip + "&materiel=" + materiel + "&media=" + media;
    xhr.open("GET", url, true);
    alert(url);

    if (xhr.status === 200)
    {
        alert("ok");

        document.getElementById("nomExo2").value = "";
        document.getElementById("objectifExo2").value = "";
        document.getElementById("descriptionExo2").value = "";
        document.getElementById("tipRepExo2").value = "";
        document.getElementById("tipExo2").value = "";
        document.getElementById("materielExo2").value = "";
        document.getElementById("mediaExo2").value = "";

    }
}



window.addEventListener("load", listeExo, false);
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("btn").addEventListener("click", creerExer);
    document.getElementById("lexo").addEventListener("click", listeExo);
    document.getElementById("btnM").addEventListener("click", modifierExer);
});

