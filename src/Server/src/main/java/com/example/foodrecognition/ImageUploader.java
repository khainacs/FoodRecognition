package com.example.foodrecognition;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
@Service
public class ImageUploader {
    private static final String fastApiUrl = "http://127.0.0.1:8000/upload";
    public String uploadOnFastApi(String filePath) {
        try {
            Gson gson = new Gson();
            String payload = gson.toJson(filePath);
            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post(fastApiUrl);


            String resultRespone =("Status code: " + response.asString());
            System.out.println(resultRespone);
            return resultRespone;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //connection.disconnect();;
        }
        return null;
    }

}