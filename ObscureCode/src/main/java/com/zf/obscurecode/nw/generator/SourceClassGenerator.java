package com.zf.obscurecode.nw.generator;



import com.zf.obscurecode.CodeSample;
import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.source.VirtualSourceCode;

import java.util.ArrayList;
import java.util.List;

public class SourceClassGenerator implements Generator<List<CodeSample>> {


    private SourceCode sourceCode = null;
    public SourceClassGenerator(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public List<CodeSample> generator(long seeds) {
        NameGenerator nameGenerator = new NameGenerator();
        List<CodeSample> codeSampleList = new ArrayList<>();
        //生存类头
        CodeSample classHeader = new CodeSample();
        classHeader.className = nameGenerator.generator(System.nanoTime());
        classHeader.lineCount = 1;
        classHeader.codeLine = new String[classHeader.lineCount];
        classHeader.codeLine[0] = "public " + Constant.keyClass  + " " + classHeader.className + " {";
        codeSampleList.add(classHeader);

        GetSetGenerator getSetGenerator = new GetSetGenerator(sourceCode);
        codeSampleList.addAll(getSetGenerator.generator(System.nanoTime()));

        //生存类结束括号
        CodeSample classTail = new CodeSample();
        classTail.lineCount = 1;
        classTail.codeLine = new String[classTail.lineCount];
        classTail.codeLine[0] = "}";
        codeSampleList.add(classTail);
        return codeSampleList;
    }
}
