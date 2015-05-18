<%@page import="java.sql.*"%>
<%@page import="modele.Questionnaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="quizz.jsp">
    <table>
      <%
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms","root", "root");
Statement st=connection.createStatement();
ResultSet rs=st.executeQuery("Select * from quiz");
int i=1;
while(rs.next()){
%>

      <tr>
        <td>
          <%=i%>
        </td>
        <td>
          <%=rs.getString("quest")%>
        </td>
        <td>
          <input type="radio" value="QA" name="radio<%=i%>"/><%=rs.getString("QA")%>
        </td>
        <td>
          <input type="radio" value="QB" name="radio<%=i%>"/><%=rs.getString("QB")%>
        </td>
        <td>
          <input type="radio" value="QC" name="radio<%=i%>"/><%=rs.getString("QC")%>
        </td>
        <td>
          <input type="radio" value="QD" name="radio<%=i%>"/><%=rs.getString("QD")%>
        </td>
        <td>
          <input type="text" value='<%=rs.getString("correctAns")%>' name="ans<%=i%>"/>
        </td>
      </tr>

      <%
i++;
}
%>

      <tr>
        <td>
          <input type="submit" value="submit"></input>
        </td>
      </tr>
    </table>
  </form>
    </body>
</html>
