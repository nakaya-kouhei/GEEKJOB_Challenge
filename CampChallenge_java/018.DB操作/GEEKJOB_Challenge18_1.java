/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guest1Day
 */
public class GEEKJOB_Challenge18_1 extends HttpServlet {

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
            
            //  データベースと接続するインスタンスを宣言
            Connection db_con = null;
            
            try {

                //  MysqlのJDBCドライバのインスタンスを生成　下行DriverManagerにセットされる
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                //  データベースと接続
                db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db", "NAKAYA", "teranoide0");
                
                out.println("データベースと接続しました。<br>");

                //  データベースとの接続を閉じる
                db_con.close();

                out.println("データベースとの接続を閉じました。<br>");

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
                        
                        out.println("データベースとの接続を最終的に閉じました。<br>");
                        
                    } catch(SQLException e_con) {
                        
                        out.println(e_con.getMessage());
                        
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
