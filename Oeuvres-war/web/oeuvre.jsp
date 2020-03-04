<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  class="col-md-8 col-md-offset-1">
    <h1 align='center'>${titre}</h1>
    <form class="form-signin form-horizontal" role="form" action="enregistrer.oe?id=${oeuvreR.idOeuvre}" method="post">
        <div class="form-group">
            <label class="col-md-3 control-label">Titre : </label>
            <div class="col-md-6">
                <input type="text" name="titre" value="${oeuvreR.titre}" class="form-control" placeholder="Titre de l'oeuvre" required autofocus>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Prix : </label>
            <div class="col-md-3">
                <input type="text" name="prix" value="${oeuvreR.prix}"  class="form-control" placeholder="Prix de l'oeuvre" required>
            </div>
        </div>            
        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider</button>
            </div>
        </div>
    </form>
</div>