package com.zf.obscurecode.nw.source;

import com.zf.obscurecode.nw.common.Constant;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceCode {
    private Map<Integer, CodeStructure> codeStructureMap = new HashMap<>();
    private List<String> codeLines = null;

    private int blockSize = 0;//要生存的变量数量
    private File file = null;

    public SourceCode(List<String> codeLines) {
        this.codeLines = codeLines;
        if (null != codeLines && codeLines.size() > 0) {
            this.blockSize = codeLines.size() / 4;
            if (codeLines.size() < Constant.DEFAULT_VARIABLE_SIZE) {
                this.blockSize = Constant.DEFAULT_VARIABLE_SIZE;
            } else if (blockSize > Constant.MAX_VARIABLE_SIZE) {
                this.blockSize = Constant.MAX_VARIABLE_SIZE;
            }
        } else {
            this.blockSize = Constant.DEFAULT_VARIABLE_SIZE;
        }
    }

    public Map<Integer, CodeStructure> getCodeStructureMap() {
        return codeStructureMap;
    }

    public void setCodeStructureMap(Map<Integer, CodeStructure> codeStructureMap) {
        this.codeStructureMap = codeStructureMap;
    }

    public List<String> getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(List<String> codeLines) {
        this.codeLines = codeLines;
    }

    public int getBlockSize() {
        return blockSize;
    }
}
