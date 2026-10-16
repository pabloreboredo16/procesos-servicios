package tcpPrimitiva;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
// import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientePrimitiva {
    public static void main(String[] args) throws IOException {

        try {
            String host = "localhost";
            int port = 6000;

            Socket cliente = new Socket(host, port); 

            /*
            System.out.println("Detalles de conexion TCP");
            InetAddress i = cliente.getInetAddress();
            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + cliente.getPort());
            System.out.println("Host Remoto: " + i.getHostName());
            System.out.println("IP Host Remoto: " + i.getHostAddress());
            */

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

            Scanner sc = new Scanner(System.in);
            boolean sigue = true;

            while (sigue) {
                System.out.println("\nNUEVA APUESTA DE LA PRIMITIVA");
                System.out.println("Introduce 6 números distintos entre 1 y 49:");

                ArrayList<Integer> apuesta = new ArrayList<>();
                boolean cancelar = false;

                while (apuesta.size() < 6) {
                    System.out.print("Número " + (apuesta.size() + 1) + ": ");
                    try {
                        int num = Integer.parseInt(sc.nextLine());

                        if (num == 0) {
                            cancelar = true;
                            break;
                        }

                        if (num < 1 || num > 49) {
                            System.out.println("El número debe estar entre 1 y 49.");
                        } else if (apuesta.contains(num)) {
                            System.out.println("No puedes repetir números en la misma apuesta.");
                        } else {
                            apuesta.add(num);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Introduce un número entero válido.");
                    }
                }

                if (cancelar) {
                    // Enviar un 0 inicial para notificar la salida al servidor
                    flujoSalida.writeInt(0);
                    flujoSalida.flush();
                    sigue = false;
                } else {
                    // Enviar un indicador de transmisión activa (1)
                    flujoSalida.writeInt(1);
                    
                    // Enviar los 6 números del ArrayList
                    for (int n : apuesta) {
                        flujoSalida.writeInt(n);
                    }
                    flujoSalida.flush();

                    // Leer la respuesta del servidor
                    System.out.println("\nRESPUESTA DEL SERVIDOR");
                    System.out.println(flujoEntrada.readUTF());
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}