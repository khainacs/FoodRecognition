package com.plcoding.CaptureImage_UploadImageOnServer;

import org.junit.Test;

public class UtilsTest {


//    @Test
//    public void yamlExist(){
//        File inputFile = new File("java/com/plcoding/CaptureImage_UploadImageOnServer/asserts/config/networkingURL.yaml");
//        assertEquals(true, inputFile.exists());
//    }
//
    @Test
    public void readeUrlServer()  {
        Utils utils = new Utils();
        String str = utils.readeUrlServer();
        System.out.println(str);
    }
}