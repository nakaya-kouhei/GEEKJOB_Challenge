/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author guest1Day
 */
public class GEEKJOB_Challenge18_3 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //  データベースと接続するdb_conとSQL文を実行するdb_stを宣言
            Connection        db_con  = null;
            PreparedStatement db_st   = null;
            ResultSet         db_data = null;
            
            try {

                //  データベースを使用する準備
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");
                
                out.println("データベースと接続しました。<br>");
                
                try {
                    
                    //  データベースに入力するSQL文
                    String sql = "SELECT * FROM profiles";
                    
                    //  SQL文を渡す
                    db_st = db_con.prepareStatement(sql);
                    
                    //  SQL文を実行　取得した情報を渡す
                    db_data = db_st.executeQuery();

                    //  データベースから取得した値を格納する変数
                    int    id;
                    String name;
                    String tel;
                    int    age;
                    String birthday;
                    
                    out.println("データベースの情報を表示します。<br><br>");
                    
                    //  取得した情報を表示　db_data内を一行づつ抽出
                    while(db_data.next()) {

                        id = db_data.getInt("profilesID");
                        name = db_data.getString("name");
                        tel = db_data.getString("tel");
                        age = db_data.getInt("age");
                        birthday = String.valueOf(db_data.getDate("birthday"));
                        
                        out.println(id + "　" + name + "　" + tel + "　" + age + "　" + birthday + "<br>");

                    }
                    
                } catch(SQLException e_sql) {
                    
                    //  例外処理　データベースへの接続にエラーが発生した場合
                    out.println("<br>接続時にエラーが発生しました：" + e_sql.toString());
                    
                } catch(Exception e) {
                
                    //  例外処理
                    out.println("<br>接続時にエラーが発生しました：" + e.toString());

                }
                
                //  データベースとの接続を閉じる
                db_con.close();
                db_st.close();
                db_data.close();
                
                out.println("<br>データベースとの接続を閉じました。<br>");
                
            } catch(SQLException e_sql) {
                
                //  例外処理　データベースへの接続にエラーが発生した場合
                out.println("<br>接続時にエラーが発生しました：" + e_sql.toString());
                
            } catch(Exception e) {
                
                //  例外処理
                out.println("<br>接続時にエラーが発生しました：" + e.toString());

            } finally {
                
                //  最終処理
                //  データベースへの接続が閉じているか確認　接続しているなら閉じる
                if(db_con != null) {
                    
                    try {
                        
                        db_con.close();
                        
                        out.println("<br>データベースとの接続を最終的に閉じました。<br>");
                        
                    } catch(SQLException e_con) {
                        
                        out.println(e_con.getMessage());
                        
                    }
                }
                
                //  上に同じ
                if(db_st != null) {
                    
                    try {
                        
                        db_st.close();
                        
                        out.println("<br>データベースとの接続を最終的に閉じました。<br>");
                        
                    } catch(SQLException e_st) {
                        
                        out.println(e_st.getMessage());
                        
                    }
                }
                
                //  上に同じ
                if(db_data != null) {
                    
                    try {
                        
                        db_data.close();
                        
                        out.println("<br>データベースとの接続を最終的に閉じました。<br>");
                        
                    } catch(SQLException e_data) {
                        
                        out.println(e_data.getMessage());
                        
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
