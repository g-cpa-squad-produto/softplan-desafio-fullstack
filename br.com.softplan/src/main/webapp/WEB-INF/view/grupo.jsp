<%-- 
    Document   : grupo
    Created on : 15/08/2018, 19:30
    Author     : Fernando.Rauber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                        <h3 class="page-title">Minha Conta</h3>
                        <div class="row">

                            <!-- Usuários -->
                            <div class="col-md-12">
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Grupos</h3>
                                    </div>
                                    <div class="panel-body no-padding">
                                        <form name="frmGrupos" id="frmGrupos" method="post" action="home" accept-charset="ISO-8859-1">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Nome</th>
                                                        <th>Permissão</th>
                                                        <th colspan="2">
                                                        	<button name="add-grupo" id="manager-grupo" type="button" 
                                                                    class="btn btn-primary pull-center">Adicionar</button>
<!--                                                                     onclick="managerGrupo('');" -->
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="grupo" items="${grupos}">
                                                        <tr>
                                                            <td>${grupo.id}</td>
                                                            <td>${grupo.nome}</td>
                                                            <td>${grupo.permissao.nome}</td>
                                                            <td>
                                                                 <a href="#" id="manager-grupo" title="Alterar" >
<%--                                                                  onclick="managerGrupo('${grupo.id}');" --%>
                                                                     <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i>
                                                                 </a>
                                                               &nbsp;&nbsp;
                                                              	 <a href="#" id="excluir-grupo" title="Excluir" >
<%--                                                               	 onclick="excluirGrupo('${grupo.id}');" --%>
                                                                 	<i class="fa fa-times fa-lg" aria-hidden="true"></i>
                                                              	 </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <input name="idGrupo" id="idGrupo" type="hidden" value="">
                                            <input name="acao" id="acao" type="hidden" value="">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT -->

                <c:import url="footer.jsp" />

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
                /*$("#logoff").click(function () {
                    $('input#acao').val('logoff');
                    $("#frmMenuTop").submit();
                });*/
                $("#usuario, #atalho-usuario").click(function () {
                    $('input#acao').val('2');
                    $("#frmMenuTop").submit();
                });
                
                $("#permissao").click(function () {
                    $('input#acao').val('5');
                    $("#frmMenuTop").submit();
                });
                
                $("#grupo").click(function () {
                    $('input#acao').val('6');
                    $("#frmMenuTop").submit();
                });
                
                $("#processo").click(function () {
                    $('input#acao').val('7');
                    $("#frmMenuTop").submit();
                });
            });

            function managerGrupo(idGrupo) {
                $('input#acao').val('manager-grupo');
                $('input#idGrupo').val(idGrupo);
                $('#frmGrupos').submit();
            } 

            function excluirGrupo(idGrupo) {
	             $('input#acao').val('excluir-grupo');
	             $('input#idGrupo').val(idGrupo);
	             $('#frmGrupos').submit();
             }
        </script>
    </body>
</html>