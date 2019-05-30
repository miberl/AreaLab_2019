package com.company;

import com.fazecast.jSerialComm.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
public class Main {
    public static boolean newline(char c){
        if (c=='\n')
            return true;
        else
            return false;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem143101"); //Porta seriale dal quale legge l'input
        sp.setComPortParameters(9600, 8, 1, 0); //Parametri default di Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); // Default lettura byte

        if (sp.openPort()) {
            System.out.println("In trasmissione");
        } else {
            System.out.println("Errore: porta seriale non collegata!");
            return;
        }

        InputStream in = sp.getInputStream();
        try
        {
            while(true) {
                String s = "";
                char z;
                while (!newline(z = (char) in.read()))
                    s += z;
                BufferedWriter fr = new BufferedWriter(new FileWriter("file.txt"));
                fr.write(s);
                fr.newLine();
                fr.close();
                TimeUnit.SECONDS.sleep(1);
            }
                 //Converte il byte appena letto in char
        } catch (Exception e) { e.printStackTrace(); }
        sp.closePort();                            //Chiude il collegamento seriale da Arduino

    }

}