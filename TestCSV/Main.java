package com.company;


import java.io.*;
import java.util.concurrent.TimeUnit;
//import com.fazecast.jSerialComm.*;

public class Main {
    public static boolean newline(char c){
        if (c=='\n')
            return true;
        else
            return false;
    }
    public static String temperatura(String s){
        String q=s.substring(1);
        int i=0;
        char umi;
        String temp="";
        while(q.charAt(i)!='U'&&i<s.length()-2){
            temp+=q.charAt(i);
            i++;
        }
        return temp;
    }
    public static String umidita(String s){
        int i=0;
        while (s.charAt(i)!='U'&&i<s.length()-1)
            i++;
        return s.substring(i+1);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem143101"); //Porta seriale dal quale legge l'input
        sp.setComPortParameters(9600, 8, 1, 0); //Parametri default di Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); // Default lettura byte
        if (sp.openPort()) {
            System.out.println("In trasmissione");
        } else {
            System.out.println("Errore: porta seriale non collegata!");
            return;
        }
        */
        int rigatemperatura=26;
        int rigaumidita=35;
        //InputStream in = sp.getInputStream();
        BufferedReader fr = new BufferedReader(new FileReader("Sito.html"));
        String riga;
        int righedelfile=49;
        String righelette[]=new String [righedelfile];
        int i=0;
	    while((riga=fr.readLine())!=null){
	        righelette[i]=riga;
	            i++;
	    }
        int valoretemperatura;
        int valoreumidita;
        BufferedWriter csvwriter = new BufferedWriter(new FileWriter("dati.csv"));
        csvwriter.write("Numero rilevamento;Temperatura;Umidita'");
        csvwriter.newLine();
	    fr.close();
        int iterazione=0;
        try
        {
            for (int t=0;t<10;t++){
                String s = "T49U34";
                char z;
                //while (!newline(z = (char) in.read()))
                  //  s += z;
                righelette[rigatemperatura]=temperatura(s);
                righelette[rigaumidita]=umidita(s);
                BufferedWriter fw = new BufferedWriter(new FileWriter("Sito.html"));
                for (int j = 0; j < righedelfile; j++) {
                    fw.write(righelette[j]);
                    fw.newLine();
                }
                fw.close();
                csvwriter.write(iterazione+";"+righelette[rigatemperatura]+";"+righelette[rigaumidita]);
                csvwriter.newLine();
                TimeUnit.SECONDS.sleep(1);
                iterazione++;
            }
                csvwriter.close();

                 //Converte il byte appena letto in char
        } catch (Exception e) { e.printStackTrace(); }
        //sp.closePort();                            //Chiude il collegamento seriale da Arduino
    }

}