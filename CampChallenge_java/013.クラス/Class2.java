/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.servlet;

/**
 *
 * @author guest1Day
 */

//  Humanクラスを宣言
class Human {
    
    //  nameフィールドとcountryフィールドを宣言
    public String name = "";
    public String country = "";

    
    //  引数の値をフィールドに設定するメソッド
    public void setHuman(String n, String c) {

        this.name = n;
        this.country = c;

    }
    
    //  二つの変数を表示するメソッド
    public void printHuman() {
        
        System.out.println(this.name);
        System.out.println(this.country);
        
    }
    
}

//  Humanクラスを継承するPlayerクラスを宣言
class Player extends Human {

    //  Humanクラスから引き継いだ情報をクリアするメソッド
    public void clearHuman() {
        
        this.name = "";
        this.country = "";
        
    }
    
}