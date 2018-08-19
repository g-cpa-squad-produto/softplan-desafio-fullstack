<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <title>Softplan</title>

        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <!-- CSS -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
        <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/bootstrap/css/interna/icon-sets.css">
        <link rel="stylesheet" href="resources/css/interna/main.css">

        <!-- ICONS -->
        <link rel="icon" type="image/png" sizes="96x96" href="resources/ico/favicon.png">
    </head>

    <body>
        <!-- WRAPPER -->
        <div id="wrapper">
        
            <!-- MAIN -->
            <div class="main">
            
                <!-- MAIN CONTENT -->
                <div class="main-content">
                    <div class="container-fluid">
                        <div class="panel panel-profile">
                            <div class="clearfix">
                                <!-- LEFT COLUMN -->
                                <div class="profile-left">

                                    <!-- PROFILE DETAIL -->
                                    <div class="profile-detail">
                                        <div class="profile-info">
                                            <h4 class="heading">LOGIN</h4>
                                            <ul class="list-unstyled list-justify">
                                                <li>Usuário: <span><input id="login" name="login" type="text"/></span></li>
                                                <li>Senha: <span><input id="senha" name="senha" type="password"/></span></li>
                                            </ul>
                                        </div>
                                        <div class="text-center"><a href="#" class="btn btn-primary">Login</a></div>
                                    </div>
                                    <!-- END PROFILE DETAIL -->

                                </div>
                                <!-- END LEFT COLUMN -->
                            </div>
                        </div>                    
                    </div>
                </div>
                <!-- END MAIN CONTENT -->


                <footer>
                    <div class="container-fluid">
                        <p class="copyright">Fernando Rauber</p>
                    </div>
                </footer>

            </div>
            <!-- END MAIN -->

        </div>
        <!-- END WRAPPER -->

        <!-- Javascript -->
        <script src="resources/js/interna/jquery-2.1.0.min.js"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="resources/js/interna/jquery.slimscroll.min.js"></script>
        <script src="resources/js/interna/klorofil.min.js"></script>
    </body>

</html>
