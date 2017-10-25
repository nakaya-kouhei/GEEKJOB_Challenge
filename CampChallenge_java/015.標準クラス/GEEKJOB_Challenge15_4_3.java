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
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author guest1Day
 */
public class GEEKJOB_Challenge15_4_3 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //  ログファイルに現在日時と処理の状況を書き込むメソッド
    void LogPrint(PrintWriter pw, String log, boolean check) {
        
        //  現在の日時を取得する
        Date                 now = new Date();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        //  処理の「開始」と「終了」を判定
        if(check) {
            
            //  ログファイルに書き込み（処理の開始）
            pw.println(dFormat.format(now) + "　" + log + "　開始");
            
        } else {
            
            //  ログファイルに書き込み（処理の終了）
            pw.println(dFormat.format(now) + "　" + log + "　終了");
            
        }
    }
    
    //  BigDecimal型とDuble型を比較する
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //  処理を記録するログファイル
            File file = new File("LogFile.txt");
            
            //  ログファイルへの書き込みを行うインスタンスを生成
            try(PrintWriter pw = new PrintWriter(file)) {

                //  doubleとBigDecimalを比較するための連想配列
                HashMap<String, Double>     mathD = new HashMap<String, Double>();
                HashMap<String, BigDecimal> mathB = new HashMap<String, BigDecimal>();

                //  BigDecimal型の変数　計算に使うための初期値を設定
                //  LogPrint()はファイル書き込みを行うメソッド
                LogPrint(pw, "BigDecimal型の変数を宣言、初期値を入力", true);
                BigDecimal numB1 = new BigDecimal("1.00");
                BigDecimal numB2 = new BigDecimal("0.10");
                BigDecimal numB3 = new BigDecimal("0.90");
                LogPrint(pw, "BigDecimal型の変数を宣言、初期値を入力", false);

                //  数式の左辺をキー、右辺を要素としてMapに格納（double用）
                LogPrint(pw, "HashMap mathDにキーとdouble値を入力", true);
                mathD.put("1.0", 1.0);
                mathD.put("0.1", 0.1);
                mathD.put("1.0 - 0.1", 1.0 - 0.1);
                mathD.put("1.0 - 0.9", 1.0 - 0.9);
                LogPrint(pw, "HashMap mathDにキーとdouble値を入力", false);
                
                //  数式の左辺をキー、右辺を要素としてMapに格納（BigDecimal用）
                LogPrint(pw, "HashMap mathBにキーとBigDecimal値を入力", true);
                mathB.put("1.0", new BigDecimal("1.00"));
                mathB.put("0.1", new BigDecimal("0.10"));
                mathB.put("1.0 - 0.1", numB1.subtract(numB2));
                mathB.put("1.0 - 0.9", numB1.subtract(numB3));
                LogPrint(pw, "HashMap mathBにキーとBigDecimal値を入力", false);
                
                LogPrint(pw, "”Double型とBigDecimal型を比較してみます。”を表示", true);
                out.println("Double型とBigDecimal型を比較してみます。<br><br>");
                LogPrint(pw, "”Double型とBigDecimal型を比較してみます。”を表示", false);
                
                //  Mapのキーを左辺、要素を右辺とする数式を表示
                LogPrint(pw, "HashMapの中身を表示", true);
                for(String math : mathD.keySet()) {

                    //  doubleの計算結果を表示
                    LogPrint(pw, "HashMap mathDのキーと値を出力", true);
                    out.println(math + "　=　" + mathD.get(math) +"　double<br>");
                    LogPrint(pw, "HashMap mathDのキーと値を出力", false);
                    
                    //  BigDecimal型の計算結果を表示
                    LogPrint(pw, "HashMap mathBのキーと値を出力", true);
                    out.println(math + "　=　" + mathB.get(math) +"　BigDecimal<br><br>");
                    LogPrint(pw, "HashMap mathBのキーと値を出力", false);
                    
                }
                LogPrint(pw, "HashMapの中身を表示", false);
                
                LogPrint(pw, "”厳密な計算には、Double型よりもBigDecimal型を使用した方が良いようです。”を表示", true);
                out.println("厳密な計算には、Double型よりもBigDecimal型を使用した方が良いようです。<br>");
                LogPrint(pw, "”厳密な計算には、Double型よりもBigDecimal型を使用した方が良いようです。”を表示", false);
                
                //  書き込みファイル（pw）を閉じる
                pw.close();
                
                //  ログファイルからの読み込みを行うインスタンスを生成
                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                    
                    String log;
                    
                    out.println("<br>ログファイルを表示します。<br><br>");
                    
                    //  ログファイルの内容を読み込んで表示
                    while((log = br.readLine()) != null) {
                        
                        out.println(log + "<br>");
                        
                    }
                    
                    //  読み込みファイル（br）を閉じる
                    br.close();
                    
                //  ファイル読み込みが失敗した場合の例外処理    
                } catch(IOException e) {
                
                    out.println(e);

                }
                
            //  ファイル書き込みが失敗した場合の例外処理
            } catch(IOException e) {
                
                out.println(e);

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
