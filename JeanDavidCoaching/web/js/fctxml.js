/**
 * Cette méthode 
 * 
 * 
 * 
 */
function rechercheKey(){
    var xhr = new XMLHttpRequest();
    var nomExo = document.getElementById("saisieExo").value;
    if(nomExo!==''){
        alert(nomExo);
        xhr.open("GET","ServletRecherche?nomExo=" + nomExo);
        xhr.onload = function(){
            if(xhr.status === 200){
                var rep = xhr.responseXML;
                var l_nom = rep.getElementsByTagName("nomExo");
                alert(l_nom.length);
                var txt = "<ul>";
                
                for(var i = 0; i<l_nom.length; i++){
                    txt +="<li class='exo'>" + l_nom[i].firstChild.nodeValue + "</li>";
                }
                txt += "</ul>";
                var listExo = document.getElementById("listExo");
                listExo.innerHTML = txt;
            }
        };
        xhr.send();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("saisieExo").addEventListener("keyup",rechercheKey);

});

