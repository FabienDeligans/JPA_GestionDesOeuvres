<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>Catalogue des oeuvres</h1>
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <td>Titre</td>
                <td>Prix</td>
                <td>Prénom propriétaire</td>
                <td>Nom propriétaire</td>
                <c:if test="${sessionScope.userTypeS == 'A'}">
                    <td>Réserver</td>
                </c:if>               
            </tr>  
        </thead>
        <tbody>
            <c:forEach var="oeuvreE" items="${lstOeuvresR}">
                <tr>
                    <td>${oeuvreE.titre}</td>
                    <td>${oeuvreE.prix}</td>
                    <td>${oeuvreE.proprietaire.prenomProprietaire}</td>
                    <td>${oeuvreE.proprietaire.nomProprietaire}</td>
                    <c:if test="${sessionScope.userTypeS == 'A'}">
                        <td><a class="btn btn-primary" href="reserver.res?id=${oeuvreE.idOeuvre}">Réserver</a></td>
                    </c:if> 
                </tr>
            </c:forEach>
        </tbody>
    </table>          
</div>
