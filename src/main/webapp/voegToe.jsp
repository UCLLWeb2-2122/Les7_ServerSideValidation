<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Voeg een huisdier toe</title>
    <link rel="stylesheet" type="text/css" href="style/style.css"/>
</head>
<body>
<div id="container">
    <nav>
        <ul>
            <li><a href="Controller?command=home">Home</a></li>
            <li><a href="Controller?command=zoekFormulier">Zoek</a></li>
            <li><a href="Controller?command=meestHongerige">Meest Hongerige</a></li>
            <li><a href="Controller?command=overview">Overzicht</a></li>
        </ul>
    </nav>

    <main>
        <c:if test="${not empty errors}">
            <div id="error" class="alert alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h2>Voeg je huisdier toe</h2>

        <form method="POST" action="Controller?command=voegToe" novalidate>

            <p class="form-group">
                <label class="control-label" for="naam">Naam:</label>
                <input id="naam" name="naam" type="text"
                       value="${naamPreviousValue}" class="${naamHasErrors? 'error' : ''}" required>
            </p>

            <p class="form-group">
                <label class="control-label" for="soort">Soort:</label>
                <input id="soort" name="soort" type="text"
                       value="${soortPreviousValue}" class="${soortHasErrors? 'error' : ''}" required>
            </p>

            <p class="form-group">
                <label for="voedsel">Aantal keer eten per dag:</label>
                <input
                        id="voedsel" name="voedsel" type="number"
                        max="10" min="1" value="${voedselPreviousValue}" class="${voedselHasErrors? 'error' : ''}">
            </p>
            <p>
                <input id="submit" type="submit" value="Verstuur">
            </p>
        </form>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>