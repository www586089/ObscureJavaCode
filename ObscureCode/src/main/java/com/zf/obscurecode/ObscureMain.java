package com.zf.obscurecode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.zf.obscurecode.Structure.CLASS;
import static com.zf.obscurecode.Structure.METHOD;

public class ObscureMain {

    //绝对路径
    static String filePath = "D:/Android/workspace/JavaTextDemo/ObscureCode/src/main/java/com/zf/obscurecode/Router.java";
    static List<String> lines = new ArrayList<>();
    static List<Structure> matchingStructure = new ArrayList<>();
    static List<Structure> unMatchingStructure = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {
        showDirectory(new File(filePath));
        System.exit(0);
    }

    public static void showDirectory(File file) {
        if (file.isFile()) {
            try {
                doRepart(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        File[] files = file.listFiles();
        for (File a : files) {
            if (a.isDirectory()) {
                showDirectory(a);
            } else {
                try {
                    doRepart(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //进入类
    static int inClass = 0;

    //重复代码
    public static void doRepart(File file) throws IOException {

//        if (!file.getName().equals("AboutActivity.java")) {
//            return;
//        }
        inClass += 1;
        lines.clear();
        matchingStructure.clear();
        unMatchingStructure.clear();
        System.out.println(file.getName());

        try {
            readLines(file);
            initStauct();
            writeToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static void initStauct() {
        Structure structure;
        String lineTxt;
        for (int i = 0; i < lines.size(); i++) {
            lineTxt = lines.get(i);
            //添加未匹配对象
            if (lineTxt.contains("{")) {
                count += 1;
                if (count == 2) {
                    if (unMatchingStructure.size() == 0)
                        structure = new Structure(CLASS);
                    else
                        structure = new Structure(METHOD);
                    structure.setLineStart(lineTxt, i);
                    unMatchingStructure.add(structure);
                }
            }
            if (lineTxt.contains("}")) {
                if (count == 2) {
                    structure = unMatchingStructure.get(0);
                    structure.setLineEnd(lineTxt, i);
                    matchingStructure.add(structure);
                    unMatchingStructure.remove(structure);
                }
                count -= 1;
            }

        }

        System.out.println("方法体总个数为=" + matchingStructure.size()); // 逐行输出文件内容
    }

    private static void readLines(File file) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null; // 创建InputStreamReader对象接收文件流
        BufferedReader br = null; // 创建reader缓冲区将文件流装进去
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String lineTxt = null;
            // 从缓冲区中逐行读取代码，调用readLine()方法
            while ((lineTxt = br.readLine()) != null) {
                lines.add(lineTxt);
            }
        } finally {
            // 文件执行完毕别忘了关闭数据流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static List<String> codeList = new ArrayList<>();
    static int vertifyNumber;

    private static void initCodeList() {
        vertifyNumber = matchingStructure.size() / 2 + matchingStructure.size() % 2;
        char x = 'a';
        for (int i = 0; i < vertifyNumber; i++) {
            codeList.add("private  int " + x + x + x + x + ";");
            x += 1;
        }
        x = 'a';
        for (int i = 0; i < vertifyNumber; i++) {
            String verticalNam = codeList.get(i);
            codeList.add("  public int get" + String.valueOf(x).toUpperCase() + x + x + x + x + "() {\n" +
                    "        return " + x + x + x + x + ";\n" +
                    "    }");
            codeList.add(" public void set" + String.valueOf(x).toUpperCase() + x + x + x + x + "(int " + x + x + x + x + ") {\n" +
                    "        this." + x + x + x + x + " = " + x + x + x + x + ";\n" +
                    "    }");
            x += 1;
        }
    }


    private static void writeToFile(File file) throws FileNotFoundException {
        initCodeList();
        String path = file.getPath();
        path = path.substring(0, path.lastIndexOf("\\"));
        String name = file.getName();
        // 临时文件
        File outFile = new File(path + "/Temp" + name);
        // 输出
        FileOutputStream fos = new FileOutputStream(outFile);
        PrintWriter out = new PrintWriter(fos);
        int count = 0;
        int writeInde = 0;
        Structure structure = matchingStructure.get(count);
        String line;
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            if (structure.indexLinesStart == i) {
                //第一个方法前面加入变量。
                if (count == 0) {
                    for (int j = 0; j < vertifyNumber; j++)
                        out.println(codeList.get(j));
                }
                //写入
                out.println(line);
                continue;
            }

            if (structure.indexLinesEnd == i) {

                //写入
                out.println(line);
                out.println(codeList.get(vertifyNumber + writeInde));
                writeInde += 1;
                count += 1;
                if (matchingStructure.size() > count)
                    structure = matchingStructure.get(count);
            } else out.println(line);
        }


        try {
            out.flush();
        } catch (Exception e) {
            System.out.println("保存异常"); // 逐行输出文件内容
            return;
        }
        try {
            out.close();
        } catch (Exception e) {
            System.out.println("关闭异常"); // 逐行输出文件内容
        }

    }

}
