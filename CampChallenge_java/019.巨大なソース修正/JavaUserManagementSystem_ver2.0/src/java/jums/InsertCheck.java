
package jums;

import java.sql.SQLException;
import java.util.List;

/**
 * 入力された値が適切かチェックするためのクラス。
 * @author nakaya-k
 */
public class InsertCheck {
    
    public static InsertCheck getInstance(){
        return new InsertCheck();
    }
    
    /**
     * 入力されたデータのうち名前を検索し、すでに登録されていた場合は
     * falseを返却する
     * @param udb　入力情報を持つUserDataBeans。
     * @return samename
     * @throws java.sql.SQLException
     */
    public Boolean nameCheck(UserDataBeans udb) throws SQLException{
        
        boolean samename = false;
        if(!udb.getName().equals("")){
            UserDataDTO udd = new UserDataDTO();
            udd.setName(udb.getName());
            List list = UserDataDAO.getInstance().searchByName(udd);
            if(!list.isEmpty()){
                samename = true;
            }
        }
        return samename;
    }
    
    /**
     * 入力されたデータのうち生年月日が適切であるか判定し、
     * 不正な日付であればfalseを返却する
     * @param udb　入力情報を持つUserDataBeans。
     * @return date
     */
    public Boolean dateCheck(UserDataBeans udb){
        
        boolean date = true;
        int year = udb.getYear();
        int month = udb.getMonth();
        int day = udb.getDay();
        if(year != 0 && month != 0 && day != 0){
            if(month == 2 && day > 28){
                if(!((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day < 30)) {
                    date = false;
                }                    
            }else if(month == 4 && day > 30){
                date = false;
            }else if(month == 6 && day > 30){
                date = false;
            }else if(month == 9 && day > 30){
                date = false;
            }else if(month == 11 && day > 30){
                date = false;
            }
        }
        return date;
    }
    
    /**
     * 入力されたデータのうち電話番号が適切であるか判定し、
     * 不正であればfalseを返却する
     * @param udb　入力情報を持つUserDataBeans。
     * @return tell
     */
    public Boolean tellCheck(UserDataBeans udb){
        
        boolean tell = true;
        String cppattern = "^(090|080|070)-[0-9]{4}-[0-9]{4}$";
        if(!udb.getTell().equals("")){
            tell = udb.getTell().matches(cppattern);
        }
        return tell;
    }
}
