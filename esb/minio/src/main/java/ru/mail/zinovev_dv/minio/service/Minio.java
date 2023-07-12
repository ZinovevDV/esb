package ru.mail.zinovev_dv.minio.service;

import io.minio.*;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Minio implements IOFileService {
    private final MinioClient minioClient;

    public Minio(String host, Integer port, String login, String password){
        minioClient = MinioClient.builder()
                .credentials(login, password)
                .endpoint(host, port, false)
                .build();
    }
    public void saveFile(String path, String filename, InputStream inputStream) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(path).build()))
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(path).build());

        minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(path)
                    .object(filename)
                    .stream(inputStream, -1, -1).build());
    }
    public InputStream getFile(String path, String filename) throws IOException, ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException{
        try(InputStream stream =
                minioClient.getObject(GetObjectArgs.builder()
                                .bucket(path)
                                .object(filename)
                        .build())){
            return stream;
        }
    }
}