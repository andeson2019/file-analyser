package br.com.andeson.fileanalyzer;

import br.com.andeson.fileanalyzer.utils.PathUtil;
import br.com.andeson.fileanalyzer.watcher.DirectoryWatcher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        var inputPath = PathUtil.getInputDataPath();
        var outputPath = PathUtil.getOutputDataPath();
        new DirectoryWatcher().watch(inputPath, outputPath);

    }

}
