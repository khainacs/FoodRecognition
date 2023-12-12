package com.example.foodrecognition;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.unregisterParser;

public class Test {
    private static final String fastApiUrl = "http://127.0.0.1:8000/process_string";

    public static void main(String[] args) {
        uploadOnFastApi("IMG_20231212_224718.jpg");
    }
    public static void uploadOnFastApi(String filePath) {
//        HttpURLConnection connection = null;
        try {
//            URL url = new URL(fastApiUrl);
//
//            connection = (HttpURLConnection) url.openConnection();
//
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);

            // send information
            //send image
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = filePath.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }
            String payload = "{\n" +
                    "    \"Name Image\": \""+filePath+"\"\n" +
//                    "    \"Quantity\": 5,\n" +
//                    "    \"Price\": \"20\"\n" +
                    "}";
            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post(fastApiUrl);

            // send respone
            //int responseCode = connection.getResponseCode();

            //String resultRespone =("Status code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //connection.disconnect();;
        }

    }
}
