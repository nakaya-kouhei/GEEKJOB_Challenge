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
public class argument2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //  ユーザー定義メソッド　計算結果を返す
    int multiplication(int num1, int num2, boolean i){
        
        int answer;
        
        if(i){
            
            //  3つ目の引数がtrueの場合
            answer = (int)Math.pow((num1*num2), 2);
            
        }else{
            
            //  3つ目の引数がfalseの場合
            answer = (num1*num2);
            
        }
        
        //  結果を返す
        return answer;
    }
    
    //  ユーザー定義メソッド　デフォルト
    int multiplication(int num1){
        
        int answer;
        
        //  デフォルト値でメソッドを呼び出す
        answer = (int)multiplication(num1, 5, false);
        
        //  結果を返す
        return answer;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            int num1 = 3;
            int num2 = 6;
            boolean i = true;
            int answer;
            
            //  3つの引数を指定してメソッドを呼び出す
            answer = multiplication(num1, num2, i);
            
            if(i){
            
                //  3つ目の引数がtrueの場合
                out.println("(" + num1 + "*" + num2 + ")^2" + "=" + answer + "<br>");
                
            }else{
                
                //  3つ目の引数がfalseの場合
                out.println(num1 + "*" + num2 + "=" + answer + "<br>");
                
            }
            
            //  デフォルトのメソッドを呼び出す
            answer = multiplication(num1);
            //  結果を表示
            out.println(num1 + "*" + 5 + "=" + answer + "<br>");
            
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
