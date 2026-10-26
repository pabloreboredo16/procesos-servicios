package udp0;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Receptor0UDP {

    public static void main(String[] args) {

        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            System.out.println("Esperando datagrama en el puerto " + puertoEscucha + "...");

            DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
            socket.receive(recibo); // Se bloquea hasta recibir un paquete

            // Obtener la longitud del mensaje recibido
            int bytesRec = recibo.getLength(); 

            // Convertir solo los bytes útiles recibidos a String usando UTF-8
            String llegada = new String(recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8);

            // Imprimir los datos recibidos
            System.out.println("Mensaje recibido         : " + llegada);
            System.out.println("Numero de bytes recibidos: " + bytesRec);
            // System.out.println("Puerto local de recepcion del mensaje: " + socket.getLocalPort());
            System.out.println("-------------------------- ");
            System.out.println("Puerto origen del mensaje: " + recibo.getPort());
            System.out.println("IP de origen             : " + recibo.getAddress().getHostAddress());

            // Cerrar el socket
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al recibir el paquete UDP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}