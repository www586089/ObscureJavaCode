package com.zf.obscurecode.nw.generator;

import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.source.VariableType;

public class GeneratorUtil {

    /**
     * 变量类到字符串转换
     * @param type
     * @return
     */
    public static String getVariableStrInfo(VariableType type) {
        String typeName = "int";
        switch (type) {
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
        }

        return typeName;
    }
}
