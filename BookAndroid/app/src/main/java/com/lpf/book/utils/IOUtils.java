package com.lpf.book.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    public static void write(InputStream in, File outPath) throws RuntimeException {
        try {
            OutputStream out = new FileOutputStream(outPath);
            byte[] bytes = new byte[1024 * 1024 * 10];
            int len;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
                out.flush();
            }
            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
