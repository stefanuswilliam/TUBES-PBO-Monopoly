/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import javax.accessibility.AccessibleContext;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author Asus
 */
public class Petak extends JPanel {

    private int id;
    private boolean sell;
    private JLabel idLabel;
    private static int totalPetak = 0;
    private Property property;
    private String nama;
    private JLabel houseIcon = new JLabel();

    public JLabel getHouseIcon() {
        return houseIcon;
    }

    public void setHouseIcon(JLabel houseIcon) {
        this.houseIcon = houseIcon;
    }

    public String getNama() {
        return nama;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
    
    
    
    public boolean isSell() {
        return sell;
    }

    public void setSell(boolean sell) {
        this.sell = sell;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }

    public static int getTotalPetak() {
        return totalPetak;
    }

    public static void setTotalPetak(int totalPetak) {
        Petak.totalPetak = totalPetak;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public Petak() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public Petak (int id, Property prop,String nama){
        add(this.houseIcon);
        this.id = id;
        this.property = prop;
        this.nama = nama;
    }
    
    public Petak setPetak(Petak p,int x, int y, int h, int w, int rotasi){
        totalPetak++;
        p.setBorder(new LineBorder(new Color(0, 0, 0)));
        p.setBounds(x, y, h, w);
        p.setLayout(null);
        p.id = id;

        if (rotasi == 0) {
            if(p.getProperty() instanceof  Housing){
                idLabel = new JLabel(p.getNama());    
            }else{
                idLabel = new JLabel("");
            }
            idLabel.setHorizontalAlignment(SwingConstants.CENTER);
            idLabel.setBounds(0, 20, this.getWidth(), 20);
            p.add(idLabel);
        } else {
            String n  = "";
            if(p.getProperty() instanceof  Housing){
                n = p.getNama();
            }else{
                n = "";
            }
            idLabel = new JLabel(n) {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    AffineTransform aT = g2.getTransform();
                    Shape oldshape = g2.getClip();
                    double x = getWidth() / 2.0;
                    double y = getHeight() / 2.0;
                    aT.rotate(Math.toRadians(rotasi), x, y);
                    g2.setTransform(aT);
                    super.paintComponent(g);
                }
            };
            if (rotasi == 90) {
                idLabel.setBounds(20, 0, this.getWidth(), this.getHeight());
            }
            if (rotasi == -90) {
                idLabel.setBounds(-10, 0, this.getWidth(), this.getHeight());
            }
            if (rotasi == 180) {
                idLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
            }
            if (rotasi == 135 || rotasi == -135 || rotasi == -45 || rotasi == 45) {
                idLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
            }
            idLabel.setHorizontalAlignment(SwingConstants.CENTER);

            p.add(idLabel);
        }
        return p;
    }
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.id == 2 || this.id == 4 || this.id == 5) {
            g.drawRect(0, this.getHeight() - 20, this.getWidth(), 20);
            g.setColor(Color.BLUE);
            g.fillRect(0, this.getHeight() - 20, this.getWidth(), 20);
        }
        if (this.id == 7 || this.id == 9 || this.id == 10) {
            g.drawRect(0, 0, 20, this.getHeight());
            g.setColor(Color.PINK);
            g.fillRect(0, 0, 20, this.getHeight());
        }
        if (this.id == 12 || this.id == 14) {
            g.drawRect(0, 0, this.getWidth(), 20);
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, this.getWidth(), 20);
        }
        if (this.id == 17 || this.id == 18 || this.id == 20) {
            g.drawRect(this.getWidth() - 20, 0, 20, this.getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(this.getWidth() - 20, 0, 20, this.getHeight());
        }

    }
}
