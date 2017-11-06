package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import jums.UserDataBeans;

/**
 * insertconfirm.jspと対応するサーブレット
 * フォーム入力された情報はここでセッションに格納し、以降持ちまわることになる
 * 直接アクセスした場合はerror.jspに振り分け
 * @author hayashi-s
 */
public class InsertConfirm extends HttpServlet {

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
        try{
            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
            
            //  直リンク防止措置
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            // 入力フォームからの情報を取得
            String name    = request.getParameter("name");
            String year    = request.getParameter("year");
            String month   = request.getParameter("month");
            String day     = request.getParameter("day");
            String type    = request.getParameter("type");
            String tell    = request.getParameter("tell");
            String comment = request.getParameter("comment");
            // 空欄があるか判定する変数
            boolean blank = false;
            // 入力項目ごとの状態を記録
            HashMap<String, Boolean> form = new HashMap<String, Boolean>();
            
            // 入力項目に空欄があるか判定
            if(name.equals("")){
                blank = true;
                form.put("name", false);
            }else{
                form.put("name", true);
            }
            
            if(year.equals("")){
                blank = true;
                form.put("year", false);
            }else{
                form.put("year", true);
            }
            
            if(month.equals("")){
                blank = true;
                form.put("month", false);
            }else{
                form.put("month", true);
            }
            
            if(day.equals("")){
                blank = true;
                form.put("day", false);
            }else{
                form.put("day", true);
            }
            
            //仕様上未入力はあり得ないが、念のため
            if(type==null){
                blank = true;
                form.put("type", false);
            }else{
                form.put("type", true);
            }
            
            if(tell.equals("")){
                blank = true;
                form.put("tell", false);
            }else{
                form.put("tell", true);
            }
            
            if(comment.equals("")){
                blank = true;
                form.put("comment", false);
            }else{
                form.put("comment", true);
            }
            
            //入力フォームの空欄情報をsessionへ
            session.setAttribute("blank", blank);
            session.setAttribute("form", form);
            
            //フォーム情報をUserDataBeansに格納
            UserDataBeans udb = new UserDataBeans();
            udb.setName(name);
            udb.setYear(year);
            udb.setMonth(month);
            udb.setDay(day);
            udb.setType(type);
            udb.setTell(tell);
            udb.setComment(comment);
            session.setAttribute("UDB", udb);
            
            // 直リンク防止用のランダムな数値
            session.setAttribute("ac", (int) (Math.random() * 1000));
            
            request.getRequestDispatcher("/insertconfirm.jsp").forward(request, response);            
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
