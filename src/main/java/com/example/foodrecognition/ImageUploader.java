package com.example.foodrecognition;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUploader {

    public static void main(String args)   {

        try {
            // Chuỗi bạn muốn gửi
            String postData = "C:\\Users\\khain\\.vsStudio\\src\\vscode\\FastApi\\images\\banhxeo.jpg";

            // URL của API FastAPI để nhận chuỗi
            String fastApiUrl = "http://127.0.0.1:8000/upload"; // Thay thế bằng đường dẫn tới endpoint của bạn

            // Tạo URL object
            URL url = new URL(fastApiUrl);

            // Tạo HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Đặt phương thức yêu cầu là POST
            connection.setRequestMethod("POST");

            // Kích thước của dữ liệu bạn đang gửi (ở đây là độ dài của chuỗi)
            connection.setDoOutput(true);

            // Gửi dữ liệu
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Lấy mã trạng thái từ phản hồi
            int responseCode = connection.getResponseCode();
            System.out.println("Status code: " + responseCode);

            // Đọc phản hồi từ server
            // ...

            // Đóng kết nối
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
