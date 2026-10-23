package tcpPrimitiva;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class ServicioCliente {

    public static int pedirEnteroPositivo(Scanner sc) {
        int numero;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("Error: Debes introducir un número entero válido.");
                sc.next();
            }
            numero = sc.nextInt();
            if ((numero < 1 || numero > 49)) {
                System.out.println("Error: El número debe estar comprendido entre 1 y 49. Inténtalo de nuevo.");
            }
        } while ((numero < 1 || numero > 49));
    return numero;
}

public static boolean tieneDuplicados(int[] numeros) {
    Set<Integer> set = new HashSet<>();

    for (int numero : numeros) {
        if (!set.add(numero)) {
              System.out.println("ERROR: no puede haber números duplicados");
            return true; 
          
        }
    }
    return false; 
}

public static int generarReintegro() {
    int numero = (int)  (Math.random() * 10 );
    return numero; 
}    
}