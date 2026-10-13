package tcp2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
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

            Scanner sc = new Scanner(System.in);
            boolean sigue = true;
            while (sigue) {
                System.out.print("\nEscribe un mensaje para el servidor (quit para salir): ");
                String mensaje = sc.nextLine();

                flujoSalida.writeUTF(mensaje);

                if (mensaje.equals("quit")) {
                    sigue = false;
                } else {
                    System.out.println("Recibiendo del SERVIDOR: " + flujoEntrada.readUTF());
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