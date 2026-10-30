package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Receptor1UDP {

    public static void main(String[] args) {

        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            System.out.println("Esperando datagrama en el puerto " + puertoEscucha + "...");

            Scanner sc = new Scanner(System.in);

            /*
            // Imprimir los datos recibidos
            System.out.println("Mensaje recibido         : " + llegada);
            System.out.println("Numero de bytes recibidos: " + bytesRec);
            // System.out.println("Puerto local de recepcion del mensaje: " + socket.getLocalPort());
            System.out.println("-------------------------- ");
            System.out.println("Puerto origen del mensaje: " + recibo.getPort());
            System.out.println("IP de origen             : " + recibo.getAddress().getHostAddress());
            */
            
            boolean sigue = true;
            while (sigue) {
                DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
                socket.receive(recibo);
                int bytesRec = recibo.getLength();
                String llegada = new String(recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8);

                System.out.println("\nMensaje del CLIENTE: " + llegada);

                if (llegada.equals("quit")) {
                    sigue = false; 
                } else {
                    System.out.print("\nEscribe un mensaje para el cliente (quit para salir): ");

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