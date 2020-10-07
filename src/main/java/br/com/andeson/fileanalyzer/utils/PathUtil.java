package br.com.andeson.fileanalyzer.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class PathUtil {
    private static final Logger logger = LoggerFactory.getLogger(PathUtil.class);

    public PathUtil() {
    }

    public static String getInputDataPath() {
        return getOrCreateDir("in");
    }

    public static String getOutputDataPath() {
        return getOrCreateDir("out");
    }

    private static String getOrCreateDir(String dir) {
        var newDir = FileUtils.getUserDirectoryPath() + "/data/" + dir;
        if (new File(newDir).mkdirs()) {
            logger.info("Creating '{}' directory", newDir);
        }else{
            logger.info("Directory '{}' already exists", newDir);
        }
        return newDir + "/";
    }
}
