package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente1UDP {

	public static void main(String[] args) {

		int puerto = 12345;
		byte[] buffer = new byte[1024];

		try (DatagramSocket socket = new DatagramSocket()) {
		
			Scanner sc = new Scanner(System.in);
			InetAddress destino = InetAddress.getLocalHost();

			// LOGICA DE BUCLE DE MENSAJES Y RESPUESTAS
            boolean sigue = true;
			while(sigue) {
				// ENVÍO AL SERVIDOR
				System.out.print("\nEscribe un mensaje para el servidor (quit para salir): ");
				String mensajeTexto = sc.nextLine();
                byte[] mensaje = mensajeTexto.getBytes(StandardCharsets.UTF_8);
                DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);

                socket.send(envio);

				// LÓGICA DE SALIDA
				if (mensajeTexto.equals("quit")) {
                    sigue = false;
                } else {
					// RECIBO DEL SERVIDOR
                    DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
                    socket.receive(recibo);
                    String llegada = new String(recibo.getData(), 0, recibo.getLength(), StandardCharsets.UTF_8);

                    System.out.println("Respuesta del SERVIDOR: " + llegada);
                }
			}

			// Cerrar el socket
            socket.close();
			
		} catch (IOException e) {
			System.err.println("Error al enviar el paquete UDP: " + e.getMessage());
			e.printStackTrace();
		}
	}
}