/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegogatohilos;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Hassan
 */
public class Gatoprinci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int ventanas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese partidas: "));
        PipedOutputStream []tuberias1= new PipedOutputStream[ventanas];
        PipedInputStream []tuberiai1= new PipedInputStream[ventanas];
        PipedOutputStream []tuberias2= new PipedOutputStream[ventanas];
        PipedInputStream []tuberiai2= new PipedInputStream[ventanas];
        CJugadores []j1 = new CJugadores[ventanas];
        CJugadores []j2 =new CJugadores[ventanas];
        
        try{
            for(int i=0;i< ventanas;i++){
                tuberias1[i]= new PipedOutputStream();
                tuberiai1[i]= new PipedInputStream(tuberias1[i]);
                tuberias2[i]= new PipedOutputStream();
                tuberiai2[i]= new PipedInputStream(tuberias2[i]);
                j1[i]= new CJugadores(tuberias1[i],tuberiai2[i],i+1,1,true);
                j2[i]= new CJugadores(tuberias2[i],tuberiai1[i],i+1,2,false);
                j1[i].start();
                j2[i].start();
            }
        } catch(IOException io){
            io.printStackTrace();
        }
    }
    
}
