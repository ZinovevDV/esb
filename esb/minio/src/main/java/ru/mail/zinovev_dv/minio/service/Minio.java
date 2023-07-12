package ru.mail.zinovev_dv.minio.service;

import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

public class Minio implements IOFileService {
    private final MinioClient minioClient;

    public Minio(String host, Integer port, String login, String password){
        String endpoint = StringBuilder
                .append(host)
                .append(":")
                .append(port.ToString())
                .ToString();
        minioClient = MinioClient.builder()
                .credentials(login, password)
                .endpoint(endpoint)
                .build();
    }
    public void saveFile(String path, String filename, FileInputStream fileInputStream){
        if(!minioClient.bucketExists(path)
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(path).build());

        minioClient.putObject(PutObjectArgs.builder()
                        .bucket(path)
                        .object(filename)
                        .stream(fileInputStream)
                .build());
    }
    public InputStream getFile(String path, String filename){
        try(InputStream stream =
                minioClient.getObject(GetObjectArgs.builder()
                                .bucket(path)
                                .object(filename)
                        .build())){
            return stream;
        }
    }


}