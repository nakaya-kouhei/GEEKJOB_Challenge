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
//  必要なインポートを設定
import java.util.Random;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import org.camp.servlet.ResultData;
import java.text.DateFormat;
import java.text.Format;

/**
 *
 * @author guest1Day
 */
public class FortuneTelling extends HttpServlet {

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
           
            //  運勢を定義
            String luckList[] = {"大吉", "中吉", "吉", "半吉", "末小吉", "小凶", "半凶", "末凶", "凶", "大凶"};
            
            Random rand = new Random();
            //  乱数を取得
            Integer index = rand.nextInt(luckList.length);            
            
            ResultData data = new ResultData();
            
            //  日時表記の書式を変更
            Format now_date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
            data.setD(now_date.format(new Date()));
            data.setLuck(luckList[index]);
            request.setAttribute("DATA", data);
            
            //　jspへのパスを設定
            String result = "/WEB-INF/jsp/FortuneTellingResult.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(result);
            //　jspにデータを渡す
            rd.forward(request, response);

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
