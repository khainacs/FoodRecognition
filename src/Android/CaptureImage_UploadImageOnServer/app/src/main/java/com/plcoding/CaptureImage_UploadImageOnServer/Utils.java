package com.plcoding.CaptureImage_UploadImageOnServer;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils extends AppCompatActivity {
    private static String URLonServer;
    private String getUrlFile = "networkingURL.yaml";

    public String readeUrlServer() {
//        FileInputStream fileInputStream = null;
        try{
            InputStream iS = getAssets().open(getUrlFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            return URLonServer = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
            return "Failed";
        }finally {
//            if (fileInputStream != null) {
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return "Upload Failed";
//            }

        }
    }
}
