package tcp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente1 {
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

            // Cerrar streams
            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
            // Cerrar Socket
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}