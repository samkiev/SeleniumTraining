package utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Scanner;

public class ApiDataGetter {

    private final String auth;

    private ApiDataGetter() {
        this("admin", "ab4f051e680095cb9a5d655dc48aa15c");
    }

    public ApiDataGetter(@NotNull String user, @NotNull String token) {
        auth = new String(Base64.getEncoder().encode((user + ":" + token).getBytes()));
    }

    public LocalDateTime getApiBuildDate(@NotNull String projectName, @NotNull String buildVersion) {

        String url = "http://seltr-kbp1-1.synapse.com:8080/job/" + projectName + "/" + buildVersion + "/";
        JSONObject jo = getPageApi(url);
        long timestamp = (long) (jo.get("timestamp")) / 1000;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    private JSONObject request(String link) {
        HttpURLConnection conn = null;
        String res = "";
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + auth);
            conn.connect();

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                throw new Error("Page not Found");
            }
                try (Scanner in = new Scanner(conn.getInputStream())) {
                while (in.hasNextLine()) {
                    res += in.nextLine();
                }
            }
        }
        catch (IOException e) {
            // TODO handle exception
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return new JSONObject(res);
    }

    public static ApiDataGetter getAPUsingDefaultCredentials() {
        return new ApiDataGetter();
    }

    private JSONObject getPageApi(String url) {
        return request(url + "api/json?pretty=true");
    }
}
