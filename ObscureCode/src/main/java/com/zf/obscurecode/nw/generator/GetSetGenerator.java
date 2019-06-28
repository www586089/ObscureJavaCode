package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.sample.CodeSample;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.source.VariableType;
import com.zf.obscurecode.nw.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class GetSetGenerator implements Generator<List<CodeSample>> {

    private SourceCode sourceCode = null;
    protected VariableType variableType1 = VariableType.TypeShortCapital;
    protected VariableType variableType2 = VariableType.TypeShortCapital;
    public GetSetGenerator(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public List<CodeSample> generate(long seeds) {
        List<CodeSample> codeSamples = new ArrayList<>();
        NameGenerator nameGenerator = new NameGenerator();
        int blockSize = sourceCode.getBlockSize();
        generateVariable(nameGenerator, codeSamples, blockSize);
        generateMethod(codeSamples, blockSize);

        return codeSamples;
    }


    private void generateVariable(NameGenerator nameGenerator, List<CodeSample> codeSamples, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            CodeSample codeSample = new CodeSample();
            String name = nameGenerator.generate(System.nanoTime());
            codeSample.lineCount = 1;
            codeSample.variableName = name;
            codeSample.codeLine = new String[codeSample.lineCount];
            String variableType = null;
            if (i > blockSize / 2) {
                variableType = getVariableStrInfo(variableType1);
            } else {
                variableType = getVariableStrInfo(variableType2);
            }
            codeSample.codeLine[0] = "private  " + variableType + " " + name + ";";
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



    private CodeSample getCodeSampleForGet(String variableName, int blockSize, int index) {
        CodeSample sample = new CodeSample();
        sample.lineCount = 3;
        sample.codeLine = new String[sample.lineCount];
        String variableType = null;

        if (index > (blockSize / 2)) {
            variableType = getVariableStrInfo(variableType1);
        } else {
            variableType = getVariableStrInfo(variableType2);
        }
        sample.codeLine[0] = "public " + variableType + " " + getMethodGetName(variableName) + "() {";
        sample.codeLine[1] = "    return " + variableName + ";";
        sample.codeLine[2] = "}";

        return sample;
    }

    private CodeSample getCodeSampleForSet(String variableName, int blockSize, int index) {
        CodeSample sample = new CodeSample();
        sample.lineCount = 3;
        sample.codeLine = new String[sample.lineCount];
        String variableType = null;

        if (index > blockSize / 2) {
            variableType = getVariableStrInfo(variableType1);
        } else {
            variableType = getVariableStrInfo(variableType2);
        }
        sample.codeLine[0] = "public void " + getMethodSetName(variableName) + "(" + variableType + " " + variableName + ") {";
        sample.codeLine[1] = "    this." + variableName + " = " + variableName + ";";
        sample.codeLine[2] = "}";

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
