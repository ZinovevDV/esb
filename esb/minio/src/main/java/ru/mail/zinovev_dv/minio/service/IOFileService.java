package ru.mail.zinovev_dv.minio.service;

public interface IOFileService {
    public void saveFile(String path, String filename, FileInputStream fileInputStream);
    public InputStream getFile(String path, String filename);
}
