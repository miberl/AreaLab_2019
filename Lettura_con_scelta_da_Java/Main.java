package com.company;
import java.util.*;
import com.fazecast.jSerialComm.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem141401"); //Porta seriale dal quale legge l'input
        sp.setComPortParameters(9600, 8, 1, 0); //Parametri default di Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); // Default lettura byte
        Scanner t = new Scanner(System.in);
        if (sp.openPort()) {
            System.out.println("In trasmissione");
        } else {
            System.out.println("Errore: porta seriale non collegata!");
            return;
        }
        OutputStream out = sp.getOutputStream();
        System.out.println("Vuoi leggere la temperatura (t), l'umidita' (u) oppure entrambe (e)?");
        char scelta=t.nextLine().charAt(0);
        if (scelta=='t')
            out.write(0);
        else if (scelta=='u')
            out.write(1);
        else
            out.write(2);


        InputStream in = sp.getInputStream();
        try
        {
            for (int j = 0; j < 5000; j++) //legge 5000 byte
                System.out.print((char)in.read()); //Converte il byte appena letto in char
            in.close();                             //Chiude il canale di memorizzazione dei valori letti
        } catch (Exception e) { e.printStackTrace(); }
        sp.closePort();                           //Chiude il collegamento seriale da Arduino
    }

}