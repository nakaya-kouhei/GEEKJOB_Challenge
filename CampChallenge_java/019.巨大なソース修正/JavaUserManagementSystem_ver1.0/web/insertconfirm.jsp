<%@page import="jums.JumsHelper" %>
<%@page import="jums.UserDataBeans" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.util.HashMap" %>
<%
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("UDB"); // フォームの情報を格納するJavaBeansインスタンス
    boolean blank = (Boolean)hs.getAttribute("blank");
    HashMap<String, Boolean> form = (HashMap)hs.getAttribute("form");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録確認画面</title>
    </head>
    <body>
        <!-- 空欄無し -->
        <% if(!blank){ %>
        <h1>登録確認</h1>
        名前:<%= udb.getName()%><br>
        生年月日:<%= udb.getYear()+"年"+udb.getMonth()+"月"+udb.getDay()+"日"%><br>
        種別:<%= udb.getType()%><br>
        電話番号:<%= udb.getTell()%><br>
        自己紹介:<%= udb.getComment()%><br>
        <br>
        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"> <!-- 直リンク防止用 -->
            <input type="submit" name="yes" value="はい">
        </form>
        <!-- 空欄有り -->
        <% }else{ %>
        <h1>入力が不完全です</h1>
        <% if(!form.get("name")){ %>
        名前を入力して下さい<br>
        <% } %>
        <% if(!form.get("year")){ %>
        誕生年を選択して下さい<br>
        <% } %>
        <% if(!form.get("month")){ %>
        誕生月を選択して下さい<br>
        <% } %>
        <% if(!form.get("day")){ %>
        誕生日を選択して下さい<br>
        <% } %>
        <% if(!form.get("type")){ %> <!-- 仕様上あり得ないが、念のため -->
        種別を選択して下さい<br>
        <% } %>
        <% if(!form.get("tell")){ %>
        電話番号を入力して下さい<br>
        <% } %>
        <% if(!form.get("comment")){ %>
        自己紹介を入力して下さい<br>
        <% } %>
        <br>
    <% } %>
        <form action="insert" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"> <!-- 直リンク防止用 -->
            <input type="hidden" name="blank"  value="" <% hs.setAttribute("blank", true);%>> <!-- 入力がある状態を保持 -->
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
        <br>
        <!-- トップページへのリンク -->
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
