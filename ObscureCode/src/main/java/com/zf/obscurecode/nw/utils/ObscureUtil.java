package com.zf.obscurecode.nw.utils;

import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.source.CodeStructure;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;

public class ObscureUtil {

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void flush(Flushable flushable) {
        if (null != flushable) {
            try {
                flushable.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getPrefix(CodeStructure structure, int flag) {
        String spaceStr = "";
        int deep = structure.getDeep();
        if (0 == flag) {
            for (int i = 0; i < deep; i++) {
                spaceStr += "    ";
            }
        } else {
            for (int i = 0; i < deep - 1; i++) {
                spaceStr += "    ";
            }
        }


        return spaceStr;
    }


    public static int doCountJavaFile(File file) {
        int javaFileCount = 0;
        String filePath = file.getPath();
        if (file.isFile()) {
            if (filePath.endsWith(Constant.keyFileJava)) {
                javaFileCount = 1;
            }
        } else {
            File[] fileList = file.listFiles();
            if (null != fileList && fileList.length > 0) {
                for (File tmpFile: fileList) {
                    javaFileCount += doCountJavaFile(tmpFile);
                }
            }
        }

        return javaFileCount;
    }
}