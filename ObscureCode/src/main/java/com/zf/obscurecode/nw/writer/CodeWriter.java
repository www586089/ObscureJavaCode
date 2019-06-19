package com.zf.obscurecode.nw.writer;

import com.zf.obscurecode.CodeSample;
import com.zf.obscurecode.nw.generator.CodeGenerator;
import com.zf.obscurecode.nw.source.CodeStructure;
import com.zf.obscurecode.nw.source.CodeType;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.utils.ObscureUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CodeWriter {

    private boolean isDebug = false;
    private SourceCode sourceCode = null;
    public CodeWriter(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void writeCode(File file, CodeGenerator generator) {
        String originPath = file.getPath();
        String parentPath = originPath.substring(0, originPath.lastIndexOf("\\"));
        String originName = file.getName();
        File outFile = getOutputFile(file, originPath, parentPath, originName);

        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            outputStream = new FileOutputStream(outFile);
            printWriter = new PrintWriter(outputStream);
            List<String> codeLines = sourceCode.getCodeLines();
            Map<Integer, CodeStructure> structureMap = sourceCode.getCodeStructureMap();
            int blockSize = sourceCode.getBlockSize();
            int codeLineSize = codeLines.size();
            CodeStructure codeStructure = null;
            String lineText = null;

            for (int i = 0; i < codeLineSize; i++) {
                lineText = codeLines.get(i);
                codeStructure = structureMap.get(i);

                if (null != codeStructure) {
                    printWriter.println(lineText);
                    if (codeStructure.isClass()) {
                        handleWriteClass(printWriter, generator.generate(CodeType.CLASS), blockSize, codeStructure);
                    } else if (codeStructure.isInterface()) {
                        handleWriteInterface(printWriter, generator.generate(CodeType.INTERFACE), blockSize, codeStructure);
                    }
                } else {
                    printWriter.println(lineText);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (!isDebug) {
                String filePath = file.getPath();
                file.delete();
                outFile.renameTo(new File(filePath));
            }
            ObscureUtil.flush(printWriter);
            ObscureUtil.close(printWriter);
            ObscureUtil.close(outputStream);
        }
    }

    private void handleWriteInterface(PrintWriter printWriter, List<CodeSample> codeSamples, int blockSize, CodeStructure codeStructure) {
        //写类头 public class fjlsjlfs {
        writeClassHeader(printWriter, codeSamples, blockSize, codeStructure);
        //写变量
        writeVariable(printWriter, codeSamples, 1, blockSize, codeStructure);
        //写方法
        writeMethod(printWriter, codeSamples, blockSize + 1, blockSize, codeStructure);
        //写类结束大括号
        writeClassTail(printWriter, codeSamples, blockSize, codeStructure);
    }

    private void writeClassHeader(PrintWriter printWriter, List<CodeSample> codeSamples, int blockSize, CodeStructure codeStructure) {
        if (null != codeSamples && codeSamples.size() > 0) {
            CodeSample codeSample = codeSamples.get(0);
            printWriter.println(codeSample.codeLine[0]);
        }
    }

    private void writeClassTail(PrintWriter printWriter, List<CodeSample> codeSamples, int blockSize, CodeStructure codeStructure) {
        if (null != codeSamples && codeSamples.size() > 0) {
            CodeSample codeSample = codeSamples.get((3 * blockSize + 2) - 1);
            printWriter.println(codeSample.codeLine[0]);
        }
    }

    private void handleWriteClass(PrintWriter printWriter, List<CodeSample> codeSamples, int blockSize, CodeStructure codeStructure) {
        //写变量
        writeVariable(printWriter, codeSamples, 0, blockSize, codeStructure);
        //写方法
        writeMethod(printWriter, codeSamples, blockSize, blockSize, codeStructure);
    }

    /***
     * 写变量
     * @param printWriter
     * @param codeSamples
     * @param blockSize
     */
    private void writeVariable(PrintWriter printWriter, List<CodeSample> codeSamples, int indexStart, int blockSize, CodeStructure codeStructure) {
        for (int j = indexStart; j < indexStart + blockSize; j++) {
            printWriter.println(getLineCode(codeStructure, codeSamples.get(j), 0));
        }
    }

    /**
     * 写方法
     * @param printWriter
     * @param codeSamples
     * @param blockSize
     * @param codeStructure
     */
    private void writeMethod(PrintWriter printWriter, List<CodeSample> codeSamples, int indexStart, int blockSize, CodeStructure codeStructure) {
        for (int j = indexStart; j < indexStart + 2 * blockSize; j++) {
            CodeSample codeSample = codeSamples.get(j);
            if (1 == codeSample.lineCount) {
                printWriter.println(getLineCode(codeStructure, codeSample, 0));
            } else {
                for (int k = 0; k < codeSample.lineCount; k++) {
                    printWriter.println(getLineCode(codeStructure, codeSample, k));
                }
            }
        }
    }

    private String getLineCode(CodeStructure codeStructure, CodeSample codeSample, int index) {
        return ObscureUtil.getPrefix(codeStructure, 0) + codeSample.codeLine[index];
    }

    private File getOutputFile(File file, String originPath, String parentPath, String originName) {
        File outFile = null;
        if (isDebug) {
            outFile = new File(parentPath + "/Tmp" + originName);
        } else {
            file.delete();
            outFile = new File(originPath);
        }

        return outFile;
    }
}
