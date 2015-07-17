package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

public class ApiDataGetter {

    public static LocalDateTime getApiBuildDate(String projectName, String buildVersion) throws JSONException, IOException {

        JSONObject jo = getPageApi("http://seltr-kbp1-1.synapse.com:8080/job/" + projectName + "/" + buildVersion + "/");
        long timestamp = (long) (jo.get("timestamp")) / 1000;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    public static JSONObject request(String link) throws IOException {
        String username = "admin";
        String password = "ab4f051e680095cb9a5d655dc48aa15c";

        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String enterData = new String(Base64.getEncoder().encode((username + ":" + password).getBytes()));
            connection.setRequestProperty("Authorization", "Basic " + enterData);
            connection.getContentEncoding();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String message = null;
            while ((message = reader.readLine()) != null) {
                stringBuilder.append(message).append("\n");
            }
            return new JSONObject(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static JSONObject getPageApi(String url) throws JSONException, IOException {
        return new JSONObject(request(url + "api/json?pretty=true").toString());
    }

    public static void main(String[] args) throws Exception {
        JSONObject jo = getPageApi("http://seltr-kbp1-1.synapse.com:8080/job/Selenium%20Training/2/");

        long timestamp = (long) (jo.get("timestamp"));
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());

        System.out.println(date);
    }
}
