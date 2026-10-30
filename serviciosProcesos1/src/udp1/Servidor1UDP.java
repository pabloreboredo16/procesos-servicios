package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Servidor1UDP {

    public static void main(String[] args) {

        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            System.out.println("Esperando datagrama en el puerto " + puertoEscucha + "...");

            Scanner sc = new Scanner(System.in);
            
            // LOGICA DE BUCLE DE MENSAJES Y RESPUESTAS
            boolean sigue = true;
            while (sigue) {
                // RECIBO DEL CLIENTE
                DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
                socket.receive(recibo);
                String llegada = new String(recibo.getData(), 0, recibo.getLength(), StandardCharsets.UTF_8);

                System.out.println("\nMensaje del CLIENTE: " + llegada);

                // LÓGICA DE SALIDA 
                if (llegada.equals("quit")) {
                    sigue = false; 
                } else {
                    // ENVÍO AL CLIENTE
                    System.out.print("Escribe un mensaje para el cliente (quit para salir): ");
				    String respuestaTexto = sc.nextLine();
                    byte[] mensaje = respuestaTexto.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, recibo.getAddress(), recibo.getPort());
                    
                    socket.send(envio);
                }
            }

            // Cerrar el socket
            socket.close();

        } catch (IOException e) {
            System.err.println("Error al recibir el paquete UDP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}