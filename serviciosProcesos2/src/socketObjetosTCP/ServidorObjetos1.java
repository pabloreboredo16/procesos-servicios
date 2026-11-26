package socketObjetosTCP;

import java.io.*;
import java.net.*;

public class ServidorObjetos1 {
    public static void main(String[] arg) {

        int numeroPuerto = 6000; // Puerto

        try (ServerSocket servidor = new ServerSocket(numeroPuerto)) {
            System.out.println("Esperando al cliente...");

            try (Socket cliente = servidor.accept();

                // Se prepara un flujo de salida para objetos con ObjectOutputStream
                ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
                // Se prepara un flujo de entrada para objetos con ObjectInputStream
                ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream())) {

                // Se envia un objeto
                Persona per = new Persona("Juan", 20);
                outObjeto.writeObject(per);
                System.out.println("Envio: " + per.getNombre() + "*" + per.getEdad());

                // Se recibe un objeto
                Persona dato = (Persona) inObjeto.readObject();
                System.out.println("Recibo: " + dato.getNombre() + "*" + dato.getEdad());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}