package com.greencrane.service.file;

import com.greencrane.entity.Communication;
import com.greencrane.entity.File;
import com.greencrane.repository.CommunicationRepository;
import com.greencrane.repository.FileRepository;
import com.greencrane.utils.DateUtils;
import com.greencrane.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Marcin on 29.09.2018.
 */
public interface FileService {
    File createFile(FileCreator fileCreator, Object content);
}

@Service
class FileServiceImpl implements FileService {
    @Autowired
    DateUtils dateUtils;
    @Autowired
    FileUtils fileUtils;

    @Override
    public File createFile(FileCreator fileCreator, Object content) {
        File file = createFile();
        return fileCreator.createFile(file, content);
    }

    private File createFile() {
        File file = new File();
        file.setName(fileUtils.createFileName());
        file.setModDate(dateUtils.getCurrentDate());
        return file;
    }
}
