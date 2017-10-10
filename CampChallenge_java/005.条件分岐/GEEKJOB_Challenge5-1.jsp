<%-- 
    Document   : GEEKJOB_Challenge5-1
    Created on : 2017/10/05, 13:39:31
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
            int num = 4;
            
            if(num==1){
                
                out.print("１です！");
                
            }else if(num==2){
                
                out.print("プログラミングキャンプ！");
                
            }else{
                
                out.print("その他です！");
                
            }
        %>
    </body>
</html>
