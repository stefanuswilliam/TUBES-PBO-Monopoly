/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Controller.*;
import MySQL.MySQL;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Board extends JPanel {
    JLabel bgBoard = new JLabel();
    Controller c = new Controller();
    ArrayList<Petak> petaks;
    ArrayList<Petak> petak = new ArrayList();
    ArrayList<ChanceCard> chanceDeck = MySQL.getChanceCard();
    ArrayList<Player> players = new ArrayList();
    Player player1;
    Player player2;

    Player currentPlayer = player1;

    public ArrayList<Petak> getPetaks() {
        return petaks;
    }

    public void setPetaks(ArrayList<Petak> petaks) {
        this.petaks = petaks;
    }

    
    
    public void nextPlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

    }

    public ArrayList<ChanceCard> getChanceDeck() {
        return chanceDeck;
    }

    public ArrayList<Petak> getPetak() {
        return petak;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayer2Icon(Icon p2Icon) {
        this.player2.setIcon(p2Icon);
    }

    public void setPlayer1Icon(Icon p1Icon) {
        this.player1.setIcon(p1Icon);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void changePosition(int count) {
        int position = currentPlayer.petak;

        position = position + count;
        if (position > 19) {
            position = position - 20;
        }

        petak.get(position).add(currentPlayer,1);
        currentPlayer.petak = position;
        this.repaint();
    }

    public Board(int x, int y, int w, int h, Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(x, y, w, h);
        this.setLayout(null);
        initBoard();
        player1.setIcon(new ImageIcon(c.resizeImage(player1.getImgFile(), 50, 50)));
        player1.setBounds(0, 0, 50, 50);
        player1.setVisible(true);
        player1.petak = 0;
        player2.setIcon(new ImageIcon(c.resizeImage(player2.getImgFile(), 50, 50)));
        player2.setBounds(50, 50, 50, 50);
        player2.setVisible(true);
        player2.petak = 0;
        petak.get(0).add(player1);
        petak.get(0).add(player2);
        players.add(player1);
        players.add(player2);
        this.currentPlayer = this.player1;
        
        JLabel bgPtk1= new JLabel();
        JLabel bgPtk3= new JLabel();
        JLabel bgPtk6= new JLabel();
        JLabel bgPtk8= new JLabel();
        JLabel bgPtk11= new JLabel();
        JLabel bgPtk13= new JLabel();
        JLabel bgPtk15= new JLabel();
        JLabel bgPtk16= new JLabel();
        JLabel bgPtk19= new JLabel();
        bgPtk1.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\GO.png",100,100)));
        bgPtk1.setBounds(0, 0, 100,100 );
        bgPtk1.setVisible(true);;
        bgPtk3.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\Gojek.png",100,100)));
        bgPtk3.setBounds(0, 0, 100,100 );
        bgPtk3.setVisible(true);;
        bgPtk6.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\Jail.png",100,100)));
        bgPtk6.setBounds(0, 0, 100,100 );
        bgPtk6.setVisible(true);;
        bgPtk8.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\ChanceCardOrange.png",100,100)));
        bgPtk8.setBounds(0, 0, 100,100 );
        bgPtk8.setVisible(true);;
        bgPtk11.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\FreeParking.png",100,100)));
        bgPtk11.setBounds(0, 0, 100,100 );
        bgPtk11.setVisible(true);;
        bgPtk13.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\Grab.png",100,100)));
        bgPtk13.setBounds(0, 0, 100,100 );
        bgPtk13.setVisible(true);;
        bgPtk15.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\pln.png",100,100)));
        bgPtk15.setBounds(0, 0, 100,100 );
        bgPtk15.setVisible(true);;
        bgPtk16.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\GoToJail.png",100,100)));
        bgPtk16.setBounds(0, 0, 100,100 );
        bgPtk16.setVisible(true);;
        bgPtk19.setIcon(new ImageIcon(c.resizeImage("src\\img\\petak\\ChanceCardBlue.png",100,100)));
        bgPtk19.setBounds(0, 0, 100,100 );
        bgPtk19.setVisible(true);;
        
        petak.get(0).add(bgPtk1);
        petak.get(2).add(bgPtk3);
        petak.get(5).add(bgPtk6);
        petak.get(7).add(bgPtk8);
        petak.get(10).add(bgPtk11);
        petak.get(12).add(bgPtk13);
        petak.get(14).add(bgPtk15);
        petak.get(15).add(bgPtk16);
        petak.get(18).add(bgPtk19);
        
        petak.get(0).add(player2,1);
        petak.get(0).add(player1,1);
    }
    
    
    public void tambahRumah(Petak petak) {
        Property prop = petak.getProperty();
        int jumlah = 0;
        JLabel houseIcon = petak.getHouseIcon();
        if(prop != null){
            if (prop instanceof Housing) {
                Housing pr = (Housing) petak.getProperty();
                jumlah = pr.getJumlahRumah();
                String url = jumlahRumah(jumlah);
                houseIcon.setIcon(new ImageIcon(c.resizeImage(url, 50, 50)));

            } else if (prop instanceof Plumbing) {
                Plumbing pb = (Plumbing) petak.getProperty();
                if (pb.getOwner() != null) {
                    houseIcon.setIcon(new ImageIcon(c.resizeImage("src\\img\\house\\other.png", 50, 50)));
                }

            } else if (prop instanceof Stasiun) {
                Stasiun st = (Stasiun) petak.getProperty();
                if (st.getOwner() != null) {
                    houseIcon.setIcon(new ImageIcon(c.resizeImage("src\\img\\house\\other.png", 50, 50)));
                }
            } else {
                System.out.println("Error Kelas bukan Property!");
            }
            houseIcon.setBounds(10, 30, 50, 50);
            petak.repaint();
        }
    }
    
    public String jumlahRumah(int jumlah) {
        String url = "";
        if (jumlah == 1) {
            url = "src\\img\\house\\1h.png";
        } else if (jumlah == 2) {
            url = "src\\img\\house\\2h.jpg";
        } else if (jumlah == 3) {
            url = "src\\img\\house\\3h.png";
        } else if (jumlah == 4) {
            url = "src\\img\\house\\4h.png";
        } else if (jumlah == 5) {
            url = "src\\img\\house\\hotel.png";
        }else if(jumlah == 0){
            url = "src\\img\\house\\blank.png";
        }

        return url;
    }
    public void initBoard() {
        int x = 0;
        int y = 0;

        petaks = MySQL.getPetakDatabase();

        int[] id = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        
        bgBoard.setIcon(new ImageIcon(c.resizeImage("src\\img\\GambarTengah.png",412,412)));
        bgBoard.setBounds(106, 106, 412,412 );
        bgBoard.setVisible(true);

        this.add(bgBoard,0);

        for (int i = 0; i < petaks.size(); i++) {
            Petak ptk;
            int j = id[i];
            //START
            if (j == 1) {
                x = 6;
                y = 6;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, 135);
                this.add(ptk, 0);
                petak.add(ptk);
                //chanceDeck.add(ptk);
                //Petak 1-
            } else if (j < 6 && j > 1) {
                x += 100;

                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, 180);
                this.add(ptk, 0);
                petak.add(ptk);
                if (j == 2) {
                    //chanceDeck.add(ptk);
                }
                //Roll DIce again
            } else if (j == 6) {
                x += 100;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, -135);
                this.add(ptk, 0);
                petak.add(ptk);
                //chanceDeck.add(ptk);
            } else if (j < 11 && j > 6) {
                y += 100;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, -90);
                this.add(ptk, 0);
                petak.add(ptk);
                if (j == 7) {
                    //chanceDeck.add(ptk);
                }
            }//free Parking
            else if (j == 11) {
                y += 100;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, -45);
                this.add(ptk, 0);
                petak.add(ptk);
                //chanceDeck.add(ptk)
            } else if (j < 16 && j > 11) {
                x -= 100;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, 0);
                this.add(ptk, 0);
                petak.add(ptk);
                if (j == 12) {
                    //chanceDeck.add(ptk);
                }
            }//free Parking
            else if (j == 16) {
                x -= 100;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, 45);
                this.add(ptk, 0);
                petak.add(ptk);
                //chanceDeck.add(ptk)
            } else if (j <= 20 && j > 16) {
                y -= 100;
                ptk = petaks.get(j - 1);
                ptk = ptk.setPetak(ptk, x, y, 100, 100, 90);
                this.add(ptk, 0);
                petak.add(ptk);
                if (j == 18) {
                    //chanceDeck.add(ptk);
                }
            }

        }

    }

}
