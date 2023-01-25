package com.ssafy.moamoa.controller;

import com.ssafy.moamoa.dto.TechStackForm;
import com.ssafy.moamoa.service.TechStackService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/techstack")
@Transactional
public class TechStackController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @Autowired
    private TechStackService techStackService;


    @ApiOperation(value = "기술스택 검색",
            notes = "기술스택을 검색하여 결과를 반환해줍니다.")
    @GetMapping("/list/{techName}")
    public ResponseEntity<?> searchTechStack(@PathVariable String techName) {


        List<TechStackForm> techStackFormList = techStackService.searchTechStackByName(techName);
        return new ResponseEntity<List<TechStackForm>>(techStackFormList,HttpStatus.OK);
    }
//    @ApiOperation(value = "기술스택 삭제",
//            notes = "")
//    @DeleteMapping("/{techName}")
//    public ResponseEntity<?> deleteTechStack(@PathVariable String techName) {
//
//
//        List<TechStackForm> techStackFormList = techStackService.searchTechStackByName(techName);
//        return new ResponseEntity<List<TechStackForm>>(techStackFormList,HttpStatus.OK);
//    }

    @ApiOperation(value = "기술스택 등록",
            notes = "프로필 수정을 누를 시에 사용자의 기술스택을 등록합니다.")
    @PutMapping("/register/{userId}")
    public ResponseEntity<?> addTechStack(@PathVariable Long userId, @RequestBody List<TechStackForm> techStackFormList) {

        String result  = techStackService.modifyUserTechStack(userId,techStackFormList);
        return new ResponseEntity<List<TechStackForm>>(techStackFormList,HttpStatus.OK);
    }
}
