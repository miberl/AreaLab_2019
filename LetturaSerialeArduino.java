package com.company;

import com.fazecast.jSerialComm.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem141101"); //Porta seriale dal quale legge l'input
        sp.setComPortParameters(9600, 8, 1, 0); //Parametri default di Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); // Default lettura byte
        //Questo è Art Attack
        if (sp.openPort()) {
            System.out.println("In trasmissione");
        } else {
            System.out.println("Errore: porta seriale non collegata!");
            return;
        }
        InputStream in = sp.getInputStream();
        try
        {
            for (int j = 0; j < 5000; j++) //legge 5000 byte
                System.out.print((char)in.read()); //Converte il byte appena letto in char
            in.close();                             //Chiude il canale di memorizzazione dei valori letti
        } catch (Exception e) { e.printStackTrace(); }
        sp.closePort();                            //Chiude il collegamento seriale da Arduino

    }

}