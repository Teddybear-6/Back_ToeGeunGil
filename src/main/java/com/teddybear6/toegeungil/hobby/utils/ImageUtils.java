package com.teddybear6.toegeungil.hobby.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class ImageUtils {

    public static byte[] compressImage(byte[] date){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(date);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(date.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()){
            int size = deflater.deflate(tmp);
            outputStream.write(tmp,0,size);
        }
        try {
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return outputStream.toByteArray();

    }
}
