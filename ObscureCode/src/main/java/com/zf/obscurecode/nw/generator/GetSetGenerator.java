package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.sample.CodeSample;
import com.zf.obscurecode.nw.sample.MethodCodeSample;
import com.zf.obscurecode.nw.sample.VariableCodeSample;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.source.VariableType;
import com.zf.obscurecode.nw.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class GetSetGenerator implements Generator<List<CodeSample>> {

    private SourceCode sourceCode = null;
    private VariableType variableType1 = GeneratorManager.getInstance().getVariableType1();
    private VariableType variableType2 = GeneratorManager.getInstance().getVariableType2();


    GetSetGenerator(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public List<CodeSample> generate(long seeds) {
        List<CodeSample> codeSamples = new ArrayList<>();
        NameGenerator nameGenerator = GeneratorManager.getInstance().getNameGenerator();
        int blockSize = sourceCode.getBlockSize();
        generateVariable(nameGenerator, codeSamples, blockSize);
        generateMethod(codeSamples, blockSize);

        return codeSamples;
    }


    private void generateVariable(NameGenerator nameGenerator, List<CodeSample> codeSamples, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            VariableCodeSample codeSample = new VariableCodeSample();
            String name = nameGenerator.generate(System.nanoTime());
            codeSample.setLineCount(1);
            codeSample.setVariableName(name);
            codeSample.setCodeLine(new String[codeSample.getLineCount()]);
            String variableType = null;
            if (i > blockSize / 2) {
                variableType = getVariableStrInfo(variableType1);
            } else {
                variableType = getVariableStrInfo(variableType2);
            }

            codeSample.getCodeLine()[0] = "private  " + variableType + " " + name + ";";
            codeSamples.add(codeSample);
        }
    }

    private void generateMethod(List<CodeSample> codeSamples, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            String variableName = ((VariableCodeSample) codeSamples.get(i)).getVariableName();
            codeSamples.add(getCodeSampleForGet(variableName, blockSize, i));//生成getter
            codeSamples.add(getCodeSampleForSet(variableName, blockSize, i));//生成setter
        }
    }



    private MethodCodeSample getCodeSampleForGet(String variableName, int blockSize, int index) {
        MethodCodeSample sample = new MethodCodeSample();
        sample.setLineCount(3);
        sample.setCodeLine(new String[sample.getLineCount()]);
        String variableType = null;

        if (index > (blockSize / 2)) {
            variableType = getVariableStrInfo(variableType1);
        } else {
            variableType = getVariableStrInfo(variableType2);
        }
        String[] sampleCodeLine = sample.getCodeLine();
        sampleCodeLine[0] = "public " + variableType + " " + getMethodGetName(variableName) + "() {";
        sampleCodeLine[1] = "    return " + variableName + ";";
        sampleCodeLine[2] = "}";

        return sample;
    }

    private MethodCodeSample getCodeSampleForSet(String variableName, int blockSize, int index) {
        MethodCodeSample sample = new MethodCodeSample();
        sample.setLineCount(3);
        sample.setCodeLine(new String[sample.getLineCount()]);
        String variableType = null;

        if (index > blockSize / 2) {
            variableType = getVariableStrInfo(variableType1);
        } else {
            variableType = getVariableStrInfo(variableType2);
        }

        String[] sampleCodeLine = sample.getCodeLine();
        sampleCodeLine[0] = "public void " + getMethodSetName(variableName) + "(" + variableType + " " + variableName + ") {";
        sampleCodeLine[1] = "    this." + variableName + " = " + variableName + ";";
        sampleCodeLine[2] = "}";

        return sample;
    }

    private String getMethodGetName(String variableName) {
        return "get" + StringUtil.getFirstCapitalLetter(variableName) + variableName.substring(1);
    }

    /**
     * set方法名字
     * @return
     */
    private String getMethodSetName(String variableName) {
        return "set" + StringUtil.getFirstCapitalLetter(variableName) + variableName.substring(1);
    }

    abstract protected String getVariableStrInfo(VariableType type);
}
