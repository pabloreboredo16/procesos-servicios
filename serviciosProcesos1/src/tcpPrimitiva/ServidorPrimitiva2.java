package tcpPrimitiva;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServidorPrimitiva2 {
    public static void main(String[] args) {

        int puerto = 6000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + servidor.getLocalPort());

            Socket cliente1 = servidor.accept();
            System.out.println("Cliente conectado: " + cliente1.getInetAddress());

            DataInputStream flujoEntrada = new DataInputStream(cliente1.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente1.getOutputStream());

            while (true) {
                ServicioServidorPrimitiva2.rellenarArrayServidor();
                ServicioServidorPrimitiva2.mostrarArrayServidor();
                int tamañoArray = flujoEntrada.readInt();
                int[] numeros = new int[tamañoArray];
                
                for (int i = 0; i < tamañoArray; i++) {
                    numeros[i] = flujoEntrada.readInt();
                }
                System.out.println("Los numeros del cliente son: ");
                System.out.println(Arrays.toString(numeros));

                int numeroReintegro = flujoEntrada.readInt();
                System.out.println("El numero de reintegro del cliente es: " + numeroReintegro);

                int numeroComplementario = flujoEntrada.readInt();
                System.out.println("El numero complementario del cliente es: " + numeroComplementario);
                String resultado = ServicioServidorPrimitiva2.devolucionAlCliente(numeros, numeroComplementario,
                        numeroReintegro);
                flujoSalida.writeUTF(resultado);
                flujoSalida.flush();

                int decision = flujoEntrada.readInt();

                if (decision == 0) {
                    System.out.println("Cliente desconectado.");
                    break;
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente1.close();
            servidor.close();

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}