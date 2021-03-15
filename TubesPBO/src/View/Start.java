/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Board;
import Model.Dice;
import Model.Player;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Asus
 */
public class Start extends JFrame {
    ArrayList<Player> players = new ArrayList<Player>();
    Controller c = new Controller();    
    public void newGame() {
        c.playOpening();
        JPanel start = new JPanel();
        JLabel bg = new JLabel();
        start.setLayout(null);
        start.setBounds(0, 0, 1080, 660);
        //Button new game & image bg 
        
        Icon btnIcon = new ImageIcon("src\\img\\startBtn.jpg");
        Icon btnIcon2 = new ImageIcon("src\\img\\pushBtn.jpg");
        JButton gameNew = new JButton();
        gameNew.setIcon(btnIcon);
        gameNew.setBounds(1080/2-100,720/2+25, 200, 50);
        bg.setIcon(new ImageIcon(c.resizeImage("src\\img\\bgTubes.png",1080,720)));
        bg.setBounds(0, 0, 1080, 720);
        start.add(bg,0);
        start.add(gameNew,0);
        gameNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);   
                c.stopSong();
                JFrame mulai = new InsertName();
                mulai.setVisible(true);
                mulai.setLocationRelativeTo(null);
                mulai.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;             
            }

        });
        gameNew.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                gameNew.setIcon(btnIcon2);
            }
            public void mouseExited(MouseEvent e){
                gameNew.setIcon(btnIcon);
            }
        });
        //Icon decor = new ImageIcon("src\\img\\p1-01.png");
        decorStart ds = new decorStart(true);
        ds.setBounds(0,550, 50, 50);        
        Thread t = new Thread(ds);
        t.start();
//        JLabel bgDecor = new JLabel();
//        bgDecor.setBounds(0, 100, 1080, 100);
//        bgDecor.setBackground(Color.WHITE);
//        bgDecor.setVisible(true);
//        start.add(bgDecor,1);
        start.add(ds,1);
        add(start);
    
    }

    Start() {
        newGame();
        setTitle("Monopoli");
        setSize(1080, 660);
        
        setLayout(null);
    }
}
