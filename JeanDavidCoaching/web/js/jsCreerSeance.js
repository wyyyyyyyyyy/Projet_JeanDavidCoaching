/**
 * Cette méthode 
 * 
 * 
 * 
 */

function rechercheExo() {
    var xhr = new XMLHttpRequest();
    var exoBox = this.parentNode;
    var l_champs = exoBox.children;
    var saisie = l_champs[0];
    var nomExo = saisie.value;
    if (nomExo !== "") {
        xhr.open("GET", "ServletRecherche?nomExo=" + nomExo);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var rep = xhr.responseXML.getElementsByTagName("Exercice");
                var txt = "";
                for (var i = 0; i < rep.length; i++) {
                    var exercice = rep[i].children;
                    var code = exercice[0].firstChild.nodeValue;
                    var nom = exercice[1].firstChild.nodeValue;
                    txt += "<li class=\"exo\" id=\"" + code + "\">" + nom + "</li>";
                }
                l_champs[2].innerHTML = txt;

                var list = document.getElementsByClassName("exo");
                console.log(list.length);
                for (var i = 0; i < list.length; i++) {
                    list[i].addEventListener("click", choisirExo);
                }
            }
        };
        xhr.send();
    } else {
        l_champs[2].innerHTML = "";
        l_champs[2].removeAttribute("id");
    }
}

function choisirExo() {
    var exoBox = this.parentNode.parentNode;
    var l_champs = exoBox.children;
    var saisie = l_champs[0];
    var list = this.parentNode;
    var exo = this.firstChild.nodeValue;
    var id = this.getAttribute("id");
    saisie.value = exo;
    list.innerHTML = "";
    list.setAttribute("id", id);
}

function ajouterExo() {
    var exobox = this.parentNode;
    var txt = "<div class=\"shadow-none p-4 mb-4 bg-light\">" + exobox.parentNode.innerHTML + "</div>";
    exobox.parentNode.insertAdjacentHTML('afterend', txt);
    var next = exobox.parentNode.nextSibling.children[0].children;
    next[2].innerHTML = "";
    next[2].removeAttribute("id");
    next[1].innerHTML = "";
    next[6].innerHTML = "";
    next[9].innerHTML = "";
    next[12].innerHTML = "";
    next[15].innerHTML = "";
    activer_btn();
}

function activer_btn() {
    var exobox = document.getElementsByClassName("exoBox");
    var nbexo = exobox.length;
    for (var i = 0; i < nbexo; i++) {
        exobox[i].children[exobox[i].children.length - 2].addEventListener("click", ajouterExo);
        exobox[i].children[exobox[i].children.length - 1].addEventListener("click", supprimerExo);
        if (nbexo === 1) {
            exobox[0].children[exobox[i].children.length - 1].disabled = true;
        } else {
            exobox[0].children[exobox[i].children.length - 1].disabled = false;
            exobox[i].children[exobox[i].children.length - 1].disabled = false;
        }
        exobox[i].children[0].addEventListener("keyup", rechercheExo);
    }
}

function supprimerExo() {
    var del = this.parentNode.parentNode;
    del.remove();
    activer_btn();
}

function verifier() {
    var exoBox = document.getElementsByClassName("exoBox");
    var resultat = true;
    for (var i = 0; i < exoBox.length; i++) {
        //recuperer des donnes des champs
        var nomSeance = document.getElementById("nomSeance").value;
        var desc = document.getElementById("desc").value;
        var details = exoBox[i].children;
        var listExo = details[2].children.length;
        var nomET = details[0].value;
        var codeET = details[2].getAttribute("id");
        var nbtempsexo = details[5].value;
        var nbserie = details[11].value;
        var tempsreposexo = details[14].value;
        var tempsreposserie = details[8].value;

        //definir des verification
        var v_seance = false;
        var v_codeET = false;
        var v_nbtempsexo = false;
        var v_nbserie = false;
        var v_tempsreposexo = false;
        var v_tempsreposserie = false;

        //virifier
        if (nomSeance !== "" && desc !== "") {
            v_seance = true;
            document.getElementById("erreur").innerHTML = "";
        } else if (nomSeance === "" && desc !== "") {
            document.getElementById("erreur").innerHTML = "<div class=\'alert alert-danger alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button><strong>Attention! Champ nom séance est vide</strong></div>";
        } else if (desc === "" && nomSeance !== "") {
            document.getElementById("erreur").innerHTML = "<div class=\'alert alert-danger alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button><strong>Attention! Champ desciption est vide</strong></div>";
        } else {
            document.getElementById("erreur").innerHTML = "<div class=\'alert alert-danger alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button><strong>Attention! Les le nom et la déscription de séance sont obligatoires</strong></div>";
        }

        if (codeET !== null && listExo === 0 && nomET !== "") {
            v_codeET = true;
            details[1].innerHTML = "";
        } else {
            if (codeET === null && listExo > 0) {
                details[1].innerHTML = "Veuillez choisir l'exercice dans la liste";
            } else if (codeET === null && listExo === 0) {
                details[1].innerHTML = "Veuillez entrer et choisir le nom d'exercice";
            }
        }

        if (nbtempsexo !== "") {
            v_nbtempsexo = true;
            details[6].innerHTML = "";
        } else {
            if (details[4].value === "temps d'exercice") {
                details[6].innerHTML = "Veuillez saisir le temps d'exercice";
            } else {
                details[6].innerHTML = "Veuillez saisir le nombre de répétition d'exerice";
            }
        }

        if (nbserie !== "") {
            v_nbserie = true;
            details[12].innerHTML = "";

        } else {
            details[12].innerHTML = "Veuillez saisir le nombre de série";
        }

        if (tempsreposexo !== "") {
            v_tempsreposexo = true;
            details[15].innerHTML = "";
        } else {
            details[15].innerHTML = "Veuillez saisir le temps de repos entre chaque group d'exercice";
        }

        if (tempsreposserie !== "") {
            v_tempsreposserie = true;
            details[9].innerHTML = "";
        } else {
            details[9].innerHTML = "Veuillez saisir le temps de repos entre série";
        }

        //retour du resultat de verification
        if (!(v_seance === true && v_codeET === true && v_nbtempsexo === true && v_nbserie === true
                && v_tempsreposexo === true && v_tempsreposserie === true)) {
            resultat = false;
        }
    }
    alert(resultat);
    return resultat;
}

function enregistrerSeance() {
    var xhr = new XMLHttpRequest();
    //enregistrer seance
    var nomSeance = document.getElementById("nomSeance").value;
    var desc = document.getElementById("desc").value;
    var codeSeance = "";

    xhr.open("GET", "ServletEnrgSeanceType?nomSeance=" + nomSeance + "&desc=" + desc);
    xhr.onload = function () {
        if (xhr.status === 200) {
            codeSeance = xhr.responseText;
            var texter = "<div class=\'alert alert-success alert-dismissible\'><button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;</button>La séance <strong>";
            texter += nomSeance + " - codeSeance : " + codeSeance;
            texter += "</strong> a bien été créée</div>";
            document.getElementById("erreur").innerHTML = texter;
            enregistrerExo(codeSeance);
        }
    };
    xhr.send();
}

function enregistrerExo(codeSeance) {
    //ajouter des exo
    var exoBox = document.getElementsByClassName("exoBox");
    for (var i = 0; i < exoBox.length; i++) {
        var xhr = new XMLHttpRequest();
        var details = exoBox[i].children;
        var codeET = details[2].getAttribute("id");
        var ordre = i + 1;
        var nbrep = 0;
        var tempsexo = 0;
        if (details[4].value === "temps d'exercice") {
            tempsexo = details[5].value;
        } else {
            nbrep = details[5].value;
        }
        var nbserie = details[11].value;
        var tempsreposexo = details[14].value;
        var tempsreposserie = details[8].value;
        xhr.open("GET", "ServletAjouterExoType?codeSeance=" + codeSeance + "&codeET=" + codeET
                + "&ordre=" + ordre + "&nbrep=" + nbrep + "&nbserie=" + nbserie
                + "&tempsexo=" + tempsexo + "&tempsreposserie=" + tempsreposserie
                + "&tempsreposexo=" + tempsreposexo);
        xhr.onload = function () {
            if (xhr.status === 200) {
                alert("insert : " + i);
            }
        };
        xhr.send();
    }
}

function valider() {
    var v_resultat = verifier();
    if (v_resultat === true) {
        this.setAttribute("data-toggle", "modal");
        this.setAttribute("data-target", "#exampleModal");
        document.getElementById("verifier").addEventListener("click", enregistrerSeance);
    }
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementsByClassName("exoBox")[0].children[0].addEventListener("keyup", rechercheExo);
    document.getElementsByClassName("exoBox")[0].children[0].addEventListener("click", rechercheExo);
    document.getElementsByClassName("btn_ajouter")[0].addEventListener("click", ajouterExo);
    document.getElementById("btn_valider").addEventListener("click", valider);
    activer_btn();
});

