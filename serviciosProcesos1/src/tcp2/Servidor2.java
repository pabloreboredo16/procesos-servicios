package tcp2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor2 {
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

            Scanner sc = new Scanner(System.in);
            boolean sigue = true;
            while (sigue) {
                String mensajeCliente = flujoEntrada.readUTF();
                System.out.println("\nRespuesta del CLIENTE: " + mensajeCliente);

                if (mensajeCliente.equals("quit")) {
                    sigue = false; 
                } else {
                    System.out.print("Escribe una respuesta para el cliente: ");
                    String respuesta = sc.nextLine();

                    flujoSalida.writeUTF(respuesta);        
                }
            }

            // Cerrar streams
            flujoEntrada.close();
            flujoSalida.close();
            // Cerrar cliente
            cliente.close();
            // Cerrar ServerSocket
            serverS.close();
            // Cerrar Scanner
            sc.close();
            System.out.println("Servidor cerrado.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}