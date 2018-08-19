<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-btn">
            <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
        </div>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu">
                <span class="sr-only">Toggle Navigation</span>
                <i class="fa fa-bars icon-nav"></i>
            </button>
        </div>
        <form name="frmMenuTop" id="frmMenuTop" method="post" action="home" accept-charset="ISO-8859-1">
            <div id="navbar-menu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        	<img src="resources/img/interna/user-default.png" width="24" height="24" class="img-circle" alt="Avatar"> 
                        		<span>Fernando Rauber</span> 
                        		<i class="icon-submenu lnr lnr-chevron-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a id="atalho-usuario" href="#"><i class="lnr lnr-user"></i> <span>Minha Conta</span></a></li>
                            <li><a id="logoff" href="#"><i class="lnr lnr-exit"></i> <span>Sair</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <input name="acao" id="acao" type="hidden" value="">
        </form>
    </div>
</nav>