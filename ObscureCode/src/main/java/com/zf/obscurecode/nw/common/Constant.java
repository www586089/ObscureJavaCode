package com.zf.obscurecode.nw.common;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Constant {

    public static int DEFAULT_VARIABLE_SIZE = 15;

    public static final String keyClass = "class";
    public static final String keyInterface = "interface";
    public static final String keyFileJava = ".java";
    public static final String keyFileXml = ".xml";

    /**
     * 小括号( )：parenthese,复数形式,单个括弧则是parenthesis
     * 中括号(或方括号) [ ] ：square brackets
     * 尖括号 ：angle brackets
     * 大括号{ }：braces
     * Lambda符号 ->
     */
    public static final String keyLeftBraces = "{";
    public static final String keyRightBraces = "}";
    public static final String keyLeftSquareBrackets = "[";
    public static final String keyRightSquareBrackets = "]";
    public static final String keyLeftParenthesis = "(";
    public static final String keyRightParenthesis = ")";
    public static final String keyLambdaSymbol = "->";
    public static final String keyLeftAngleBrackets = "<";
    public static final String keyRightAngleBrackets = ">";


    public static class JavaTypeStrInfo {
        public static final String typeChar = "char";
        public static final String typeByte = "byte";
        public static final String typeInt = "int";
        public static final String typeLong = "long";
        public static final String typeDouble = "double";
    }
}
