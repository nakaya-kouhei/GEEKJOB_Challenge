<%-- 
    Document   : GEEKJOB_Challenge16_2_1
    Created on : 2017/10/25, 16:58:00
    Author     : guest1Day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int    total;
            int    count;
            double point;
        
            //  HTMLからの入力情報の文字コードを設定（UTF-8）
            request.setCharacterEncoding("UTF-8");
            
            //  クエリストリングから商品の種別を取得
            //  スイッチで場合分け
            switch(Integer.valueOf(request.getParameter("type"))) {
                
                //  種別が1ならば雑貨
                case 1:
                   
                   out.println("商品の種別は雑貨です。<br>");
                   break;
                   
                //  種別が2ならば生鮮食品
                case 2:
                   
                   out.println("商品の種別は生鮮食品です。<br>");
                   break;
                   
                //  種別が3ならばその他
                case 3:
                   
                   out.println("商品の種別はその他です。<br>");
                   break;
               
            }

            //  クエリストリングから総額を取得
            total=Integer.valueOf(request.getParameter("total"));
            
            //  クエリストリングから個数を取得
            count=Integer.valueOf(request.getParameter("count"));
            
            out.println("総額" + total + "円の商品を" + count + "個買いました。<br>");
            
            //  商品の単価を計算して表示
            count=total/count;
            out.println("商品の単価は" + count + "円です。<br>");
            
            //  ポイントをつける　3000円以上なら4%　5000円以上なら5%
            if(total >= 5000) {
                
                //  ポイントを計算して表示
                point = total * 0.05;
                out.println((int)point + "ポイントつきました！<br>");
                
            } else if(total >= 3000) {
                
                //  ポイントを計算して表示
                point = total * 0.04;
                out.println((int)point + "ポイントつきました！<br>");
            
            } else {
                
                //  ポイントがつかない場合
                out.println("ポイントはつきません。<br>");
                
            }
        %>
    </body>
</html>
