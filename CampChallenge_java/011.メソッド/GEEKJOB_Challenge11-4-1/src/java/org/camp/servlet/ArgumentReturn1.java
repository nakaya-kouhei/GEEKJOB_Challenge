/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guest1Day
 */
public class ArgumentReturn1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //  人物の情報を配列に格納して返すメソッド
    ArrayList<String> profile(String id){
        
        HashMap<String, ArrayList> profile = new HashMap<String, ArrayList>();
        ArrayList<String> user1 = new ArrayList<String>();
        ArrayList<String> user2 = new ArrayList<String>();
        ArrayList<String> user3 = new ArrayList<String>();
        
        //  一人目の情報を配列に格納
        user1.add("座波");
        user1.add("1995年5月23日");
        user1.add("東京都世田谷区用賀");
        
        //  二人目の情報を配列に格納       
        user2.add("四囲");
        user2.add("1972年1月25日");
        user2.add("東京都千代田区九段北");
        
        //  三人目の情報を配列に格納
        user3.add("浪美");
        user3.add("1993年2月24日");
        user3.add("島根県松江市北堀町");
        
        //  idをキーとして三人の情報を連想配列に格納
        profile.put("01", user1);
        profile.put("02", user2);
        profile.put("03", user3);
        
        //  渡されたidから返却する情報を選択
        return profile.get(id);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            ArrayList<String> profile = new ArrayList<String>();
            String id = "03";

            //  メソッドを呼び出して配列を受け取る
            profile = profile(id);
            
            //  受け取った配列から、ID以外を表示
            for(int i=0; i<=profile.size(); i++){
                
                out.println(profile.get(i) + "<br>");
                
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
