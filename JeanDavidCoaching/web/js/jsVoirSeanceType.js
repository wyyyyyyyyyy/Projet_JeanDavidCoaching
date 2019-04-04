/* 
 * this js page is used by voirSeanceType.jsp.
 */

/*
 * get all seances types in data base.
 * use servlet: ServletFindSeanceType.
 * @returns {list of senace type}
 */
function afficherSeanceType() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletFindSeanceType", true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            var lstS = xhr.responseXML.getElementsByTagName("list_ST");
            var childrenlstS = lstS[0].children;
            var txt = "<option value=\"head\">----------</option>";
            for (var i = 0; i < childrenlstS.length; i++) {
                var childlstS = childrenlstS[i].children;
                txt += "<option value=\"" + childlstS[0].firstChild.nodeValue + "\"> "
                        + childlstS[1].firstChild.nodeValue + "</option>";
            }
            document.getElementById("zoneST").innerHTML = txt;
        }
    };
    xhr.send();
}


/*
 * to find all seance type in data base. 
 * use servlet:ServletFindObjSeanceType.
 * @returns {list of seance in data base}
 */
function rechercheObjSeanceType() {
    /*-- get code of the seance type --*/
    var codeST = document.getElementById("zoneST").value;
    var nomST = this.options[this.selectedIndex].text;

    /*-- controle the display of zone "affiche le nom et la description de seance type"--*/
    var divAffiche = document.getElementById("zoneInfoST");
    if (codeST !== "head") {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ServletFindObjSeanceType?codest=" + codeST, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                var lstObjST = xhr.responseXML.getElementsByTagName("objST");
                console.log(lstObjST.length);
                if (lstObjST.length !== 0) {
                    var childlstObjST = lstObjST[0].children;
                    divAffiche.innerHTML = "<br><blockquote class=\'blockquote text-center bg-light text-dark\'>" +
                            "<p class=\'text-dark\'>Séance : " + nomST + "</p><br>" +
                            "<footer class=\'blockquote-footer\'><strong class=\'text-dark\'>" +
                            " Description : </strong><cite class=\'text-warning\'>" +
                            childlstObjST[0].firstChild.nodeValue + "</cite></footer></blockquote>";
                }
            }
        };
        xhr.send();
        afficheExoType(codeST);
    } else {
        divAffiche.innerHTML = "<div class=\"container\"><div class=\"py-5 text-center\">" +
                "<h1>Choisir un client</h1></div></div>";
    }
}


/*
 * find the information of a exercice type by a seance type's code.
 * use servlet: ServletFindETbySTCode.
 * @returns {information of a exercice type}
 */
function afficheExoType(codeST) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ServletFindETbySTCode?codest=" + codeST, true);
    xhr.onload = function () {
        var lexoType = xhr.responseXML.getElementsByTagName("ExerciceType");
                var txt_seance = "<div>"
                +"<table class=\'table table-hover table-dark\'><thead><tr>"+
                "<th scope=\'col\'>Repetitions</th><th scope=\'col\'>"+
                "Nom exercice</th><th scope=\'col\'>Recuperation serie</th>"+
                "<th scope=\'col\'>Materiel</th><th scope=\'col\'>"+
                "Tips Repetition</th><th scope=\'col\'>Tips Exercice</th>"+
                "<th scope=\'col\'>Recuperation exercice</th></tr></thead><tbody>";

            for (var j = 0; j < lexoType.length; j++){  
                var exo = lexoType[j].children;
                var codeE = exo[0].firstChild.nodeValue;
                var nomE = exo[1].firstChild.nodeValue;
                var descE = exo[2].firstChild.nodeValue;
                var objE = exo[3].firstChild.nodeValue;

                var tipsE = exo[4].firstChild.nodeValue;
                var tipsRep = exo[5].firstChild.nodeValue;

                var mat = exo[6].firstChild.nodeValue;
                var ordreE = exo[7].firstChild.nodeValue;
                var nbRep = exo[8].firstChild.nodeValue;
                var nbSerie = exo[9].firstChild.nodeValue;
                var tempsExo = exo[10].firstChild.nodeValue;
                var tempsreposS = exo[11].firstChild.nodeValue;
                var tempsreposE = exo[12].firstChild.nodeValue;

                var solution = 0;
                var solutiontips = "Aucun";
                var solutiontips2 = "Aucun";

                if (tempsExo !== "null") {
                    solution = tempsExo + "s";
                } else {
                    solution = nbRep;
                }
                if (tipsE !== "--") {
                    solutiontips = tipsE;
                }
                if (tipsRep !== "--") {
                    solutiontips2 = tipsRep;
                }
                txt_seance += "<tr>";
                txt_seance += "<td>" + nbSerie + "x" + solution + "</td>";
                txt_seance += "<td>" + nomE + "</td>";
                txt_seance += "<td>" + tempsreposS + "</td>";
                txt_seance += "<td>" + mat + "</td>";
                txt_seance += "<td>" + solutiontips2 + "</td>";
                txt_seance += "<td>" + solutiontips + "</td>";
                txt_seance += "<td>" + tempsreposE + "</td>";
                txt_seance += "</tr>";
            }
            txt_seance += "</tbody></table>";
            txt_seance += "</div>";
            document.getElementById("zoneExoType").innerHTML = txt_seance;
    };
xhr.send();
}

/*afficheExoType
 * function for add events .
 */
document.addEventListener("DOMContentLoaded", () => {
    // initialisation 
    document.getElementById("btn_findST").addEventListener("click", afficherSeanceType);
    // event for find the description of a seance type
    document.getElementById("zoneST").addEventListener("change", rechercheObjSeanceType);
});
