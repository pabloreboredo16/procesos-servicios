package tcpPrimitiva;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorPrimitiva {
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
            ServicioPrimitiva servicio = new ServicioPrimitiva();
            boolean sigue = true;
            while (sigue) {
                int control = flujoEntrada.readInt();

                if (control == 0) {
                    sigue = false; 
                } else {
                    List<Integer> primitivaCliente = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        primitivaCliente.add(flujoEntrada.readInt());
                    }

                    String respuesta = servicio.comprobarApuesta(primitivaCliente);
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