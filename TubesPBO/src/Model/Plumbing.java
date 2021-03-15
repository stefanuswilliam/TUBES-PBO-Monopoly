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
public class Plumbing extends Property{
    int price;

    public Plumbing(String nama, int hargaBeli) {
        this.nama = nama;
        this.hargaBeli = hargaBeli;
        this.price = 15;
    }
    @Override
    public int getHargaSewa(){
        int sewa = 15;
        
        return sewa;
    }

    public int getPrice() {
        return this.price;
    }

    public int getHargaSewa(int jumlahProperty) {
        return(getHargaSewa()*jumlahProperty);
    }
}
