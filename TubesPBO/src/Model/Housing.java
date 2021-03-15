/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

public class Housing extends Property{
    int jumlahRumah;
    int hargaRumah;
    String warna;
    
    ArrayList<Integer> hargasewa = new ArrayList<>();
    
    public Housing(String nama, int hargaBeli, int biaya0, int biaya1, int biaya2, int biaya3, int biaya4){
        this.nama = nama;
        this.hargaBeli = hargaBeli;
        this.hargasewa.add(biaya0);
        this.hargasewa.add(biaya1);
        this.hargasewa.add(biaya2);
        this.hargasewa.add(biaya3);
        this.hargasewa.add(biaya4);
        
    }

    public int getHargaRumah() {
        return hargaRumah;
    }

    public int getJumlahRumah() {
        return jumlahRumah;
    }

    public void setJumlahRumah(int jumlahRumah) {
        this.jumlahRumah = jumlahRumah;
    }
    
    @Override
    public int getHargaSewa() {
         //count rent price (return array maybe)
        if (hargasewa != null){
            return hargasewa.get(jumlahRumah);
        }
        return 0;
    }

}
