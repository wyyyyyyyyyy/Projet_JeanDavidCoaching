
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Espace Coaching</title>
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
            <a class="navbar-brand" href="/JeanDavidCoaching">Jean David Coaching</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="accueilCoach.html">Accueil Coach</a>
                    </li>
                </ul>
            </div>
        </nav>
        <br><br>
        <div class="py-5 text-center">
                <h1>Gérer vos clients</h1>
            </div>
        <div class="row">
            <div id="accordion1" class="col">
                <div class="card text-white bg-dark">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link text-warning collapsed font-weight-bold" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                Gérer les clients et les programmes
                            </button>
                        </h5>
                    </div>
                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion1">
                        <br>
                        <div>
                            <a href="affecterProgrammeClient" class="btn btn-warning btn-block">Affecter un programme</a>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
            <div id="accordion2" class="col">
                <div class="card text-white bg-dark">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link text-warning collapsed font-weight-bold" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Suivi des clients
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion2">
                        <br>
                        <div>
                            <a href="ListCtrlPrg" class="btn btn-warning btn-block expand">Visualiser l'évolution</a>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <!--ferme-->

    </body>
</html>

<script type="text/javascript">
</script>




<!--        <div class="col"><button class="btn btn-outline-warning btn-block" data-toggle="collapse" data-target="#container">Pourcentage</buuton>
                <div id="container" class="collapse" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
        </div>-->

