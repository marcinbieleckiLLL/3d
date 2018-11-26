package com.greencrane.consts;

/**
 * Created by Marcin on 29.09.2018.
 */
public enum FileType {
    ZIP("zip", 0),
    TXT("txt", 1);

    private String extension;
    private int code;

    FileType(String extension, int code) {
        this.extension = extension;
        this.code = code;
    }

    public String getExtension() {
        return extension;
    }
}
