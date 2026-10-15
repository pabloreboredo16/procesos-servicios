package tcp0;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente0 {
    public static void main(String[] args) throws IOException {

        try {
            String host = "localhost"; // Host
            int port = 6000; // Puerto

            // Abrir Socket
            Socket cliente = new Socket(host, port); 

            // Mostrar informacion del cliente
            InetAddress i = cliente.getInetAddress();
            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + cliente.getPort());
            System.out.println("Host Remoto: " + i.getHostName().toString());
            System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

            // Cerrar cliente
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}