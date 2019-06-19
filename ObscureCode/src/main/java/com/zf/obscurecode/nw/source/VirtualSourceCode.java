package com.zf.obscurecode.nw.source;

import com.zf.obscurecode.nw.common.Constant;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualSourceCode extends SourceCode {
    public VirtualSourceCode() {

    }
    public VirtualSourceCode(List<String> codeLines) {
        super(codeLines);
    }

    public int getBlockSize() {
        return 15;
    }
}
