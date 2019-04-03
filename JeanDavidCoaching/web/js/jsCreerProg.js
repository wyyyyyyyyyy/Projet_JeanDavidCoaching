/*
 *   Document   : creerProgramme
 *    Author     : yiyang
 */

/*
 * check if coach add the same name.
 * use servlet: ServletCheckNameProgType.
 * @returns {undefined}
 */
function checkNom() {
    /*--initialisation--*/
    // zone: message of check nom existe; message of champ empty
    var eltDivMsgCheck = document.getElementById("checkNomMsg");
    var eltmsgErrorNom = document.getElementById("msgErrorNom");
    eltDivMsgCheck.innerHTML = "";
    eltmsgErrorNom.innerHTML = "";
    document.getElementById("btn_addProg").disabled = false;

    // verifier le nom de programme type saisie
    var name = encodeURIComponent(document.getElementById("nomProg").value);
    if (name !== "") {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ServletCheckNameProgType?nom=" + name, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var msg = xhr.responseXML.getElementById("msg").childNodes[0].nodeValue;

                // affichier les messages
                var errorMsg = "<div class=\'alert alert-danger alert-dismissible\'>" +
                        "<button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;" +
                        "</button>Nom déjà existe.</div>";
                var correctMsg = "<div class=\'alert alert-success alert-dismissible\'>" +
                        "<button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;" +
                        "</button>Nouveau Nom.</div>";

                if (msg === "existe") {
                    eltDivMsgCheck.innerHTML = correctMsg;
                    document.getElementById("btn_addProg").disabled = false;
                } else {
                    eltDivMsgCheck.innerHTML = errorMsg;
                    document.getElementById("btn_addProg").disabled = true;
                }
            }
        }
        xhr.send();
    }
}

/*
 * use servlet: ServletFindSeanceType.
 * @returns {list of senace type}
 */
function afficherSeanceType() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletFindSeanceType", true);
    var div = this.parentNode;
    xhr.onload = function () {
        if (xhr.status === 200) {
            var lstS = xhr.responseXML.getElementsByTagName("list_ST");
            var childrenlstS = lstS[0].children;
            console.log(childrenlstS.length);
            var txt = "<option value=\"\">---------</option> ";
            for (var i = 0; i < childrenlstS.length; i++) {
                var childlstS = childrenlstS[i].children;
                txt += "<option value=\"" + childlstS[0].firstChild.nodeValue + "\"> " + childlstS[1].firstChild.nodeValue + "</option>";
            }
            div.children[1].innerHTML = txt;
        }
    };
    xhr.send();
    activer_btn();
}

/*
 * to find all seance type in data base. 
 * use servlet:ServletFindObjSeanceType.
 * @returns {list of seance in data base}
 */
function rechercheObjSeanceType() {
    var xhr = new XMLHttpRequest();

    var div = this.parentNode;
    var children = div.children;
    var find = children[1].value;
    var listS = children[4];
    if (find === "") {
        listS.style.display = "none";
    } else {
        listS.style.display = "block";
    }

    if (find !== "") {
        xhr.open("GET", "ServletFindObjSeanceType?codest=" + find, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var lstObjST = xhr.responseXML.getElementsByTagName("objST");
                console.log(lstObjST.length);
                var txt = "";
                if (lstObjST.length !== 0) {
                    var childlstObjST = lstObjST[0].children;
                    txt += "<p>Description de séance: " + childlstObjST[0].firstChild.nodeValue + "</p>";
                }
                listS.innerHTML = txt;
            }
        };
        xhr.send();
    }

    activer_btn();

}

/**
 * when click button ajouter une seance.
 * @returns {a new div of zone add seance type}
 */
function addDivForAddST() {
    // add a new zone of add seance type 
    var divs = this.parentNode;
    var txt = "<div class=\"shadow-none p-4 mb-4 bg-light\">" + divs.innerHTML + "</div>";
    divs.insertAdjacentHTML('afterend', txt);
    // initialise zone
    var nextnode = divs.nextSibling.children[4];
    nextnode.innerHTML = "<div></div>";
    activer_btn();
}

/**
 * when click button supprimer cette seance.
 * @returns {delete a div of zone add seance type}
 */
function suppDivForAddST() {
    var del = this.parentNode;
    del.remove();
    activer_btn();
}

/*
 * controler les buttons actives.
 */
function activer_btn() {
    var divs = document.getElementsByClassName("shadow-none p-4 mb-4 bg-light");
    var ordre = divs.length;
    for (var i = 0; i < ordre; i++) {
        var children = divs[i].children;
        if (children.length === 8) {
            //delete the error message
            divs[i].addEventListener("click", suppMsgErrorForSeance);
        } else {
            // button ajouter prog disabled
            document.getElementById("btn_addProg").disabled = false;
            // event for find the description of a seance type
            children[1].addEventListener("change", rechercheObjSeanceType);
            // button for find the seance types
            children[2].addEventListener("click", afficherSeanceType);
            // event for button of add a new zone 
            children[5].addEventListener("click", addDivForAddST);
            // event for button of delete a zone 
            // verifier que le nombre de div n'egale pas 0
            if (ordre === 1) {
                children[6].disabled = true;
            } else {
                children[6].disabled = false;
                children[6].addEventListener("click", suppDivForAddST);
            }
        }
    }
}

/*
 * verifier si les champs sont vides.
 * demander le coach de verifier l'ordre de seance.
 */
function confirmerCreerProgType() {
    var txt = "";
    var champNomDesOK = true;
    var champSeanceOK = true;
    // recuperer leselements
    var nom = document.getElementById("nomProg");
    var des = document.getElementById("descriptionProg");
    var divs = document.getElementsByClassName("shadow-none p-4 mb-4 bg-light");
    var ordre = divs.length;
    var listSemaine = [];
    var listSeance = [];

    // Check: nom prog type 
    if (nom.value === "" || nom.value === null) {
        var errorNom = "<div class=\'alert alert-danger alert-dismissible\'>" +
                "<button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;" +
                "</button><strong>Attention! Champ " +
                "Nom de programme est vide</strong></div>";
        document.getElementById("msgErrorNom").innerHTML = errorNom;
        document.getElementById("btn_addProg").disabled = true;
        champNomDesOK = false;
    } else {
        txt += "<p>Nom de programme type: " + nom.value + "</P>";
    }
    // Check: description prog type
    if (des.value === "" || des.value === null) {
        var errorDes = "<div class=\'alert alert-danger alert-dismissible\'>" +
                "<button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;" +
                "</button><strong>Attention! Champ " +
                "Description de programme est vide</strong></div>";
        document.getElementById("msgErrorDes").innerHTML = errorDes;
        document.getElementById("btn_addProg").disabled = true;
        champNomDesOK = false;
    } else {
        txt += "<p>Description de programme type: " + des.value + "</P>";
    }

    // Check; numSem, ordreSeanceType
    for (var i = 0; i < ordre; i++) {
        var children = divs[i].children;
        // verifier si les champs sont vides
        if (children[0].value === "" || children[1].value === "") {
            var errorChamp = "<div id =\'sem" + i + "\' class=\'alert alert-danger alert-dismissible\'>" +
                    "<button type=\'button\' class=\'close\' data-dismiss=\'alert\'>&times;" +
                    "</button><strong>Attention!</strong> Champ est vide</div>";
            divs[i].insertAdjacentHTML("beforeend", errorChamp);
            document.getElementById("btn_addProg").disabled = true;
            champSeanceOK = false;
        } else {
            listSemaine.push(children[0].value);
            listSeance.push(children[1].value);
        }
    }

    for (var x = 0; x < listSemaine.length; x++) {
        txt += "<p>NumSem: " + listSemaine[x] + "</P>";
        txt += "<p>OrdreSeance " + (x + 1);
        txt += "    CodeSeance: " + listSeance[x] + "</P>";
    }

    // Display a confirmation box
    if (champSeanceOK === true && champNomDesOK === true) {
        document.getElementById("zonetext").innerHTML = txt;
        this.setAttribute("data-toggle", "modal");
        this.setAttribute("data-target", "#exampleModal");
        // when click valider
//        $(document).on("click","#verifier",function(){  
        $("#verifier").on("click", function () {
//            this.setAttribute("data-dismiss", "#exampleModal");

//            document.getElementById("verifier").disabled = "true";
            document.getElementById("verifier").innerHTML = "En cours d'enregistrer";
            document.getElementById("verifier").disabled = true;
            addProgType(nom.value, des.value, listSemaine, listSeance);


//            this.setAttribute("data-dismiss", "#exampleModal");

//            if (msgOK === true) {
//                var txt = "<div class=\"shadow-none p-4 mb-4 bg-light\"><h2>Message: " +
//                        "Vous avec bien créé un nouveau programme.</h2></div>";
//                document.getElementById("msgCreateOK").innerHTML = txt;
//            }

        });
    }
    activer_btn();
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

/*
 * delete the error message when user click the champ.
 * @returns {undefined}
 */
function suppMsgErrorForSeance() {
    var child = this.children;
    child[7].remove();
    activer_btn();
}

/*
 * method for add a new prog type in data base.
 * use servlet: ServletAddProgType
 * @returns {undefined}
 */
function addProgType(nom, des, listSemaine, listSeance) {
    var xhr = new XMLHttpRequest();
    // parametres
    var url = "ServletAddProgType?nom=" + nom + "&des=" + des + "&listSemaine="
            + listSemaine + "&listSeance=" + listSeance;
    xhr.open("GET", url, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            var msg = xhr.responseXML.getElementsByTagName("message");
            if (msg[0].firstChild.nodeValue !== "") {
                sleep(1000);
                location.reload(true);
            }
        }
    };
    xhr.send();

}

/*
 * method for delete the empty error message when keyup
 */
function suppMsgEmptyDes() {
    var eltmsgError = document.getElementById("msgErrorDes");
    eltmsgError.innerHTML = "";
}


/*
 * function for add events .
 */
document.addEventListener("DOMContentLoaded", () => {
    // recuperer les divs "ajouter une seance"
    var divs = document.getElementsByClassName("shadow-none p-4 mb-4 bg-light");
    //Nombre de div "ajouter une seance"
    var ordre = divs.length;
    // Le dernier div
    var div = divs[ordre - 1];
    // les nodes d'enfants de dernier div
    var childrenDiv = div.children;

    // button for find the seance types
    childrenDiv[2].addEventListener("click", afficherSeanceType);
    // event for find the description of a seance type
    childrenDiv[1].addEventListener("change", rechercheObjSeanceType);
    // event for button of add a new zone 
    childrenDiv[5].addEventListener("click", addDivForAddST);
    // event for button of delete a zone 
    childrenDiv[6].addEventListener("click", suppDivForAddST);
    // verifier les buttons
    activer_btn();
    // event for check the information of prog type before add the information in data base
    document.getElementById("btn_addProg").addEventListener("click", confirmerCreerProgType);
    // event for check the name of programme type and delete empty error message 
    document.getElementById("nomProg").addEventListener("keyup", checkNom);
    // event for delete empty error message of description
    document.getElementById("descriptionProg").addEventListener("keyup", suppMsgEmptyDes);
});
