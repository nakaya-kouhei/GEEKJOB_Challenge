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
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.Cookie;
import java.util.ArrayList;

/**
 *
 * @author guest1Day
 */
public class GEEKJOB_Challenge16_3 extends HttpServlet {

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
            
            //  現在の日時を取得
            Date                 now = new Date();
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");            
            //  Cookieインスタンスに日時を入力
            Cookie                cd = new Cookie("time", dFormat.format(now));
            
            //  Cookieの有効期限を30秒に設定
            cd.setMaxAge(30);
            //  Cookieに情報を渡す
            response.addCookie(cd);
            
            //  Cookieから情報を取得　上で渡した情報は含まれていない
            Cookie[] cList = request.getCookies();
            
            
            out.println("現在の日時　：　" +  dFormat.format(now) + "<br>");            
            
            //  Cookieが空ではないか判定
            if(cList != null) {
                
                //  Cookieから取得した情報を表示　前回アクセスの日時が表示される
                for(Cookie ca : cList) {
                    
                    out.println("前回の日時　：　" + ca.getValue() + "<br>");
                    
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
