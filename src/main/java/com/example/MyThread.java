package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyThread extends Thread {
      
    private Socket s;

    public MyThread(Socket s){
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String stringaRicevuta; 
            int scelta;
            String risposta;
            do {
                risposta = "";
                scelta = Integer.parseInt(in.readLine());
                switch (scelta) {
                    case 0:
                        System.out.println("Connessione chiusa");
                        break;
                    case 1:
                        stringaRicevuta = in.readLine();
                        
                        risposta = stringaRicevuta.toUpperCase();
                        break;
                    case 2:
                        stringaRicevuta = in.readLine();
                        
                        risposta = stringaRicevuta.toLowerCase();
                        break;
                    case 3:
                        stringaRicevuta = in.readLine();
                        
                        for (int i = 0; i < (stringaRicevuta.length()); i++) {
                            risposta = stringaRicevuta.charAt(i) + risposta;
                        }
                        break;
                    case 4:
                        stringaRicevuta = in.readLine();
                        
                        risposta = Integer.toString((stringaRicevuta.length()));
                        break;
                    default:
                        risposta = "COMANDO NON ACCETTATO";
                        break;
                }
                out.writeBytes(risposta + "\n");
            } while (scelta != 0);
            
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
