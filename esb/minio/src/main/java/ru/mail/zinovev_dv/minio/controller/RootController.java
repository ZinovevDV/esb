package ru.mail.zinovev_dv.minio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {
    @GetMapping()
    public String swaggerUI(){
        return  "redirect:swagger-ui/index.html";
    }
}
