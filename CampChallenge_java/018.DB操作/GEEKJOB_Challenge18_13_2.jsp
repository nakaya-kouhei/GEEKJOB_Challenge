<%-- 
    Document   : GEEKJOB_Challenge18_13_2
    Created on : 2017/10/31, 18:10:32
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
            
            //  セッションから情報を取得
            //  商品情報登録テーブル goodsID INT Primary Key, name VARCHAR(255), price INT, stock INT
            hs.setAttribute("goodsID", request.getParameter("goodsID"));
            hs.setAttribute("name", request.getParameter("name"));
            hs.setAttribute("price", request.getParameter("price"));
            hs.setAttribute("stock", request.getParameter("stock"));
            
            //  念のため初期化
            boolean login   = false;
            String  goodsID = "";            
            String  name    = "";            
            String  price   = "";
            String  stock   = "";
            boolean judge   = true;
            
            // セッションからの情報をStringに変換して入力  
            login   = Boolean.valueOf(String.valueOf(hs.getAttribute("login")));
            goodsID = String.valueOf(hs.getAttribute("goodsID"));
            name    = String.valueOf(hs.getAttribute("name"));
            price   = String.valueOf(hs.getAttribute("price"));
            stock   = String.valueOf(hs.getAttribute("stock"));
            
            if(!login) {
                
        %>
        
                <form action="./GEEKJOB_Challenge18_13_1.jsp" method="post">
                <!-- HTMLとJavaの処理を記述 -->

                        ログインできません。
                        <pre>       </pre>

                       <!-- 前のページに戻る -->
                        <input type="submit" name="send" value="戻る"><br><br>
                </form>
        
        <%
                
            } else if(login) {

        %>
                <form action="./GEEKJOB_Challenge18_13_2.jsp" method="post">
                <!-- HTMLとJavaの処理を記述 -->

                            商品情報管理ページ
                            <pre>       </pre>
                            商品情報登録
                            <pre>       </pre>

                    <!-- 入力フィールドは、セッションからのデータの有無で場合分け -->
                    <!-- 商品IDを入力するテキストボックス -->                    　
                    <%  if(goodsID == "null" || goodsID.isEmpty()) { 

                            //  フォームに入力があるか判定
                            judge = false;

                    %>

                            商品ID　　　　
                            <input type="text" name="goodsID">　入力して下さい。<br><br>

                    <%  } else { %>

                            <!-- 入力情報を保持 -->
                            商品ID　　　　
                            <input type="text" name="goodsID" value="<%= goodsID %>"><br><br>

                    <% } %>

                    <!-- 商品名を入力するテキストボックス -->                    　
                    <%  if(name == "null" || name.isEmpty()) { 

                            //  フォームに入力があるか判定
                            judge = false;

                    %>

                            商品名　　　　
                            <input type="text" name="name">　入力して下さい。<br><br>

                    <%  } else { %>

                            <!-- 入力情報を保持 -->
                            商品名　　　　
                            <input type="text" name="name" value="<%= name %>"><br><br>

                    <% } %>

                    <!-- 値段を入力するテキストボックス -->                    　
                    <%  if(price == "null" || price.isEmpty()) { 

                            //  フォームに入力があるか判定
                            judge = false;

                    %>

                            値段　　　　　　
                            <input type="text" name="price">　入力して下さい。<br><br>

                    <%  } else { %>

                            <!-- 入力情報を保持 -->
                            値段　　　　　　
                            <input type="text" name="price" value="<%= price %>"><br><br>

                    <% } %>

                    <!-- 在庫を入力するテキストボックス -->                    　
                    <%  if(stock == "null" || stock.isEmpty()) { 

                            //  フォームに入力があるか判定
                            judge = false;

                    %>

                            在庫　　　　　　
                            <input type="text" name="stock">　入力して下さい。<br><br>

                    <%  } else { %>

                            <!-- 入力情報を保持 -->
                            在庫　　　　　　
                            <input type="text" name="stock" value="<%= stock %>"><br><br>

                    <% } %>

                            <!-- 送信ボタン -->
                            <input type="submit" name="send" value="送信"><br><br>
                </form>
            
        <%
            
                if(!judge) {

                    out.println("未入力の項目があります。<br>");
                    out.println("空欄を埋めてください。<br>");

                } else {

                    //  データベースとやり取りを行う変数
                    Connection        db_con  = null;
                    PreparedStatement db_st   = null;
                    ResultSet         db_data = null;

                    try {

                        //  データベースを使用する準備
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");

                        try {

                            //  データベースに入力する情報を設定
                            String sql = "INSERT INTO goodslist VALUES(?, ?, ?, ?)";

                            //  SQL文を渡す
                            db_st = db_con.prepareStatement(sql);

                            //  上記"?"に設定する値
                            db_st.setInt(1, Integer.valueOf(goodsID));
                            db_st.setString(2, name);
                            db_st.setInt(3, Integer.valueOf(price));
                            db_st.setInt(4, Integer.valueOf(stock));

                            //  SQL文を実行　結果を取得
                            db_st.executeUpdate();

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

                        if(db_st != null) {

                            try {

                                db_st.close();

                            } catch(SQLException e_st) {

                                out.println(e_st.getMessage());

                            }
                        }

                        if(db_data != null) {

                            try {

                                db_data.close();

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
                        if(db_con != null) {

                            try {

                                db_con.close();

                            } catch(SQLException e_con) {

                                out.println(e_con.getMessage());

                            }
                        }

                        //  上に同じ
                        if(db_st != null) {

                            try {

                                db_st.close();

                            } catch(SQLException e_st) {

                                out.println(e_st.getMessage());

                            }
                        }

                        //  上に同じ
                        if(db_data != null) {

                            try {

                                db_data.close();

                            } catch(SQLException e_data) {

                                out.println(e_data.getMessage());

                            }
                        }
                    }
                }
            }

        %>
                <!-- 商品一覧の表示ページに移動 -->
                <form action="./GEEKJOB_Challenge18_13_3.jsp" method="post">
                <!-- HTMLの処理を記述 -->

                        <pre>　       </pre>
                        商品一覧<br>

                        <!-- 商品一覧表示ボタン -->
                        <input type="submit" name="send2" value="一覧表示" ><br><br>
                        
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
