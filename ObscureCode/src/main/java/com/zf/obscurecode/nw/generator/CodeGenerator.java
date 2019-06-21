package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.sample.CodeSample;
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
            case CLASS://类[抽象类、一般类]------生存get set
                GetSetGenerator getSetGenerator = new GetSetGenerator(sourceCode);
                codeSamples = getSetGenerator.generate(System.nanoTime());
                break;
            case INTERFACE://接口--------生存类
                SourceClassGenerator sourceClassGenerator = new SourceClassGenerator(sourceCode);
                codeSamples = sourceClassGenerator.generate(System.nanoTime());
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
            case ENUM://枚举--------生存类
                SourceClassGenerator enumClassGenerator = new SourceClassGenerator(sourceCode);
                codeSamples = enumClassGenerator.generate(System.nanoTime());
                break;
            case TYPE_NONE:
                break;
        }
        return codeSamples;
    }
}
