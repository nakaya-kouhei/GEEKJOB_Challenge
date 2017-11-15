<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper" %>
<% 
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除結果画面</title>
    </head>
    <body>
    <h1>削除確認</h1>
    削除しました。<br>
    <br>
    <form action="SearchResult" method="POST">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
        <input type="hidden" name="name"  value="<%= request.getAttribute("name")%>"><!-- 再表示 -->
        <input type="hidden" name="year"  value="<%= request.getAttribute("year")%>"><!-- 再表示 -->
        <input type="hidden" name="type"  value="<%= request.getAttribute("type")%>"><!-- 再表示 -->
        <input type="submit" name="btnSubmit" value="検索結果画面に戻る">
    </form>
    <br>
    <%=jh.home()%>
    </body>
</html>
