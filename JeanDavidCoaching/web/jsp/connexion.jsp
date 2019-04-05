<%-- 
    Document   : connexion
    Created on : 3 avr. 2019, 10:39:27
    Author     : Marck
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"><title>Login Page</title>

        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Custom styles-->
        <link rel="stylesheet" type="text/css" href="styleConnexion.css">


        <title>Connexion</title>
    </head>
    <body background="http://www.relations-presse-publiques.com/wp-content/uploads/2018/03/sport-1.jpg">
        <form method="post" action="ServletConnexion">
            <div class="container" style="width: 500px; margin:  250px auto; color: orange">
                <div class="d-flex justify-content-center h-100">
                    <div class="card">
                        <div class="card-header bg-dark">
                            <h3>Se connecter</h3>
                            <%
                                String mail = "";
                                String mdp = "";
                                if ((String) request.getAttribute("mail") != null) {
                                    mail = (String) request.getAttribute("mail");
                                }

                                if ((String) request.getAttribute("mdp") != null) {
                                    mdp = (String) request.getAttribute("mdp");
                                }


                            %>
                        </div>
                        <div class="card-body">
                            <form>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="email" id="email" name="email" value="<%=mail%>" size="30" maxlength="60" />
                                    <span class="erreur">${form.erreurs['email']}</span>

                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    </div>
                                    <input type="password" id="motdepasse" name="motdepasse" value="<%=mdp%>" size="30" maxlength="60" />
                                    <span class="erreur">${form.erreurs['motdepasse']}</span>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Se connecter" class="btn float-right login_btn bg-dark text-warning">
                                </div>
                                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                            </form>

                        </div>

                    </div>
                </div>
            </div>
        </form>
    </body>
</html>