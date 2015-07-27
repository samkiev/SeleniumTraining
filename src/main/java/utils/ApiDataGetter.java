package utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
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
        long timestamp = (jo.getLong("timestamp")) / 1000;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    @NotNull
    private JSONObject request(String link) throws RuntimeException {
        HttpURLConnection conn = null;
        String res = "";
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + auth);
            conn.connect();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (Scanner in = new Scanner(conn.getInputStream())) {
                    while (in.hasNextLine()) {
                        res += in.nextLine();
                    }
                }
            }
            else {
                throw new RuntimeException(String.format("[%d] - %s", conn.getResponseCode(), conn.getResponseMessage()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return res.isEmpty() ? new JSONObject() : new JSONObject(res);
    }

    public JSONObject getMainPageInfo() {
        return getPageApi("http://seltr-kbp1-1.synapse.com:8080/");
    }

    @NotNull
    public static ApiDataGetter getAPUsingDefaultCredentials() {
        return new ApiDataGetter();
    }

    @NotNull
    public JSONObject getPageApi(String url) {
        return request(url + "api/json?pretty=true");
    }

    public JSONObject getProjectPageInfo(String existedProjectName) {
        return getPageApi("http://seltr-kbp1-1.synapse.com:8080/job/" + StringGenerator.encode(existedProjectName)+ "/");
    }
}
