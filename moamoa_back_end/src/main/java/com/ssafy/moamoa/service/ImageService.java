package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class ImageService {

	String defaultPath = "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/default/";
	ArrayList<String> defaultProfileImages =new ArrayList<>(List.of("blue_cat.png","blue_dog.png","yellow_cat.png","yellow_dog.png"));

	ArrayList<String> defaultProjectImages = new ArrayList<>(List.of("blueteam1.png","blueteam2.png","purpleteam1.png","purpleteam2.png","yellowteam1.png","yellowteam2.png"));
	Random rand;

	public String getRandomDefaultProfileImage()
	{
		rand = new Random();
		int index = rand.nextInt(defaultProfileImages.size());

		return defaultPath+defaultProfileImages.get(index);
	}

	public String getRandomDefaultProjectImage()
	{
		rand = new Random();
		int index = rand.nextInt(defaultProjectImages.size());

		return defaultPath+defaultProjectImages.get(index);
	}
}
