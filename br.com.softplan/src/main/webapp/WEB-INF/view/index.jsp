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
        
             <c:import url="menu-esquerda.jsp" />

            <!-- MAIN -->
            <div class="main">

                <c:import url="menu-topo.jsp" />
            
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
                                            <h4 class="heading">Seja Bem Vindo!!!</h4>
                                        </div>
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
        <script>
            $(document).ready(function () {
//                 $("#logoff").click(function () {
//                     $('input#acao').val('logoff');
//                     $("#frmMenuTop").submit();
//                 });
                $("#usuario, #atalho-usuario").click(function () {
                    $('input#acao').val('2');
                    $("#frmMenuTop").submit();
                });
                $("#permissao").click(function () {
                    $('input#acao').val('5');
                    $("#frmMenuTop").submit();
                });
                $("#processo").click(function () {
                    $('input#acao').val('7');
                    $("#frmMenuTop").submit();
                });
            });
        </script>
    </body>

</html>
