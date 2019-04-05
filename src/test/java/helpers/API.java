package helpers;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class API {

    public void userAg() throws IOException {
        URL url = new URL("http://app.irontrade.com/harakiri");
        URLConnection hc = url.openConnection();
        hc.setRequestProperty("User-Agent", "os: Android, user_key: jsdffjl");

        System.out.println(hc.getContentType());
    }

    public String serverRequest(String Url) {
        String serverResponse = null;
        try (Scanner response = new java.util.Scanner(new URL(Url).openStream())) {
            System.out.println(response.useDelimiter("\\A").next());
            serverResponse = response.useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
    @Test
    public void connect() throws IOException {
        String url = "http://baza.irontrade-sg.local/baza/ru/api/user-codes?email=testmighty22@gmail.com";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        //connection.setRequestProperty("User-Agent", "os: Android, user_key: jsdffjl");
        //connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        //return serverResponse;
    }

}
