package com.ssafy.moamoa.controller;

import com.ssafy.moamoa.domain.dto.FileDto;
import com.ssafy.moamoa.domain.entity.File;
import com.ssafy.moamoa.service.FileService;
import com.ssafy.moamoa.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class S3Controller {

    private final S3Service s3Service;
    private final FileService fileService;

    @GetMapping("/api/upload")
    public String goToUpload() {
        return "GOOD";
    }

    @PostMapping("/api/upload")
    public String uploadFile(FileDto fileDto) throws IOException {
        String url = s3Service.uploadFile(fileDto.getFile());
        log.info(url);
        fileDto.setUrl(url);
        fileService.save(fileDto);

        return "GOOD";
    }

    @GetMapping("/api/list")
    public String listPage(Model model) {
        List<File> fileList = fileService.getFiles();
        model.addAttribute("fileList", fileList);
        return "list";
    }
}
