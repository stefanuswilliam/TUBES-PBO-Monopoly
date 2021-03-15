/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class Stasiun extends Property{
    ArrayList<Integer> hargasewa = new ArrayList<>();

    public Stasiun(String nama, int hargaBeli, int harga1, int harga2) {
        this.nama = nama;
        this.hargaBeli = hargaBeli;
        this.hargasewa.add(harga1);
        this.hargasewa.add(harga2);
        
    }
    
    
    
    @Override
    public int getHargaSewa() {
        return this.getOwner().getOwnedStation() * 75; 
    }
    
    public int getOwned(){
        if(owner != null){   
            return this.owner.getOwnedStation();
        }
        return 0;
    }

}
