<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quizz</title>
    </head>
    <body>
        <h1>Bienvenue sur le Quizz du siècle</h1>
        <p>Début ${questionnaire.libelle} (quiz n° ${param["idQuiz"]})</p>
    </body>
    <ul style="list-style-type:disc">
        <li>Le niveau 1 du Quizz contient 10 questions à choix multiple</li>
        <li>La durée totale du Quizz est 10 Minutes</li>
        <li>Lisez attentivement les questions avant de répondre</li>
        <li>Vous pouvez changer vos réponses avant de valider</li>
        <li>Bonne chance pour le test</li>
    </ul>
    <form action="" method="post">
        <ol>
            <c:forEach items="${questionnaire.questionCollection}" var="question">
                <li>${question.question}
                    <c:forEach items="${question.options}" var="choix">
                        <input type="radio" name="${question.id}"
                               value="${choix.id}"/> ${choix.libelle}
                        <br />
                    </c:forEach>
                </li>
            </c:forEach>
        </ol>
        <button type="submit">Valider</button>
    </form>
</html>
