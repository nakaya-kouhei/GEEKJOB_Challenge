<%-- 
    Document   : GEEKJOB_Challenge8-1-3
    Created on : 2017/10/11, 14:48:33
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
            
            int answer = 0;

            //  1から100までを順に加算
            for(int i=1;i<=100;i++){
            
            answer+=i;
            
            }
        
        %>
    </body>
</html>
