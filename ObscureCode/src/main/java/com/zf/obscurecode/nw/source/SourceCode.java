package com.zf.obscurecode.nw.source;

import com.zf.obscurecode.nw.common.Constant;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述读取的源代码
 */
public class SourceCode {
    private Map<Integer, CodeStructure> codeStructureMap = new HashMap<>();
    private List<String> codeLines = null;

    private int blockSize = 0;//要生成的变量数量
    private File file = null;

    public SourceCode() {

    }
    public SourceCode(List<String> codeLines) {
        this.codeLines = codeLines;
        if (null != codeLines && codeLines.size() > 0) {
            this.blockSize = codeLines.size() / 10;
            if (blockSize < Constant.DEFAULT_VARIABLE_SIZE) {
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

    /**
     * 要生成的变量数
     * @return 变量数量
     */
    public int getBlockSize() {
        return blockSize;
    }
}
