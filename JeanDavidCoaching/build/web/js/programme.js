window.onload=affiche(1);

    function affiche(numSem)
    {


        var codecli = document.getElementById("numCli").value;
//            var numSem = document.getElementById("numSem").value;
        xhr = new XMLHttpRequest();

        var param = "codecli=" + codecli + "&numSem=" + numSem + "";
        var url = "ServletListeSeance";
        go = url + "?" + param;
        xhr.open("GET", go, true);

        xhr.onload = function () {
            if (xhr.status === 200)
            {

                lstSeance = xhr.responseXML.getElementsByTagName("seance");
                text = "<br><div class=\'col\'>";
                for (var i = 0; i < lstSeance.length; i++) {
                    var seance = lstSeance[i].children;
                    text += "<div class=\'row\'><div class=\'col-sm-3\'>";
                    text +="<button class=\'btn btn-outline-warning btn-block\' data-toggle=\'collapse\' data-target=\'#a"+i+"\'>" + seance[1].firstChild.nodeValue + "</button></div>";
                    text +="<br><br>";
                    text +="<div class=\'col\'><table id=\'a"+i+"\' class=\'table table-hover table-dark collapse\'><tbody><tr>";
                    for (var x = 2; x < seance.length; x++) {
                        var exo = seance[x].children;
                        
                        text += "<td><label class=\'bb"+i+"\'>" + exo[1].firstChild.nodeValue + "</label><td>";
                        
                    }
                    text += "</tr></table></div></div>";
                }text +="<div>";
                document.getElementById("sem1").innerHTML = text;

            }
        }

        xhr.send();
    }
    
    
    
