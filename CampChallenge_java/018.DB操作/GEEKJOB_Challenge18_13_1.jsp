<%-- 
    Document   : GEEKJOB_Challenge18_13
    Created on : 2017/10/31, 17:49:35
    Author     : guest1Day
--%>

<%@page import="java.sql.*"%>
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
            
            //  念のため初期化
            String  name     = "";            
            String  password = "";
            boolean judge    = true;
            boolean login    = false;

            //  セッションから情報を取得
            hs.setAttribute("name", request.getParameter("name"));
            hs.setAttribute("password", request.getParameter("password"));
            hs.setAttribute("login", request.getParameter("login"));

            // セッションからの情報をStringに変換して入力
            name       = String.valueOf(hs.getAttribute("name"));
            password   = String.valueOf(hs.getAttribute("password"));
            
        %>
        <!-- ログイン画面 -->
        <form action="./GEEKJOB_Challenge18_13_1.jsp" method="post">
        <!-- HTMLとJavaの処理を記述 -->
        
                    在庫管理システム　ログインページ
                    <pre>       </pre>
            
            <!-- 入力フィールドは、セッションからのデータの有無で場合分け -->
            <!-- ユーザー名を入力するテキストボックス -->                    　
            <%  if(name == "null" || name.isEmpty()) { 
            
                    //  フォームに入力があるか判定
                    judge = false;
            
            %>
            
                    ユーザー名　　
                    <input type="text" name="name">　入力して下さい。<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    ユーザー名　　
                    <input type="text" name="name" value="<%= name %>"><br><br>
                    
            <% } %>
            
            <!-- パスワードを入力するテキストボックス -->                    　
            <%  if(password == "null" || password.isEmpty()) { 
            
                    //  フォームに入力があるか判定
                    judge = false;
            
            %>
            
                    パスワード　　
                    <input type="text" name="password">　入力して下さい。<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    パスワード　　
                    <input type="text" name="password" value="<%= password %>"><br><br>
                    
            <%
                
                }

                if(!login) { 
            
            %>
                    
                    <!-- ログインボタン -->
                    <input type="submit" name="send" value="ログイン"><br><br>

            <%  } %>
                    
        </form>

        <%
            
            //  未入力の項目があるか判定
            if(!judge) {
                
                //  空欄があるのでログインできない
                hs.setAttribute("login", false);
                
            } else {
                
                //  データベースとやり取りを行う変数
                Connection        db_con  = null;
                PreparedStatement db_st   = null;
                ResultSet         db_data = null;
            
                try {

                    //  データベースを使用する準備　ユーザー名とパスワードを照合
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");

                    //  ユーザー名とパスワードを照合
                    try {

                        //  データベースに入力する情報を設定
                        String sql = "SELECT * FROM userprofiles WHERE name=? AND password=?";

                        //  SQL文を渡す
                        db_st = db_con.prepareStatement(sql);

                        //  上記"?"に設定する値
                        db_st.setString(1, name);
                        db_st.setString(2, password);

                        //  SQL文を実行　結果を取得
                        db_data = db_st.executeQuery();
                        
                        //  該当データが存在するか確認
                        db_data.last();
                        
                        int length = db_data.getRow();
                        
                        db_data.beforeFirst();

                        //  条件に一致するデータがあるか判定
                        if(length == 0) {
                            
                            //  ログインできません
                            hs.setAttribute("login", false);
                            
                        //  データがある場合
                        } else {
                            
                            //  ログインできます
                            hs.setAttribute("login", true);
                            
                        }
                        
                    } catch(SQLException e_sql) {

                        out.println("接続時にエラーが発生しました：" + e_sql.toString());

                    } catch(Exception e) {

                        out.println("接続時にエラーが発生しました：" + e.toString());

                    }
                    
                    //  データベースとの接続を閉じる
                    if(db_con != null) {

                        try {

                            db_con.close();

                        } catch(SQLException e_con) {

                            out.println(e_con.getMessage());

                        }
                    }

                    //  データベースとの接続を閉じる
                    if(db_st != null) {

                        try {

                            db_st.close();

                        } catch(SQLException e_st) {

                            out.println(e_st.getMessage());

                        }
                    }

                    //  データベースとの接続を閉じる
                    if(db_data != null) {

                        try {

                            db_data.close();

                        } catch(SQLException e_data) {

                            out.println(e_data.getMessage());

                        }
                    }
                    
                } catch(SQLException e_sql) {

                    //  例外処理　データベースへの接続にエラーが発生した場合
                    out.println("接続時にエラーが発生しました：" + e_sql.toString() + "<br>");
                    out.println("ログインできません。<br>");

                } catch(Exception e) {

                    //  例外処理
                    out.println("接続時にエラーが発生しました：" + e.toString());

                } finally {

                    //  最終処理
                    //  データベースへの接続が閉じているか確認　接続しているなら閉じる
                    if(db_con != null) {

                        try {

                            db_con.close();

                        } catch(SQLException e_con) {

                            out.println(e_con.getMessage());

                        }
                    }
                }
            }
            
        %>
            
        <%
                
                //  ログインの可否を取得
                login = Boolean.valueOf(String.valueOf(hs.getAttribute("login")));
                
                //  ログインしている場合
                if(login) { 
            
        %>

        <!-- DB操作ページに移動する -->
        <form action="./GEEKJOB_Challenge18_13_2.jsp" method="post">
        <!-- HTMLの処理を記述 -->            
        
                    ログインしました。<br>
                    在庫管理ページに移動してください。<br>
                    <!-- 送信ボタン -->
                    <input type="submit" name="send" value="在庫管理"><br><br>

        </form>

        <%      } %>            
            
    </body>
</html>
