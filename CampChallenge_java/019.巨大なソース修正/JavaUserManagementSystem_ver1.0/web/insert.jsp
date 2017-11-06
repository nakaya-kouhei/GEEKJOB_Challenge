<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.JumsHelper" %>
<%@page import="jums.UserDataBeans" %>
<%@page import="java.util.HashMap" %>
<%
    HttpSession hs = request.getSession();
    
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("UDB");
    boolean blank = (Boolean)hs.getAttribute("blank");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
    </head>
    <body>
    <form action="insertconfirm" method="POST">
    <%
        //確認画面から戻った場合
        if(!blank){
    %>
        名前:
        <input type="text" name="name" value="">
        <br><br>

        生年月日:　
        <select name="year">
            <option value="">----</option>
            <%
            for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>"> <%=i%> </option>
            <% } %>
        </select>年
        <select name="month">
            <option value="">--</option>
            <%
            for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>"><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="">--</option>
            <%
            for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>"><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
        <input type="radio" name="type" value="1" checked="checked">エンジニア<br>
        <input type="radio" name="type" value="2">営業<br>
        <input type="radio" name="type" value="3">その他<br>
        <br>

        電話番号:
        <input type="text" name="tell" value="">
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"></textarea><br><br>
        
    <%
        }else{
            //各項目の入力状態を取得
            HashMap<String, Boolean> form = (HashMap)hs.getAttribute("form");
    %>
        <br>
        空欄を埋めて下さい
        <br><br>
    
        名前:
        <input type="text" name="name" value="<%=udb.getName()%>">
        <%
            if(!form.get("name")){%>　記入して下さい <% } %>
        <br><br>

        生年月日:　
        <select name="year">
            <%
                if(!form.get("year")){ %>
            <option value="">---</option>
            <%
                }else{ %>
            <option value=<%=udb.getYear()%>><%=udb.getYear()%></option>
            <% } 
            for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>"> <%=i%> </option>
            <% } %>
        </select>年
        <select name="month">
            <%
                if(!form.get("month")){ %>
            <option value="">--</option>
            <%
                }else{ %>
            <option value=<%=udb.getMonth()%>><%=udb.getMonth()%></option>
            <% } 
            for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>"><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <%
                if(!form.get("day")){ %>
            <option value="">--</option>
            <%
                }else{ %>
            <option value=<%=udb.getDay()%>><%=udb.getDay()%></option>
            <% } 
            for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>"><%=i%></option>
            <% } %>
        </select>日
        <%
            if(!form.get("year")&&!form.get("month")&&!form.get("day")){%>　年月日を選択して下さい
        <%
            }else if(!form.get("year")&&!form.get("month")){%>　年と月を選択して下さい
        <%
            }else if(!form.get("year")&&!form.get("day")){%>　年と日を選択して下さい
        <%
            }else if(!form.get("month")&&!form.get("day")){%>　月と日を選択して下さい
        <%
            }else if(!form.get("year")){%>　年を選択して下さい
        <%
            }else if(!form.get("month")){%>　月を選択して下さい
        <%
            }else if(!form.get("day")){%>　日を選択して下さい <% } %>
        <br><br>

        種別:
        <%
            if(!form.get("type")){%>　選択して下さい <% } %>
        <br>
        <%
            if(udb.getType().equals("1")){ %>
        <input type="radio" name="type" value="1" checked="checked">エンジニア<br>
        <input type="radio" name="type" value="2">営業<br>
        <input type="radio" name="type" value="3">その他<br>
        <%
            }else if(udb.getType().equals("2")){ %>
        <input type="radio" name="type" value="1">エンジニア<br>
        <input type="radio" name="type" value="2" checked="checked">営業<br>
        <input type="radio" name="type" value="3">その他<br>
        <%
            }else if(udb.getType().equals("3")){ %>
        <input type="radio" name="type" value="1">エンジニア<br>
        <input type="radio" name="type" value="2">営業<br>
        <input type="radio" name="type" value="3" checked="checked">その他<br>
        <% } %>
        <br>

        電話番号:
        <input type="text" name="tell" value="<%=udb.getTell()%>">
        <%
            if(!form.get("tell")){%>　記入して下さい <% } %>
        <br><br>

        自己紹介文
        <%
            if(!form.get("comment")){%>　記入して下さい <% } %>
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%=udb.getComment()%></textarea>
        <br><br>
        
    <%}%>
        
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"> <!-- 直リンク防止用 -->
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <!-- トップページへのリンク -->
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
