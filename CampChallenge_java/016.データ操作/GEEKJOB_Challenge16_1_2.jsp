<%-- 
    Document   : GEEKJOB_Challenge16_1_2
    Created on : 2017/10/25, 14:34:39
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
        
            //  HTMLからの入力情報の文字コードを設定（UTF-8）
            request.setCharacterEncoding("UTF-8");
            
            //  氏名を表示
            out.println("氏名：" + request.getParameter("name") + "<br>");
            
            //  性別を判定して表示
            if(request.getParameter("sex") == "man") {
                
                out.println("性別：男" + "<br>");
                
            } else {
                
                out.println("性別：女" + "<br>");
                
            }
            
            //  趣味を表示
            out.println("趣味：" + request.getParameter("hobby") + "<br>");
        
        %>
    </body>
</html>
