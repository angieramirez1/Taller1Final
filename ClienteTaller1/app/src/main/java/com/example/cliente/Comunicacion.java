package com.example.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

//constructor de la clase de comunicacion
public class Comunicacion extends Observable implements Runnable{
    private static Comunicacion ref;
    private Socket s;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private boolean conectado;

    private Comunicacion(){
        conectado = false;
    }

    public static Comunicacion getInstance() {
        if( ref == null ){
            ref = new Comunicacion();
            new Thread(ref).start();
            System.out.println("INICIADO");
        }
        return ref;
    }

    @Override
    //metodo que corre la coneccion y revisa si esta o no conectado
    public void run() {
        if(!conectado){
            try {
                InetAddress ip = InetAddress.getByName("172.30.185.180");
                s = new Socket(ip, 5000);
                entrada = new DataInputStream(s.getInputStream());
                salida = new DataOutputStream(s.getOutputStream());
                conectado = true;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while(true){
            try {
                recibir();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void recibir() throws IOException {
        if(conectado){
            String mensajeEntrada = entrada.readUTF();
            setChanged();
            notifyObservers(mensajeEntrada);
            clearChanged();
        }
    }

    public void enviar(final String mensaje){
        if(conectado) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        salida.writeUTF(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
