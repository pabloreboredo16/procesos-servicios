package tcpPrimitiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ServicioPrimitiva {

    private final List<Integer> primitivaGanadora;

    public ServicioPrimitiva() {
        primitivaGanadora = generarCombinacion();
    }

    private List<Integer> generarCombinacion() {
        List<Integer> numeros = new ArrayList<>();
        Random rand = new Random();
        while (numeros.size() < 6) {
            int num = rand.nextInt(49) + 1;
            if (!numeros.contains(num)) {
                numeros.add(num);
            }
        }
        Collections.sort(numeros);
        return numeros;
    }

    public String comprobarApuesta(List<Integer> primitivaCliente) {
        int aciertos = 0;
        List<Integer> acertados = new ArrayList<>();

        for (int num : primitivaCliente) {
            if (primitivaGanadora.contains(num)) {
                aciertos++;
                acertados.add(num);
            }
        }

        return "Combinación ganadora: " + primitivaGanadora + 
               "\nTu apuesta: " + primitivaCliente + 
               "\nHas tenido " + aciertos + " acierto(s): " + acertados;
    }
}