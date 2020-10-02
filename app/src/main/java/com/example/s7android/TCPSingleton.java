package com.example.s7android;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPSingleton extends Thread {


    //Se declara la instancia unica del socket
    private static TCPSingleton instanceUnique;

    //Se le da el acceso a la misma clase
    public static TCPSingleton getInstance() {
        if (instanceUnique != null) {
            instanceUnique = new TCPSingleton();
            instanceUnique.start();
        }

        return instanceUnique;
    }

    //constructor privado perteniente unicamente al singleton
    private TCPSingleton() {

    }

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    @Override
    public void run() {
        try {
            // Solicito la conexión
            Log.e("Conexión", "Esperando");
            socket = new Socket("192.168.0.4", 5000);
            Log.e("Conexión", "Conexión establecida");


            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            OutputStream ou = socket.getOutputStream();
            OutputStreamWriter ouw = new OutputStreamWriter(ou);
            writer = new BufferedWriter(ouw);

            while (true) {
                String line = reader.readLine();
                Log.e(">>>", line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMesages(String msj) {

        new Thread(
                () -> {
                    try {
                        writer.write(msj);
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

        ).start();
    }

}


