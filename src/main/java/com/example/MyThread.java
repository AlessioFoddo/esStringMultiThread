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
            String risposta;
            do {
                risposta = "";
                stringaRicevuta = in.readLine();
                System.out.println("La stringa ricevuta è: " + stringaRicevuta);

                if(stringaRicevuta.lastIndexOf("^") != -1){
                   risposta = stringaRicevuta.replace("^", "").toUpperCase();
                }else if(stringaRicevuta.lastIndexOf("-") != -1){
                    risposta = stringaRicevuta.replace("-", "").toLowerCase();
                }else if(stringaRicevuta.lastIndexOf("<") != -1){
                    for (int i = 0; i < (stringaRicevuta.length() - 1); i++) {
                        risposta = stringaRicevuta.charAt(i) + risposta;
                    }
                }else if(stringaRicevuta.lastIndexOf(":") != -1){
                    risposta = Integer.toString(stringaRicevuta.length());
                }else{
                    risposta = "COMANDO NON ACCETTATO";
                }
                
                out.writeBytes(risposta + "\n");
            } while (!stringaRicevuta.equals("!"));
            
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
