package com.zf.obscurecode.nw;

import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.generator.CodeGenerator;
import com.zf.obscurecode.nw.parser.CodeParser;
import com.zf.obscurecode.nw.reader.CodeReader;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.utils.ObscureUtil;
import com.zf.obscurecode.nw.writer.CodeWriter;

import java.io.File;

public class StrongObscureMain {

    private static StrongObscureMain instance = null;
    private static boolean countJavaFileCount = false;
    private static String[] filePath = {
            "D:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\FileManager\\app\\src\\main\\java\\com\\amaze\\filemanager\\asynchronous\\asynctasks\\PrepareCopyTask.java"
    };
    public static void main(String[] args) {
        instance = new StrongObscureMain(filePath);

        if (countJavaFileCount) {
            instance.countJavaFile(instance.pathArray);
        } else {
            instance.obscureStart(instance.pathArray);
        }
    }

    private String[] pathArray = null;
    private StrongObscureMain(String[] pathArray) {
        this.pathArray = pathArray;
    }

    private void obscureStart(String[] pathArray) {
        for (String filePath : pathArray) {
            if (filePath.endsWith(Constant.keyFileJava)) {
                obscureCode(new File(filePath));
            } else {
                File[] fileList = new File(filePath).listFiles();
                if (null != fileList && fileList.length > 0) {
                    for (File itemFile : fileList) {
                        obscureCode(itemFile);
                    }
                }
            }
        }
    }


    private void obscureCode(File file) {
        if (file.isFile() && file.getPath().endsWith(Constant.keyFileJava)) {
            CodeReader codeReader = new CodeReader(file);
            CodeParser codeParser = new CodeParser(file, codeReader.readCode());
            SourceCode sourceCode = codeParser.parse();
            CodeGenerator codeGenerator = new CodeGenerator(sourceCode);
            CodeWriter codeWriter = new CodeWriter(sourceCode);
            codeWriter.writeCode(file, codeGenerator);
        } else if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (null != fileList && fileList.length > 0) {
                for (File itemFile : fileList) {
                    obscureCode(itemFile);
                }
            }
        }
    }


    private void countJavaFile(String[] pathArray) {
        if (null != pathArray && pathArray.length > 0) {
            int javaFileCount = 0;
            for (String filePath : pathArray) {
                if (filePath.endsWith(Constant.keyFileJava)) {
                    javaFileCount += ObscureUtil.doCountJavaFile(new File(filePath));
                } else {
                    File[] fileList = new File(filePath).listFiles();
                    if (null != fileList && fileList.length > 0) {
                        for (File itemFile : fileList) {
                            javaFileCount += ObscureUtil.doCountJavaFile(itemFile);
                        }
                    }
                }
            }

            System.out.println("countJavaFile, javaFileCount = " + javaFileCount);
        }
    }
}
