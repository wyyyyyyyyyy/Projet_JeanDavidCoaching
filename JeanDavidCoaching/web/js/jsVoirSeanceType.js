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
function afficheExoType() {

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
