package defalt.kze.kzeweathermax;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {
    private static final String cheieAPI = "179bdb044a9275b22776e0150670a1d9";
    private static final String urlVreme = "https://api.openweathermap.org/data/2.5/weather";

    private static final String urlGeolocatia = "http://api.openweathermap.org/geo/1.0/direct";

    public String getLocatie(String oras) throws Exception {
        String cereGeolocatia = urlGeolocatia + "?q=" + oras + "&appid=" + cheieAPI;

        URL url = new URL(cereGeolocatia);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Scanner scan =new Scanner(url.openStream());
        StringBuilder raspuns = new StringBuilder();
        while(scan.hasNext()){
            raspuns.append(scan.nextLine());
        }
        scan.close();

        return parseGeolocatie(raspuns.toString());
    }
    public static String parseGeolocatie(String raspuns) throws Exception {

        try {
            JSONParser parser = new JSONParser();

            // Parsează răspunsul ca un JSONArray (array de obiecte JSON)
            JSONArray jsonArray = (JSONArray) parser.parse(raspuns);

            // Accesăm primul obiect din array (dacă există)
            JSONObject jsonObiect = (JSONObject) jsonArray.get(0);

            // Extragem câmpurile dorite
            String name = (String) jsonObiect.get("name");

            // Latitudine și longitudine sunt de tip double, deci trebuie să le accesăm ca atare
            double lat = (double) jsonObiect.get("lat");
            double lon = (double) jsonObiect.get("lon");

            // Returnează un String formatat
            return getVreme(lat, lon);
        } catch (Exception e) {

            return "Eroare, nu ai scris un oras!";
        }
    }

    public static String getVreme(double lat, double lon) throws Exception {

        String cereVremea =urlVreme + "?lat=" + lat + "&lon=" + lon + "&appid=" + cheieAPI;
        URL url=new URL(cereVremea);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Scanner scan =new Scanner(url.openStream());
        StringBuilder raspuns = new StringBuilder();
        while(scan.hasNext()){
            raspuns.append(scan.nextLine());
        }
        scan.close();

        return parseVreme(raspuns.toString());


    }
    public static String parseVreme(String raspuns) throws Exception {
        JSONParser parser = new JSONParser();

        // Parsează răspunsul ca un JSONArray (array de obiecte JSON)
        JSONObject jsonObiect = (JSONObject) parser.parse(raspuns);

        JSONObject mainData = (JSONObject) jsonObiect.get("main");

        JSONArray sideData = (JSONArray) jsonObiect.get("weather");
        JSONObject weatherObject = (JSONObject) sideData.get(0);

        JSONObject country = (JSONObject) jsonObiect.get("sys");

        double temp = (double) mainData.get("temp");
        String desc = (String) weatherObject.get("description");
        String tara= (String) country.get("country");
        temp = temp-273.15;
        temp=Math.round(temp);


        String tempAsString = "Tara: "+tara+"\n"+" Temperature: "+Double.toString(temp) + " Grade " + "\n" + "Description: "+desc;






        return tempAsString;
    }




}

