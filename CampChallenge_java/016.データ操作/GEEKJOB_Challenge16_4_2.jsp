<%-- 
    Document   : GEEKJOB_Challenge16_4_2
    Created on : 2017/10/26, 13:48:05
    Author     : guest1Day
--%>

<%@page import="javax.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            //  セッションのインスタンスを宣言
            HttpSession hs = request.getSession();
            //  HTMLからの入力情報の文字コードを設定（UTF-8）
            request.setCharacterEncoding("UTF-8");
            
            //  セッションから情報を取得
            hs.setAttribute("name", request.getParameter("name"));
            hs.setAttribute("sex", request.getParameter("sex"));
            hs.setAttribute("hobby", request.getParameter("hobby"));
            
            //  念のため初期化
            String name  = "";
            String sex   = "";
            String hobby = "";
            
            // セッションからの情報をStringに変換して入力
            name  = String.valueOf(hs.getAttribute("name"));
            sex   = String.valueOf(hs.getAttribute("sex"));
            hobby = String.valueOf(hs.getAttribute("hobby"));
                    
        %>
        <!-- 同じjspページに遷移する -->
        <form action="./GEEKJOB_Challenge16_4_2.jsp" method="post">
        <!-- HTMLとJavaの処理を記述 -->
        
                    <pre>       </pre>
            
            <!-- 入力フィールドは、セッションからのデータの有無で場合分け -->
            <!-- 氏名を入力するテキストボックス -->                    　
            <%  if(name.equals("null")) { %>
            
                    氏名
                    <input type="text" name="name"><br><br>
                    
            <%  } else { %>
            
                    氏名
                    <input type="text" name="name" value="<%= name %>"><br><br>
                    
            <% } %>
                    
            <!-- 性別を指定するラジオボタン -->　
            <%  if(sex.equals("null")) { %>
            
                    性別　男
                    <input type="radio" name="sex" value="man"><br>
              　    　　　　女
                    <input type="radio" name="sex" value="woman"><br><br>
                    
            <%  } else { %>
            
                    性別　男
                    <!-- セッションからのデータで初期値を決定 -->
                    <input type="radio" name="sex" value="man"
                           <% if(sex.equals("man")) { %> checked="" <% } %>><br>
                 　 　　　　女
                    <input type="radio" name="sex" value="woman"
                           <% if(sex.equals("woman")) { %> checked="" <% } %>><br><br>
                    
            <%  } %>
            
            <!-- 趣味を入力するテキストエリア -->　
                    
            <%  if(hobby.equals("null")) { %>
            
                    趣味　
                    <textarea name="hobby"></textarea><br><br>
                    
            <%  } else { %>
            
                    趣味　
                    <textarea name="hobby"><%= hobby %></textarea><br><br>
                    
            <%  } %>
            
                    <!-- 送信ボタン -->
                    <input type="submit" name="send" value="送信">
        </form>
    </body>
    </body>
</html>
