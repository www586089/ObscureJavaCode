package com.zf.obscurecode.nw.sample;

import com.zf.obscurecode.nw.source.CodeType;

/**
 * 对应于要生成的变量
 */
public class VariableCodeSample extends CodeSample {

    private String variableName = null;
    @Override
    public CodeType getType() {
        return CodeType.VARIABLE;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
}
