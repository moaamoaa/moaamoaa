package com.ssafy.moamoa.service;

import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ssafy.moamoa.domain.dto.MailForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailService {

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final ProjectRepository projectRepository;

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	public int makeRandomNum() {
		Random random = new Random();
		return random.nextInt(888888) + 111111;
	}

	public String makeRandomPassword() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		//특수문자 아스키 코드
		int arr[] = {33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
			43, 44, 45, 46, 47, 58, 59, 60, 61, 62,
			63, 64, 91, 92, 93, 94, 95, 96, 123, 124,
			125, 126};
		int n = 0;

		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(4);
			switch (rIndex) {
				case 0:
					// a-z 영어소문자 아스키코드
					temp.append((char)((int)(rnd.nextInt(26)) + 97));
					break;
				case 1: // A-Z 영어대문자 아스키코드
					temp.append((char)((int)(rnd.nextInt(26)) + 65));
					break;
				case 2:
					// 0-9 숫자 아스키코드
					temp.append((rnd.nextInt(10)));
					break;
				case 3:
					// arr배열에 담긴 특수문자
					n = rnd.nextInt(32);
					temp.append((char)arr[n]);
					break;
			}
		}
		String password = temp.toString();
		return (password);
	}

	public void sendEmail(MailForm mailForm) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

		helper.setFrom(mailForm.getMailFrom());
		helper.setTo(mailForm.getMailTo());
		helper.setSubject(mailForm.getMailSubject());
		helper.setText(mailForm.getMailContent(), true);

		mailSender.send(mimeMessage);
	}

	public String joinEmail(String email) throws MessagingException {
		int checkNum = makeRandomNum();
		MailForm mailForm = new MailForm();
		String setFrom = "moaamoaaofficial@gmail.com";
		String subject = "[MoaaMoaa] 회원 가입 인증 이메일 입니다.";

		Context context = new Context();
		context.setVariable("checkNum", checkNum);
		String content = templateEngine.process("welcome", context);

		mailForm.setMailFrom(setFrom);
		mailForm.setMailTo(email);
		mailForm.setMailSubject(subject);
		mailForm.setMailContent(content);
		sendEmail(mailForm);
		return Integer.toString(checkNum);
	}

	// 임시 비밀번호
	public String tempPassword(String email) throws MessagingException {
		// 존재하는 메일인지 확인
		Optional<User> findUsers = userRepository.findByEmail(email);
		if (!findUsers.isPresent()) {
			throw new EntityNotFoundException("가입된 이메일이 아닙니다.");
		}
		String tempPassword = makeRandomPassword();
		MailForm mailForm = new MailForm();
		String setFrom = "moaamoaaofficial@gmail.com";
		String subject = "[MoaaMoaa] 임시 비밀번호 발송 이메일 입니다.";

		Context context = new Context();
		context.setVariable("tempPassword", tempPassword);
		String content = templateEngine.process("tempPassword", context);

		mailForm.setMailFrom(setFrom);
		mailForm.setMailTo(email);
		mailForm.setMailSubject(subject);
		mailForm.setMailContent(content);
		sendEmail(mailForm);
		return (tempPassword);
	}

	public void receiveApply(Long userId, Long projectId, Long leaderId) throws MessagingException {
		// 받은 지원 알림
		// 유저의 닉네임 search
		// project title search
		Profile profile = profileRepository.findByUser_Id(userId).get();
		Project project = projectRepository.findById(projectId).get();

		// 팀장의 email search
		String email = userRepository.findById(leaderId).get().getEmail();
		String userNickname = profile.getNickname();
		String projectTitle = project.getTitle();
		MailForm mailForm = new MailForm();
		String setFrom = "moaamoaaofficial@gmail.com";
		String subject = "[MoaaMoaa] 지원 알림 이메일 입니다.";

		Context context = new Context();
		context.setVariable("userNickname", userNickname);
		context.setVariable("projectTitle", projectTitle);
		String content = templateEngine.process("apply", context);

		mailForm.setMailFrom(setFrom);
		mailForm.setMailTo(email);
		mailForm.setMailSubject(subject);
		mailForm.setMailContent(content);
		sendEmail(mailForm);
	}

	// 받은 제안 알림
	public void receiveOffer(Long userId, Long projectId) throws MessagingException {
		// 유저의 닉네임 search
		// project title search
		Profile profile = profileRepository.findByUser_Id(userId).get();
		Project project = projectRepository.findById(projectId).get();

		// 유저의 email search
		String email = userRepository.findById(userId).get().getEmail();
		String userNickname = profile.getNickname();
		String projectTitle = project.getTitle();
		MailForm mailForm = new MailForm();
		String setFrom = "moaamoaaofficial@gmail.com";
		String subject = "[MoaaMoaa] 제안 알림 이메일 입니다.";

		Context context = new Context();
		context.setVariable("userNickname", userNickname);
		context.setVariable("projectTitle", projectTitle);
		String content = templateEngine.process("offer", context);
		
		mailForm.setMailFrom(setFrom);
		mailForm.setMailTo(email);
		mailForm.setMailSubject(subject);
		mailForm.setMailContent(content);
		sendEmail(mailForm);
	}
}

