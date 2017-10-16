/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.servlet;

//  必要なインポートを設定
import java.io.Serializable;

/**
 *
 * @author guest1Day
 */
public class ResultData implements Serializable {
    
    private String d;
    private String luck;
    
    //  publicかつ引数の無いコンストラクタを宣言
    public ResultData() {}
    
    //  日付のgetterメソッド
    public String getD() {
        
        return d;
        
    }
    
    //  日付のsetterメソッド
    public void setD(String d) {
        
        this.d = d;
        
    }
    
    //  運勢のgetterメソッド
    public String getLuck() {
        
        return luck;
        
    }
    
    //  運勢のsetterメソッド    
    public void setLuck(String luck) {
        
        this.luck = luck;
    
    }
    
}
