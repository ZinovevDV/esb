package ru.mail.zinovev_dv.minio.service;

import java.io.IOException;
import java.io.InputStream;

public interface IOFileService {
    void saveFile(String path, String filename, InputStream fileInputStream) throws IOException;
    InputStream getFile(String path, String filename) throws IOException;
}
