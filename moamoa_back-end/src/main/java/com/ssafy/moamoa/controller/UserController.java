package com.ssafy.moamoa.controller;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ssafy.moamoa.domain.Profile;
import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.service.ProfileService;
import com.ssafy.moamoa.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/users")
@Transactional
public class UserController {

	private final UserService userService;
	private final ProfileService profileService;
	@GetMapping
	public ResponseEntity<?> showList() throws Exception {
		List<User> users = userService.findUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

/*	@PostMapping("/signUp")
	public ResponseEntity<?> signup(@RequestBody User user) {
		User newUser = userService.save(user);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}*/

/*	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/signUp")
	public User signup(@RequestBody ObjectNode objectNode) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper(); // JSON을 Object화 하기 위한 ObjectMapper
		User user = mapper.treeToValue(objectNode.get("user"), User.class);
		User newUser = userService.save(user);

*//*		Profile profile = mapper.treeToValue(objectNode.get("profile"), Profile.class);
		profile.addUser(user);
		Profile newProfile = profileService.save(profile);*//*

		return newUser;//new ResponseEntity<User>(newUser, HttpStatus.OK);
	}*/

	@PostMapping("/signUp")
	public ResponseEntity<?> signup(/*@RequestParam("email") String email,
									@RequestParam("password") String password,
									@RequestParam("nickname") String nickname
		                            */
	@RequestParam HashMap<String,String> paramMap) throws JsonProcessingException {

		String email = paramMap.get("email");
		String password = paramMap.get("password");
		String nickname = paramMap.get("nickname");

		System.out.println(paramMap.get("email"));
		String userNickname = userService.signup(email, password, nickname);

/*		Profile profile = mapper.treeToValue(objectNode.get("profile"), Profile.class);
		profile.addUser(user);
		Profile newProfile = profileService.save(profile);*/

		return new ResponseEntity<String>(userNickname, HttpStatus.OK);
	}
}
