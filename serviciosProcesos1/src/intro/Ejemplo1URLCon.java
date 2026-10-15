package intro;

import java.net.*;
import java.io.*;

public class Ejemplo1URLCon {
    public static void main(String[] args) {
        URL url = null;
        // Obtendremos una instancia de un objeto Connection
        URLConnection urlCon = null;
        try {
            url = new URL("https://www.cebem.es/es/");
            // Referencia a Connection
            urlCon = url.openConnection();

            BufferedReader br;
            // Manjeja el objeto Connection para obtener el InputStream
            InputStream inputStream = urlCon.getInputStream();
            br = new BufferedReader(new 
                    InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = br.readLine()) != null)
                System.out.println(inputLine);

            br.close();

        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    } 
} 