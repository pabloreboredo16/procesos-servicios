package intro;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ejemplo2URLCon {
    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost/vernombre/vernombre.php");
            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true);

            // String cadena = "nombre=Pablo&apellidos=Reboredo Fernández";
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce los apellidos: ");
            String apellidos = sc.nextLine();

            String cadena = "nombre=" + nombre + "&apellidos=" + apellidos;
            System.out.println("Cadena por output hacia servidor: " + cadena);

            // ESCRIBIR EN LA URL
            PrintWriter output = new PrintWriter
                    (conexion.getOutputStream());
            output.write(cadena);
            output.close(); // cerrar flujo

            // LEER DE LA URL
            BufferedReader br = new BufferedReader
                    (new InputStreamReader(conexion.getInputStream()));
            String linea;
            System.out.println("Cadena por input hacia servidor: ");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close(); // cerrar flujo

        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    } 
} 