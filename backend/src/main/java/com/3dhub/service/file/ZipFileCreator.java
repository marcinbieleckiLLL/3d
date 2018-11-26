package com.greencrane.service.file;

import com.greencrane.consts.FileType;
import com.greencrane.entity.Customer;
import com.greencrane.entity.File;
import com.greencrane.utils.FileUtils;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Marcin on 29.09.2018.
 */
@Service("zipFileCreator")
public class ZipFileCreator implements FileCreator {

    @Value("${zip.file.password}")
    String password;

    @Autowired
    FileUtils fileUtils;

    @Override
    public File createFile(File file, Object content) {
        if (content instanceof Customer) {
            try {
                Customer customer = (Customer) content;
                fillFileWithProperties(file, customer);
                createZipFile(file);
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private File fillFileWithProperties(File file, Customer customer) {
        file.setCustomer(customer);
        file.setContent(customer.toString());
        file.setFileType(FileType.ZIP);
        return file;
    }

    private void createZipFile(File file) throws ZipException {
        ZipFile zipFile = new ZipFile(fileUtils.createCompletedPath(file));
        zipFile.addFiles(createSourceFiles(file), createZipParameters());
    }

    private ZipParameters createZipParameters() {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters.setPassword(password);
        return parameters;
    }

    private ArrayList<java.io.File> createSourceFiles(File zipFile) {
        ArrayList<java.io.File> filesToAdd = new ArrayList<>();
        filesToAdd.add(fileUtils.createFileOnDisk(createSourceFile(zipFile)));
        return filesToAdd;
    }

    private File createSourceFile(File zipFile) {
        File sourceFile = new File();
        sourceFile.setContent(zipFile.getContent());
        sourceFile.setName(zipFile.getName());
        sourceFile.setModDate(zipFile.getModDate());
        sourceFile.setFileType(FileType.TXT);
        return sourceFile;
    }
}
