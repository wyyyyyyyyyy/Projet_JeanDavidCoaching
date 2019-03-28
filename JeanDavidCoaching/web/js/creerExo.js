/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function creerExer ()
    {
        var nom = encodeURIComponent(document.getElementById("nomExo").value);
        var objectif = encodeURIComponent(document.getElementById("objectifExo").value);
        var description = encodeURIComponent(document.getElementById("descriptionExo").value);
        var tipRep = encodeURIComponent(document.getElementById("tipRepExo").value);
        var tip = encodeURIComponent(document.getElementById("tipExo").value);
        var materiel = encodeURIComponent(document.getElementById("materielExo").value);
        var media = encodeURIComponent(document.getElementById("mediaExo").value);      

        var xhr = new XMLHttpRequest();

        var url = "ServletCreExo?nom="+nom+"&objectif="+objectif+"&description="+description
                +"&tipRep="+tipRep+"&tip="+tip+"&materiel="+materiel+"&media="+media;
        //var test = "ServletCreExo?nom=a&objectif=b&description=c&tipRep=d&tip=e&materiel=f&media=g"
        xhr.open("GET",url,true);
        alert(url);

        //alert(nom+objectif+description+tipRep+tip+materiel+media);
        //alert("hello");

        xhr.onload = function()
            {
                if (xhr.status ===200)
                {
                     alert("ok");
                     var texte="<p>"+nom+"</p>";

                     //récupère le composant HTML à mettre à jour
                     var elt=document.getElementById("exoCre");

                     var e_div = document.createElement("div");
                     e_div.innerHTML = texte;
                     elt.insertBefore(e_div,elt.lastElementChild);

                     document.getElementById("nomExo").value="";
                     document.getElementById("objectifExo").value="";
                     document.getElementById("descriptionExo").value="";
                     document.getElementById("tipRepExo").value="";
                     document.getElementById("tipExo").value="";
                     document.getElementById("materielExo").value="";
                     document.getElementById("mediaExo").value="";
                }
            };
        xhr.send();
    }    
    
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("btn").addEventListener("click",creerExer);
});

