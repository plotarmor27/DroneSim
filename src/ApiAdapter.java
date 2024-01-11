//package de.frauas.muellerbady.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ApiAdapter {

    private static final String USER_AGENT = "Group 26";
    private static String URL = "https://dronesim.facets-labs.com/api/";
    private static String JSON_FORMAT = "/?format=json";
    private static String drones = "drones";
    private static int offset = 0;
    private static String QUERY = "&limit=10&offset=";
    private static final String TOKEN = "Token 1bbbbd05efe3c733efcf8f443582a09cac4ca02c";

    private static JSONObject jsonResponse;



    public static JSONArray apiReader(String category) {

        URL url;
        try {
            url = new URL(URL + category + JSON_FORMAT + QUERY + String.valueOf(offset));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            //int responseCode = connection.getResponseCode();
            //System.out.println("Response Code " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONTokener tokener = new JSONTokener(in);
            jsonResponse = new JSONObject(tokener);
            System.out.println(jsonResponse.toString());
            in.close();
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("General IO Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return jsonResponse.getJSONArray("results");
    }



    public static String formatJson(String input) {
        final int indentSpaces = 4;
        Object json = new JSONTokener(input).nextValue();

        if (json instanceof JSONObject) {
            JSONObject o = (JSONObject) json;

            return o.toString(indentSpaces);
        } else if (json instanceof JSONArray) {
            return ((JSONArray) json).toString(indentSpaces);
        } else {
            throw new IllegalArgumentException("Input string is not a valid JSON");
        }
    }


}
