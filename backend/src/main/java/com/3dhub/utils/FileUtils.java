package com.greencrane.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
/**
 * Created by Marcin on 29.09.2018.
 */
public interface FileUtils {
    File createFileOnDisk(com.greencrane.entity.File file);
    String createFileName();
    String createCompletedPath(com.greencrane.entity.File file);
}

@Service
class FileUtilsImpl implements FileUtils {
    @Value("${file.source.path}")
    String sourceFilePath;

    @Override
    public File createFileOnDisk(com.greencrane.entity.File file) {
        try {
            File f = new File(createCompletedPath(file));
            org.apache.commons.io.FileUtils.writeStringToFile(f, file.getContent());
            f.createNewFile();
            return f;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String createFileName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String createCompletedPath(com.greencrane.entity.File file) {
        if (file == null) return null;
        return new StringBuffer().append(sourceFilePath)
                .append(file.getName()).append(".")
                .append(file.getFileType().getExtension()).toString();
    }
}
