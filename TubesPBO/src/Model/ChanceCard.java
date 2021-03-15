/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Asus
 */
public class ChanceCard{
    
    String nama;
    int money;
    String target;
    String isGet;

    public String getNama() {
        return nama;
    }

    public int getMoney() {
        return money;
    }

    public String getTarget() {
        return target;
    }

    public String getIsGet() {
        return isGet;
    }

    public ChanceCard(String nama, int money, String target, String isGet) {
        this.nama = nama;
        this.money = money;
        this.target = target;
        this.isGet = isGet;
    }
    
    
    
    
}
