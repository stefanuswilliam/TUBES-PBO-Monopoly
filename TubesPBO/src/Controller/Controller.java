/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board;
import Model.ChanceCard;
import Model.Housing;
import Model.Petak;
import Model.Player;
import Model.Plumbing;
import Model.Property;
import Model.Stasiun;
import MySQL.MySQL;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Asus
 */
public class Controller {
    
    
    public String listProperty(Player p) {

        String property = "";
        String kelas;
        
        for (int i = 0; i < p.getProp().size(); i++) {
            kelas = p.getProp().get(i).getClass().getSimpleName();
            if ("Housing".equals(kelas)) {
                Housing pr = (Housing) p.getProp().get(i);

                property += (i + 1) + ". " + pr.getNama() + "   (Tempat) \r\n"
                        + "Jumlah Rumah = " + pr.getJumlahRumah() + "\r\n"
                        + "Harga Sewa = " + pr.getHargaSewa() + "\r\n\r\n";
            } else if ("Plumbing".equals(kelas)) {
                Plumbing pb = (Plumbing) p.getProp().get(i);

                property += (i + 1) + ". " + pb.getNama() + "   (Plumber)\r\n"
                        + "Price = " + pb.getPrice() + "\r\n"
                        + "Harga Sewa = " + pb.getHargaSewa() + "\r\n\r\n";
            } else if ("Stasiun".equals(kelas)) {
                Stasiun st = (Stasiun) p.getProp().get(i);

                property += (i + 1) + ". " + st.getNama() + "   (Service)\r\n"
                        + "Harga Sewa = " + st.getHargaSewa() + "\r\n\r\n";
            } else {
                System.out.println("Error Kelas bukan Property!");
            }

        }
        return property;
    }
    public Image resizeImage(String url, int w, int h) {
        Image dimg = null;
        try{
            BufferedImage img = ImageIO.read(new File(url));
            dimg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        }catch(IOException ex){
            ex.printStackTrace(System.err);
        }
        System.out.println(dimg);
        return dimg;
    }

    public void doEvent(Board panelBoard) {
        Player myPlayer = panelBoard.getCurrentPlayer();
        ArrayList<Petak> petaks = panelBoard.getPetaks();
        
        int myPetakId = myPlayer.getPetak();
        Petak myPetak = petaks.get(myPetakId);
        
        if (myPetak.getProperty() != null){
            Property tempProperty = myPetak.getProperty();
            if (tempProperty.getOwner() == null){
                String dialog = tempProperty.getNama() + " belum dimiliki siapapun \nMau beli ? \nHarga : $" + tempProperty.getHargaBeli();
                if (JOptionPane.showConfirmDialog(null, dialog, "Wanna Buy ?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (myPlayer.getMoney() < tempProperty.getHargaBeli()){
                        JOptionPane.showMessageDialog(null, "Anda tidak memiliki cukup uang");
                    }else{
                        myPlayer.setMoney(myPlayer.getMoney() - tempProperty.getHargaBeli());
                        myPlayer.addProperty(tempProperty);
                        tempProperty.setOwner(myPlayer);
                        if (tempProperty instanceof Stasiun){
                            myPlayer.setOwnedStation(myPlayer.getOwnedStation()+1);
                        }
                    }
                    
                }
            }else{
                
                if(tempProperty instanceof Housing){
                    Housing h = (Housing)tempProperty;
                    if (h.getOwner().equals(myPlayer)){
                        if (h.getJumlahRumah() < 2){
                            String dialog = h.getNama() + "milik Anda \nMau bangun rumah ? \nRumah sementara : "+h.getJumlahRumah()+"\nHarga : $" + h.getHargaRumah();
                            if (JOptionPane.showConfirmDialog(null, dialog, "Wanna Up Grade ?",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                if (myPlayer.getMoney() < h.getHargaRumah()){
                                    JOptionPane.showMessageDialog(null, "Anda tidak memiliki cukup uang");
                                }else{
                                    myPlayer.setMoney(myPlayer.getMoney() - h.getHargaRumah());
                                    h.setJumlahRumah(h.getJumlahRumah()+1);
                                    panelBoard.tambahRumah(myPetak);
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Mendarat di Property sendiri (Jumlah Rumah sudah maks)");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Property milik "+h.getOwner().getName()+"\n"+myPlayer.getName()+" membayar : $"+h.getHargaSewa()+ " ke "+h.getOwner().getName());
                        myPlayer.setMoney(myPlayer.getMoney() - h.getHargaSewa());
                        h.getOwner().setMoney(h.getOwner().getMoney() + h.getHargaSewa());
                    }
                }
                if(tempProperty instanceof Plumbing){
                    Plumbing p = (Plumbing)tempProperty;
                    if (p.getOwner().equals(myPlayer)){
                        JOptionPane.showMessageDialog(null, "Mendarat di Property sendiri");
                    }else{
                        JOptionPane.showMessageDialog(null, "Property milik "+p.getOwner().getName()+"\n"+myPlayer.getName()+" membayar : $"+p.getHargaSewa()+ " dikali jumlah property yang dimiliki ke "+p.getOwner().getName());
                        myPlayer.setMoney(myPlayer.getMoney() - p.getHargaSewa(myPlayer.getProp().size()));
                        p.getOwner().setMoney(p.getOwner().getMoney() + p.getHargaSewa(myPlayer.getProp().size()));
                    }
                }
                if(tempProperty instanceof Stasiun){
                    Stasiun s = (Stasiun)tempProperty;
                    if (s.getOwner().equals(myPlayer)){
                        JOptionPane.showMessageDialog(null, "Mendarat di Property sendiri");
                    }else{
                        JOptionPane.showMessageDialog(null, "Property milik "+s.getOwner().getName()+"\n"+myPlayer.getName()+" membayar : $"+s.getHargaSewa()+ " ke "+s.getOwner().getName());
                        myPlayer.setMoney(myPlayer.getMoney() - s.getHargaSewa());
                        s.getOwner().setMoney(s.getOwner().getMoney() + s.getHargaSewa());
                    }
                }
            }
        }else if (myPetak.getId() == 16)/*Penjara*/{
            JOptionPane.showMessageDialog(null, "Anda terjerumus ke dalam penjara");
            myPlayer.setInJail(true);
        }else if (myPetak.getId() == 8 || myPetak.getId() == 19 )/*Chance*/{
            
            Random rand = new Random(); 
            int roll = rand.nextInt(6);
            ChanceCard c = panelBoard.getChanceDeck().get(roll);
            String s = c.getNama();
            if (c.getIsGet().equals("false")){
                s+="\nAnda membayar ";
                if (c.getTarget().equals("bank")){
                    s+="bank sebanyak : $"+c.getMoney();
                    myPlayer.setMoney(myPlayer.getMoney()-c.getMoney());
                }else{
                    s+="pemain lain sebanyak : $"+c.getMoney();
                    if (myPlayer.equals(panelBoard.getPlayer1())){
                        myPlayer.setMoney(myPlayer.getMoney()-c.getMoney());
                        panelBoard.getPlayer2().setMoney(panelBoard.getPlayer1().getMoney()+c.getMoney());
                    }else{
                        myPlayer.setMoney(myPlayer.getMoney()-c.getMoney());
                        panelBoard.getPlayer1().setMoney(panelBoard.getPlayer1().getMoney()+c.getMoney());
                    }
                }
            }else{
                s+="\nAnda mendapat uang dari ";
                if (c.getTarget().equals("bank")){
                    s+="bank sebanyak : $"+c.getMoney();
                    
                    myPlayer.setMoney(myPlayer.getMoney()+c.getMoney());
                }else{
                    s+="pemain lain sebanyak : $"+c.getMoney();
                    if (myPlayer.equals(panelBoard.getPlayer1())){
                        myPlayer.setMoney(myPlayer.getMoney()+c.getMoney());
                        panelBoard.getPlayer2().setMoney(panelBoard.getPlayer1().getMoney()-c.getMoney());
                    }else{
                        myPlayer.setMoney(myPlayer.getMoney()+c.getMoney());
                        panelBoard.getPlayer1().setMoney(panelBoard.getPlayer1().getMoney()-c.getMoney());
                    } 
                }
            }
            
            JOptionPane.showMessageDialog(null,s);
        }
        
    }

    public void passStart(Player currentPlayer) {
        JOptionPane.showMessageDialog(null, currentPlayer.getName()+" mendarat/melewati Start\nUang bertambah 100");
        currentPlayer.setMoney(currentPlayer.getMoney()+100);
    }

    public void checkWin(JFrame game, Board panelBoard) {
        Player p1 = panelBoard.getPlayer1();
        Player p2 = panelBoard.getPlayer2();
        
        if (p1.getMoney() <= 0){
            game.setVisible(false);
            this.win(p2);
        }else if (p2.getMoney() <= 0){
            game.setVisible(false);
            this.win(p1);
        }
        
        int setP1 = 0;
        int setP2 = 0;
        
        ArrayList<Petak> p = panelBoard.getPetaks();
        if (p1.getProp().contains(p.get(1).getProperty()) && p1.getProp().contains(p.get(3).getProperty()) && p1.getProp().contains(p.get(4).getProperty())){
            setP1++;
        }
        if (p1.getProp().contains(p.get(6).getProperty()) && p1.getProp().contains(p.get(8).getProperty()) && p1.getProp().contains(p.get(9).getProperty())){
            setP1++;
        }
        if (p1.getProp().contains(p.get(11).getProperty()) && p1.getProp().contains(p.get(13).getProperty())){
            setP1++;
        }
        if (p1.getProp().contains(p.get(16).getProperty()) && p1.getProp().contains(p.get(17).getProperty()) && p1.getProp().contains(p.get(19).getProperty())){
            setP1++;
        }
        
        if (setP1 >=2 ){
            win(p1);
        }
        if (p2.getProp().contains(p.get(1).getProperty()) && p2.getProp().contains(p.get(3).getProperty()) && p2.getProp().contains(p.get(4).getProperty())){
            setP2++;
        }
        if (p2.getProp().contains(p.get(6).getProperty()) && p2.getProp().contains(p.get(8).getProperty()) && p2.getProp().contains(p.get(9).getProperty())){
            setP2++;
        }
        if (p2.getProp().contains(p.get(11).getProperty()) && p2.getProp().contains(p.get(13).getProperty())){
            setP2++;
        }
        if (p2.getProp().contains(p.get(16).getProperty()) && p2.getProp().contains(p.get(17).getProperty()) && p2.getProp().contains(p.get(19).getProperty())){
            setP2++;
        }
        
        if (setP2 >=2 ){
            win(p2);
        }
    }

    private void win(Player player) {
        JOptionPane.showMessageDialog(null, "Selamat "+player.getName()+" \nAnda menang dengan uang $"+player.getMoney()+" !");
        MySQL.setHighScore(player);
        JOptionPane.showMessageDialog(null, "High Score\n "+MySQL.getHighScore());
        System.exit(0);
    }
    
    
   
    public void stopSong(){
        clip.stop();
    }
    
    
    Clip clip;
    public void playOpening() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\music\\music1.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public void playGameSong() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\music\\music2.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
