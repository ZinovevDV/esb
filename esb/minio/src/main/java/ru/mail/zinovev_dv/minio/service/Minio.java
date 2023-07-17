package ru.mail.zinovev_dv.minio.service;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Minio implements IOFileService {
    private final MinioClient minioClient;

    public Minio(String host, Integer port, String login, String password){
        minioClient = MinioClient.builder()
                .credentials(login, password)
                .endpoint(host, port, false)
                .build();
    }
    public void saveFile(String path, String filename, InputStream inputStream) throws  IOException {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(path).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(path).build());
            }
        }
        catch (ErrorResponseException | InsufficientDataException | InvalidKeyException | NoSuchAlgorithmException | XmlParserException e) {
            log.error("make bucket error: " + path);
            e.printStackTrace();
        }
        catch (InternalException | ServerException | InvalidResponseException e ){
            log.error("check your Minio " + path);
            e.printStackTrace();
        }


        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(path)
                            .object(filename)
                            .stream(inputStream, -1, -1).build());
        }
        catch (ErrorResponseException | InsufficientDataException | NoSuchAlgorithmException | XmlParserException | InvalidKeyException e){
            log.error("putObject error: " + path + " filename=" + filename);
            e.printStackTrace();
        }
        catch (InternalException | ServerException | InvalidResponseException e){
            log.error("putObject error, check your Minio ");
            e.printStackTrace();
        }
    }
    public InputStream getFile(String path, String filename) throws IOException{
        try(InputStream stream =
                minioClient.getObject(GetObjectArgs.builder()
                                .bucket(path)
                                .object(filename)
                        .build())){
            return stream;
        }
        catch (ErrorResponseException | InsufficientDataException  | InvalidKeyException | NoSuchAlgorithmException | XmlParserException e){
            log.error("getObject error: " + path + " filename=" + filename);
            e.printStackTrace();
        }
        catch (InternalException | ServerException | InvalidResponseException e){
            log.error("getObject error, check your Minio ");
            e.printStackTrace();
        }
        return  null;
    }
}