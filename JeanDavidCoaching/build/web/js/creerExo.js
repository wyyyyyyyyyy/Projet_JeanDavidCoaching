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
    xhr.open("GET", url, true);
//    alert(url);

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            $.alert("L'exercice est déjà enregistré.", "Succès");
            document.getElementById("nomExo").value = "";
            document.getElementById("objectifExo").value = "";
            document.getElementById("descriptionExo").value = "";
            document.getElementById("tipRepExo").value = "";
            document.getElementById("tipExo").value = "";
            document.getElementById("materielExo").value = "";
            document.getElementById("mediaExo").value = "";
            document.getElementById("preview").style.display = "none";
        }
    };
    xhr.send();
}


function listeExo()
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletListeExo");
    xhr.onload = function () {
        if (xhr.status === 200) {
            var rep = xhr.responseXML.getElementsByTagName("Exercice");
            var txt = "<option></option>";
            for (var i = 0; i < rep.length; i++) {
                var exercice = rep[i].children;
                var code = exercice[0].firstChild.nodeValue;
                var nom = exercice[1].firstChild.nodeValue;
                txt += "<option class=\"exo\" value=\"" + code + "\">" + code + " " + nom + "</option>";
            }
            var elt = document.getElementById("lexo");
            elt.innerHTML = txt;
        }
    };
    xhr.send();
}

function processKey()
{
    var nom = document.getElementById("saisie").value;
    var elt = document.getElementById("zoneaff");
    if (nom === "")
    {
        elt.style.display = "none";
    } else
    {
        // Objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ServletRecherche?nomExo=" + nom);
        xhr.onload = function ()
        {
            // Si la requête http s'est bien passée.
            if (xhr.status === 200)
            {
                // récupère la répose du serveur
                var rep = xhr.responseXML.getElementsByTagName("Exercice");
                var txt = "";
                for (var i = 0; i < rep.length; i++) {
                    var exercice = rep[i].children;
                    var code = exercice[0].firstChild.nodeValue;
                    var nom = exercice[1].firstChild.nodeValue;
                    txt += "<div class=\"exo\" value=\"" + code + "\" id=\"" + code + "\">" + nom + "</div>";
                }
                elt.style.display = "block";
                elt.innerHTML = txt;

                var choix = document.querySelectorAll(".exo");
                for (var i = 0; i < choix.length; i++)
                {
                    choix[i].addEventListener("click", selectEvent);
                }
            }
        };
        xhr.send();
    }
}

function selectEvent()
{
    var codeExo = this.getAttribute("value");
//    alert(codeExo);

    var xhr = new XMLHttpRequest();
    var url = "ServletExoTypeInfo?codeExo=" + codeExo;
    xhr.open("GET", url, true);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var rep = xhr.responseXML.getElementsByTagName("Exercice");
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
                document.getElementById("numExo2").style.display = "none";

                document.getElementById("nomExo2").value = nom;
                document.getElementById("objectifExo2").value = objectif;
                document.getElementById("descriptionExo2").value = description;
                document.getElementById("tipRepExo2").value = tipsrep;
                document.getElementById("tipExo2").value = tips;
                document.getElementById("materielExo2").value = materiel;
                document.getElementById("mediaExo2").value = media;
                document.getElementById("preview2").style.display = "none";
                document.getElementById("saisie").value = "";
                document.getElementById("zoneaff").style.display = "none";

            }
        }
    };
    xhr.send();

}

function modifierExer()
{
    var id = $("#numExo2").html();
//    alert(id);
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
//    alert(url);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            $.alert("L'exercice est déjà modifié.", "Succès");
            $("#numExo2").html("");
            document.getElementById("nomExo2").value = "";
            document.getElementById("objectifExo2").value = "";
            document.getElementById("descriptionExo2").value = "";
            document.getElementById("tipRepExo2").value = "";
            document.getElementById("tipExo2").value = "";
            document.getElementById("materielExo2").value = "";
            document.getElementById("mediaExo2").value = "";
            document.getElementById("preview2").style.display = "none";
        }
    };
    xhr.send();
}

function supprimerExer()
{
    var id = $("#numExo2").html();
//    alert(id);
    var xhr = new XMLHttpRequest();
    var url = "ServletSupExo?id=" + id;
    xhr.open("GET", url, true);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            $.alert("L'exercice est déjà supprimé.", "Succès");
            $("#numExo2").html("");
            document.getElementById("nomExo2").value = "";
            document.getElementById("objectifExo2").value = "";
            document.getElementById("descriptionExo2").value = "";
            document.getElementById("tipRepExo2").value = "";
            document.getElementById("tipExo2").value = "";
            document.getElementById("materielExo2").value = "";
            document.getElementById("mediaExo2").value = "";
            document.getElementById("preview2").style.display = "none";
        }
    };
    xhr.send();
}

function imgPreview()
{
    var media = document.getElementById("mediaExo").value;
//    alert(media);
    document.getElementById("preview").src = media;
    document.getElementById("preview").style.display = "";
}

function imgPreview2()
{
    var media = document.getElementById("mediaExo2").value;
//    alert(media);
    document.getElementById("preview2").src = media;
    document.getElementById("preview2").style.display = "";
}

function verifierExoCre()
{
    var nom = encodeURIComponent(document.getElementById("nomExo").value);
    var objectif = encodeURIComponent(document.getElementById("objectifExo").value);
    var description = encodeURIComponent(document.getElementById("descriptionExo").value);
    var tipRep = encodeURIComponent(document.getElementById("tipRepExo").value);
    var tip = encodeURIComponent(document.getElementById("tipExo").value);
    var materiel = encodeURIComponent(document.getElementById("materielExo").value);
    var media = encodeURIComponent(document.getElementById("mediaExo").value);

    var notice = document.getElementById("notice");
    notice.style.color = "#F00";

    var xhr = new XMLHttpRequest();
    var url = "ServletVerifExo?nomExo=" + nom;
    xhr.open("GET", url, true);
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var exist = xhr.responseXML.getElementsByTagName("verifier")[0].firstChild.nodeValue;

            if (nom === "") {
                notice.innerHTML = "* Veuillez saisir le nom d'exercice";
            } else if (exist === "true") {
                notice.innerHTML = "* Le nom existe déjà";
            } else if (objectif === "") {
                notice.innerHTML = "* Veuillez saisir l'objectif d'exercice";
            } else if (description === "") {
                notice.innerHTML = "* Veuillez saisir la description d'exercice";
            } else if (tipRep === "") {
                notice.innerHTML = "* Veuillez saisir les tips de répétition d'exercice";
            } else if (tip === "") {
                notice.innerHTML = "* Veuillez saisir les tips d'exercice";
            } else if (materiel === "") {
                notice.innerHTML = "* Veuillez saisir les matériels d'exercice";
            } else if (media === "") {
                notice.innerHTML = "* Veuillez saisir le lien de l'image d'exercice";
            } else {
                $("#notice").empty();
                $.confirm({
                    title: 'Confirmation',
                    content: 'Voulez-vous enregistrer cet exercice ?',
                    buttons: {
                        Oui: function () {
                            creerExer();
                        },
                        Non: function () {}
                    }
                });
            }
        }
    };
    xhr.send();
}

function verifierExoMod()
{
    var objectif = encodeURIComponent(document.getElementById("objectifExo2").value);
    var description = encodeURIComponent(document.getElementById("descriptionExo2").value);
    var tipRep = encodeURIComponent(document.getElementById("tipRepExo2").value);
    var tip = encodeURIComponent(document.getElementById("tipExo2").value);
    var materiel = encodeURIComponent(document.getElementById("materielExo2").value);
    var media = encodeURIComponent(document.getElementById("mediaExo2").value);

    var notice = document.getElementById("notice2");
    notice.style.color = "#F00";

    if (objectif === "") {
        notice.innerHTML = "* Veuillez saisir l'objectif d'exercice";
    } else if (description === "") {
        notice.innerHTML = "* Veuillez saisir la description d'exercice";
    } else if (tipRep === "") {
        notice.innerHTML = "* Veuillez saisir les tips de répétition d'exercice";
    } else if (tip === "") {
        notice.innerHTML = "* Veuillez saisir les tips d'exercice";
    } else if (materiel === "") {
        notice.innerHTML = "Veuillez saisir les matériels d'exercice";
    } else if (media === "") {
        notice.innerHTML = "* Veuillez saisir le lien de l'image d'exercice";
    } else {
        $("#notice2").empty();
        $.confirm({
            title: 'Modification',
            content: 'Voulez-vous modifier cet exercice ?',
            buttons: {
                Oui: function () {
                    modifierExer();
                },
                Non: function () {}
            }
        });
    }

}

function confirmerSup()
{
    $.confirm({
        title: 'Suppression',
        content: 'Voulez-vous supprimer cet exercice ?',
        buttons: {
            Oui: function () {
                supprimerExer();
            },
            Non: function () {}
        }
    });
}

window.addEventListener("load", processKey, false);
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("btn").addEventListener("click", verifierExoCre);
    document.getElementById("btnM").addEventListener("click", verifierExoMod);
    document.getElementById("btnS").addEventListener("click", confirmerSup);
    document.getElementById("saisie").addEventListener("keyup", processKey);
}
);

