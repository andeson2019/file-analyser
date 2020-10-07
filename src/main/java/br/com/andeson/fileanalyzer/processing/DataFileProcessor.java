package br.com.andeson.fileanalyzer.processing;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.exceptions.FileProcessingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class DataFileProcessor {

    private static final Logger logger = LoggerFactory.getLogger(DataFileProcessor.class);

    private final String dataInputPath;
    private final String dataOutputPath;

    public DataFileProcessor(String dataInputPath, String dataOutputPath) {
        this.dataInputPath = dataInputPath;
        this.dataOutputPath = dataOutputPath;
    }

    public void readFile(File file) throws IOException {
        if (isDatFile(file.toPath())) {
            var reportProcessor = new ReportProcessor();
            try (var iterator = FileUtils.lineIterator(file, "UTF-8")) {
                while (iterator.hasNext()) {
                    var line = iterator.nextLine();
                    reportProcessor.process(line);
                }
            } catch (FileProcessingException | ConvertStringToArrayException e) {
               logger.error("[Data File Process] {}", e.getMessage());
            } finally {
                writeOutputFile(reportProcessor.getReportData(), file.getName());
            }
        }
    }

    public void readExistingFiles() {
        var files = FileUtils.listFiles(new File(dataInputPath),
                new String[]{"dat"}, false);

        files.forEach(f -> {
            try {
                readFile(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void writeOutputFile(String data, String fileName)
            throws IOException {

        if (!data.isBlank()) {
            var file = dataOutputPath + FilenameUtils
                    .getBaseName(fileName) + ".done.dat";

            logger.info("[Data File Process] Writing file {}", file);
            FileUtils.write(new File(file), data + "\n",
                    "UTF-8", true);
        }
    }

    private boolean isDatFile(Path path) {
        return FilenameUtils.getExtension(path.toString())
                .equalsIgnoreCase("dat");
    }
}
