package uom.mosip.attendanceservice.services;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HttpService {
    private final OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public HttpService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }


    public void post(String url, String requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(requestBody, HttpService.JSON))
                .build();
    }

    public void sendRequest(Request request) {
        try (Response response = client.newCall(request).execute()) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
