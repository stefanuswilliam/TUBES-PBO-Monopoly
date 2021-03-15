/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Controller.*;
import Model.*;
import java.util.ArrayList;
import javax.swing.border.Border;

/**
 *
 * @author Asus
 */
public class InsertName extends JFrame {
    Controller c = new Controller();
    ArrayList<JLabel> list_pion = new ArrayList<>();
    ArrayList<String> imgLocation = new ArrayList<>();
    boolean isPlayer1Picked = false;
    String imgPilihan;
    Player p1;
    Player p2;
    Border border1;
    Border border2;

    public void insert() {
        JPanel main = new JPanel();
        main.setBounds(0, 0, 1080, 660);
        main.setLayout(null);
        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(c.resizeImage("src\\img\\bgInsert.jpg",1080,720)));
        bg.setBounds(0, 0, 1080, 720);

        JLabel pion1 = new JLabel(new ImageIcon(c.resizeImage("src\\img\\pawn\\black.png", 100, 100)));
        pion1.setBounds(170, 200, 100, 100);
        JLabel pion2 = new JLabel(new ImageIcon(c.resizeImage("src\\img\\pawn\\blue.png", 100, 100)));
        pion2.setBounds(300, 200, 100, 100);
        JLabel pion3 = new JLabel(new ImageIcon(c.resizeImage("src\\img\\pawn\\green.png", 100, 100)));
        pion3.setBounds(430, 200, 100, 100);
        JLabel pion4 = new JLabel(new ImageIcon(c.resizeImage("src\\img\\pawn\\red.png", 100, 100)));
        pion4.setBounds(560, 200, 100, 100);
        JLabel pion5 = new JLabel(new ImageIcon(c.resizeImage("src\\img\\pawn\\white.png", 100, 100)));
        pion5.setBounds(690, 200, 100, 100);
        JLabel pion6 = new JLabel(new ImageIcon(c.resizeImage("src\\img\\pawn\\yellow.png", 100, 100)));
        pion6.setBounds(830, 200, 100, 100);

        
        imgLocation.add("src\\img\\pawn\\black.png");
        imgLocation.add("src\\img\\pawn\\blue.png");
        imgLocation.add("src\\img\\pawn\\green.png");
        imgLocation.add("src\\img\\pawn\\red.png");
        imgLocation.add("src\\img\\pawn\\white.png");
        imgLocation.add("src\\img\\pawn\\yellow.png");
        
        
        list_pion.add(pion1);
        list_pion.add(pion2);
        list_pion.add(pion3);
        list_pion.add(pion4);
        list_pion.add(pion5);
        list_pion.add(pion6);

        border1 = BorderFactory.createLineBorder(Color.black, 3);
        border2 = BorderFactory.createLineBorder(Color.red, 3);
        imgPilihan = "";
        pion1.setBorder(border1);
        
        
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Border currentBorder = border1;
                if (isPlayer1Picked){
                    currentBorder = border2;
                } 
                
                JLabel pion = (JLabel) e.getSource();
                for (int i = 0; i < list_pion.size(); i++) {
                    if (list_pion.get(i).getBorder() != null && list_pion.get(i).getBorder().equals(currentBorder)){
                        list_pion.get(i).setBorder(null);
                    }
                    pion.setBorder(currentBorder);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        //PICTURE
        pion1.addMouseListener(listener);
        pion2.addMouseListener(listener);
        pion3.addMouseListener(listener);
        pion4.addMouseListener(listener);
        pion5.addMouseListener(listener);
        pion6.addMouseListener(listener);

        //PLAYER 1
        JLabel labelNameP1 = new JLabel("PLAYER 1");
        labelNameP1.setBounds(355, 350, 150, 40);
        labelNameP1.setFont(new Font("Serif", Font.PLAIN, 20));
        JTextField nameP1 = new JTextField(20);
        nameP1.setBounds(320, 400, 150, 40);

        //PLAYER 2
        JLabel labelNameP2 = new JLabel("PLAYER 2");
        labelNameP2.setFont(new Font("Serif", Font.PLAIN, 20));
        labelNameP2.setBounds(650, 350, 150, 40);
        labelNameP2.setVisible(false);

        JTextField nameP2 = new JTextField(20);
        nameP2.setBounds(620, 400, 150, 40);
        nameP2.setVisible(false);

        //OTHER LABEL
        JLabel names = new JLabel("INSERT PLAYERS NAME");
        names.setFont(new Font("Serif", Font.PLAIN, 17));
        names.setBounds(80, 400, 200, 40);

        //BUTTON
        JButton ok1 = new JButton("OK");
        ok1.setBounds(320, 460, 150, 50);

        JButton ok2 = new JButton("OK");
        ok2.setBounds(620, 460, 150, 50);
        ok2.setVisible(false);

        ok1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name1 = nameP1.getText();

                
                for (int i = 0; i < list_pion.size(); i++) {
                    if (list_pion.get(i).getBorder()!=null && list_pion.get(i).getBorder().equals(border1)) {
                        list_pion.get(i).setEnabled(false);
                        imgPilihan = imgLocation.get(i);
                        p1 = new Player(name1, imgPilihan);
                    }
                }
                labelNameP1.setVisible(false);
                nameP1.setVisible(false);
                ok1.setVisible(false);

                labelNameP2.setVisible(true);
                nameP2.setVisible(true);
                ok2.setVisible(true);
                isPlayer1Picked = true;
            }
        });

        ok2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name2 = nameP2.getText();

                for (int i = 0; i < list_pion.size(); i++) {
                    if (list_pion.get(i).getBorder()!=null && list_pion.get(i).getBorder().equals(border2)) {
                        imgPilihan = imgLocation.get(i);
                        p2 = new Player(name2, imgPilihan);
                    }
                }
                c.stopSong();
                Gameplay gp = new Gameplay(p1, p2);
                setVisible(false); 
                dispose(); 
            }
        });

        //Add to Panel
        main.add(ok1);
        main.add(ok2);
        main.add(pion1);
        main.add(pion2);
        main.add(pion3);
        main.add(pion4);
        main.add(pion5);
        main.add(pion6);
        main.add(names);
        main.add(labelNameP1);
        main.add(nameP1);
        main.add(labelNameP2);
        main.add(nameP2);
        main.add(bg);
        add(main);
    }

    InsertName() {
        c.playOpening();
        insert();
        setTitle("Monopoli");
        setSize(1080, 720);

        setLayout(null);
    }
}
