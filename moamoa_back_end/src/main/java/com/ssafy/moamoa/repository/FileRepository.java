package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
