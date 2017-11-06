/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.Serializable;

/**
 * フォーム情報を持ちまわるJavaBeans
 * insert.jspの入力項目に対応している
 * @version 1.00
 * @author guest1Day
 */
public class UserDataBeans implements Serializable {
    
    //  フォームから受け取る値を格納
    private String name = "";
    private String year = "";
    private String month = "";
    private String day = "";
    private String type = "";
    private String tell = "";
    private String comment = "";
    
    public UserDataBeans() {        
    }
    
    //  各値のgetterとsetter
    public String getName() {
        return this.name;
    }
    
    public void setName(String n) {
        this.name = n;
    }
    
    public String getYear() {
        return this.year;
    }
    
    public void setYear(String y) {
        this.year = y;
    }
    
    public String getMonth() {
        return this.month;
    }
    
    public void setMonth(String m) {
        this.month = m;
    }
    
    public String getDay() {
        return this.day;
    }
    
    public void setDay(String d) {
        this.day = d;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String t) {
        this.type = t;
    }
    
    public String getTell() {
        return this.tell;
    }
    
    public void setTell(String t) {
        this.tell = t;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String c) {
        this.comment = c;
    }
}
