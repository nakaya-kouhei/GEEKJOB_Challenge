<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.util.List" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    List<UserDataDTO> resultList = (List<UserDataDTO>)hs.getAttribute("resultList");
    boolean data = (Boolean)hs.getAttribute("data");
    int i = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <% if(data){%><!-- 情報の有無を判定 -->
        <table border=1>
            <tr>
                <th>名前</th>
                <th>生年</th>
                <th>種別</th>
                <th>登録日時</th>
            </tr>
            <% for(UserDataDTO udd:resultList){ %><!-- 複数表示対応 -->
            <tr>
                <td>
                <form action="ResultDetail" method="POST" name="resultdetail">
                    <a href="javascript:document.getElementsByName('resultdetail')[<%=i%>].submit();"><%= udd.getName()%></a>
                    <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"><!-- 直リンク防止用 -->
                    <input type="hidden" name="id"  value="<%= udd.getUserID()%>">
                </form></td>
                <td><%= udd.getBirthday()%></td>
                <td><%= jh.exTypenum(udd.getType())%></td>
                <td><%= udd.getNewDate()%></td>
            </tr>
            <%   i++;%>
            <% } %>
        </table>
        <% }else{out.print("条件に合致する人が見つかりません。<br>条件を変えて再検索して下さい。");}%>
        <br>
        <form action="Search" method="POST">
            <input type="submit" name="no" value="検索画面に戻る">
            <input type="hidden" name="mode" value="REINPUT"><!-- 再入力 -->
        </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
