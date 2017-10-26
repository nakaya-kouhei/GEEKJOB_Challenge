<%-- 
    Document   : GEEKJOB_Challenge16_2_2
    Created on : 2017/10/25, 18:20:16
    Author     : guest1Day
--%>

<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Integer> list = new ArrayList<Integer>();
            int                num;
            boolean            judge;
            
            //  HTMLからの入力情報の文字コードを設定（UTF-8）
            request.setCharacterEncoding("UTF-8");
            
            //  クエリストリングから値を受け取る
            num = Integer.valueOf(request.getParameter("num"));
            
            //  素数を取得
            for(int i = 2; i < 10; i++) {
                
                //  iが素数と仮定
                judge = true;
                
                //  素数かどうか判定
                for(int j = 2; j < i; j++) {
                    
                    //  1とi以外で割り切れたら素数ではない
                    if(i % j == 0) {
                        
                        judge = false;
                        
                        break;
                        
                    }
                }
                
                //  iが素数なら配列に格納
                if(judge) {
                    
                   list.add(i);
                    
                }
            }
            
            //  元の値を表示
            out.println("元の値" + num + "　");
            
            //  素因数分解  配列内の小さい素数から
            for(int data : list) {
                
                //  余りが出る（分解できなくなる）まで繰り返す
                while(num % data == 0) {
                    
                    //  割り切れるなら表示
                    out.println(data + "　");
                    
                    //  数値を素数で割る
                    num /= data;
                    
                }                
            }
            
            //  一桁の素数で割り切れなかった場合
            if(num > 1) {
                
                //  余りを表示
                out.println("余った値" + num);
            
            }

        %>
    </body>
</html>
