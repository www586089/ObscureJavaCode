package com.zf.obscurecode.nw.reader;

import com.zf.obscurecode.nw.utils.ObscureUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CodeReader {
    private File file = null;
    public CodeReader(File file) {
        this.file = file;
    }

    public List<String>  readCode() {
        List<String> codeLines = new ArrayList<>();
        FileInputStream inputStream = null;
        InputStreamReader streamReader = null; // 创建InputStreamReader对象接收文件流
        BufferedReader bufferedReader = null; // 创建reader缓冲区将文件流装进去
        try {
            inputStream = new FileInputStream(file);
            streamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(streamReader);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                codeLines.add(lineTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ObscureUtil.close(bufferedReader);
            ObscureUtil.close(streamReader);
            ObscureUtil.close(inputStream);
        }

        return codeLines;
    }

}
