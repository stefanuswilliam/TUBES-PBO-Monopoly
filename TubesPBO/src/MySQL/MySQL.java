/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Model.ChanceCard;
import Model.Housing;
import Model.Petak;
import Model.Player;
import Model.Plumbing;
import Model.Stasiun;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author USER
 */
public class MySQL {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/monopoly";
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public static Connection getConnection(){
        if (conn == null){
            conn = logOn();
        }
        return conn;
    }

    public static ResultSet query(String sql) {
        ResultSet result = null;

        try {
            getConnection();
            
            stmt = conn.createStatement();

            result = stmt.executeQuery(sql);
            

        } catch (Exception e) {
            e.printStackTrace();
            
        }
        logOff();
        return result;
    }
    
    public static void main(String[] args) {
        query("select * from user");
    }

    public static ArrayList<ChanceCard> getChanceCard()  {
        ArrayList<ChanceCard> chances = new ArrayList<>();
        
        try{
            try{
                getConnection();
                
                stmt = conn.createStatement();
                
                rs = stmt.executeQuery("SELECT * FROM chance ");
            }catch(SQLException e){
                e.printStackTrace();
            }
            while (rs.next()){
                String nama = (rs.getString("nama"));
                String isGet = (rs.getString("is_get"));
                int money = rs.getInt("jumlah");
                String target = rs.getString("pihak_2");
                chances.add(new ChanceCard(nama, money, target, isGet));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            logOff();
            
        }

        return chances;
        
    }
    public static String getHighScore()  {
        String s = "";
        try{
            try{
                getConnection();
                
                stmt = conn.createStatement();
                
                rs = stmt.executeQuery("SELECT * FROM `high score` LIMIT 10");
            }catch(SQLException e){
                e.printStackTrace();
            }
            int i = 0;
            while (rs.next()){
                i++;
                s+= i+". "+rs.getString("nama")+" => $"+rs.getString("money") + "\n";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            logOff();
            
        }

        return s;
        
    }
    public static void setHighScore(Player p)  {
        String nama = p.getName();
        int money = p.getMoney();
        try{
            getConnection();

            stmt = conn.createStatement();
            String query = "INSERT INTO `high score` (`nama`, `money`) VALUES ('"+nama+"', '"+money+"') ";
            stmt.execute(query);

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            logOff();
            
        }

        
    }
    
    
    public static ArrayList<Petak> getPetakDatabase()  {
        ArrayList<Petak> petaks = new ArrayList<>();
        try{
            try{
                getConnection();
                
                stmt = conn.createStatement();
                
                rs = stmt.executeQuery("SELECT * FROM petaks ");
            }catch(SQLException e){
                e.printStackTrace();
            }
            while (rs.next()){
                String nama = (rs.getString("nama_lokasi"));
                int id = (rs.getInt("id_kelompok"));
                String tipe = (rs.getString("tipe"));
                int hargaBeli = 0;
                int biaya0 = 0;
                int biaya1 = 0;
                int biaya2 = 0;
                int biaya3 = 0;
                int biaya4 = 0;
                switch(tipe){
                    case "start":
                        petaks.add(new Petak(id, null, nama));
                        break;
                    case "service":
                        hargaBeli = rs.getInt("harga_beli");
                        biaya0 = rs.getInt("biaya0");
                        biaya1 = rs.getInt("biaya1");
                        petaks.add(new Petak(id, new Stasiun(nama, hargaBeli, biaya0, biaya1), nama));
                        break;
                    case "biru":
                        hargaBeli = rs.getInt("harga_beli");
                        biaya0 = rs.getInt("biaya0");
                        biaya1 = rs.getInt("biaya1");
                        biaya2 = rs.getInt("biaya2");
                        biaya3 = rs.getInt("biaya3");
                        biaya4 = rs.getInt("biaya4");
                        petaks.add(new Petak(id,new Housing(nama, hargaBeli, biaya0, biaya1, biaya2, biaya3, biaya4),nama) );
                        break;
                    case "pink":
                        hargaBeli = rs.getInt("harga_beli");
                        biaya0 = rs.getInt("biaya0");
                        biaya1 = rs.getInt("biaya1");
                        biaya2 = rs.getInt("biaya2");
                        biaya3 = rs.getInt("biaya3");
                        biaya4 = rs.getInt("biaya4");
                        petaks.add(new Petak(id,new Housing(nama, hargaBeli, biaya0, biaya1, biaya2, biaya3, biaya4),nama) );
                        break;
                    case "chance":
                        petaks.add(new Petak(id, null, nama));
                        break;
                    case "jail":
                        petaks.add(new Petak(id, null, nama));
                        break;
                    case "free":
                        petaks.add(new Petak(id, null, nama));
                        break;
                    case "orange":
                        hargaBeli = rs.getInt("harga_beli");
                        biaya0 = rs.getInt("biaya0");
                        biaya1 = rs.getInt("biaya1");
                        biaya2 = rs.getInt("biaya2");
                        biaya3 = rs.getInt("biaya3");
                        biaya4 = rs.getInt("biaya4");
                        petaks.add(new Petak(id,new Housing(nama, hargaBeli, biaya0, biaya1, biaya2, biaya3, biaya4),nama) );
                        break;
                    case "plumber":
                        hargaBeli = rs.getInt("harga_beli");
                        biaya0 = rs.getInt("biaya0");
                        biaya1 = rs.getInt("biaya1");
                        biaya2 = rs.getInt("biaya2");
                        biaya3 = rs.getInt("biaya3");
                        biaya4 = rs.getInt("biaya4");
                        petaks.add(new Petak(id,new Plumbing(nama, hargaBeli),nama) );
                        break;
                    case "go_jail":
                        petaks.add(new Petak(id, null, nama));
                        break;
                    case "green":
                        hargaBeli = rs.getInt("harga_beli");
                        biaya0 = rs.getInt("biaya0");
                        biaya1 = rs.getInt("biaya1");
                        biaya2 = rs.getInt("biaya2");
                        biaya3 = rs.getInt("biaya3");
                        biaya4 = rs.getInt("biaya4");
                        petaks.add(new Petak(id,new Housing(nama, hargaBeli, biaya0, biaya1, biaya2, biaya3, biaya4),nama) );
                        break;
                }
                
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            logOff();
            
        }
        
        return petaks;
    }

    private static Connection logOn() {
        try {
            Class.forName(JDBC_DRIVER);
            
            System.out.println("Connection Succesful");
            
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            System.out.println("Connection Failed "+e.toString());
        }catch(ClassNotFoundException ex){
            ex.printStackTrace(System.err);
            System.out.println("JDBC.ODBC Server not found");
        }
        return null;
    }
    
    private static void logOff(){
        try{
            conn.close();
            System.out.println("Connection Closed");
            conn = null;
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
}
