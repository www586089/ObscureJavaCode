package com.zf.obscurecode.nw.sample;

import com.zf.obscurecode.nw.source.CodeType;

public class MethodCodeSample extends CodeSample {

    private String methodName;
    @Override
    public CodeType getType() {
        return CodeType.METHOD;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
