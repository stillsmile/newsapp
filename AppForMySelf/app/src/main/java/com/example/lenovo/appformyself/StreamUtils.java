package com.example.lenovo.appformyself;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2018/5/14.
 */

public class StreamUtils {

    public static String streamToString(InputStream in) {
        String result = "";

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int lenght = 0;
            while ((lenght = in.read(buffer)) != -1) {
                out.write(buffer, 0, lenght);
                out.flush();
            }
            result = out.toString();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
