package com.zf.obscurecode.nw.source;

public enum  CodeType {
    TYPE_NONE,        //非可识别代码
    CLASS,            //类[抽象类、一般类]
    CLASS_HEADER,     //类头[类说明及申明]
    CLASS_TAIL,       //类尾[类结束大括号，还可能再加些说明信息]
    INTERFACE,        //接口
    ANONYMOUS_CLS,    //匿名内部类
    METHOD,           //方法
    BLOCK,            //代码块
    LAMBDA,           //Lambda表达式
    VARIABLE,         //变量声明
    ENUM,             //枚举
}
