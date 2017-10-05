<%-- 
    Document   : GEEKJOB_Challenge2-1
    Created on : 2017/10/05, 11:08:40
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
            String text = "おはようございます！";
            String name = "中谷です。";
            int    age  = 28;
            
            text += name;
            text += age;
            text += "歳になりました。";
            
            out.print(text);
        %>
    </body>
</html>
