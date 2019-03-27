/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function creerExer ()
{
    var nom = document.getElementById("nomExo").value;
    var objectif = document.getElementById("objectifExo").value;
    var description = document.getElementById("descriptionExo").value;
    var tipRep = document.getElementById("tipRepExo").value;
    var tip = document.getElementById("tipExo").value;
    var materiel = document.getElementById("materielExo").value;
    var media = document.getElementById("mediaExo").value;
    
    var elt = document.getElementById("btn");
    elt.style.display="disabled";
    
    var xhr = new XMLHttpRequest();
    
    var url = "ServletCreExo?nom="+nom+"&objectif="+objectif+"&description="+description
            +"&tipRep="+tipRep+"&tip="+tip+"&materiel="+materiel+"&media="+media;
    xhr.open("GET",url,true);
    alert(nom);
    alert("hello");
    
    xhr.onload = function()
        {
            if (xhr.status ===200)
            {

            }
        };
    xhr.send();
}    
    document.addEventListener("DOMContentLoaded", () => {
	document.getElementById("btn").addEventListener("click",creerExer);
    });

