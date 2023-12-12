package com.example.foodrecognition;

import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ImageUploader {

    /* URL fast api */
    private static final String fastApiUrl = "http://127.0.0.1:8000/process_string";
    public String uploadOnFastApi(String filePath)   {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(fastApiUrl);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            // send information
            //send image
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = filePath.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }

            try (OutputStream os = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(os, true, StandardCharsets.UTF_8)) {
                writer.println(filePath);
            }

            // send respone
            int responseCode = connection.getResponseCode();
            String resultRespone =("Status code: " + responseCode);

            return resultRespone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            connection.disconnect();;
        }
    }

}
