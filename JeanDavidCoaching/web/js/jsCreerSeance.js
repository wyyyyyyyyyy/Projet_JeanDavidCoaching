/**
 * Cette méthode 
 * 
 * 
 * 
 */

function creerSeance() {
    var xhr = new XMLHttpRequest();
    var nomSeance = document.getElementById("nomSeance").value;
    var desc = document.getElementById("desc").value;
    if (nomSeance !== "" && desc !== "") {
        console.log(nomSeance);
        console.log(desc);
        xhr.open("GET", "ServletEnrgSeanceType?nomSeance=" + nomSeance + "&desc=" + desc);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var rep = xhr.responseText;
                 var texter ="<div class=\'alert alert-success alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button>La séance <strong>";
                 texter += nomSeance;
                 texter +="</strong> a bien été créée</div>";
                document.getElementById("erreur").innerHTML = texter;
                       
                var exo = document.getElementsByClassName("exoBox");
                exo[0].insertAdjacentHTML('beforebegin', rep);
            }
        }
        xhr.send();
    } else {
        if (nomSeance === "" && desc === "") {
            document.getElementById("erreur").innerHTML = "<div class=\'alert alert-danger alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button><strong>Attention! Les deux champs sont obligatoires</strong></div>";
        } else {
            if (nomSeance !== "") {
                document.getElementById("erreur").innerHTML = "<div class=\'alert alert-danger alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button><strong>Attention! Champ nom séance est vide</strong></div>";
            } else if (desc !== "") {
                document.getElementById("erreur").innerHTML = "<div class=\'alert alert-danger alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button><strong>Attention! Champ desciption est vide</strong></div>";
            }
        }

    }
}

function rechercheExo() {
    var xhr = new XMLHttpRequest();
    var exoBox = document.getElementsByClassName("exoBox");
    var l_champs = exoBox[exoBox.length - 1].children;
    var saisie = l_champs[0];
    var nomExo = saisie.value;
    if (nomExo !== "") {
        console.log(nomExo);
        xhr.open("GET", "ServletRecherche?nomExo=" + nomExo);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var rep = xhr.responseXML.getElementsByTagName("Exercice");
                var txt = "";
                for (var i = 0; i < rep.length; i++) {
                    var exercice = rep[i].children;
                    var code = exercice[0].firstChild.nodeValue;
                    var nom = exercice[1].firstChild.nodeValue;
                    txt += "<p class=\"exo\" id=\"" + code + "\">" + nom + "</p>";
                }
                var listExo = document.getElementsByClassName("listExo");
                listExo[listExo.length - 1].innerHTML = txt;

                var list = document.getElementsByClassName("exo");
                console.log(list.length);
                for (var i = 0; i < list.length; i++) {
                    list[i].addEventListener("click", choisirExo);
                }
            }
        };
        xhr.send();
    }
}

function choisirExo() {
    var exoBox = document.getElementsByClassName("exoBox");
    var l_champs = exoBox[exoBox.length - 1].children;
    var saisie = l_champs[0];
    var list = document.getElementsByClassName("listExo");
    var exo = this.firstChild.nodeValue;
    var id = this.getAttribute("id");
    saisie.value = exo;
    list[list.length - 1].innerHTML = "";
    list[list.length - 1].setAttribute("id", id);
}

function ajouterExo() {
    var xhr = new XMLHttpRequest();
    var span = document.getElementsByClassName("listExo");
    var codeET = span[span.length - 1].getAttribute("id");
    var nomSeance = document.getElementById("nomSeance").value;
    var exobox = document.getElementsByClassName("exoBox");
    var ordre = exobox.length;
    var detail = exobox[ordre - 1].children;
    alert(detail.length);
    var nbrep = 0;
    var tempsexo = 0;
    if (detail[3].value === "temps d'exercice") {
        tempsexo = detail[4].value;
    } else {
        nbrep = detail[4].value;
    }
    ;
    var nbserie = detail[8].value;
    var tempsreposexo = detail[10].value;
    var tempsreposserie = detail[6].value;

    var txt = "<div class=\"exoBox\">" + exobox[ordre - 1].innerHTML
            + "</div>";
    xhr.open("GET", "ServletAjouterExoType?nomSeance=" + nomSeance + "&codeET=" + codeET
            + "&ordre=" + ordre + "&nbrep=" + nbrep + "&nbserie=" + nbserie
            + "&tempsexo=" + tempsexo + "&tempsreposserie=" + tempsreposserie
            + "&tempsreposexo=" + tempsreposexo);
    xhr.onload = function () {
        if (xhr.status === 200) {
            alert(txt);
            document.getElementById("ajouter").insertAdjacentHTML('beforebegin', txt);
        }
        document.getElementsByClassName("exoBox")[document.getElementsByClassName("exoBox")
                .length - 1].children[0].addEventListener("keyup", rechercheExo);
    }
    xhr.send();
}

function supprimerExo() {

}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementsByClassName("exoBox")[document.getElementsByClassName("exoBox")
            .length - 1].children[0].addEventListener("keyup", rechercheExo);
    document.getElementById("ajouter").addEventListener("click", ajouterExo);
    document.getElementById("creerSeance").addEventListener("click", creerSeance);
});

