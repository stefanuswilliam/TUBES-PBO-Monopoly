/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Asus
 */
public class Dice{
    
    
    private int value;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public void rollDice() {
        Random rand = new Random(); 
        int roll = rand.nextInt(6);
        setValue(roll + 1);
    }

}
    