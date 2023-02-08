package com.ssafy.moamoa.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/images/bucket.png
    private final String imgLink = "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket";
    private final AmazonS3 amazonS3;

    private final ProfileRepository profileRepository;

    public String uploadProfileImg(Long profileId, MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        log.info("File Name: " + fileName);
        Profile profile = profileRepository.getProfileById(profileId);
        String fileWithNickName = profile.getNickname() + ".";
        String imgSet = "";

        //파일 형식 구하기
        String ext = fileName.split("\\.")[1];
        String contentType = "";

        //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작됨.
        switch (ext) {
            case "jpeg":
                contentType = "image/jpeg";
                fileWithNickName += ext;
                break;
            case "png":
                contentType = "image/png";
                fileWithNickName += ext;
                break;
            case "txt":
                contentType = "text/plain";
                fileWithNickName += ext;
                break;
            case "csv":
                contentType = "text/csv";
                fileWithNickName += ext;
                break;
        }

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            boolean isExist = false;

            log.info("====================" + fileWithNickName);

            String dbImg = profile.getImg();
            if (dbImg != null) {
                isExist = true;
            }

            if (isExist) {
                // 기존 이미지 삭제
                profileRepository.setProfileImgNull(profileId);
                amazonS3.deleteObject(bucket, dbImg);

            }

            // 이미지 추가
            // Set File name here
            amazonS3.putObject(new PutObjectRequest(bucket, "profile/" + fileWithNickName, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            imgSet = imgLink + "/profile" + "/" + fileWithNickName;
            profileRepository.setProfileImgLink(profileId, imgSet);
            log.info(" Input Image Link " + imgSet);

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        //object 정보 가져오기
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();

        for (S3ObjectSummary object : objectSummaries) {
            System.out.println("object = " + object.toString());
        }
        return imgSet;
    }


//    public String uploadProjectImg(Long projectId, MultipartFile multipartFile) throws IOException {
//        String fileName = multipartFile.getOriginalFilename();
//
//        //파일 형식 구하기
//        String ext = fileName.split("\\.")[1];
//        String contentType = "";
//
//        //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작됨.
//        switch (ext) {
//            case "jpeg":
//                contentType = "image/jpeg";
//                break;
//            case "png":
//                contentType = "image/png";
//                break;
//            case "txt":
//                contentType = "text/plain";
//                break;
//            case "csv":
//                contentType = "text/csv";
//                break;
//        }
//
//        try {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType(contentType);
//
//            String nickName = profileRepository.getProfileById(profileId).getNickname();
//
//
//            // Set File name here
//            amazonS3.putObject(new PutObjectRequest(bucket, "images/" + nickName, multipartFile.getInputStream(), metadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        } catch (AmazonServiceException e) {
//            e.printStackTrace();
//        } catch (SdkClientException e) {
//            e.printStackTrace();
//        }
//
//        //object 정보 가져오기
//        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket);
//        List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
//
//        for (S3ObjectSummary object : objectSummaries) {
//            System.out.println("object = " + object.toString());
//        }
//        return amazonS3.getUrl(bucket, fileName).toString();
//    }
}
