package com.greencrane.file;

import com.greencrane.consts.FileType;
import com.greencrane.entity.File;
import com.greencrane.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by Marcin on 30.09.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUtilsTest {

    @Autowired
    FileUtils fileUtils;

    @Test
    public void FileUtilsTest() throws Exception{
        File file = new File();
        file.setName(fileUtils.createFileName());
        file.setContent("123456");
        Arrays.stream(FileType.values()).forEach(type -> {
            file.setFileType(type);
            fileUtils.createFileOnDisk(file);
        });
    }
}
