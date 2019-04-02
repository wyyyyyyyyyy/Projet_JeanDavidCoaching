/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function afficheseances()
{
    var codeprog = document.getElementById("codept").value;
    xhr = new XMLHttpRequest();
    var param = "codeprog=" + codeprog;
    var url = "ServletVoirDetailsProgType1";
    go = url + "?" + param;
    xhr.open("GET", go, true);
    xhr.onload = function () {
        if (xhr.status === 200)
        {

//                var codeProg = xhr.responseXML.getElementsByTagName("Code");
            var nomProg = xhr.responseXML.getElementsByTagName("Nom");
//                alert(nomProg[0].firstChild.nodeValue);
            var descriptionProg = xhr.responseXML.getElementsByTagName("Description")[0].firstChild.nodeValue;
            text = "<br><blockquote class=\'blockquote text-center bg-light text-dark\'><p class=\'text-dark\'>Programme : " + nomProg[0].firstChild.nodeValue + "</p><br>";
            text += "<footer class=\'blockquote-footer\'><strong class=\'text-dark\'> Description : </strong><cite class=\'text-warning\'>" + descriptionProg + "</cite></footer></blockquote>";
            var prog = document.getElementById("prog");
            prog.innerHTML = text;
            var lseanceType = xhr.responseXML.getElementsByTagName("SeanceType");
//                alert(lseanceType.length);
            for (var i = 0; i < lseanceType.length; i++)
            {

                var seance = lseanceType[i].children;
//                    alert(seance);
                var codeS = seance[0].firstChild.nodeValue;
//                    alert(codeS);
                var nomS = seance[1].firstChild.nodeValue;
                var descS = seance[2].firstChild.nodeValue;
//                     "<div class=\"seance\"><p>codeS" + codeS + "</p><p>nomS" + 
//                            nomS + "</p><p>descS" + descS + "</p></div>"

                var txt_seance = "<div class=\'row\'><div class=\'col\'><button class=\'btn btn-outline-warning btn-block\' data-toggle=\'collapse\' data-target=\'#aa" + i + "\'><h3>" + nomS + "</h3><h5>" + descS + "</h5></button><div>"
//                    alert(txt_seance);
                txt_seance += "<div id=\'aa" + i + "\' class=\'col collapse\'>";
                txt_seance += "<table class=\'table table-hover table-dark\'>";
                txt_seance += "<thead><tr><th scope=\'col\'>Repetitions</th><th scope=\'col\'>Nom exercice</th><th scope=\'col\'>Recuperation serie</th><th scope=\'col\'>Materiel</th><th scope=\'col\'>Tips Repetition</th><th scope=\'col\'>Tips Exercice</th><th scope=\'col\'>Recuperation exercice</th></tr></thead><tbody>";
                for (var j = 3; j < seance.length; j++) {
                    var exo = seance[j].children;
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
                    
                    if(tempsExo !== "null"){
                        solution = tempsExo+"s";
                    }else{
                            solution = nbRep;
                    }
                    if(tipsE !== "--"){
                        solutiontips = tipsE;
                    }
                    if(tipsRep !== "--"){
                        solutiontips2 = tipsRep;
                    }

                    txt_seance += "<tr>";
                    txt_seance += "<td>"+nbSerie+"x" + solution+"</td>"
                    txt_seance += "<td>" + nomE + "</td>"
                    txt_seance += "<td>" + tempsreposS + "</td>"
                    txt_seance += "<td>" + mat + "</td>"
                    txt_seance += "<td>" + solutiontips2 + "</td>"
                    txt_seance += "<td>" + solutiontips + "</td>"
                    txt_seance += "<td>" + tempsreposE + "</td>"
                    txt_seance += "</tr>";
                    
                }
                txt_seance += "</table>";
                txt_seance += "</div></div>";
                prog.insertAdjacentHTML("beforeend", txt_seance);
            }

        }
    }

    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("codept").addEventListener("change", afficheseances);
    document.getElementById("codept").addEventListener("click", afficheseances);
});