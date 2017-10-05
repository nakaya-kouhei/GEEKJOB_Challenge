<%-- 
    Document   : GEEKJOB_Challenge3-1
    Created on : 2017/10/05, 11:37:23
    Author     : guest1Day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            final int num1 = 200;
            int num2 = 20;
            int num3;
            
            num3 = num1+num2;            
            out.print(num1 + "+" + num2 + "=" + num3 + "<br>");
            num3 = num1-num2;            
            out.print(num1 + "-" + num2 + "=" + num3 + "<br>");
            num3 = num1*num2;            
            out.print(num1 + "*" + num2 + "=" + num3 + "<br>");
            num3 = num1/num2;            
            out.print(num1 + "/" + num2 + "=" + num3 + "<br>");
        %>
    </body>
</html>
