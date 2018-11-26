package com.greencrane.file;

import com.greencrane.customer.TestsHelper;
import com.greencrane.service.file.FileService;
import com.greencrane.service.file.ZipFileCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Marcin on 30.09.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest extends TestsHelper {

    @Autowired
    FileService fileService;
    @Autowired
    ZipFileCreator zipFileCreator;

    @Test
    public void fileServiceTest() throws Exception {
        fileService.createFile(zipFileCreator, createCustomer());
    }
}
