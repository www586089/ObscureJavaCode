package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.source.VariableType;

public class CustomGetSetGenerator extends GetSetGenerator {
    public CustomGetSetGenerator(SourceCode sourceCode) {
        super(sourceCode);
    }

    @Override
    protected String getVariableStrInfo(VariableType type) {
        return GeneratorManager.getInstance().getClsName();
    }

    @Override
    public void destroy() {

    }
}
