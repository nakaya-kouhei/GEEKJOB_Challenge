/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.servlet;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author guest1Day
 */
public class BlackJack {
    
}

//  抽象クラスHumanを宣言
abstract class Human {
    
    abstract public int open();
    
    abstract public void setCard(ArrayList<Integer> cards);
    
    abstract public boolean checkSum();
    
    ArrayList<Integer> myCards = new ArrayList<Integer>();
    
}

//  Humanクラスを継承するDealerクラスを宣言　ディーラーの役割をこなす
class Dealer extends Human {
    
    //  トランプを配列で定義(1と11は区別する　12と13は1とする)
    ArrayList<Integer> cards = new ArrayList<Integer>(){{add(1); add(1); add(1); add(1);
                                                         add(2); add(2); add(2); add(2);
                                                         add(3); add(3); add(3); add(3);
                                                         add(4); add(4); add(4); add(4);
                                                         add(5); add(5); add(5); add(5);
                                                         add(6); add(6); add(6); add(6);
                                                         add(7); add(7); add(7); add(7);
                                                         add(8); add(8); add(8); add(8);
                                                         add(9); add(9); add(9); add(9);
                                                         add(10); add(10); add(10); add(10);
                                                         add(10); add(10); add(10); add(10);                                                         
                                                         add(10); add(10); add(10); add(10);
                                                         add(10); add(10); add(10); add(10);
                                                         }};
    
    
    //  ディールを実行するメソッド
    public ArrayList<Integer> deal() {        
        
        //  受け渡すカードを入れる配列
        ArrayList<Integer> cards = new ArrayList<Integer>();        
        //  乱数を取得
        Random rand = new Random();
        
        //  受け渡すカードをランダムに選出
        Integer index1 = rand.nextInt(this.cards.size());
        Integer index2 = rand.nextInt(this.cards.size());
        
        //  同じカードを渡さないための処理
        while(index1.equals(index2)) {
            
            index1 = rand.nextInt(this.cards.size());
            index2 = rand.nextInt(this.cards.size());
            
        }        
        
        //  選出したカードを配列に格納
        cards.add(this.cards.get(index1));
        cards.add(this.cards.get(index2));            
        
        return cards;
        
    }
    
    
    //  ヒットを実行するメソッド
    public ArrayList<Integer> hit() {        
        
        //  受け渡すカードを入れる配列
        ArrayList<Integer> cards = new ArrayList<Integer>();        
        //  乱数を取得
        Random rand = new Random();
        
        //  受け渡すカードをランダムに選出
        Integer index = rand.nextInt(this.cards.size());
        
        //  Aの区別を行う処理
        if(0 <= index && index <= 3 && 10 < this.open()) {
            
            //  Aを1として配列に格納
            cards.add(1);
            
        } else {
            
            //  選出したカードを配列に格納
            cards.add(this.cards.get(index));

        }
             
        return cards;
        
    }
    
    
    //  カードの合計を計算して返すメソッド    
    public int open(){
        
        int blackjack = 0;
        
        //  カードの合計値を計算
        for(int card : this.myCards) {
            
            blackjack += card;
            
        }
        
        return blackjack;
        
    }
    
    
    //  ディーラーの持ち札を設定するメソッド
    public void setCard(ArrayList<Integer> myCards){
       
       for(int card : myCards) {
           
           this.myCards.add(card);
           
       }
    }
    
    
    //  カードを追加するか判定するメソッド
    public boolean checkSum(){
        
        int blackjack = 0;
        
        //  カードの合計値を計算
        blackjack = this.open();
        
        //  カードの追加判定(ディーラーは17未満ならカードを追加する)
        if(blackjack < 17) {
            
            return true;
            
        } else {
            
            return false;
            
        }
    }
    
    //  後処理
    @Override
    protected void finalize() throws Throwable {
        
        this.cards = null;
        this.myCards = null;
        
    }
}

//  Humanクラスを継承するUserクラスを宣言　プレイヤーの役割をこなす
class User extends Human {
    
    //  カードの合計を計算して返すメソッド
    public int open(){
        
        int blackjack = 0;
        
        //  カードの合計値を計算
        for(int card : this.myCards) {
            
            blackjack += card;
            
        }
        
        return blackjack;
        
    }
    
    
    //  プレイヤーの持ち札を設定するメソッド
    public void setCard(ArrayList<Integer> myCards){
        
       this.myCards = myCards;
        
    }
    
    
    //  カードを追加するか判定するメソッド  
    public boolean checkSum(){
        
        int blackjack = 0;
        
        //  カードの合計値を計算
        blackjack = this.open();
        
        //  カードの追加判定(プレイヤーは21未満ならカードを追加する)
        if(blackjack < 21) {
            
            return true;
            
        } else {
            
            return false;
            
        }
    }
}
