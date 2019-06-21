package com.zf.obscurecode;

import com.zf.obscurecode.nw.sample.CodeSample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.zf.obscurecode.Structure.CLASS;
import static com.zf.obscurecode.Structure.METHOD;

public class ObscureMain2 {

    private static char[] radomAlpha = null;
    private static char[] radomDigit = null;
    private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static String digit = "0123456789";
    private static boolean isDebug = false;
    //绝对路径
    static String filePath = "D:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\FileManager\\app\\src\\main\\java";
//    static String filePath = "D:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\MoneyManager\\app\\src\\main\\java\\com\\tct\\moneykeeper\\ui\\setting\\SettingActivity.java";
    static List<String> lines = new ArrayList<>();
    static List<Structure> matchingStructure = new ArrayList<>();
    static Map<Integer, Structure> hasMap = new HashMap<>();
    static List<Structure> unMatchingStructure = new ArrayList<>();
    static int count = 0;
    static int javaFileCount = 0;

    public static void main(String[] args) {
//        init();
//        showDirectory(new File(filePath));
        countJavaFile(new File(filePath));
        System.exit(0);
    }

    private static void countJavaFile(File file) {
        if (null != file) {
            doCountJavaFile(file);
            System.out.println("countJavaFile, javaFileCount = " + javaFileCount);
        }
    }

    private static void doCountJavaFile(File file) {
        String filePath = file.getPath();
        if (file.isFile() && filePath.endsWith(".java")) {
            javaFileCount++;
        } else {
            File[] fileList = file.listFiles();
            if (null != fileList && fileList.length > 0) {
                for (File tmpFile : fileList) {
                    doCountJavaFile(tmpFile);
                }
            }
        }
    }

    private static void init() {
        radomAlpha = new char[alpha.length()];
        radomDigit = new char[digit.length()];
        int i = 0;
        System.out.print("{");
        for (char ch: alpha.toCharArray()) {
            radomAlpha[i++] = ch;
            System.out.print( "\""+ ch + "\", ");
        }
        System.out.println("}");
        i = 0;
        System.out.print("{");
        for (char ch: digit.toCharArray()) {
            radomDigit[i++] = ch;
            System.out.print( "\""+ ch + "\", ");
        }
        System.out.println("}");
    }

    public static void showDirectory(File file) {
        System.out.println("process file " + file.getPath());
        if (file.isFile()) {
            try {
                if (file.getPath().endsWith(".java")) {
                    doRepart(file);
                }

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
                    if (a.getPath().endsWith(".java")) {
                        doRepart(a);
                    }
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
        codeList.clear();
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
        boolean isCommentStart = false;
        for (int i = 0; i < lines.size(); i++) {
            lineTxt = lines.get(i);
            if (lineTxt.startsWith("/*")) {
                isCommentStart = true;
            }
            if (isCommentStart && lineTxt.endsWith("*/")) {
                isCommentStart = false;
            }
            if (isCommentStart) {
                continue;
            }
            //添加未匹配对象
            if (lineTxt.contains("{")) {
                count += 1;
                if (lineTxt.contains("class") || lineTxt.contains("interface")) {
                    structure = new Structure(CLASS);
                } else {
                    structure = new Structure(METHOD);
                }
                structure.deep = count;
                structure.setLineStart(lineTxt, i);
                unMatchingStructure.add(structure);
            }


//            if (!lineTxt.startsWith("//") && !lineTxt.startsWith("/*") && lineTxt.contains("{")) {
//                count += 1;
//                if (lineTxt.contains("class") || lineTxt.contains("->")) {
//                    structure = new Structure(CLASS);
//                } else {
//                    structure = new Structure(METHOD);
//                }
//                structure.deep = count;
//                structure.setLineStart(lineTxt, i);
//                unMatchingStructure.add(structure);
//            }
//            if ((lineTxt.contains("(") || lineTxt.contains("->")) && lineTxt.contains("{")) {//代码快，lamb for while
//                count += 1;
//                structure = new Structure(BLOCK);
//                structure.deep = count;
//                structure.setLineStart(lineTxt, i);
//                unMatchingStructure.add(structure);
//            }



            if (lineTxt.contains("}")) {
                structure = unMatchingStructure.get(unMatchingStructure.size() - 1);
                structure.setLineEnd(lineTxt, i);
                matchingStructure.add(structure);
                hasMap.put(structure.indexLinesStart, structure);
                hasMap.put(i, structure);
                unMatchingStructure.remove(structure);
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

    static List<CodeSample> codeList = new ArrayList<>();
    static int vertifyNumber;


    private static String getRandomSampleName() {
        Random random = new Random(System.nanoTime());//3 4 3

        int variableLength = random.nextInt(10);

        if (variableLength < 3) {
            variableLength = 3;
        }

        int firstCount, secondCount, thirdCount;
        if (3 == variableLength) {
            firstCount = 1;
            secondCount = 1;
            thirdCount = 1;
        } else if (4 == variableLength) {
            firstCount = 1;
            secondCount = 2;
            thirdCount = 1;
        } else if (5 == variableLength) {
            firstCount = 2;
            secondCount = 2;
            thirdCount = 1;
        } else {
            firstCount = random.nextInt(variableLength) -1;

            if (firstCount <= 0) {
                firstCount = 2;
            }
            secondCount = firstCount / 2 + 1;
            thirdCount = variableLength - firstCount - secondCount;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(radomAlpha[random.nextInt(radomAlpha.length)]);

        for (int i = 0; i < firstCount; i++) {
            stringBuilder.append(radomAlpha[random.nextInt(radomAlpha.length)]);
        }

        for (int i = 0; i < secondCount; i++) {
            stringBuilder.append(radomDigit[random.nextInt(radomDigit.length)]);
        }
        for (int i = 0; i < thirdCount; i++) {
            stringBuilder.append(radomAlpha[random.nextInt(radomAlpha.length)]);
        }

        return stringBuilder.toString();
    }

    private static void initCodeList() {
        vertifyNumber = matchingStructure.size() / 2 + matchingStructure.size() % 2;
        if (vertifyNumber < 36) {
            vertifyNumber = 36;
        }
        if (vertifyNumber > 56) {
            vertifyNumber = 56;
        }
        char x = 'a';
        List<String> variableNameList = new ArrayList<>();
        for (int i = 0; i < vertifyNumber; i++) {
            String name = getRandomSampleName();//"" + x + "" + (x -1) + "" + (x +1);
            variableNameList.add(name);
            CodeSample codeSample = new CodeSample();
            codeSample.lineCount = 1;
            codeSample.codeLine = new String[codeSample.lineCount];
            if (i > (vertifyNumber / 2)) {
                codeSample.codeLine[0] = "private  long " + name + ";";
            } else {
                codeSample.codeLine[0] = "private  char " + name + ";";
            }

            codeList.add(codeSample);
            x += 1;
        }
        for (int i = 0; i < vertifyNumber; i++) {
            String variableName = variableNameList.get(i);
//            codeList.add("public int get" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "() {\n" +
//                    "        return " + variableName + ";\n" +
//                    "    }");
//            codeList.add("public void set" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "(int " + variableName + ") {\n" +
//                    "        this." + variableName + " = " + variableName + ";\n" +
//                    "    }");

            codeList.add(getCodeSampleForGet(variableName, i));
            codeList.add(getCodeSampleForSet(variableName, i));
        }
    }

    private static CodeSample getCodeSampleForGet(String variableName, int index) {
        CodeSample sample = new CodeSample();
        sample.lineCount = 3;
        sample.codeLine = new String[sample.lineCount];

        if (index > (vertifyNumber / 2)) {
            sample.codeLine[0] = "public long get" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "() {";
        } else {
            sample.codeLine[0] = "public char get" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "() {";
        }

        sample.codeLine[1] = "    return " + variableName + ";";
        sample.codeLine[2] = "}";

        return sample;
    }

    private static CodeSample getCodeSampleForSet(String variableName, int index) {
        CodeSample sample = new CodeSample();
        sample.lineCount = 3;
        sample.codeLine = new String[sample.lineCount];

        if (index > vertifyNumber / 2) {
            sample.codeLine[0] = "public void set" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "(long " + variableName + ") {";
        } else {
            sample.codeLine[0] = "public void set" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "(char " + variableName + ") {";
        }
        sample.codeLine[1] = "    this." + variableName + " = " + variableName + ";";
        sample.codeLine[2] = "}";

        return sample;
    }

    private static String getPrefix(Structure structure, int flag) {
        String spaceStr = "";
        if (0 == flag) {
            for (int i = 0; i < structure.deep; i++) {
                spaceStr += "    ";
            }
        } else {
            for (int i = 0; i < structure.deep - 1; i++) {
                spaceStr += "    ";
            }
        }


        return spaceStr;
    }

    private static void writeToFile(File file) throws FileNotFoundException {
        initCodeList();
        String path = file.getPath();
        path = path.substring(0, path.lastIndexOf("\\"));
        String name = file.getName();
        file.delete();
        // 临时文件
        File outFile = new File(file.getPath()/*path + "/Temp" + name*/);
        // 输出
        FileOutputStream fos = new FileOutputStream(outFile);
        PrintWriter out = new PrintWriter(fos);
        int count = 0;
        int writeIndex = 0;
        Structure structure = matchingStructure.get(count);
        String line;
        for (int i = 0; i < lines.size(); i++) {
            structure = hasMap.get(i);
            line = lines.get(i);
            if (null == structure) {
                out.println(line);
                continue;
            }

            if (CLASS == structure.type && !structure.hasAdd && (structure.indexLinesStart == i || structure.deep == 2)) {
                //写入
                out.println(line);
                //第一个方法前面加入变量。
                if (1 == structure.deep || structure.deep == 2) {
                    for (int j = 0; j < vertifyNumber; j++)
                        out.println(getPrefix(structure, 0) + codeList.get(j).codeLine[0]);
                    structure.hasAdd = true;
                }

                count = 0;
                writeIndex = 0;

                int barkCount = count;
                int barkwriteInde = writeIndex;
                //写入
//                out.println(line);
                int writeLineCount;
                if (2 == structure.deep) {
                    writeLineCount = 6;
                    count++;
                } /*else if (3 == structure.deep) {
                    writeLineCount = 2 * vertifyNumber - 1;
                    writeIndex = 0;
                }*/ else {
                    writeLineCount = 2 * vertifyNumber - 1;;
                }

                for (int j = 0; j < writeLineCount; j++) {
                    if (vertifyNumber + writeIndex >= codeList.size()) {
                        break;
                    }
                    CodeSample codeSample = codeList.get(vertifyNumber + writeIndex);
                    if (1 == codeSample.lineCount) {
                        out.println(getPrefix(structure, 1) + codeSample.codeLine[0]);
                    } else {
                        for (int k = 0; k < codeSample.lineCount; k++) {
                            out.println(getPrefix(structure, 1) + codeSample.codeLine[k]);
                        }
                    }

                    writeIndex += 1;
                    count += 1;
                }
                if (3 == structure.deep) {
                    count = barkCount;
                    writeIndex = barkwriteInde;
                }

                continue;
            }

            if (structure.indexLinesEnd == i && CLASS == structure.type) {
                out.println(line);
//                int barkCount = count;
//                int barkwriteInde = writeIndex;
//                //写入
//                out.println(line);
//                int writeLineCount;
//                if (2 == structure.deep) {
//                    writeLineCount = 6;
//                    count++;
//                } /*else if (3 == structure.deep) {
//                    writeLineCount = 2 * vertifyNumber - 1;
//                    writeIndex = 0;
//                }*/ else {
//                    writeLineCount = 0;
//                }
//
//                for (int j = 0; j < writeLineCount; j++) {
//                    if (vertifyNumber + writeIndex >= codeList.size()) {
//                        break;
//                    }
//                    CodeSample codeSample = codeList.get(vertifyNumber + writeIndex);
//                    if (1 == codeSample.lineCount) {
//                        out.println(getPrefix(structure, 1) + codeSample.codeLine[0]);
//                    } else {
//                        for (int k = 0; k < codeSample.lineCount; k++) {
//                            out.println(getPrefix(structure, 1) + codeSample.codeLine[k]);
//                        }
//                    }
//
//                    writeIndex += 1;
//                    count += 1;
//                }
//                if (3 == structure.deep) {
//                    count = barkCount;
//                    writeIndex = barkwriteInde;
//                }
            } else out.println(line);
        }

        if (!isDebug) {
            String filePath = file.getPath();
            file.delete();
            outFile.renameTo(new File(filePath));
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
