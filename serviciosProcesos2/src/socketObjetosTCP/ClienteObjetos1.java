package socketObjetosTCP;

import java.io.*;
import java.net.*;

public class ClienteObjetos1 {
    public static void main(String[] arg) {

        String Host = "localhost";
        int Puerto = 6000; // puerto remoto
        System.out.println("Listo para enviar al servidor...");

        try (Socket cliente = new Socket(Host, Puerto);
             
            // Se prepara un flujo de salida para objetos con ObjectOutputStream
            ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
            // Se prepara un flujo de entrada para objetos con ObjectInputStream
            ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream())) {

            // Se prepara un objeto y se envía
            Persona per = new Persona("Juan", 20);
            perSal.writeObject(per);
            System.out.println("Envio: " + per.getNombre() + "*" + per.getEdad());

            // Se recibe el objeto modificado
            Persona dato = (Persona) perEnt.readObject();
            System.out.println("Recibo: " + dato.getNombre() + "*" + dato.getEdad());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}