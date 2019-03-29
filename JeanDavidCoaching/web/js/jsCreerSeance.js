/**
 * Cette méthode 
 * 
 * 
 * 
 */

function creerSeance(){
    var xhr = new XMLHttpRequest();
    var nomSeance = document.getElementById("nomSeance").value;
    var desc = document.getElementById("desc").value;
    if(nomSeance!=="" && desc!==""){
        console.log(nomSeance);
        console.log(desc);
        xhr.open("GET","ServletEnrgSeanceType?nomSeance=" + nomSeance + "&desc=" + desc);
        xhr.onload = function(){
            if(xhr.status === 200){
                var rep = xhr.responseText;
                console.log(rep);
                var exo = document.getElementsByClassName("exoBox");
                exo[0].insertAdjacentHTML('beforebegin',rep);
            }
        }
        xhr.send();
    }
}

function rechercheExo(){
    var xhr = new XMLHttpRequest();
    var nomExo = document.getElementById("saisieExo").value;
    if(nomExo!=="" ){
        console.log(nomExo);
        xhr.open("GET","ServletRecherche?nomExo=" + nomExo);
        xhr.onload = function(){
            if(xhr.status === 200){
                var rep = xhr.responseXML.getElementsByTagName("Exercice");
                var txt ="";
                for(var i = 0; i< rep.length; i++){
                    var exercice = rep[i].children;
                    var code = exercice[0].firstChild.nodeValue;
                    var nom = exercice[1].firstChild.nodeValue;
                    txt += "<p class=\"exo\" id=\"" + code + "\">" + nom + "</p>";
                }
                var listExo = document.getElementsByClassName("listExo");
                listExo[listExo.length-1].innerHTML = txt;
                
                var list = document.getElementsByClassName("exo");
                console.log(list.length);
                for(var i = 0; i< list.length; i++){
                    list[i].addEventListener("click",choisirExo);
                }
            }
        };
        xhr.send();
    }
}

function choisirExo(){
    var champs = document.getElementById("saisieExo");
    var list = document.getElementsByClassName("listExo");
    var exo = this.firstChild.nodeValue;
    var id = this.getAttribute("id");
    champs.value=exo; 
    list[0].innerHTML = "";
    list[0].setAttribute("id",id);
}

function ajouterExo(){
    var xhr = new XMLHttpRequest();
    var span = document.getElementsByClassName("listExo");
    var codeET = span[0].getAttribute("id");
    var nomSeance = document.getElementById("nomSeance").value;
    var ordre = document.getElementsByClassName("exoBox").length - 1;
    var tempsexo = document.getElementById("tempsexo").value;
    var nbserie = document.getElementById("nbserie").value;
    var tempsreposexo = document.getElementById("tempsreposexo").value;
    var tempsreposserie = document.getElementById("tempsreposserie").value;
    var nbrep = document.getElementById("nbrep").value;
    
    xhr.open("GET","ServletAjouterExoType?nomSeance="+ nomSeance + "&codeET=" + codeET
            + "&ordre=" + ordre + "&nbrep=" + nbrep + "&nbserie=" + nbserie
            + "&tempsexo=" + tempsexo + "&tempsreposserie=" + tempsreposserie
            + "&tempsreposexo=" + tempsreposexo);
    xhr.onload = function(){
        if(xhr.status === 200){
            
        }
    }
    xhr.send();
}

function supprimerExo(){
    
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("saisieExo").addEventListener("keyup",rechercheExo);
    document.getElementById("ajouter").addEventListener("click",ajouterExo);
    document.getElementById("creerSeance").addEventListener("click",creerSeance);
});

