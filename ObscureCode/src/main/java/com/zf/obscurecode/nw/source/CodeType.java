package com.zf.obscurecode.nw.source;

public enum  CodeType {
    TYPE_NONE,        //非可识别代码
    CLASS,            //类[抽象类、一般类]
    INTERFACE,        //接口
    ANONYMOUS_CLS,    //匿名内部类
    METHOD,           //方法
    BLOCK,            //代码块
    LAMBDA,           //Lambda表达式
    VARIABLE,         //变量声明
    ENUM,             //枚举
}
