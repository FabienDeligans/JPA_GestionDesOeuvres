<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-target">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Accueil</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse-target">
                <c:if test="${sessionScope.userIdS != null}">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">Oeuvres<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="catalogue.oe">Lister</a></li>
                                    <c:if test="${sessionScope.userTypeS == 'P'}">
                                    <li><a href="myCatalogue.oe">Mes oeuvres</a></li>
                                    <li><a href="ajouter.oe">Ajouter</a></li>
                                    </c:if>
                            </ul>
                        </li>                           
                    </ul> 
                    <ul class="nav navbar-nav">
                        <li><a href="getReservations.res">Réservations</a></li>
                    </ul>    
                </c:if>
                <ul class="nav navbar-nav navbar-right"> 
                    <c:if test="${sessionScope.userIdS != null}">
                        <li><a href="deconnecter.log">Se déconnecter</a></li>
                        </c:if>  
                        <c:if test="${sessionScope.userIdS == null}">                   
                        <li><a href="login.log">Se connecter</a></li>
                        </c:if>  
                </ul>
            </div>
        </div>
    </nav>
</div><!--/.container-fluid -->

