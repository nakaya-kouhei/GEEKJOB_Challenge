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
import org.camp.servlet.BlackJack;
import java.util.ArrayList;

/**
 *
 * @author guest1Day
 */
public class GameStage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //  両者のカードを表示するメソッド
    void cardOpen(Dealer dealer, User user, PrintWriter pw) {
        
        pw.println("<br>まずはディーラーから。<br>");
            
        //  ディーラーのカードを表示
        for(int card : dealer.myCards) {
                
            pw.println(" " + card + " ");
                
        }
        
        //  ディーラーのカードの合計値を表示
        pw.println(" 合計は" + dealer.open() + "です<br>");            
        pw.println("<br>続いてプレイヤーです。<br>");
            
        //  プレイヤーのカードを表示
        for(int card : user.myCards) {
                
            pw.println(" " + card + " ");
                
        }
        
        //  プレイヤーのカードの合計値を表示
        pw.println(" 　合計は" + user.open() + "です<br>");
        
    }
    
    //  勝敗を判定し結果を表示するメソッド
    void juge(Dealer dealer, User user, PrintWriter pw) {
        
        Integer dealerPoint = dealer.open();
        Integer userPoint   = user.open();
        
        pw.println("<br>結果は・・・<br>");
        
        if(dealerPoint.equals(userPoint)) {
            
            pw.println("<br>引き分けです！<br>");
            
        } else if(dealerPoint > 21) {
            
            pw.println("<br>ディーラーがバストしました。<br>プレイヤーの勝利です！<br>");
            
        } else if(userPoint > 21) {
            
            pw.println("<br>プレイヤーがバストしました。<br>ディーラーの勝利です！<br>");
            
        } else if(dealerPoint.equals(21)) {
            
            pw.println("<br>ブラックジャック！<br>ディーラーの勝利です！<br>");
            
        } else if(userPoint.equals(21)) {
            
            pw.println("<br>ブラックジャック！<br>プレイヤーの勝利です！<br>");
            
        } else if(dealerPoint > userPoint) {
            
            pw.println("<br>ディーラーの勝利です！<br>");
            
        } else if(userPoint > dealerPoint) {
            
            pw.println("<br>プレイヤーの勝利です！<br>");
            
        }        
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //  DealerクラスとUserクラスのインスタンスを宣言
            Dealer dealer = new Dealer();
            User   user   = new User();
                       
            out.println("ブラックジャックを始めます！<br>");
            out.println("ディーラーはカードを配ってください！<br><br>");            
            
            //  ディーラーがカードをディール
            dealer.setCard(dealer.deal());

            //  ユーザーがカードをディール
            user.setCard(dealer.deal());
            
            out.println("カードが配られました。見てみましょう！<br>");
            
            //  ディール時点のカードを表示
            cardOpen(dealer, user, out);
            
            //  お互いヒットするか判断　バーストかブラックジャックで強制終了
            while(dealer.checkSum() || user.checkSum()) {
                
                out.println("<br>両者ヒットしますか？<br>");
                
                //  ディーラーがヒットするか判定
                if(dealer.checkSum()) {
                    
                    dealer.myCards.addAll(dealer.hit());
                    
                    out.println("ディーラーはヒットしました。<br>");
                    
                }
                
                //  プレイヤーがヒットするか判定
                if(user.checkSum()) {
                    
                    user.myCards.addAll(dealer.hit());
                    
                    out.println("プレイヤーはヒットしました。<br>");
                    
                }
                
                out.println("<br>現在のカードを見てみましょう！<br>");
                //  現在のカードを表示
                cardOpen(dealer, user, out);
                
                //  バストかブラックジャックで強制終了
                if(dealer.open() >= 21 || user.open() >= 21) {
                    
                    break;
                    
                }
            }
            
            // 結果を判定し表示するメソッドを呼び出す
            juge(dealer, user, out);
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
