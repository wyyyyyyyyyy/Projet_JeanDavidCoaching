<%@page import="db.Resultatexo"%>
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
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/export-data.js"></script>
        <style>
            .highcharts-credits{
                display: none;  
            }
        </style>
    </head>


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
        <br>
        <% ArrayList<Seance> listeseancesf = (ArrayList<Seance>) request.getAttribute("listeSeanceFinis");
            int fini = listeseancesf.size();

            ArrayList<Seance> listeTouteseances = (ArrayList<Seance>) request.getAttribute("listeSeances");
            float pourcentagefini = (float)100*listeseancesf.size() / listeTouteseances.size();
            float pourcentage = (float)100*(listeTouteseances.size()-listeseancesf.size()) / listeTouteseances.size();
        %>
        
        
        <div class="progress" style="height:40px">
            <div class="progress-bar progress-bar-striped bg-warning progress-bar-animated" role="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width: <%=pourcentagefini%>%"><b style="color:#2b542c"><%=pourcentagefini%>% fini</b></div>
            <div class="progress-bar progress-bar-striped bg-dark progress-bar-animated" role="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width: <%=pourcentage%>%"><b style="color:#ff0"><%=pourcentage%>% pas encore fini</b></div>
        </div>
        
        <br>
        <div class="row">
            <div class="col">
                <button class="btn btn-outline-warning btn-block" data-toggle="collapse" data-target="#fait"><h3>fait</h3></buuton>
                    <div id="fait" class="col collapse">


                        <%
                            if (!listeseancesf.isEmpty()) {%>
                        <table class="table table-hover table-dark">
                            <tbody>
                                <%
                                    for (Seance seancef : listeseancesf) {
                                        Integer xl = 0;
                                        for (Resultatexo rExo : (ArrayList<Resultatexo>) request.getAttribute("listeExoFinis")) {
                                            if (rExo.getExercice().getSeance().equals(seancef)) {
                                                xl++;
                                            }
                                        }

                                %>
                                <tr>
                                    <td rowspan="<%=xl%>"><%=seancef.getSeancetype().getNoms()%></td>



                                    <%
                                        for (Resultatexo rExo : (ArrayList<Resultatexo>) request.getAttribute("listeExoFinis")) {
                                            if (rExo.getExercice().getSeance().equals(seancef)) {
                                    %>
                                    <td class="table-success"><b><%=rExo.getExercice().getExercicetype().getNomet()%></b></td>
                                            <%}
                                                        }
                                                    }
                                                }%>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>

            <!--nonfait-->
            <div class="col"><button class="btn btn-outline-warning btn-block" data-toggle="collapse" data-target="#nonFait"><h3>Non fait</h3></buuton>
                    <div id="nonFait" class="col collapse">
                        <h1>seances reste</h1>


                        <table class="table table-hover table-dark">
                            <tbody>
                                <%

                                    for (Seance seance : listeTouteseances) {
                                        if (!listeseancesf.contains(seance)) {%>
                                <tr><td><%=seance.getSeancetype().getNoms()%></td>
                                    <%
                                        for (Exercice Exo : (ArrayList<Exercice>) request.getAttribute("listeExos")) {
                                            if (Exo.getSeance().equals(seance)) {
                                    %>
                                    <td class="table-danger"><%=Exo.getExercicetype().getNomet()%></td>
                                    <%}
                                                }
                                            }
                                        }%>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>
        </div><br>
        <!--ferme-->

    </div>
</body>
</html>

<script type="text/javascript">

    Highcharts.chart('containers', {
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Taux de seances fini par <%=request.getAttribute("nomClient")%> <%=request.getAttribute("prenomClient")%>'
        },
        xAxis: {
            categories: ['seances']
        },
        yAxis: {
            min: 0,
            title: {
                text: 'pourcentage de seances fini'
            }
        },
        legend: {
            reversed: true
        }, tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
            shared: true
        },
        plotOptions: {
            series: {
                stacking: 'percent'
            }
        },
        series: [{
                name: 'fini',
                data: [<%=fini%>]
            }, {
                name: 'pas fini',
    <%float pasfini = (float) listeTouteseances.size() - listeseancesf.size();%>
                data: [<%=pasfini%>]
            }]
    });
</script>




<!--        <div class="col"><button class="btn btn-outline-warning btn-block" data-toggle="collapse" data-target="#container">Pourcentage</buuton>
                <div id="container" class="collapse" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
        </div>-->