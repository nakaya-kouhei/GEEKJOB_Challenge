<%-- 
    Document   : GEEKJOB_Challenge18_8
    Created on : 2017/10/31, 10:11:18
    Author     : guest1Day
--%>

<%@page import="java.util.ArrayList"%>
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
            
            //　念のため初期化
            String  name = "";
            
            // セッションからの情報をStringに変換して入力
            name = String.valueOf(hs.getAttribute("name"));
                    
        %>
        <!-- 同じjspページに遷移する -->
        <form action="./GEEKJOB_Challenge18_8.jsp" method="post">
        <!-- HTMLとJavaの処理を記述 -->
        
                    氏名検索フォーム<br><br>
                    
                    　氏名もしくは一文字を入力して下さい<br><br>
                     
            <!-- 入力フィールドは、セッションからのデータの有無で場合分け -->
            <!-- 名前を入力するテキストボックス -->                    　
            <%  //nameが"null"か判定
                if(name.equals("null")) { %>
            
                    氏名
                    <input type="text" name="name">　　入力して下さい<br><br>
                    
            <%  } else { %>
            
                    <!-- 入力情報を保持 -->
                    氏名
                    <input type="text" name="name" value="<%= name %>"><br><br>
                    
            <% } %>
            
                    <!-- 送信ボタン -->
                    <input type="submit" name="send" value="送信"><br><br>
        </form>
            
        <%
            
            //  入力がない場合、データベースとは接続しない
            if(name.equals("null") || name.isEmpty()) {
                
                out.println("氏名が未入力です。<br>");
                out.println("入力してください。<br>");
                
            } else {

                //  データベースと接続するdb_conとSQL文を実行するdb_stを宣言
                Connection        db_con  = null;
                PreparedStatement db_st   = null;
                ResultSet         db_data = null;

                try {

                    //  データベースを使用する準備
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");

                    try {

                        //  データベースに入力するSQL文
                        String sql = "SELECT * FROM profiles WHERE name LIKE ?";

                        //  SQL文を渡す
                        db_st = db_con.prepareStatement(sql);

                        //  上記"?"に設定する値
                        db_st.setString(1, "%" + name + "%");

                        //  SQL文を実行　情報を取得
                        db_data = db_st.executeQuery();
                        
                        //  データベースから取得した値を格納する変数
                        int    id;
                        String tel;
                        int    age;
                        String birthday;
                        ArrayList<String> rows = new ArrayList<String>();

                        //  取得した情報を表示　db_data内を一行づつ抽出
                        while(db_data.next()) {

                            id = db_data.getInt("profilesID");
                            name = db_data.getString("name");
                            tel = db_data.getString("tel");
                            age = db_data.getInt("age");
                            birthday = String.valueOf(db_data.getDate("birthday"));

                            rows.add(id + "　　" + name + "　" + tel + "　" + age + "　 " + birthday + "<br><br>");

                        }

                        //  条件に一致するデータが無い場合
                        if(rows.isEmpty()) {

                            out.println("合致するデータがありません。<br>");

                        //  条件に一致するデータが有る場合    
                        } else {

                            out.println("データベースの情報を表示します。<br><br>");
                            out.println("ID　　" + "　氏名 　　" + "　　電話番号　　" + " 年齢　" + "生年月日" + "<br>");
                            
                            for(String data : rows) {
                                
                                out.println(data);
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
