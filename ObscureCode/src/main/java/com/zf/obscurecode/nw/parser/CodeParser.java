package com.zf.obscurecode.nw.parser;

import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.source.CodeStructure;
import com.zf.obscurecode.nw.source.CodeType;
import com.zf.obscurecode.nw.source.SourceCode;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CodeParser {

    private File file = null;
    private List<String> codeLines = null;
    public CodeParser(File file, List<String> codeLines) {
        this.file = file;
        this.codeLines = codeLines;
    }

    public SourceCode parse() {
        SourceCode sourceCode = new SourceCode(codeLines);
        int length = codeLines.size();
        Deque<CodeStructure> structureStack = new LinkedList<>();

        boolean leftBracesStart = false;
        for (int i = 0; i < length; i++) {
            String lineText = codeLines.get(i);
            if (lineText.contains("import")) {
                continue;
            }

            CodeStructure codeStructure = null;
            if (lineText.contains(Constant.keyClass) || lineText.contains(Constant.keyInterface) || lineText.contains(Constant.keyEnum)) {//类、抽象类、接口
                CodeType codeType = getCodeType(lineText);
                if (!lineText.contains(Constant.keyLeftBraces)) {//类的开始占了多行
                    for (; i < length; i++) {
                        lineText = codeLines.get(i);
                        if (lineText.contains(Constant.keyRightBraces)) {
                            break;
                        }
                        if (lineText.contains(Constant.keyLeftBraces)) {
                            codeStructure = new CodeStructure(0, 0, i, codeType);
                            break;
                        }
                    }
                } else if (lineText.contains(Constant.keyLeftBraces) && lineText.contains(Constant.keyRightBraces)) {//空类
                    continue;
                } else if (lineText.contains(Constant.keyLeftBraces)) {
                    codeStructure = new CodeStructure(0, 0, i, codeType);
                }

                if (null != codeStructure) {
                    leftBracesStart = true;
                    codeStructure.setDeep(structureStack.size() + 1);
                    codeStructure.setLineText(lineText);
                    structureStack.push(codeStructure);
                }
            } else if (leftBracesStart && lineText.endsWith(Constant.keyRightBraces)) {
                leftBracesStart = false;
                codeStructure = structureStack.pop();
                codeStructure.setLineEnd(i);
            } else {//特殊判断
                codeStructure = structureStack.peek();
                if (lineText.endsWith(Constant.keyRightBraces) && null != codeStructure && codeStructure.isEnum()) {
                    structureStack.pop();
                    codeStructure.setLineEnd(i);
                    codeStructure.setDeep(codeStructure.getDeep() + 1);
                }
            }

            if (null != codeStructure) {
                sourceCode.getCodeStructureMap().put(codeStructure.getLineStart(), codeStructure);
            }
        }

        System.out.println("匹配数: " + sourceCode.getCodeStructureMap().size() + ", file = " + file.getPath());
        return sourceCode;
    }


    private CodeType getCodeType(String lineText) {
        if (lineText.contains(Constant.keyClass)) {
            return CodeType.CLASS;
        } else if (lineText.contains(Constant.keyInterface)){
            return CodeType.INTERFACE;
        } else if (lineText.contains(Constant.keyEnum)) {
            return CodeType.ENUM;
        }


        return CodeType.TYPE_NONE;
    }
}
