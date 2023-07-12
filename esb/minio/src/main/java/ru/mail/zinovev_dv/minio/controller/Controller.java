package ru.mail.zinovev_dv.minio.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.zinovev_dv.minio.service.IOFileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/")
public class Controller {
    private final IOFileService fileService;
}
