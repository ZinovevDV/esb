package ru.mail.zinovev_dv.minio.controller;


import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mail.zinovev_dv.minio.service.IOFileService;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/")
public class Controller {
    private final IOFileService fileService;
    @GetMapping(value = "file/{path}/{filename}")
    public @ResponseBody byte[] getFile(@PathVariable String path, @PathVariable String filename) throws IOException{
        InputStream inputStream = fileService.getFile(path, filename);
        if (inputStream == null) {
            return new byte[0];
        }
        return IOUtils.toByteArray(inputStream);
    }

    @PostMapping(value = "file/{path}")
    public ResponseEntity<String> putFile(@PathVariable String path, @RequestParam("file") MultipartFile file) throws IOException{
        if(file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        fileService.saveFile(path, filename, inputStream);
        return new ResponseEntity<>(filename, HttpStatus.OK);
    }

}
