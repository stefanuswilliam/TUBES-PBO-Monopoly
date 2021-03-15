/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author prata
 */
public class decorStart extends JLabel implements Runnable{
    boolean openStart;
    Controller c = new Controller();
            int i = 1;
    public decorStart(boolean open){
        this.openStart = open;
    }
    @Override
    public void run() {
        int x = this.getX();
        int y = this.getY();
        this.setVisible(true);
        
        while(openStart == true){
            x+=10;
            this.setLocation(x,y);
            Icon decor = new ImageIcon("src\\img\\p1-01.png");
            Icon decor1 = new ImageIcon("src\\img\\p1-02.png");
            Icon decor2 = new ImageIcon("src\\img\\p1-03.png");
            Icon decor3 = new ImageIcon("src\\img\\p1-04.png");
            Icon decor4 = new ImageIcon("src\\img\\p1-05.png");
            Icon decor5 = new ImageIcon("src\\img\\p1-06.png");
            if(i==1){
                this.setIcon(decor);
            }else if(i==2){
                this.setIcon(decor1);
            }else if(i==3){
                this.setIcon(decor2);
            }else if(i==4){
                this.setIcon(decor3);
            }else if(i==5){
                this.setIcon(decor4);
            }else if(i==6){
                this.setIcon(decor5);
            }
            repaint();
            try{
                i+=1;
                if(i>6){
                    i=1;
                }
                Thread.sleep(250);
            }catch(InterruptedException ex){
                System.out.println(System.err);
            }
        }
    }
    
}
