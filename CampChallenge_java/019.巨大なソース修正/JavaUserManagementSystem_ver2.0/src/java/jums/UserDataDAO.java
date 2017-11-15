package jums;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author hayashi-s
 */
public class UserDataDAO {

    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }

    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,birthday,tell,type,comment,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
        }

    }

    /**
     * データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    public List<UserDataDTO> search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t";
            List<Integer> list = new ArrayList<Integer>();
            if (!ud.getName().equals("")) {
                sql += " WHERE name like ?";
                list.add(1);
            }
            if (ud.getBirthday()!=null) {
                if(list.isEmpty()){
                    sql += " WHERE birthday like ?";
                    list.add(2);
                }else{
                    sql += " AND birthday like ?";
                    list.add(3);
                }
            }
            if (ud.getType()!=0) {
                if(list.isEmpty()){
                    sql += " WHERE type like ?";
                    list.add(4);
                }else{
                    sql += " AND type like ?";
                    list.add(5);
                }
            }
            st = con.prepareStatement(sql);
            
            //入力に応じて全件・条件付き検索
            for(int i : list){
                switch(i){
                    case 1:
                        st.setString(1, "%"+ud.getName()+"%");
                        break;
                    case 2:
                        st.setString(1, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                        break;
                    case 3:
                        st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                        break;
                    case 4:
                        st.setInt(1, ud.getType());
                        break;
                    case 5:
                        if(ud.getName().equals("") || ud.getBirthday() == null){
                            st.setInt(2, ud.getType());
                        }else{
                            st.setInt(3, ud.getType());
                        }
                        break;
                    default:
                        break;                    
                }
            }

            //検索結果が複数の場合に対応
            ResultSet rs = null;
            List<UserDataDTO> resultList = new ArrayList<UserDataDTO>();
            try{
                rs = st.executeQuery();
                while(rs.next()){
                    UserDataDTO resultUd = new UserDataDTO();
                    resultUd.setUserID(rs.getInt(1));
                    resultUd.setName(rs.getString(2));
                    resultUd.setBirthday(rs.getDate(3));
                    resultUd.setTell(rs.getString(4));
                    resultUd.setType(rs.getInt(5));
                    resultUd.setComment(rs.getString(6));
                    resultUd.setNewDate(rs.getTimestamp(7));
                    resultList.add(resultUd);
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
                throw new SQLException(e);
            }finally{
                if(rs != null){
                    rs.close();
                }
            }
            System.out.println("search completed");

            return resultList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
        }

    }

    /**
     * ユーザーIDによる1件のデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t WHERE userID = ?";

            st = con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());

            ResultSet rs = null;
            UserDataDTO resultUd = new UserDataDTO();
            try{
                rs = st.executeQuery();
                rs.next();
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setBirthday(rs.getDate(3));
                resultUd.setTell(rs.getString(4));
                resultUd.setType(rs.getInt(5));
                resultUd.setComment(rs.getString(6));
                resultUd.setNewDate(rs.getTimestamp(7));
            }catch(SQLException e){
                System.out.println(e.getMessage());
                throw new SQLException(e);
            }finally{
                if(rs != null){
                    rs.close();
                }
            }
            System.out.println("searchByID completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
        }
    }

    /**
     * 名前によるデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    public List<UserDataDTO> searchByName(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t WHERE name = ?";

            st = con.prepareStatement(sql);
            st.setString(1, ud.getName());

            //検索結果をリストに格納
            ResultSet rs = null;
            List<UserDataDTO> resultList = new ArrayList<UserDataDTO>();
            try{
                rs = st.executeQuery();
                while(rs.next()){
                    UserDataDTO resultUd = new UserDataDTO();
                    resultUd.setUserID(rs.getInt(1));
                    resultUd.setName(rs.getString(2));
                    resultUd.setBirthday(rs.getDate(3));
                    resultUd.setTell(rs.getString(4));
                    resultUd.setType(rs.getInt(5));
                    resultUd.setComment(rs.getString(6));
                    resultUd.setNewDate(rs.getTimestamp(7));
                    resultList.add(resultUd);
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
                throw new SQLException(e);
            }finally{
                if(rs != null){
                    rs.close();
                }
            }
            System.out.println("searchByName completed");

            return resultList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
        }
    }

    /**
     * データの更新処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void update(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET name=?,birthday=?,tell=?,type=?,comment=?,newDate=? WHERE userID=?");
            st.setString(1, ud.getName());
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.setInt(7, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
        }
    }

    /**
     * データの削除処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void delete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("DELETE FROM user_t WHERE userID=?");
            st.setInt(1, ud.getUserID());
            st.executeUpdate();
            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
            if(st != null){
                st.close();
            }
        }
    }

}
