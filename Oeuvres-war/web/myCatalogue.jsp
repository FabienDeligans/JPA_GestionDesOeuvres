<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>Catalogue des oeuvres</h1>
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <td>Titre</td>
                <td>Prix</td>  
                <td>Modifier</td>
                <td>Supprimer</td>              
            </tr>  
        </thead>
        <tbody>
            <c:forEach var="oeuvreE" items="${lstOeuvresR}">
                <tr>
                    <td>${oeuvreE.titre}</td>
                    <td>${oeuvreE.prix}</td>
                    <td><a class="btn btn-primary" href="modifier.oe?id=${oeuvreE.idOeuvre}">Modifier</a></td>  
                    <td><a class="btn btn-primary" onclick="javascript:if (confirm('Suppression confirmée ?')) {
                                    window.location = 'supprimer.oe?id=${oeuvreE.idOeuvre}';
                                }">Supprimer</a></td>                     
                </tr>
            </c:forEach>
        </tbody>
    </table>          
</div>
