package tcpPrimitiva;

import java.util.Scanner;

public class ServicioClientePrimitiva {

    public static int pedirEnteroPositivo(Scanner sc) {
        int numero;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Error: Debes introducir un número entero válido.");
                sc.next();
            }
            numero = sc.nextInt();
            if ((numero < 1 || numero > 49) && numero != 0) {
                System.out.println("Error: El número debe estar comprendido entre 1 y 49. Inténtalo de nuevo.");
            }
        } while ((numero < 1 || numero > 49) && numero != 0);
        return numero;
    }

    public static int pedirReintegro(Scanner sc) {
        int numero;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Error: Debes introducir un número entero válido.");
                sc.next();
            }
            numero = sc.nextInt();
            if (numero < 0 || numero > 9) {
                System.out.println("Error: El número debe estar comprendido entre 0 y 9. Inténtalo de nuevo.");
            }
        } while (numero < 0 || numero > 9);
        return numero;
    }
}