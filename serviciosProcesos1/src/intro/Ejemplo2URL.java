package intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo2URL {

    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://www.cebem.es/es/");
        } catch (MalformedURLException e) { e.printStackTrace(); }

        BufferedReader br;
        try {
            InputStream inputStream = url.openStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            while ((inputLine = br.readLine()) != null)
                System.out.println(inputLine);
            br.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}