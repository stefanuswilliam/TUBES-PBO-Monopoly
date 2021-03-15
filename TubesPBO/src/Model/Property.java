/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Asus
 */
public abstract class Property {
    String nama;
    int hargaBeli;
    Player owner;
    Petak petak;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(int hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Petak getPetak() {
        return petak;
    }

    public void setPetak(Petak petak) {
        this.petak = petak;
    }
    
    
    
    abstract int getHargaSewa();
    
}
