/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Asus
 */
public class Player extends JLabel implements Runnable{
    private String name;
    private String imgFile;
    int money = 1000;
    ArrayList<Property> prop = new ArrayList<>();
    Boolean inJail = false;
    int petak;
    private int ownedStation = 0;

    public Boolean isInJail() {
        return inJail;
    }

    public void setInJail(Boolean inJail) {
        this.inJail = inJail;
    }

    public int getOwnedStation() {
        return ownedStation;
    }

    public void setOwnedStation(int ownedStation) {
        this.ownedStation = ownedStation;
    }

    public ArrayList<Property> getProp() {
        return prop;
    }

    
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addProperty(Property p) {
        this.prop.add(p);
    }

        
    public Player(String name, String imgFile) {
        this.name = name;
        this.imgFile = imgFile;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPetak() {
        return petak;
    }

    public void setPetak(int petak) {
        this.petak = petak;
    }

    
    
    
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.setVisible(true);
    }
   

}
