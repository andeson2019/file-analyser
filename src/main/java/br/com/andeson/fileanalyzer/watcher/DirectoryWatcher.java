package br.com.andeson.fileanalyzer.watcher;

import br.com.andeson.fileanalyzer.processing.DataFileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;

public class DirectoryWatcher {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryWatcher.class);

    public DirectoryWatcher() {
    }

    public void watch(String dataInputPath, String dataOutputPath)
            throws IOException, InterruptedException {

        var watchService = FileSystems.getDefault().newWatchService();

        var inputPath = Paths.get(dataInputPath);
        inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        var dataFileProcessor = new DataFileProcessor(dataInputPath, dataOutputPath);
        dataFileProcessor.readExistingFiles();

        while (true) {
            var key = watchService.take();
            Optional<WatchEvent<?>> watchEvent = key.pollEvents()
                    .stream().findFirst();

            if (watchEvent.isPresent()) {
                if (watchEvent.get().kind() == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }
                var file = (Path) watchEvent.get().context();
                dataFileProcessor.readFile(new File(dataInputPath + file));
            }
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
        watchService.close();
    }
}
