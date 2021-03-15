package View;

import Controller.Controller;
import Model.Board;
import Model.Dice;
import Model.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gameplay {

    Board panelBoard;
    Controller c = new Controller();
    Dice dice = new Dice();
    Player p;
    JPanel panelCommand;
    JPanel panelInfo;
    JLabel labelInfo;
    JTextArea infoArea;
    JLabel money;
    JScrollPane sp;

    public void gamePlay(Player p1, Player p2) {
        //Music
        c.playGameSong();
        
        //FRAME
        JFrame game = new JFrame();
        game.setTitle("Monopoli");
        game.setBounds(100, 100, 940, 660);
        game.setLayout(null);
        game.setVisible(true);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //BUAT PANEL
        panelBoard = new Board(6, 6, 612, 612, p1, p2);
        JPanel panelDice = new JPanel();
        panelDice.setLayout(null);
        panelDice.setBounds(620, 6, 300, 205);
        panelDice.setBorder(BorderFactory.createLineBorder(Color.black));

        panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBounds(620, 211, 300, 205);
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.black));

        panelCommand = new JPanel();
        panelCommand.setLayout(null);
        panelCommand.setBounds(620, 410, 300, 205);
        panelCommand.setBorder(BorderFactory.createLineBorder(Color.black));

        //PANEL DICE
        JLabel labelDice = new JLabel("<html> <u> DICE </u></html>");
        labelDice.setBounds(120, 0, 100, 40);
        labelDice.setFont(new Font("Serif", Font.PLAIN, 18));
        panelDice.add(labelDice);

        JLabel dadu = new JLabel();
        dice.setIcon("src\\img\\one.png");
        dice.setValue(1);
        dadu.setIcon(new ImageIcon(c.resizeImage(dice.getIcon(), 110, 110)));
        dadu.setBounds(90, 50, 110, 110);
        panelDice.add(dadu);

        //PANEL INFO
        labelInfo = new JLabel();
        infoArea = new JTextArea(10, 10);
        infoArea.setBounds(0, 0, 200, 200);
        infoArea.setEditable(false);
        
        refreshInfo();
        
        sp = new JScrollPane(infoArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(0, 50, 300, 155);
        panelInfo.add(sp);

        //PANEL COMMAND
        JLabel labelCommand = new JLabel("<html> <u> COMMAND BUTTON </u></html>");
        labelCommand.setBounds(60, 0, 200, 40);
        labelCommand.setFont(new Font("Serif", Font.PLAIN, 18));
        panelCommand.add(labelCommand);

        JButton roll = new JButton("ROLL");
        roll.setBounds(95, 50, 100, 40);
        panelCommand.add(roll);

        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelBoard.getCurrentPlayer().isInJail()){
                    if (JOptionPane.showConfirmDialog(null,panelBoard.getCurrentPlayer().getName() + ", anda berada di penjara\nApakah mau keluar ?\n(Bayar 100$)", "Wanna Get Out ?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        panelBoard.getCurrentPlayer().setMoney( panelBoard.getCurrentPlayer().getMoney()-100);
                        panelBoard.getCurrentPlayer().setInJail(false);
                    }else{
                        panelBoard.nextPlayer();
                    }
                }else{
                    int new_val = updateDice();
                    int prevPosition = panelBoard.getCurrentPlayer().getPetak();
                    dadu.setIcon(new ImageIcon(c.resizeImage(dice.getIcon(), 110, 110)));
                    panelBoard.changePosition(new_val);
                    if (panelBoard.getCurrentPlayer().getPetak() < prevPosition){
                        c.passStart(panelBoard.getCurrentPlayer());
                    }
                    c.doEvent(panelBoard);

                    
                    
                    while(panelBoard.getCurrentPlayer().isInJail() && panelBoard.getCurrentPlayer().getPetak() != 5){
                        panelBoard.changePosition(1);
                    }
                    c.checkWin(game,panelBoard);
                    panelBoard.nextPlayer();
                    refreshInfo();
                    //Player player1 = panelBoard.getPlayer1();
                    //panelBoard.getPetak().get(0).remove(player1);
                }
            }
                
            public int updateDice(){
                String new_icon = "";
                dice.rollDice();
                int new_val = dice.getValue();
                System.out.println("Angka dadu = " + new_val);
                switch (new_val) {
                    case 1:
                        new_icon = "src\\img\\one.png";
                        break;
                    case 2:
                        new_icon = "src\\img\\two.png";
                        break;
                    case 3:
                        new_icon = "src\\img\\three.png";
                        break;
                    case 4:
                        new_icon = "src\\img\\four.png";
                        break;
                    case 5:
                        new_icon = "src\\img\\five.png";
                        break;
                    case 6:
                        new_icon = "src\\img\\six.png";
                        break;
                }
                dice.setIcon(new_icon);
                return new_val;
            }
            
        });
        //ADD PANEL
        game.add(panelBoard);
        game.add(panelDice);
        game.add(panelInfo);
        game.add(panelCommand);
    }

    Gameplay(Player p1, Player p2) {
        gamePlay(p1, p2);

    }

    public void refreshInfo() {
        p = panelBoard.getCurrentPlayer();

        labelInfo.setText("<html> <u>" + p.getName() + "</u></html>");
        labelInfo.setBounds(100, 0, 200, 40);
        labelInfo.setFont(new Font("Serif", Font.PLAIN, 24));

        String property = c.listProperty(p);
        infoArea.setText("Money = " + String.valueOf(p.getMoney()) + "\r\n\r\n ----PROPERTY---- \r\n" + property);
        infoArea.setFont(new Font("Serif", Font.PLAIN, 16));

        //ADD TO PANEL INFO
        panelInfo.add(labelInfo);
        panelInfo.repaint();
    }

}
