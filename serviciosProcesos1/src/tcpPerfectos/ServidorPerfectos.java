package tcpPerfectos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPerfectos {
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
            ServicioPerfectos servicio = new ServicioPerfectos();
            boolean sigue = true;
            while (sigue) {
                int numeroRecibido = flujoEntrada.readInt();

                if (numeroRecibido == 0) {
                    sigue = false; 
                } else {
                    String respuesta = servicio.esNumeroPerfecto(numeroRecibido);
                    flujoSalida.writeUTF(respuesta);
                    flujoSalida.flush();
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
}