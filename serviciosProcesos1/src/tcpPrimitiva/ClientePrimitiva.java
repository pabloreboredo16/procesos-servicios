package tcpPrimitiva;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientePrimitiva {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try {
            Socket cliente = new Socket(host, puerto);
            Scanner sc = new Scanner(System.in);

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            System.out.println("Conectado al servidor de Primitiva.");

            while (true) {
                int[] numerosCliente = new int[6];

                System.out.println("Introduce los numeros de tu primitiva entre 1-49");
                for (int i = 0; i < numerosCliente.length; i++) {
                    numerosCliente[i] = ServicioClientePrimitiva.pedirEnteroPositivo(sc);
                }
                flujoSalida.writeInt(numerosCliente.length);
                for (int num : numerosCliente) {
                    flujoSalida.writeInt(num);
                }

                System.out.println("Por favor introduzca su numero reintegro entre 0-9:");
                int reintegro = ServicioClientePrimitiva.pedirReintegro(sc);
                flujoSalida.writeInt(reintegro);

                System.out.println("Por favor introduzca su numero complementario entre 1-49: ");
                int complementario = ServicioClientePrimitiva.pedirEnteroPositivo(sc);
                flujoSalida.writeInt(complementario);

                String respuesta = flujoEntrada.readUTF();
                System.out.println("Recibiendo un mensaje del servidor: " + respuesta);
                
                System.out.println("Si desea salir introduzca 0: ");
                int decision =ServicioClientePrimitiva.pedirEnteroPositivo(sc);

                flujoSalida.writeInt(decision);
                flujoSalida.flush();
                
                if (decision==0) {
                    System.out.println("Cerrando comunicacion");
                    break;
                }
                flujoSalida.flush();
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            sc.close();

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}