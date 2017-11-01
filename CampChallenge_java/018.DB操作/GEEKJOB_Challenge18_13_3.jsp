<%-- 
    Document   : GEEKJOB_Challenge18_13_3
    Created on : 2017/11/01, 10:14:15
    Author     : guest1Day
--%>

<%@page import="java.sql.*"%>
<%@page import="javax.servlet.http.HttpSession"%>
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
            
            //  念のため変数を初期化
            //  ログインの有無を判定する変数
            boolean login = false;
            
            //  商品情報登録テーブル goodsID INT Primary Key, name VARCHAR(255), price INT, stock INT
            String  goodsID = "";            
            String  name    = "";            
            String  price   = "";
            String  stock   = "";
            
            // ログインの有無を取得  
            login = Boolean.valueOf(String.valueOf(hs.getAttribute("login")));
                
        %>
        
                        <pre>       </pre>
                        商品一覧
                        <pre>   </pre>
            
        <%
            
            if(login) {

                //  データベースとやり取りを行う変数
                Connection        db_con2  = null;
                PreparedStatement db_st2   = null;
                ResultSet         db_data2 = null;

                try {

                    //  データベースを使用する準備
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    db_con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");

                    try {

                        //  データベースに入力する情報を設定
                        String sql2 = "SELECT * FROM goodslist";

                        //  SQL文を渡す
                        db_st2 = db_con2.prepareStatement(sql2);

                        //  SQL文を実行　結果を取得
                        db_data2 = db_st2.executeQuery();
                        
        %>
        
                <!-- データベースの情報を表にして表示 -->
                <table border="1" width="700" cellspacing="0" cellpadding="5">
                    <!-- 表題 -->
                    <caption><b><big>商品情報</big></b></caption>
                    <tr>
                        <th>商品ID</th><th>商品名</th><th>値段(円)</th><th>在庫</th>
                    </tr>                

        <%

                        //  取得した情報を表示　db_data内を一行づつ抽出
                        while(db_data2.next()) {

                            goodsID = String.valueOf(db_data2.getInt("goodsID"));
                            name    = db_data2.getString("name");
                            price   = String.valueOf(db_data2.getInt("price"));
                            stock   = String.valueOf(db_data2.getInt("stock"));

        %>
       
                    <tr>
                        <th><%= goodsID %></th>
                        <th><%= name %></th>
                        <th><%= price %></th>
                        <th><%= stock %></th>
                    </tr>
        
        <%              } %>
        
                </table>
        
        <%

                    } catch(SQLException e_sql) {

                        out.println("接続時にエラーが発生しました：" + e_sql.toString());

                    } catch(Exception e) {

                        out.println("接続時にエラーが発生しました：" + e.toString());

                    }

                    //  データベースとの接続を閉じる
                    if(db_con2 != null) {

                        try {

                            db_con2.close();

                        } catch(SQLException e_con) {

                            out.println(e_con.getMessage());

                        }
                    }

                    if(db_st2 != null) {

                        try {

                            db_st2.close();

                        } catch(SQLException e_st) {

                            out.println(e_st.getMessage());

                        }
                    }

                    if(db_data2 != null) {

                        try {

                            db_data2.close();

                        } catch(SQLException e_data) {

                            out.println(e_data.getMessage());

                        }
                    }

                } catch(SQLException e_sql) {

                    //  例外処理　データベースへの接続にエラーが発生した場合
                    out.println("接続時にエラーが発生しました：" + e_sql.toString());

                } catch(Exception e) {

                    //  例外処理
                    out.println("接続時にエラーが発生しました：" + e.toString());

                } finally {

                    //  最終処理
                    //  データベースへの接続が閉じているか確認　接続しているなら閉じる
                    if(db_con2 != null) {

                        try {

                            db_con2.close();

                        } catch(SQLException e_con) {

                            out.println(e_con.getMessage());

                        }
                    }

                    //  上に同じ
                    if(db_st2 != null) {

                        try {

                            db_st2.close();

                        } catch(SQLException e_st) {

                            out.println(e_st.getMessage());

                        }
                    }

                    //  上に同じ
                    if(db_data2 != null) {

                        try {

                            db_data2.close();

                        } catch(SQLException e_data) {

                            out.println(e_data.getMessage());

                        }
                    }
                }
            }
                    
        %>
                <form action="./GEEKJOB_Challenge18_13_2.jsp" method="post">
                <!-- HTMLとJavaの処理を記述 -->

                        <pre>       </pre>
                        
                        <!-- 前のページに戻る -->
                        <input type="submit" name="send2" value="戻る" ><br><br>
                </form>

                <!-- ログアウト -->
                <form action="./GEEKJOB_Challenge18_13_1.jsp" method="post">
                <!-- HTMLの処理を記述 -->

                        <pre>　       </pre>

                        <!-- ログアウトボタン -->
                        <input type="submit" name="send2" value="ログアウト" ><br><br>
                        <input type="hidden" name="login" value="false">
                        
                </form>
    </body>
</html>
