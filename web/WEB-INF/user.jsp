<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Session</title>
  </head>
  <body>
    <form action="Connexion" method="post">
      <c:if test='${sessionScope["user"] == null}'>
        Login :
        <input type="text" name="login"/>
        Mot de passe :
        <input type="password" name="pwd"/>
        <button type="submit">Connecter</button>
        <div style="color: red;">${msg}</div>
      </c:if>
      <c:if test='${sessionScope["user"] != null}'>
        <input type="hidden" name="action" value="deconnecter"/>
        <button type="submit">Déconnecter ${sessionScope["user"].login}</button>
      </c:if>
    </form>
  </body>
</html>
