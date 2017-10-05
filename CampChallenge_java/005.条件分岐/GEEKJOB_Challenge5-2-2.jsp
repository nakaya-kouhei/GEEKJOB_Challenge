<%-- 
    Document   : GEEKJOB_Challenge5-2-2
    Created on : 2017/10/05, 17:10:20
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
            char word = 'A';
           
            switch(word){
                
                case 'A':
                
                    out.print("英語");
                    
                    break;
                    
                case 'あ':
                
                    out.print("日本語");
                    
                    break;
                
                default:
                    
                    break;
            }

        %>   
    </body>
</html>
