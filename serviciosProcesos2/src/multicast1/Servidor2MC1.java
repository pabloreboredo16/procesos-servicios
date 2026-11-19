package multicast1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class Servidor2MC1 {
    public static void main(String args[]) throws Exception {
        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Se crea el socket multicast
        MulticastSocket ms = new MulticastSocket();
        int Puerto = 12345; // Puerto multicast[cite: 38]
        InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo[cite: 38]

        // ArrayList con las frases de ejemplo
        ArrayList<String> frases = new ArrayList<>();
        frases.add("Bienvenidos al canal multicast");
        frases.add("La conexion UDP no garantiza entrega");
        frases.add("Programacion de Servicios y Procesos");
        frases.add("Enviando mensaje automatico al grupo");
        frases.add("Recordad revisar los puertos libres");

        Random random = new Random();
        String cadena = "";

        while (!cadena.trim().equals("*")) {
            System.out.print("Pulsa Enter para enviar frase aleatoria (* para salir): ");
            cadena = in.readLine();

            String mensajeAEnviar;
            if (cadena.trim().equals("*")) {
                mensajeAEnviar = "*";
            } else {
                mensajeAEnviar = frases.get(random.nextInt(frases.size()));
            }

            // ENVIANDO AL GRUPO
            DatagramPacket paquete = new DatagramPacket(
                    mensajeAEnviar.getBytes(), mensajeAEnviar.length(), grupo, Puerto);
            ms.send(paquete);
        }

        ms.close(); // cierro socket
        System.out.println("Socket cerrado...");
    }
}