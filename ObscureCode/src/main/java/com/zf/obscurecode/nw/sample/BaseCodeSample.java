package com.zf.obscurecode.nw.sample;

import com.zf.obscurecode.nw.source.CodeType;

public class BaseCodeSample {
    protected CodeType type = CodeType.TYPE_NONE;
    /**
     * 代码行数
     *  变量 1行
     *  类头、类尾 1行
     *  方法 多行
     */
    protected int lineCount = 0;
    protected String[] codeLine = null;

    public CodeType getType() {
        return type;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public String[] getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String[] codeLine) {
        this.codeLine = codeLine;
    }
}
