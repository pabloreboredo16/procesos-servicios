package tcpEco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEco {
    public static void main(String[] args) throws IOException {

        try {
            String host = "localhost"; // Host
            int port = 6000; // Puerto

            // Abrir Socket
            Socket cliente = new Socket(host, port); 

            // Mostrar informacion del cliente
            System.out.println("Detalles de conexion TCP");
            InetAddress i = cliente.getInetAddress();
            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + cliente.getPort());
            System.out.println("Host Remoto: " + i.getHostName().toString());
            System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

            // Flujo de salida y entrada del cliente
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

            // Bucle de respuestas
            Scanner sc = new Scanner(System.in);
            boolean sigue = true;
            while (sigue) {
                System.out.print("\nEscribe una cadena para que el servidor la pase a MAYUS (quit para salir): ");
                String cadena = sc.nextLine();

                flujoSalida.writeUTF(cadena);

                if (cadena.equalsIgnoreCase("quit")) {
                    sigue = false;
                } else {
                    System.out.println("Respuesta del SERVIDOR (Mayúsculas): " + flujoEntrada.readUTF());
                }
            }

            // Cerrar streams
            flujoEntrada.close();
            flujoSalida.close();
            // Cerrar cliente
            cliente.close();
            // Cerrar Scanner
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}