<%-- 
    Document   : GEEKJOB_Challenge18_10
    Created on : 2017/10/31, 12:02:24
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
            
            //  セッションから情報を取得
            hs.setAttribute("profilesID", request.getParameter("profilesID"));
            
            //  念のため初期化
            String  profilesID = "";
            boolean judge      = true;
            
            // セッションからの情報をStringに変換して入力
            profilesID = String.valueOf(hs.getAttribute("profilesID"));
                    
        %>
        <!-- 同じjspページに遷移する -->
        <form action="./GEEKJOB_Challenge18_10.jsp" method="post">
        <!-- HTMLとJavaの処理を記述 -->
        
                    削除フォーム
                    <pre>       </pre>
            
            <!-- 入力フィールドは、セッションからのデータの有無で場合分け -->
            <!-- IDを入力するテキストボックス -->                    　
            <%  if(profilesID == "null" || profilesID.isEmpty()) { 
            
                    //  フォームに入力があるか判定
                    judge = false;
            
            %>
            
                    ID
                    <input type="text" name="profilesID">　入力して下さい。<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    ID
                    <input type="text" name="profilesID" value="<%= profilesID %>"><br><br>
                    
            <% } %>
            
                    <!-- 送信ボタン -->
                    <input type="submit" name="send" value="送信"><br><br>
        </form>
            
        <%
            
            //  profilesIDが入力されていない場合、データベースとは接続しない
            if(!judge) {
                
                out.println("IDが未入力です。<br>");
                out.println("入力してください。<br>");
                
            } else {

                //  データベースと接続するdb_conとSQL文を実行するdb_stを宣言
                Connection        db_con  = null;
                PreparedStatement db_st   = null;

                try {

                    //  データベースを使用する準備
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");

                    try {

                        //  データベースに入力する情報を設定
                        String sql = "DELETE FROM profiles WHERE profilesID=?";

                        //  SQL文を渡す
                        db_st = db_con.prepareStatement(sql);

                        //  上記"?"に設定する値
                        db_st.setInt(1, Integer.valueOf(profilesID));

                        //  SQL文を実行
                        db_st.executeUpdate();
                        
                        out.println("データを削除しました。<br>");

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

                    //  上に同じ
                    if(db_st != null) {

                        try {

                            db_st.close();

                        } catch(SQLException e_st) {

                            out.println(e_st.getMessage());

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
                }
            }

        %>
    </body>
</html>
