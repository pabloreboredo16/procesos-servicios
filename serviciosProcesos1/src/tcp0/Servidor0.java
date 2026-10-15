package tcp0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor0 {
    public static void main(String[] args) throws IOException {
        try {
            int port = 6000; // Puerto 

            // Abrir ServerSocket
            ServerSocket serverS = new ServerSocket(port);
            System.out.println("Escuchando en " + serverS.getLocalPort());

            Socket cliente1 = serverS.accept();

            // Cerrar ServerSocket
            serverS.close();
            System.out.println("Servidor finalizado.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}