package com.ssafy.moamoa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.dto.ApplyForm;
import com.ssafy.moamoa.domain.dto.MatchingForm;
import com.ssafy.moamoa.domain.dto.OfferForm;
import com.ssafy.moamoa.domain.entity.Apply;
import com.ssafy.moamoa.domain.entity.Offer;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ApplyRepository;
import com.ssafy.moamoa.repository.OfferRepository;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OfferService {

	private final UserService userService;
	private final ProjectService projectService;
	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final OfferRepository offerRepository;
	private final ProfileRepository profileRepository;

	// 제안 보내기
	public void sendOffer(Long userId, Long projectId) throws Exception {
		User user = userService.findUser(userId);
		Project project = projectService.findProjectById(projectId);

		if(user.isLocked() || !(userRepository.findById(userId).isPresent()))
		{
			throw new Exception("존재하지 않는 회원입니다.");
		}
		if(teamRepository.findByUser_IdAndProject_Id(userId, projectId).isPresent())
		{
			throw new Exception("이미 참여하는 팀원입니다.");
		}
		if(offerRepository.findByUser_IdAndProject_Id(userId, projectId).isPresent())
		{
			throw new Exception("이미 해당 회원에게 제안을 보냈습니다.");
		}
		else {
			Offer offer = Offer.builder()
				.user(user)
				.project(project)
				.time(LocalDateTime.now())
				.build();
			offerRepository.save(offer);
			Profile profile = profileRepository.findByUser_Id(userId).get();
			profile.setCountOffer(profile.getCountOffer()+1);
		}
	}

	public List<OfferForm> showReceiveOffer(Long userId) {
		User user = userRepository.findById(userId).get();
		List<Offer> offers = offerRepository.findByUser(user);
		List<OfferForm> offerForms = new ArrayList<>();
		for (Offer o : offers) {
			if(o.getProject().isLocked()) continue;
			OfferForm offerForm = OfferForm.toEntity(o);
			offerForm.setNickname(profileRepository.findByUser(o.getUser()).get().getNickname());
			offerForm.setProfileContext(profileRepository.findByUser(o.getUser()).get().getContext());
			offerForm.setTitle(o.getProject().getTitle());
			offerForm.setProjectContents(o.getProject().getContents());
			offerForm.setProjectImg(o.getProject().getImg());
			offerForms.add(offerForm);
		}
		return offerForms;
	}

	public List<OfferForm> showSendOffer(Long projectId) {
		Project project = projectRepository.findById(projectId).get();
		List<Offer> offers = offerRepository.findByProject(project);
		List<OfferForm> offerForms = new ArrayList<>();
		for (Offer o : offers) {
			if(o.getUser().isLocked()) continue;
			OfferForm offerForm = OfferForm.toEntity(o);
			offerForm.setNickname(profileRepository.findByUser(o.getUser()).get().getNickname());
			offerForm.setProfileContext(profileRepository.findByUser(o.getUser()).get().getContext());
			offerForm.setTitle(o.getProject().getTitle());
			offerForm.setProjectContents(o.getProject().getContents());
			offerForm.setProfileImg(profileRepository.findByUser(o.getUser()).get().getImg());
			offerForms.add(offerForm);
		}
		return offerForms;
	}

	public void acceptOffer(Long userId, MatchingForm matchingForm) throws Exception {
		// 수락할 project id를 받고 -> team에 등록
		// offer id받고 user id, project id 검증
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		int change = project.getCurrentPeople() + 1;
		if(project.getTotalPeople() >= change) {
			// 지원자를 팀에 등록
			project.setCurrentPeople(change);

			User user = userRepository.findById(userId).get();
			Team team = Team.builder()
				.role(TeamRole.MEMBER)
				.project(project)
				.user(user)
				.build();
			teamRepository.save(team);

			deleteReceiveOffer(matchingForm);
		}
		else throw new Exception("인원 모집이 끝났습니다.");
	}

	public void deleteReceiveOffer(MatchingForm matchingForm) {
		// 철회할 offer id를 받고 -> offer에서 삭제
		offerRepository.deleteById(matchingForm.getOfferId());
	}
	public void deleteSendOffer(MatchingForm matchingForm) {
		// 철회할 offer id를 받고 -> offer에서 삭제
		// profile countOffer--
		Profile profile = profileRepository.findByUser_Id(matchingForm.getUserId()).get();
		profile.setCountOffer(profile.getCountOffer()-1);
		offerRepository.deleteById(matchingForm.getOfferId());
	}
}