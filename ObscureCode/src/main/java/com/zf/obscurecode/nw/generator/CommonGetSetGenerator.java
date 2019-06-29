package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.source.VariableType;

public class CommonGetSetGenerator extends GetSetGenerator {
    public CommonGetSetGenerator(SourceCode sourceCode) {
        super(sourceCode);
    }

    @Override
    protected String getVariableStrInfo(VariableType type) {
        return type.getTypeName();
    }

    @Override
    public void destroy() {

    }
}
