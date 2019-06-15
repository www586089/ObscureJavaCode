package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.CodeSample;
import com.zf.obscurecode.nw.source.CodeType;
import com.zf.obscurecode.nw.source.SourceCode;

import java.util.List;

public class CodeGenerator {

    private SourceCode sourceCode = null;
    public CodeGenerator(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    public List<CodeSample> generate(CodeType codeType) {
        List<CodeSample> codeSamples = null;
        switch (codeType) {
            case CLASS://类[抽象类、一般类]
                GetSetGenerator getSetGenerator = new GetSetGenerator(sourceCode);
                codeSamples = getSetGenerator.generator(System.nanoTime());
                break;
            case INTERFACE://接口
                break;
            case ANONYMOUS_CLS://匿名内部类
                break;
            case METHOD://方法
                break;
            case BLOCK://代码块
                break;
            case LAMBDA://Lambda表达式
                break;
            case VARIABLE://变量声明
                break;
            case TYPE_NONE:
                break;
        }
//        codeSamples = new ArrayList<>();
//        NameGenerator nameGenerator = new NameGenerator();
//        int blockSize = sourceCode.getCodeStructureMap().size();
//        generateVariable(nameGenerator, codeSamples, blockSize);
//        generateMethod(codeSamples, blockSize);
        return codeSamples;
    }

    private void generateVariable(NameGenerator nameGenerator, List<CodeSample> codeSamples, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            CodeSample codeSample = new CodeSample();
            String name = nameGenerator.generator(System.nanoTime());
            codeSample.lineCount = 1;
            codeSample.variableName = name;
            codeSample.codeLine = new String[codeSample.lineCount];
            if (i > blockSize / 2) {
                codeSample.codeLine[0] = "private  long " + name + ";";
            } else {
                codeSample.codeLine[0] = "private  char " + name + ";";
            }
            codeSamples.add(codeSample);
        }
    }

    private void generateMethod(List<CodeSample> codeSamples, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            String variableName = codeSamples.get(i).variableName;
            codeSamples.add(getCodeSampleForGet(variableName, blockSize, i));//生存getter
            codeSamples.add(getCodeSampleForSet(variableName, blockSize, i));//生存setter
        }
    }



    private static CodeSample getCodeSampleForGet(String variableName, int blockSize, int index) {
        CodeSample sample = new CodeSample();
        sample.lineCount = 3;
        sample.codeLine = new String[sample.lineCount];

        if (index > (blockSize / 2)) {
            sample.codeLine[0] = "public long get" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "() {";
        } else {
            sample.codeLine[0] = "public char get" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "() {";
        }

        sample.codeLine[1] = "    return " + variableName + ";";
        sample.codeLine[2] = "}";

        return sample;
    }

    private static CodeSample getCodeSampleForSet(String variableName, int blockSize, int index) {
        CodeSample sample = new CodeSample();
        sample.lineCount = 3;
        sample.codeLine = new String[sample.lineCount];

        if (index > blockSize / 2) {
            sample.codeLine[0] = "public void set" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "(long " + variableName + ") {";
        } else {
            sample.codeLine[0] = "public void set" + String.valueOf(variableName.charAt(0)).toUpperCase() + variableName.substring(1) + "(char " + variableName + ") {";
        }
        sample.codeLine[1] = "    this." + variableName + " = " + variableName + ";";
        sample.codeLine[2] = "}";

        return sample;
    }
}
