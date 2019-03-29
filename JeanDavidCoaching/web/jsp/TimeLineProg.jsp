<%@page import="db.Exercice"%>
<%@page import="db.Seance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.Client"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/cardio.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
                    

        <nav class="navbar navbar-toggleable-md navbar-light bg-light">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand"><img src="img/logo.png" data-active-url="img/logo-active.png" alt=""></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right main-nav">
                        <li><a href="#" data-toggle="modal" data-target="#modal1" class="btn btn-blue">Sign Up</a></li>
                        <li><a href="ListCtrlPrg">Programme Client</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        
        <!--liiiist-->   
            <header id="intro">
                <form action="ServletvoirPrgClient" method="GET">
                    <div class="input-group">
                        <select class="custom-select" name="idClient">
                            <%
                                ArrayList<Client> lClients = (ArrayList<Client>) request.getAttribute("listeClients");
                                for (Client c : lClients) {
                            %>
                            <option  value="<%=c.getCodecli()%>">
                                <%=c.getNomcli()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="submit">Choisir</button>
                        </div>
                    </div>
                </form>

        <!--fin liiiist-->
        <%  ArrayList<Integer> semaines = new ArrayList<Integer>();
            if (request.getAttribute("listeSeances") != null) {
                ArrayList<Seance> lSeances = (ArrayList<Seance>) request.getAttribute("listeSeances");
                for (Seance sc : lSeances) {
                    if (!semaines.contains(sc.getSemaines())) {
                        semaines.add(sc.getSemaines());
                    }
                }
            }

        %>
        <div class="container mt-3">
            <%if (request.getAttribute("nomClient") != null) {%>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Client</th>
                        <th scope="col">Age</th>
                        <th scope="col">Poids</th>
                        <th scope="col">Handicap</th>
                        <th scope="col">Tour de teaille</th>
                        <th scope="col">Poitrine</th>
                        <th scope="col">Bras</th>
                        <th scope="col">Cuisses</th>
                        <th scope="col">Hanches</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>
                            <%=request.getAttribute("nomClient")%>  <%=request.getAttribute("prenomClient")%>
                        </td>
                        <td>
                            <%=request.getAttribute("ageCli")%> ans
                        </td>
                        <td>
                            <%=request.getAttribute("Poids")%> Kg
                        </td>
                        <td>
                            <%=request.getAttribute("Handicap")%> 
                        </td>
                        <td>
                            <%=request.getAttribute("Taille")%> 
                        </td>
                        <td>
                            <%=request.getAttribute("Poitrine")%> 
                        </td>
                        <td>
                            <%=request.getAttribute("Bras")%> 
                        </td>
                        <td>
                            <%=request.getAttribute("Cuisses")%> 
                        </td>
                        <td>
                            <%=request.getAttribute("Hanches")%> 
                        </td>
                    </tr>

                </tbody>
            </table>



            <%}%>
            <ul class="nav nav-tabs">
                <%    ArrayList<Exercice> lExercices = (ArrayList<Exercice>) request.getAttribute("listeExos");
                    if (!semaines.isEmpty()) {
                        for (Integer sem : semaines) {%>
                <li class="nav-item">
                    <button class="nav-link active" id="numSem" onclick="affiche(<%=sem%>)" data-toggle="tab" value="<%=sem%>">Semaine <%=sem%></button>
                    <input type="hidden" id="numCli" value="<%=request.getAttribute("codeCli")%>"/>
                </li>
                <% }
                } else {
                    if (request.getAttribute("nomClient") != null) {
                %>
                <h3> Ce client n'a pas de programme affecté</h3>
                <%} else {%>
                <h3> Veuillez choisir un client</h3>
                <%}
                        }%>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">

                <div id="sem1" class="container tab-pane active"><br>   
                    <% if (!semaines.isEmpty()) { %>
                    <h3>Choisir la semaine</h3>
                    <%}%>
                </div>
            </div>
        </div>
    </header>
</body>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <p>2019 All Rights Reserved On Wheels company</p>
            </div>
        </div>
    </div>
</footer>
</html>
<script type="text/javascript">
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
                text = "<div=\'row\'> ";
                for (var i = 0; i < lstSeance.length; i++) {
                    var seance = lstSeance[i].children;
                    text += "<button class=\'btn btn-info\'>" + seance[1].firstChild.nodeValue + "</button>";
                    for (var x = 2; x < seance.length; x++) {
                        var exo = seance[x].children;
                        text += "<div>"
                        text += "<p>" + exo[1].firstChild.nodeValue + "</p>";
                        text += "</div>";
                    }
                    text += "</div>";
                }


                document.getElementById("sem1").innerHTML = text;

            }
        }

        xhr.send();
    }
</script>