<%@page import="jums.InsertCheck"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.UserDataBeans"
        import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
    ArrayList<String> chkList = udb.chkproperties();
    boolean datecheck = InsertCheck.getInstance().dateCheck(udb);
    boolean tellcheck = InsertCheck.getInstance().tellCheck(udb);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
    </head>
    <body>
        <% if(chkList.isEmpty() && datecheck && tellcheck){ %>
            <h1>変更結果</h1><br>
            名前:<%= udb.getName()%><br>
            生年月日:<%= udb.getYear()%>年<%= udb.getMonth()%>月<%= udb.getDay()%>日<br>
            種別:<%= jh.exTypenum(udb.getType())%><br>
            電話番号:<%= udb.getTell()%><br>
            自己紹介:<%= udb.getComment()%><br>
            <br>
            以上の内容で更新しました。<br>
            <form action="ResultDetail" method="POST">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
                <input type="hidden" name="id"  value="<%= request.getAttribute("id")%>"><!-- 再表示 -->
                <input type="submit" name="btnSubmit" value="詳細情報画面に戻る">
            </form>
        <% }else{ %>
            <h1>入力が不完全です</h1>
            <%= jh.chkinput(chkList)%>
            <%= jh.dateCheck(datecheck)%>
            <%= jh.tellCheck(tellcheck)%>
            <br>
            <form action="Update" method="POST">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
                <input type="submit" name="btnSubmit" value="変更画面に戻る">
            </form>
        <% } %>
        <br>
        <%=jh.home()%>
    </body>
</html>
