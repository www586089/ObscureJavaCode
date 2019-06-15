package com.zf.obscurecode.nw.source;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceCode {
    private Map<Integer, CodeStructure> codeStructureMap = new HashMap<>();
    private List<String> codeLines = null;
    private File file = null;

    public SourceCode(List<String> codeLines) {
        this.codeLines = codeLines;
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
}
