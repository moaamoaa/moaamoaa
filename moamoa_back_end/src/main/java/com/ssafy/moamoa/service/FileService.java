package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.FileDto;
import com.ssafy.moamoa.domain.entity.File;
import com.ssafy.moamoa.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(FileDto fileDto) {
        File file = new File(fileDto.getTitle(), fileDto.getUrl());
        fileRepository.save(file);
    }

    public List<File> getFiles() {
        List<File> all = fileRepository.findAll();
        return all;
    }
}