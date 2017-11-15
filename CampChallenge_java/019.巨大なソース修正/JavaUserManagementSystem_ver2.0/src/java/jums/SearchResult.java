package jums;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class SearchResult extends HttpServlet {

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

        try{
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk == null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }

            //詳細・更新確認画面からの遷移判定
            String mode = request.getParameter("mode");
            String re = (String)session.getAttribute("re");
            boolean flag = false;
            UserDataBeans udb = new UserDataBeans();
            if(mode == null || mode.isEmpty()){
                //フォームからの入力を取得して、JavaBeansに格納
                udb.setName(request.getParameter("name"));
                udb.setYear(request.getParameter("year"));
                udb.setType(request.getParameter("type"));
                session.setAttribute("udb", udb);
                //フォームの入力情報を保持
                session.setAttribute("searchdata", udb);
                flag = true;
            }else if(re != null && re.equals("yes")){
                //保持されたフォームの入力情報を取得して、JavaBeansに格納
                udb = (UserDataBeans)session.getAttribute("searchdata");
                session.setAttribute(re, "no");
                flag = true;
            }

            if(flag){
                //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                UserDataDTO searchData = new UserDataDTO();
                udb.UD2DTOMapping(searchData);
                //検索結果を取得
                List<UserDataDTO> resultList = UserDataDAO.getInstance().search(searchData);
                if(resultList.isEmpty()){
                    session.setAttribute("data", false);
                }else{
                    session.setAttribute("data", true);
                    session.setAttribute("resultList", resultList);
                }
            }
            System.out.println("Session updated!!");

            request.getRequestDispatcher("/searchresult.jsp").forward(request, response);
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
