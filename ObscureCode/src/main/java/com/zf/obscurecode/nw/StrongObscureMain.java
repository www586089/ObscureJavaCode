package com.zf.obscurecode.nw;

import com.zf.obscurecode.nw.common.Constant;
import com.zf.obscurecode.nw.generator.CodeGenerator;
import com.zf.obscurecode.nw.parser.CodeParser;
import com.zf.obscurecode.nw.reader.CodeReader;
import com.zf.obscurecode.nw.source.SourceCode;
import com.zf.obscurecode.nw.utils.ObscureUtil;
import com.zf.obscurecode.nw.writer.CodeWriter;

import java.io.File;

public class StrongObscureMain {

    private static StrongObscureMain instance = null;
    private static boolean countJavaFileCount = false;

    private static String fa = "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\MainActivityHelper.java";
    private static String[] filePath = {
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\asynchronous\\asynctasks\\compress\\CompressedHelperTask.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\asynchronous\\asynctasks\\compress\\GzipHelperTask.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\asynchronous\\asynctasks\\compress\\RarHelperTask.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\asynchronous\\asynctasks\\compress\\TarHelperTask.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\asynchronous\\handlers\\FileHandler.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\database\\models\\CloudEntry.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\database\\models\\EncryptedEntry.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\database\\models\\OperationData.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\database\\models\\Tab.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\activities\\superclasses\\PreferenceActivity.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\data\\IconDataParcelable.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\glide\\apkimage\\ApkImageDataFetcher.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\glide\\AppsAdapterPreloadModel.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\glide\\RecyclerPreloadModelProvider.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\holders\\CompressedItemViewHolder.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\holders\\ItemViewHolder.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\adapters\\holders\\SpecialViewHolder.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\ColorCircleDrawable.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\colors\\ColorUtils.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\colors\\UserColorPreferences.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\fragments\\DbViewerFragment.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\fragments\\preference_fragments\\PreferencesConstants.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\fragments\\preference_fragments\\QuickAccessPref.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\appbar\\AppBar.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\CheckableCircleView.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\CircleGradientDrawable.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\drawer\\ActionViewStateManager.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\preference\\CheckBox.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\preference\\PathSwitchPreference.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\preference\\SelectedColorsPreference.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\ThemedImageView.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\ui\\views\\WarnableTextInputLayout.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\AnimUtils.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\broadcast_receiver\\PackageReceiver.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\cloud\\CloudStreamSource.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\EditTextColorStateUtil.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\FingerprintHandler.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\glide\\CloudIconDataFetcher.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\glide\\CloudIconModelLoader.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\ImmutableEntry.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\InterestingConfigChange.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\LruBitmapCache.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\ScreenUtils.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\streams\\RandomAccessStream.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\test\\DummyFileGenerator.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\theme\\AppThemeManager.java",
            "E:\\Android\\p3_phone_cleaner\\dev\\main\\crazyApp\\SmartFileManager\\app\\src\\main\\java\\com\\cmm\\filemanager\\utils\\TinyDB.java"
    };
    public static void main(String[] args) {
        instance = new StrongObscureMain(filePath);

        if (countJavaFileCount) {
            instance.countJavaFile(instance.pathArray);
        } else {
            instance.obscureStart(instance.pathArray);
        }
    }

    private String[] pathArray = null;
    private StrongObscureMain(String[] pathArray) {
        this.pathArray = pathArray;
    }

    private void obscureStart(String[] pathArray) {
        for (String filePath : pathArray) {
            if (filePath.endsWith(Constant.keyFileJava)) {
                obscureCode(new File(filePath));
            } else {
                File[] fileList = new File(filePath).listFiles();
                if (null != fileList && fileList.length > 0) {
                    for (File itemFile : fileList) {
                        obscureCode(itemFile);
                    }
                }
            }
        }
    }


    private void obscureCode(File file) {
        if (file.isFile() && file.getPath().endsWith(Constant.keyFileJava)) {
            CodeReader codeReader = new CodeReader(file);
            CodeParser codeParser = new CodeParser(file, codeReader.readCode());
            SourceCode sourceCode = codeParser.parse();
            CodeGenerator codeGenerator = new CodeGenerator(sourceCode);
            CodeWriter codeWriter = new CodeWriter(sourceCode);
            codeWriter.writeCode(file, codeGenerator);
        } else if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (null != fileList && fileList.length > 0) {
                for (File itemFile : fileList) {
                    obscureCode(itemFile);
                }
            }
        }
    }


    private void countJavaFile(String[] pathArray) {
        if (null != pathArray && pathArray.length > 0) {
            int javaFileCount = 0;
            for (String filePath : pathArray) {
                if (filePath.endsWith(Constant.keyFileJava)) {
                    javaFileCount += ObscureUtil.doCountJavaFile(new File(filePath));
                } else {
                    File[] fileList = new File(filePath).listFiles();
                    if (null != fileList && fileList.length > 0) {
                        for (File itemFile : fileList) {
                            javaFileCount += ObscureUtil.doCountJavaFile(itemFile);
                        }
                    }
                }
            }

            System.out.println("countJavaFile, javaFileCount = " + javaFileCount);
        }
    }
}
