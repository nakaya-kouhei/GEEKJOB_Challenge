<%@page import="javax.servlet.http.HttpSession" %>
<%
    //セッション値を削除
    HttpSession hs = request.getSession();
    hs.invalidate();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JUMSトップ</title>
</head>
<body>
    <h1>ユーザー情報管理システム　トップページ</h1><br>
    <h3>ここでは、ユーザー情報の登録や検索、
        及び修正や削除を行うことができます。</h3><br>
    <a href="insert">ユーザー新規登録</a><br>
    <a href="Search" >ユーザー検索(修正・削除)</a><br>
</body>
</html>
