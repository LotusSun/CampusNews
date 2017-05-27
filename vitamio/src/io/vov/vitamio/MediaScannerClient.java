/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package io.vov.vitamio;

/**
 * DON'T TOUCH THIS FILE IF YOU DON'T KNOW THE MediaScanner PROCEDURE!!!
 */
public interface MediaScannerClient {
    void scanFile(String path, long lastModified, long fileSize);

    void addNoMediaFolder(String path);

    void handleStringTag(String name, byte[] value, String valueEncoding);

    void setMimeType(String mimeType);
}