package com.zf.obscurecode.nw.source;

public class CodeStructure {
    private int lineStart;//代码快开始行号
    private int lineEnd;//代码结束行号
    private int columnStart;//代码开始列
    private int columnEnd;//代码结束列
    private int deep = 1;//代码嵌套深度，用于产生代码缩进
    private CodeType codeType = CodeType.TYPE_NONE;
    private String lineText = null;

    public CodeStructure(int columnStart, int columnEnd, int lineStart, CodeType codeType) {
        this.lineStart = lineStart;
        this.codeType = codeType;
    }

    /**
     * 类[抽象类、一般类]
     * @return
     */
    public boolean isClass() {
        return CodeType.CLASS == codeType;
    }

    /**
     * 接口
     * @return
     */
    public boolean isInterface() {
        return CodeType.INTERFACE == codeType;
    }

    /**
     * 匿名内部类
     * @return
     */
    public boolean isAnonymousCls() {
        return CodeType.ANONYMOUS_CLS == codeType;
    }

    /**
     * 方法
     * @return
     */
    public boolean isMethod() {
        return CodeType.METHOD == codeType;
    }

    /**
     * 代码块
     * @return
     */
    public boolean isBlock() {
        return CodeType.BLOCK == codeType;
    }

    /**
     * Lambda表达式
     * @return
     */
    public boolean isLambda() {
        return CodeType.LAMBDA == codeType;
    }

    public int getLineStart() {
        return lineStart;
    }

    public void setLineStart(int lineStart) {
        this.lineStart = lineStart;
    }

    public int getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(int lineEnd) {
        this.lineEnd = lineEnd;
    }

    public int getColumnStart() {
        return columnStart;
    }

    public void setColumnStart(int columnStart) {
        this.columnStart = columnStart;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public void setColumnEnd(int columnEnd) {
        this.columnEnd = columnEnd;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public String getLineText() {
        return lineText;
    }

    public void setLineText(String lineText) {
        this.lineText = lineText;
    }
}
