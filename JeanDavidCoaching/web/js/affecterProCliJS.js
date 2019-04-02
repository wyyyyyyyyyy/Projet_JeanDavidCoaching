window.onload=l_clients();
window.onload=l_programmetype();

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

            var l_client = rep.getElementsByTagName("client");
           
            var txt = "";
            for (var i = 0; i < l_client.length; i++)
            {
                var nodes = l_client[i].children;
                    txt += "<option value="+nodes[0].firstChild.nodeValue+">" + nodes[1].firstChild.nodeValue + "</option>";

                
                
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
    var elt = document.getElementById("nomClient");
    var nomCli = elt.options[elt.selectedIndex].text;
    
    var codecli = elt.options[elt.selectedIndex].value;

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

function affectation()
{
    var xhr = new XMLHttpRequest();
    var elt = document.getElementById("nomClient");
    var codecli = elt.options[elt.selectedIndex].value;
    alert(codecli);
    
    var elt2 = document.getElementById("nomProgrammetype");
    var codeprogrammetype = elt2.options[elt2.selectedIndex].value;
    alert(codeprogrammetype);
    
    xhr.open("GET", "ServletAffecterProgrammeClient?codecli="+codecli+"&codeprogrammetype="+codeprogrammetype+"");

    
    xhr.send();
}

function l_programmetype()
{
    //Objet XMLHttpRequest
    var xhr = new XMLHttpRequest();
    //Requête au serveur
    xhr.open("GET", "ServletProgrammetype");

    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {

            var rep = xhr.responseXML;

            var l_programmetype = rep.getElementsByTagName("programmetype");

            var txt = "";
            for (var i = 0; i < l_programmetype.length; i++)
            {
                var nodes = l_programmetype[i].children;
                    txt += "<option value="+nodes[0].firstChild.nodeValue+">" + nodes[1].firstChild.nodeValue + "</option>";
            }
            var eltOption = document.getElementById("nomProgrammetype");
            eltOption.innerHTML = txt;
            document.getElementById("bt_programmetype").disabled = "disabled";
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    //document.getElementById("bt_clients").addEventListener("click", l_clients);
    document.getElementById("nomClient").addEventListener("click", l_objectifs);
    //document.getElementById("bt_programmetype").addEventListener("click", l_programmetype);
    document.getElementById("bt_affectation").addEventListener("click",affectation);

});


