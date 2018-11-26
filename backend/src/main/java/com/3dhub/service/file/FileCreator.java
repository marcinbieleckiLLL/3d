package com.greencrane.service.file;

import com.greencrane.entity.File;

/**
 * Created by Marcin on 29.09.2018.
 */
public interface FileCreator {
    File createFile(File file, Object content);
}
