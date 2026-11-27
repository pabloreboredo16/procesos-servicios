package socketObjetosTCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClienteObjetos2 {
    @SuppressWarnings("unchecked")
    public static void main(String[] arg) {

        String Host = "localhost";
        int Puerto = 6000; // puerto remoto
        System.out.println("Listo para enviar al servidor...");

        try (Socket cliente = new Socket(Host, Puerto);

            // Se prepara un flujo de salida para objetos con ObjectOutputStream
            ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
            // Se prepara un flujo de entrada para objetos con ObjectInputStream
            ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream())) {

            // Se prepara una lista de objetos Persona y se envía
            ArrayList<Persona> listaPersonas = new ArrayList<>();
            listaPersonas.add(new Persona("Pablo Reboredo", 25));
            listaPersonas.add(new Persona("Ana López", 30));
            listaPersonas.add(new Persona("Carlos Gómez", 40));

            perSal.writeObject(listaPersonas);
            System.out.println("Envio lista de personas:");
            for (Persona p : listaPersonas) {
                System.out.println("  " + p.getNombre() + "-" + p.getEdad());
            }

            // Se recibe la lista de objetos modificada
            ArrayList<Persona> listaModificada = (ArrayList<Persona>) perEnt.readObject();
            System.out.println("Recibo lista modificada:");
            for (Persona p : listaModificada) {
                System.out.println("  " + p.getNombre() + "-" + p.getEdad());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}