<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="jums.UserDataBeans" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>削除確認</h1>
    名前:<%= udb.getName()%><br>
    生年月日:<%= udb.getYear()%>年<%= udb.getMonth()%>月<%= udb.getDay()%>日<br>
    種別:<%= jh.exTypenum(udb.getType())%><br>
    電話番号:<%= udb.getTell()%><br>
    自己紹介:<%= udb.getComment()%><br>
    登録日時:<%= udd.getNewDate()%><br>
    <br>
    このレコードを本当に削除しますか？<br>
    <form action="DeleteResult" method="POST" name="deleteresult">
        <a href="javascript:document.getElementsByName('deleteresult')[0].submit();">はい</a><br>
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
    </form>
    <form action="ResultDetail" method="POST" name="resultdetail">
        <a href="javascript:document.getElementsByName('resultdetail')[0].submit();">いいえ</a><br>
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
        <input type="hidden" name="id"  value="<%= udd.getUserID()%>">
    </form>
    <br>
    <%=jh.home()%>
    </body>
</html>
