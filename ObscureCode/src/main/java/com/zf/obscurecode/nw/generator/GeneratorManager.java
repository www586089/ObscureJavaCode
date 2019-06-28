package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.sample.CodeSample;
import com.zf.obscurecode.nw.source.CodeStructure;
import com.zf.obscurecode.nw.source.CodeType;
import com.zf.obscurecode.nw.source.VariableType;
import com.zf.obscurecode.nw.source.VirtualSourceCode;
import com.zf.obscurecode.nw.utils.ObscureUtil;
import com.zf.obscurecode.nw.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GeneratorManager {
    private static GeneratorManager INSTANCE = null;
    public static synchronized GeneratorManager getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new GeneratorManager();
        }
        return INSTANCE;
    }


    /**
     * 要生存的变量的类型
     */
    private VariableType variableType1 = VariableType.TypeShortCapital;
    private VariableType variableType2 = VariableType.TypeShortCapital;
    private VariableType variableType = VariableType.TypeInner;//指定是否生存自定义类
    /**
     * 自定义类中的变量类型
     */
    private String customTypeClassVariableType = Constant.JavaTypeStrInfo.typeByteCapital;

    //将会提取java 下面的路径名作为生存的包名
    private String customTypeClsPath = "E:\\Android\\crazyApp\\FileManager\\app\\src\\main\\java\\com\\amaze\\filemanager\\utils\\application\\";
    private String importClassCodeLine = null;//要导入的类代码
    private String clsName = null;//要导入的类名


    private GeneratorManager() {

    }

    public VariableType getVariableType1() {
        return variableType1;
    }

    public VariableType getVariableType2() {
        return variableType2;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public String getImportClassCodeLine() {
        return importClassCodeLine;
    }

    /**
     * 首次调用时clsName还没生存，这里会指定一个默认的类型{@link GeneratorManager#customTypeClassVariableType}
     * @return 首次调用返回
     */
    public String getClsName() {
        if (null == clsName) {
            /**
             * 要生存的类中的变量类型
             */
            return customTypeClassVariableType;
        }
        return clsName;
    }

    public void init() {
        /**
         * 首次运行会生存一个自定义的类
         */
        if (variableType.isCustomType()) {
            if (null != customTypeClsPath && customTypeClsPath.length() > 0) {
                CodeGenerator codeGenerator = new CodeGenerator(new VirtualSourceCode());
                List<CodeSample> codeSamples = codeGenerator.generate(CodeType.INTERFACE);
                clsName = codeSamples.get(0).className;
                clsName = StringUtil.getFirstCapitalLetter(clsName) + clsName.substring(1);//类名
                File file = new File(customTypeClsPath + clsName + ".java");
                importClassCodeLine = "import " + getPackage(file) + "." + clsName + ";";
                writeCode(file, codeSamples);
            }
        }
    }

    private void writeCode(File file, List<CodeSample> codeSamples) {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            CodeStructure codeStructure = new CodeStructure(0, 0, 0, CodeType.CLASS);
            codeStructure.setDeep(0);
            outputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(outputStream);

            int size = codeSamples.size();
            //写文件头
            //1 包声明
            printWriter.println("package " + getPackage(file) + ";\n\n\n\n\n\n");
            printWriter.println(getProgramDescription());

            CodeSample codeSampleHead = codeSamples.get(0);
            printWriter.println(getLineCode(codeStructure, codeSampleHead, 0, Constant.TYPE_CLASS_HEADER));


            //写类体
            codeStructure.setDeep(1);
            for (int i = 1; i < (size - 1); i++) {
                CodeSample codeSample = codeSamples.get(i);
                if (1 == codeSample.lineCount) {
                    printWriter.println(getLineCode(codeStructure, codeSample, 0, Constant.TYPE_CLASS_MEMBER));
                } else {
                    for (int k = 0; k < codeSample.lineCount; k++) {
                        printWriter.println(getLineCode(codeStructure, codeSample, k, Constant.TYPE_CLASS_MEMBER));
                    }
                }
            }

            //写类尾 "}"
            CodeSample codeSampleTail = codeSamples.get(size - 1);
            printWriter.println(getLineCode(codeStructure, codeSampleTail, 0, Constant.TYPE_CLASS_TAIL));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            ObscureUtil.flush(printWriter);
            ObscureUtil.close(printWriter);
            ObscureUtil.close(outputStream);
        }
    }

    private String getPackage(File file) {
        String path = file.getPath();
        int start = path.indexOf("com");
        int end = path.lastIndexOf("\\");
        String packagePath = path.substring(start, end);
        return packagePath.replaceAll("\\\\", "\\.");
    }

    private String getProgramDescription() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(EEEE) HH:mm:ss", Locale.getDefault());
        String timeStr = sdf.format(calendar.getTime());
        String stringBuilder = "/**\n" + " * This is generated by program at " + timeStr +
                " */";
        return stringBuilder;
    }

    private String getLineCode(CodeStructure codeStructure, CodeSample codeSample, int index, int codeType) {
        return ObscureUtil.getPrefix(codeStructure, codeType) + codeSample.codeLine[index];
    }
}
