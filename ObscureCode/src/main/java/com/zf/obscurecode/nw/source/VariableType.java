package com.zf.obscurecode.nw.source;

public enum  VariableType {
    TypeChar,
    TypeByte,
    TypeShort,
    TypeInt,
    TypeLong,
    TypeDouble,
    TypeCharCapital,//包装类型
    TypeByteCapital,
    TypeShortCapital,
    TypeIntCapital,
    TypeLongCapital,
    TypeDoubleCapital,
    TypeString,
    TypeCustom;//自定义类型

    String typeName = "";

    public String getTypeName() {
        return typeName;
    }
}
