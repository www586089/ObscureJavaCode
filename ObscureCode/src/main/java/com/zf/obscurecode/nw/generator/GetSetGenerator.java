package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.CodeSample;
import com.zf.obscurecode.nw.source.SourceCode;

import java.util.ArrayList;
import java.util.List;

public class GetSetGenerator implements Generator<List<CodeSample>> {

    private SourceCode sourceCode = null;
    public GetSetGenerator(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }
    @Override
    public List<CodeSample> generator(long seeds) {
        List<CodeSample> codeSamples = new ArrayList<>();
        NameGenerator nameGenerator = new NameGenerator();
        int blockSize = sourceCode.getCodeStructureMap().size();
        generateVariable(nameGenerator, codeSamples, blockSize);
        generateMethod(codeSamples, blockSize);
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
