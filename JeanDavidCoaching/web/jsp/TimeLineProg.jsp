<%@page import="db.Exercice"%>
<%@page import="db.Seance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.Client"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Chronologie Programme</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
    
    
 <!--liiiist-->   
    <form action="ServletvoirPrgClient" method="GET">
        <div class="input-group">
            <select class="custom-select" name="idClient">
            <%
            ArrayList<Client> lClients = (ArrayList<Client>)request.getAttribute("listeClients");
                for (Client c: lClients){
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
    if(request.getAttribute("listeSeances")!=null){
        ArrayList<Seance> lSeances = (ArrayList<Seance>)request.getAttribute("listeSeances");
            for(Seance sc: lSeances){
                if(!semaines.contains(sc.getSemaines())){
                    semaines.add(sc.getSemaines());
                }
            }
    }
    

    %>
<div class="container mt-3">
  <h3><%=request.getAttribute("nomClient")%>  <%=request.getAttribute("prenomClient")%></h3>

  <ul class="nav nav-tabs">
      <%    ArrayList<Exercice> lExercices = (ArrayList<Exercice>)request.getAttribute("listeExos");
          if(!semaines.isEmpty()){
          for(Integer sem: semaines){%>
    <li class="nav-item">
      <a class="nav-link active" data-toggle="tab" id="numSem" value="<%=sem%>" href="#sem<%=sem%>">Semaine <%=sem%></a>
    </li>
        <% }
                }else{
                %>
                <h3> Ce client n'a pas de programme affecté</h3>
    <%
       
            }
            %>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
 
    <div id="sem" class="container tab-pane active"><br>
      <h3></h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>

      
      
      
  </div>
  
</div>

</body>
</html>

<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", () => {
        document.getElementById("numSem").addEventListener("click", ok);
});
    
    
    function ok(){
         // Objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();
        // Requête au serveur avec les paramètres éventuels.
        var sem = document.getElementById("numSem").value;
        xhr.open("GET", "ServletCitation?nomauteur=" + aut);
    }
</script>
