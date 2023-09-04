package com.teddybear6.toegeungil.hobby.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    //deflate 특정한 파일을 압축
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


   //Inflater 압축된걸 해제
    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try{
            while (!inflater.finished()){
                int count = inflater.inflate(tmp);
                outputStream.write(tmp,0,count);
            }
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
