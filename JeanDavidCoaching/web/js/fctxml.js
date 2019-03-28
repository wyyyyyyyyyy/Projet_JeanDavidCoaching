/**
 * Cette méthode 
 * 
 * 
 * 
 */
function rechercheKey() {
    var xhr = new XMLHttpRequest();
    var nomExo = document.getElementById("saisieExo").value;
    if (nomExo !== '') {
        //alert(nomExo);
        xhr.open("GET", "ServletRecherche?nomExo=" + nomExo);
        xhr.onload = function () {
            if (xhr.status === 200) {
                //alert(nomExo);
                var rep = xhr.responseXML;
                var l_nom = rep.getElementsByTagName("nomExo");
                alert(l_nom.length);
                var txt = "<ul>";

                for (var i = 0; i < l_nom.length; i++) {
                    txt += "<li class='exo'>" + l_nom[i].firstChild.nodeValue + "</li>";
                }
                txt += "</ul>";
                var listExo = document.getElementById("listExo");
                listExo.innerHTML = txt;
            }
        };
        xhr.send();
    }
}

function l_clients()
{

    //Objet XMLHttpRequest
    var xhr = new XMLHttpRequest();
    //Requête au serveur
    xhr.open("GET", "ServletClient");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {

            var rep = xhr.responseXML;

            var l_client = rep.getElementsByTagName("nomClient");

            var txt = "<option>----------</option>";
            for (var i = 0; i < l_client.length; i++)
            {
                txt += "<option>" + l_client[i].firstChild.nodeValue + "</option>";
            }
            var eltOption = document.getElementById("nomClient");
            eltOption.innerHTML = txt;
            document.getElementById("bt_clients").disabled = "disabled";
        }
    };
    xhr.send();
}

function l_objectifs()
{
    var xhr = new XMLHttpRequest();
    var nomCli = document.getElementById("nomClient").value;
    xhr.open("GET", "ServletObjectif?nomClient=" + nomCli);
    
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var rep = xhr.responseXML;
            var l_objectifs = rep.getElementsByTagName("objectifsClient");
            var txt = "<p>";
            //alert(nomCli);
            for (var i = 0; i < l_objectifs.length; i++)
            {
                txt += l_objectifs[i].firstChild.nodeValue + "</br>";
            }
            txt += "</p>";
            var eltOption = document.getElementById("objectifs");
            eltOption.innerHTML = txt;
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    //document.getElementById("saisieExo").addEventListener("keyup",rechercheKey);
    document.getElementById("bt_clients").addEventListener("click", l_clients);
    document.getElementById("nomClient").addEventListener("click", l_objectifs);

});

