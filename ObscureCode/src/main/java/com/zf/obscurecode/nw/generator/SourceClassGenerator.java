package com.zf.obscurecode.nw.generator;


import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.sample.CodeSample;
import com.zf.obscurecode.nw.sample.ClassHeaderCodeSample;
import com.zf.obscurecode.nw.sample.ClassTailCodeSample;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SourceClassGenerator implements Generator<List<CodeSample>> {


    private SourceCode sourceCode = null;
    public SourceClassGenerator(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public List<CodeSample> generate(long seeds) {
        NameGenerator nameGenerator = new NameGenerator();
        List<CodeSample> codeSampleList = new ArrayList<>();
        //生成类头
        codeSampleList.add(getClassHeaderCodeSample(nameGenerator));

        //类变量及方法
        codeSampleList.addAll(getClassBody());

        //生成类结束括号
        codeSampleList.add(getClassTailCodeSample(nameGenerator));
        return codeSampleList;
    }


    private CodeSample getClassHeaderCodeSample(NameGenerator nameGenerator) {
        ClassHeaderCodeSample classHeader = new ClassHeaderCodeSample();
        String className = nameGenerator.generate(System.nanoTime());
        classHeader.setClassName(StringUtil.getFirstCapitalLetter(className) + className.substring(1));
        classHeader.setLineCount(1);
        classHeader.setCodeLine(new String[classHeader.getLineCount()]);
        // public class Sample {
        String[] classHeaderCodeLine = classHeader.getCodeLine();
        classHeaderCodeLine[0] = "public " + Constant.keyClass  + Constant.keySpace + classHeader.getClassName() + Constant.keySpace + Constant.keyLeftBraces;

        return classHeader;
    }

    private CodeSample getClassTailCodeSample(NameGenerator nameGenerator) {
        ClassTailCodeSample classTail = new ClassTailCodeSample();
        classTail.setLineCount(1);
        classTail.setCodeLine(new String[classTail.getLineCount()]);
        String[] classTailCodeLine = classTail.getCodeLine();
        classTailCodeLine[0] = Constant.keyRightBraces;

        return classTail;
    }

    private List<CodeSample> getClassBody() {
        GetSetGenerator getSetGenerator = null;
        if (GeneratorManager.getInstance().getVariableType().isCustomType()) {
            getSetGenerator = new CustomGetSetGenerator(sourceCode);
        } else {
            getSetGenerator = new CommonGetSetGenerator(sourceCode);
        }

        return getSetGenerator.generate(System.nanoTime());
    }
}
