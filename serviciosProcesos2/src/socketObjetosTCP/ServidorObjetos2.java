package socketObjetosTCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServidorObjetos2 {
    @SuppressWarnings("unchecked")
    public static void main(String[] arg) {

        int numeroPuerto = 6000; // Puerto

        try (ServerSocket servidor = new ServerSocket(numeroPuerto)) {
            System.out.println("Esperando al cliente...");

            try (Socket cliente = servidor.accept();

                // Se prepara un flujo de entrada para objetos con ObjectInputStream
                ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
                // Se prepara un flujo de salida para objetos con ObjectOutputStream
                ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream())) {

                // Se recibe la lista de objetos
                ArrayList<Persona> listaDatos = (ArrayList<Persona>) inObjeto.readObject();
                System.out.println("Recibo lista de personas:");
                for (Persona p : listaDatos) {
                    System.out.println("  " + p.getNombre() + "-" + p.getEdad());
                }

                // Modifico cada objeto de la lista
                for (Persona p : listaDatos) {
                    p.setNombre(convertirMayus(p.getNombre()));
                    p.setEdad(p.getEdad() + 10);
                }

                // Se envía la lista de objetos modificada
                outObjeto.writeObject(listaDatos);
                System.out.println("Envio lista modificada:");
                for (Persona p : listaDatos) {
                    System.out.println("  " + p.getNombre() + "-" + p.getEdad());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String convertirMayus(String texto) {
        return texto.toUpperCase();
    }
}