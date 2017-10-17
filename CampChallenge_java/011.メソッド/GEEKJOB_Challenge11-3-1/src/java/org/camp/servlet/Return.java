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

/**
 *
 * @author guest1Day
 */
public class Return extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //  ユーザー定義メソッド　計算結果を表示し、boolean型を返す
    boolean multiplication(int num1, int num2, boolean i, PrintWriter pw){
        
        int     answer;
        boolean Authenticity = true;
        
        if(i){
            
            //  3つ目の引数がtrueの場合
            answer = (int)Math.pow((num1*num2), 2);
            //  結果を表示
            pw.println("(" + num1 + "*" + num2 + ")^2" + "=" + answer + "<br>");
            
        }else{
            
            //  3つ目の引数がfalseの場合
            answer = (num1*num2);
            //  結果を表示
            pw.println(num1 + "*" + num2 + "=" + answer + "<br>");
            
        }
        
        //  結果を返す
        return Authenticity;
    }
    
    //  ユーザー定義メソッド　デフォルト値でメソッドを呼び出し、boolean型を返す
    boolean multiplication(int num1, PrintWriter pw){        
        
        boolean Authenticity;
        
        //  デフォルト値でメソッドを呼び出す
        Authenticity = multiplication(num1, 5, false, pw);
        
        //  結果を返す
        return Authenticity;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            int num1 = 3;
            int num2 = 6;
            boolean i = true;
            //  メソッドの戻り値を受け取る変数
            boolean Authenticity;
            
            //  メソッドを呼び出して戻り値を受け取る
            Authenticity = multiplication(num1, num2, i, out);
            
            // 結果を表示
            if(Authenticity){
            
                //  戻り値がtrueの場合
                out.println("この処理は正しく実行できました<br>");
                
            }else{
                
                //  戻り値がtrue以外の場合
                out.println("正しく実行できませんでした<br>");
                
            }
            
            //  デフォルトのメソッドを呼び出して戻り値を受け取る
            Authenticity = multiplication(num1, out);
            //  結果を表示
            if(Authenticity){
            
                //  戻り値がtrueの場合
                out.println("この処理は正しく実行できました<br>");
                
            }else{
                
                //  戻り値がtrue以外の場合
                out.println("正しく実行できませんでした<br>");
                
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
