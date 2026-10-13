package tcpPerfectos;

public class ServicioPerfectos {

    public String esNumeroPerfecto(int numero) {
        if (numero <= 0) {
            return "El número debe ser un entero positivo.";
        }

        int sumaDivisores = 0;
        for (int i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                sumaDivisores += i;
            }
        }

        if (sumaDivisores == numero) {
            return "El " + numero + " SI es un número perfecto.";
        } else {
            return "El " + numero + " NO es un número perfecto.";
        }
    }
}