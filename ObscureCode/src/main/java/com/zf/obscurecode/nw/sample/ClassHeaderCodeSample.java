package com.zf.obscurecode.nw.sample;

import com.zf.obscurecode.nw.source.CodeType;

public class ClassHeaderCodeSample extends CodeSample {

    private String className = null;

    @Override
    public CodeType getType() {
        return CodeType.CLASS_HEADER;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
