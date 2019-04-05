<%@page import="db.Exercice"%>
<%@page import="db.Seance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.Client"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Ok</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    </head>
    <style>
        h3{
            color:darkmagenta;
        }
    </style>
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
    <body>
        <nav class="navbar navbar-expand-md bg-dark navbar-dark">
            <a class="navbar-brand" href="/JeanDavidCoaching">Jean David Coaching</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="accueilCoach.html">Accueil Coach</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="accueilCoaching">Espace coaching</a>
                    </li>
                    <% if (request.getAttribute("nomClient") != null && request.getAttribute("codeCli") != null) {
                            if (!semaines.isEmpty()) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="ServletChronoProg?idClient=<%=request.getAttribute("codeCli")%>">Voir progression de <%=request.getAttribute("prenomClient")%></a>
                    </li> 
                    <% }
                        } %>
                </ul>
            </div> 
        </nav> 
        <!--liiiist-->   


        <form action="ServletvoirPrgClient" method="GET">
            <div class="input-group">
                <select class="custom-select" name="idClient">
                    <%                        ArrayList<Client> lClients = (ArrayList<Client>) request.getAttribute("listeClients");
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
                    <button class="btn btn-outline-secondary" type="submit" >Choisir</button>
                </div>
            </div>
        </form>

        <!--fin liiiist-->

        <div class="container mt-3">
            <%if (request.getAttribute("nomClient") != null) {%>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Client</th>
                        <th scope="col">Age</th>
                        <th scope="col">Poids</th>
                        <th scope="col">Handicap</th>
                        <th scope="col">Tour de taille</th>
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

                <div id="sem1" class="container tab-pane active"><br></div>   
            </div>
        </div>
    </header>
</body>
</html>
<script type="text/JavaScript" src="js/programme.js"></script>



