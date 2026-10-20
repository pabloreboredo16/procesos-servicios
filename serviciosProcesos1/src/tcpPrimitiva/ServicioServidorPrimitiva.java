package tcpPrimitiva;

import java.util.Arrays;

public class ServicioServidorPrimitiva {

    private static int[] arrayServidor = new int[6];
    private static int complementarioServidor;
    private static int reintegroServidor;

    public static void rellenarArrayServidor() {
        for (int i = 0; i < arrayServidor.length; i++) {
            arrayServidor[i] = (int) (Math.random() * 49) + 1;
        }
        complementarioServidor = (int) (Math.random() * 49) + 1;
        reintegroServidor = (int) (Math.random() * 10);
    }

    public static int coincidencias(int[] arrayCliente) {
        int contadorIguales = 0;
        for (int i = 0; i < arrayServidor.length; i++) {
            for (int j = 0; j < arrayCliente.length; j++) {
                if (arrayServidor[i] == arrayCliente[j]) {
                    contadorIguales++;
                    break;
                }
            }
        }
        return contadorIguales;
    }

    public static boolean coincidenReintegro(int reintegro) {
        if (reintegro == reintegroServidor) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean coincidenciaComplementario(int complementarioCliente) {
        if (complementarioServidor == complementarioCliente) {
            return true;
        } else {
            return false;
        }
    }

    public static String devolucionAlCliente(int[] arrayCliente, int complementarioCliente, int reintegroCliente) {
        int numCoincidencias = coincidencias(arrayCliente);
        boolean esReintegro = coincidenReintegro(reintegroCliente);
        boolean esComplementario = coincidenciaComplementario(complementarioCliente);

        if (numCoincidencias == 6 && esReintegro) {
            String categoriaEspecial = "Has acertado los 6 numeros mas el reintegro eres el ganador de la categoria especial";
            return categoriaEspecial;
        } else if (numCoincidencias == 6) {
            String primeraCategoria = "Has acertado los seis numeros eres el ganador del primer premio";
            return primeraCategoria;
        } else if (numCoincidencias == 5 && esComplementario) {
            String segundaCategoria = "Has acertado cinco numeros mas el complementario eres el ganador del segundo premio";
            return segundaCategoria;
        } else if (numCoincidencias == 5) {
            String terceraCategoria = "Has acertado cinco numeros eres el ganador del tercer premio";
            return terceraCategoria;
        } else if (numCoincidencias == 4) {
            String cuartaCategoria = "Has acertado 4 numeros eres el ganador del cuarto premio";
            return cuartaCategoria;
        } else if (numCoincidencias == 3) {
            String quintaCategoria = "Has acertado 3 numeros eres el ganador del quinto premio";
            return quintaCategoria;
        } else if (esReintegro) {
            String coincidenciaReintegro = "Has acertado el numero del reintegro se te devolvera el importe de la operacion";
            return coincidenciaReintegro;
        } else {
            String sinPremio = "No has ganado nada lo siento";
            return sinPremio;
        }
    }

    public static void mostrarArrayServidor() {
        System.out.println("Array servidor: " + Arrays.toString(arrayServidor));
        System.out.println("Complementario servidor: " + complementarioServidor);
        System.out.println("Reintegro servidor: " + reintegroServidor);
    }
}