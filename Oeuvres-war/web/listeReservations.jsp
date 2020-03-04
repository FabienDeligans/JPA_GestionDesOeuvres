<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>Liste des réservations</h1>             
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <td>Titre</td>
                <td>Date</td>
                <td>Statut</td>
                <td>Prénom adhérent</td>
                <td>Nom adhérent</td>
                <c:if test="${sessionScope.userTypeS == 'P'}">
                    <td>Confirmer</td>
                    <td>Supprimer</td>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="reservationE" items="${lstReservationsR}">
                <tr>
                    <td>${reservationE.oeuvre.titre}</td>
                    <td><fmt:formatDate value="${reservationE.reservationPK.dateReservation}" type="date" pattern="dd/MM/yyyy"/></td>
                    <td>${reservationE.statut}</td>
                    <td>${reservationE.adherent.prenomAdherent}</td>
                    <td>${reservationE.adherent.nomAdherent}</td>
                    <c:if test="${sessionScope.userTypeS == 'P'}">
                        <td><a class="btn btn-primary" href="confirmerReservation.res?id=${reservationE.reservationPK.idOeuvre}&dateres='<fmt:formatDate value="${reservationE.reservationPK.dateReservation}" type="date" pattern="yyyy-MM-dd"/>'"<c:if test="${reservationE.oeuvre.proprietaire.idProprietaire != sessionScope.userIdS}"> disabled</c:if>>Confirmer</a></td>
                        <td><a class="btn btn-primary" href="supprimerReservation.res?id=${reservationE.reservationPK.idOeuvre}&dateres='<fmt:formatDate value="${reservationE.reservationPK.dateReservation}" type="date" pattern="yyyy-MM-dd"/>'"<c:if test="${reservationE.oeuvre.proprietaire.idProprietaire != sessionScope.userIdS}"> disabled</c:if>>Supprimer</a></td>                    
                    </c:if>
                </tr>
            </c:forEach>                    
        </tbody>
    </table>              
</div>