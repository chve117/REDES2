
package juegogatohilos;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Hassan
 */
public class CJugadores extends Thread{
    private DataInputStream entrada;
    private DataOutputStream salida;
    public Tablero Jtablero;
    private int id;
    private int jugador;
    private boolean turno;
    char letra;
    public char [] Gatotab = new char [9];
    
    public CJugadores (OutputStream os,InputStream is,int id, int jugador, boolean turno){
        entrada= new DataInputStream(is);
        salida= new DataOutputStream(os);
        this.id=id;
        this.turno=turno;
        this.jugador=jugador;
        if(jugador==1){
            letra='x';
        }else{
            letra='o';
        }
        for(int i=0;i<9;i++){
            Gatotab[i]='n';
        }
    }
    @Override
    public void run(){
        System.out.println("Empieza jugador 1");
        Jtablero = new Tablero(id,jugador);
        Jtablero.setVisible(true);
        Jtablero.lblInfo.setVisible(turno);
        Jtablero.btns[0].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[0]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[0]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
         Jtablero.btns[1].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[1]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[1]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
          Jtablero.btns[2].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[2]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[2]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
           Jtablero.btns[3].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[3]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[3]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
            Jtablero.btns[4].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[4]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[4]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
            Jtablero.btns[5].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[5]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[5]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
            Jtablero.btns[6].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[6]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[6]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
            Jtablero.btns[7].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[7]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[7]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
            Jtablero.btns[8].addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(Gatotab[8]=='n'&& turno){
                    try{
                        turno=false;
                        Jtablero.lblInfo.setVisible(turno);
                        Gatotab[8]=letra;
                        actualizacion(Gatotab);
                        salida.writeUTF(String.valueOf(Gatotab));
                        salida.flush();
                    }catch(IOException io){
                      io.printStackTrace();
                    }
                }
            }
        });
            while(true){
                try{
                    String ent=entrada.readUTF();
                    Gatotab=ent.toCharArray();
                    actualizacion(Gatotab);
                    turno=true;
                    Jtablero.lblInfo.setVisible(turno);
                }catch(IOException io){
                    io.printStackTrace();
                }
            }
            
    } 
    void actualizacion(char[] t) {
           ImageIcon img= new ImageIcon();
           ImageIcon ImgScal;
          for (int i = 0; i < 9; i++) {
              switch(t[i]){
                  case'x':
                      img= new ImageIcon(getClass().getResource("gato.png"));
                      ImgScal= new ImageIcon(img.getImage().getScaledInstance(Jtablero.btns[0].getWidth(), Jtablero.btns[0].getHeight(),Image.SCALE_DEFAULT));
                      Jtablero.btns[i].setIcon(ImgScal);
                      break;
                  
                  case 'o':
                       img= new ImageIcon(getClass().getResource("circle.png"));
                      ImgScal= new ImageIcon(img.getImage().getScaledInstance(Jtablero.btns[0].getWidth(), Jtablero.btns[0].getHeight(),Image.SCALE_DEFAULT));
                      Jtablero.btns[i].setIcon(ImgScal);
                      break;
              }
    }
 winner(t);
 }

    private void winner(char[] t) {
        //verificar ganador en vertical
        for(int i=0; i<9;i+=3){
            if(t[i] !='n' && t[i]==t[i+1]&&t[i]==t[i+2]){
                if(t[i]=='x'){
                    JOptionPane.showMessageDialog(null,"Jugador 1 Gano","Partida"+id, JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Jugador 2 Gano","Partida"+id, JOptionPane.WARNING_MESSAGE);
                }
                Jtablero.setVisible(false);
                Jtablero=null;
                this.stop();
            }
        }
        //verificar al ganador horizontalmente
        for(int i=0; i<3;i++){
            if(t[i] !='n' && t[i]==t[i+3]&&t[i]==t[i+6]){
                if(t[i]=='x'){
                    JOptionPane.showMessageDialog(null,"Jugador 1 Gano","Partida"+id, JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Jugador 2 Gano","Partida"+id, JOptionPane.WARNING_MESSAGE);
                }
                Jtablero.setVisible(false);
                Jtablero=null;
                this.stop();
            }
        }
        //Empate
            if(t[1]!='n' && t[2]!='n'&& t[3]!='n'&& t[4]!='n'&& t[5]!='n'&& t[6]!='n'&& t[7]!='n'&& t[8]!='n'&& t[0]!='n'){
                JOptionPane.showMessageDialog(null,"EMPATE","Partida"+id, JOptionPane.WARNING_MESSAGE);
            }
        
        //ganador en raya
            if(t[4] !='n' && (t[4]==t[8]&&t[4]==t[0] || t[4]==t[2]&&t[4]==t[6])){
                if(t[4]=='x'){
                    JOptionPane.showMessageDialog(null,"Jugador 1 Gano","Partida"+id, JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Jugador 2 Gano","Partida"+id, JOptionPane.WARNING_MESSAGE);
                }
                Jtablero.setVisible(false);
                Jtablero=null;
                this.stop();
            }
        
    }


}

