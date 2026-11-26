package socketObjetosTCP;

import java.io.*;
import java.net.*;

public class ServidorObjetos2 {
    public static void main(String[] arg) {

        int numeroPuerto = 6000; // Puerto

        try (ServerSocket servidor = new ServerSocket(numeroPuerto)) {
            System.out.println("Esperando al cliente...");

            try (Socket cliente = servidor.accept();

                // Se prepara un flujo de entrada para objetos con ObjectInputStream
                ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
                // Se prepara un flujo de salida para objetos con ObjectOutputStream
                ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream())) {

                // Se recibe un objeto
                Persona dato = (Persona) inObjeto.readObject();
                System.out.println("Recibo: " + dato.getNombre() + "-" + dato.getEdad());

                // Modifico el objeto
                dato.setNombre(convertirMayus(dato.getNombre()));
                dato.setEdad(dato.getEdad() + 10);

                // Se envía el objeto modificado
                outObjeto.writeObject(dato);
                System.out.println("Envio: " + dato.getNombre() + "-" + dato.getEdad());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String convertirMayus(String texto) {
        return texto.toUpperCase();
    }
}