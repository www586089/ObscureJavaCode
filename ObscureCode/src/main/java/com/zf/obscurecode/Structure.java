package com.zf.obscurecode;

public class Structure {
    int type;
    String lineStart;
    String lineEnd;


    //类
    public static int CLASS = 0;
    //方法
    public static int METHOD = 1;

    public static int BLOCK = 2;//代码块
    int indexLinesStart;
    int indexLinesEnd;
    int indexStart;
    int indexEnd;
    boolean lineEndWithStart;
    boolean lineEndWithEnd;
    int deep = 1;
    Structure parent;
    Structure children[];
    boolean hasAdd = false;

    public Structure(int type) {
        this.type = type;
    }

    public void setLineStart(String lineStart, int indexLinesStart) {
        this.lineStart = lineStart;
        this.indexLinesStart = indexLinesStart;
        indexStart = lineStart.indexOf("{");
        lineEndWithStart = lineStart.endsWith("{");
    }

    public void setLineEnd(String lineEnd, int indexLinesEnd) {
        this.lineEnd = lineEnd;
        this.indexLinesEnd = indexLinesEnd;
        indexEnd = lineEnd.indexOf("}");
        lineEndWithEnd = lineEnd.endsWith("}");
    }
}

