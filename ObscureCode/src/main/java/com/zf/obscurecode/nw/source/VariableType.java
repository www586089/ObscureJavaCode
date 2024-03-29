package com.zf.obscurecode.nw.source;

import com.zf.obscurecode.nw.common.Constant;

/**
 * 表示生成的代码的变量类型
 * 包括了系统内置的6种基本类型及相应的6种包装类型、String类型、自定义类型{@link VariableType#TypeCustom}
 * {@link VariableType#TypeInner} 用于表示采用内置类型
 * {@link VariableType#TypeCustom} 用于表示采用自定义类型
 * {@link VariableType#TypeNone} 预留以表示不属于任何类型，它代表一种错误。
 */
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
    TypeCustom,//自定义类型
    TypeInner,//内置类型
    TypeNone;//不属于任何类型

    private String typeName = "";
    private String canonicalName = null;

    public String getTypeName() {
        String typeName = "int";
        switch (this) {
            case TypeByte:
                typeName = Constant.JavaTypeStrInfo.typeByte;
                break;

            case TypeChar:
                typeName = Constant.JavaTypeStrInfo.typeChar;
                break;

            case TypeShort:
                typeName = Constant.JavaTypeStrInfo.typeShort;
                break;

            case TypeInt:
                typeName = Constant.JavaTypeStrInfo.typeInt;
                break;

            case TypeLong:
                typeName = Constant.JavaTypeStrInfo.typeLong;
                break;

            case TypeDouble:
                typeName = Constant.JavaTypeStrInfo.typeDouble;
                break;

            case TypeByteCapital:
                typeName = Constant.JavaTypeStrInfo.typeByteCapital;
                break;

            case TypeCharCapital:
                typeName = Constant.JavaTypeStrInfo.typeCharCapital;
                break;

            case TypeShortCapital:
                typeName = Constant.JavaTypeStrInfo.typeShortCapital;
                break;

            case TypeIntCapital:
                typeName = Constant.JavaTypeStrInfo.typeIntCapital;
                break;

            case TypeLongCapital:
                typeName = Constant.JavaTypeStrInfo.typeLongCapital;
                break;

            case TypeDoubleCapital:
                typeName = Constant.JavaTypeStrInfo.typeDoubleCapital;
                break;

            case TypeString:
                typeName = Constant.JavaTypeStrInfo.typeString;
                break;
            case TypeCustom:
                typeName = Constant.JavaTypeStrInfo.typeCustom;
                break;
        }

        return typeName;
    }

    /***
     * 要生成的类是否为自定义类型
     * @return
     */
    public boolean isCustomType() {
        return this == TypeCustom;
    }
}
