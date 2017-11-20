package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * updateresult.jspと対応するサーブレット
 * フォームから入力された値を受け取り、データベースをupdateする
 * 直接アクセスした場合はerror.jspに振り分け
 * @author nakaya-k
 */
public class UpdateResult extends HttpServlet {

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

        //セッションスタート
        HttpSession session = request.getSession();

        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk == null || (Integer)session.getAttribute("ac") != Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }

            //フォームからの入力を取得して、JavaBeansに格納
            UserDataBeans udb = new UserDataBeans();
            udb.setName(request.getParameter("name"));
            udb.setYear(request.getParameter("year"));
            udb.setMonth(request.getParameter("month"));
            udb.setDay(request.getParameter("day"));
            udb.setType(request.getParameter("type"));
            udb.setTell(request.getParameter("tell"));
            udb.setComment(request.getParameter("comment"));
            
            //入力フォームに不足や問題があれば再入力を促す
            boolean datecheck = InsertCheck.getInstance().dateCheck(udb);
            boolean tellcheck = InsertCheck.getInstance().tellCheck(udb);
            if(udb.chkproperties().isEmpty() && datecheck && tellcheck){
                //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                UserDataDTO resultData = (UserDataDTO)session.getAttribute("resultData");
                udb.UD2DTOMapping(resultData);
                //DBをアップデート
                UserDataDAO.getInstance().update(resultData);
                //詳細情報画面へ渡す情報
                request.setAttribute("id", resultData.getUserID());
                session.setAttribute("re", "resultdetail");
            }else{
                //更新入力画面へ
                session.setAttribute("re", "update");
            }

            //セッション上のUserDataBeans値を更新
            session.setAttribute("udb", udb);
            System.out.println("Session updated!!");
            
            request.getRequestDispatcher("/updateresult.jsp").forward(request, response);
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
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
