<%@page import="db.Exercicetype"%>
<%@page import="java.util.Set"%>
<%@page import="db.Seancetype"%>
<%@page import="db.Predefinirseance"%>
<%@page import="db.Programmetype"%>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    </head>
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
            <!-- Brand -->
            <a class="navbar-brand" href="/JeanDavidCoaching">Jean David Coaching</a>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">

                    <li class="nav-item">
                        <a class="nav-link" href="creerSeance">Créer une seance</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="creerExercice">Créer un exercice</a>
                    </li> 
                </ul>
            </div> 
        </nav> 
        <!--liiiist-->   



            <div class="input-group">
                <select id="codept" class="custom-select" onchange="afficheseances()">
                    <%  ArrayList<Seancetype> lSeances = (ArrayList<Seancetype>) request.getAttribute("listeSeancest");
                    ArrayList<Exercicetype> lSem = (ArrayList<Exercicetype>) request.getAttribute("listeExo");

//                        Programmetype pt = lProgsGet.get(0);
                        ArrayList<Programmetype> lProgs = (ArrayList<Programmetype>) request.getAttribute("listeProgs");
                        for (Programmetype p : lProgs) {
                    %>
                    <option value="<%=p.getCodept()%>">
                        <%=p.getNomp()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
  
                <div id="prog"></div>
                    
                    
                    

        
                


       
</body>
</html>
<script type="text/JavaScript" src="js/progtype.js"></script>
