<%-- 
    Document   : GEEKJOB_Challenge18_12
    Created on : 2017/10/31, 13:48:21
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
            hs.setAttribute("name", request.getParameter("name"));
            hs.setAttribute("age", request.getParameter("age"));
            hs.setAttribute("birthday", request.getParameter("birthday"));
            
            //  念のため初期化
            String  profilesID = "";            
            String  name       = "";            
            String  tel        = "";
            String  age        = "";
            String  birthday   = "";
            boolean judge      = true;
            
            // セッションからの情報をStringに変換して入力   
            name       = String.valueOf(hs.getAttribute("name"));
            age        = String.valueOf(hs.getAttribute("age"));
            birthday   = String.valueOf(hs.getAttribute("birthday"));
            
        %>
        <!-- 同じjspページに遷移する -->
        <form action="./GEEKJOB_Challenge18_12.jsp" method="post">
        <!-- HTMLとJavaの処理を記述 -->
        
                    検索フォーム
                    <pre>       </pre>
            
            <!-- 入力フィールドは、セッションからのデータの有無で場合分け -->
            <!-- 氏名を入力するテキストボックス -->                    　
            <%  if(name == "null" || name.isEmpty()) { 
            
                    //  フォームに入力があるか判定
                    judge = false;
            
            %>
            
                    氏名　　　　
                    <input type="text" name="name">　入力して下さい。<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    氏名　　　　
                    <input type="text" name="name" value="<%= name %>"><br><br>
                    
            <% } %>
            
            <!-- 年齢を入力するテキストボックス -->                    　
            <%  if(age == "null" || age.isEmpty()) { 
            
                    //  フォームに入力があるか判定
                    judge = false;
            
            %>
            
                    年齢　　　　
                    <input type="text" name="age">　入力して下さい。<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    年齢　　　　
                    <input type="text" name="age" value="<%= age %>"><br><br>
                    
            <% } %>
            
            <!-- 生年月日を入力するテキストボックス -->                    　
            <%  if(birthday == "null" || birthday.isEmpty()) { 
            
                    //  フォームに入力があるか判定
                    judge = false;
            
            %>
            
                    生年月日　
                    <input type="text" name="birthday">　入力して下さい。<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    生年月日　
                    <input type="text" name="birthday" value="<%= birthday %>"><br><br>
                    
            <% } %>
            
                    <!-- 送信ボタン -->
                    <input type="submit" name="send" value="送信"><br><br>
        </form>
            
        <%
            
            if(!judge) {
                
                out.println("入力情報が不足しています。<br>");
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
                        String sql = "SELECT * FROM profiles WHERE name=? AND age=? AND birthday=?";

                        //  SQL文を渡す
                        db_st = db_con.prepareStatement(sql);

                        //  上記"?"に設定する値
                        db_st.setString(1, name);
                        db_st.setInt(2, Integer.valueOf(age));
                        db_st.setString(3, birthday);

                        //  SQL文を実行　結果を取得
                        db_data = db_st.executeQuery();

                        //  条件に一致するデータがあるか判定
                        if(db_data.wasNull()) {
                            
                            out.println("合致するデータがありません。<br>");
                            
                        //  データがある場合
                        } else {
                            
                            out.println("データベースの情報を表示します。<br><br>");

                            //  取得した情報を表示　db_data内を一行づつ抽出
                            while(db_data.next()) {

                                profilesID = String.valueOf(db_data.getInt("profilesID"));
                                name = db_data.getString("name");
                                tel = db_data.getString("tel");
                                age = String.valueOf(db_data.getInt("age"));
                                birthday = String.valueOf(db_data.getDate("birthday"));

                                out.println("ID　　" + "　氏名 　　" + "　　電話番号　　" + " 年齢　" + "生年月日" + "<br>");
                                out.println(profilesID + "　　" + name + "　" + tel + "　" + age + "　 " + birthday + "<br><br>");

                            }
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

        %>
    </body>
</html>
