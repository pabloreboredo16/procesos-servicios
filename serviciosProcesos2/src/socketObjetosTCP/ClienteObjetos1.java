package socketObjetosTCP;

import java.io.*;
import java.net.*;

public class ClienteObjetos1 {
    public static void main(String[] arg) {

        String Host = "localhost";
        int Puerto = 6000; // puerto remoto
        System.out.println("PROGRAMA CLIENTE INICIADO...");

        try (Socket cliente = new Socket(Host, Puerto);
             
            // Se prepara un flujo de entrada para objetos con ObjectInputStream
            ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
            // Se prepara un flujo de salida para objetos con ObjectOutputStream
            ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream())) {

            // Se recibe un objeto
            Persona dato = (Persona) perEnt.readObject(); 
            System.out.println("Recibo: " + dato.getNombre() + "*" + dato.getEdad());

            // Modifico el objeto
            dato.setNombre("Juan Ramos");
            dato.setEdad(22);

            // Se envia el objeto
            perSal.writeObject(dato);
            System.out.println("Envio: " + dato.getNombre() + "*" + dato.getEdad());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}