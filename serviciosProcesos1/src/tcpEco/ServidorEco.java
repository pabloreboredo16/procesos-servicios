package tcpEco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco {
    public static void main(String[] args) throws IOException {
        try {
            int port = 6000; // Puerto 

            // Abrir ServerSocket
            ServerSocket serverS = new ServerSocket(port);
            System.out.println("Escuchando en " + serverS.getLocalPort());

            Socket cliente = serverS.accept();

            // Flujo de entrada y salida del servidor
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            // Bucle de respuestas
            boolean sigue = true;
            while (sigue) {
                String mensajeCliente = flujoEntrada.readUTF();

                if (mensajeCliente.equalsIgnoreCase("quit")) {
                    sigue = false;
                } else {
                    String respuestaMayusculas = convertirMayus(mensajeCliente);
                    flujoSalida.writeUTF(respuestaMayusculas);
                }
            }    

            // Cerrar streams
            flujoEntrada.close();
            flujoSalida.close();
            // Cerrar cliente
            cliente.close();
            // Cerrar ServerSocket
            serverS.close();
            System.out.println("Servidor cerrado.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    public static String convertirMayus(String texto) {
        return texto.toUpperCase();
    }
}