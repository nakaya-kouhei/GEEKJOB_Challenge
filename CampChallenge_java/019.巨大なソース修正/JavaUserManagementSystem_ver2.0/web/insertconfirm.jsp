<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataBeans" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
    ArrayList<String> chkList = udb.chkproperties();
    boolean namecheck = (Boolean)hs.getAttribute("namecheck");
    boolean datecheck = (Boolean)hs.getAttribute("datecheck");
    boolean tellcheck = (Boolean)hs.getAttribute("tellcheck");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録確認画面</title>
    </head>
    <body>
    <% if(chkList.size() == 0 && datecheck && tellcheck){ %>
        <h1>登録確認</h1>
        名前:<%= udb.getName()%><br>
        生年月日:<%= udb.getYear()+"年"+udb.getMonth()+"月"+udb.getDay()+"日"%><br>
        種別:<%= jh.exTypenum(udb.getType())%><br>
        電話番号:<%= udb.getTell()%><br>
        自己紹介:<%= udb.getComment()%><br>
        <br>
        <%= jh.nameCheck(namecheck)%>
        上記の内容で登録します。よろしいですか？<br>
        <form action="insertresult" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
            <input type="submit" name="yes" value="はい">
        </form>
    <% }else{ %>
        <h1>入力が不完全です</h1>
        <%= jh.chkinput(chkList)%>
        <%= jh.nameCheck(namecheck)%>
        <%= jh.dateCheck(datecheck)%>
        <%= jh.tellCheck(tellcheck)%>
        <br>
    <% } %>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT"><!-- 再入力 -->
        </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
