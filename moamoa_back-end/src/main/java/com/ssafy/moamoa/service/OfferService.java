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
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ApplyRepository;
import com.ssafy.moamoa.repository.OfferRepository;
import com.ssafy.moamoa.repository.ProjectRepository;
import com.ssafy.moamoa.repository.TeamRepository;
import com.ssafy.moamoa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OfferService {

	private final UserService userService;
	private final TeamService teamService;
	private final ProjectService projectService;
	private final ProjectRepository projectRepository;
	private final ApplyRepository applyRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final OfferRepository offerRepository;

	// 제안 보내기
	public void sendOffer(Long userId, Long projectId) {
		User user = userService.findUser(userId);
		Project project = projectService.findProjectById(projectId);
		Offer offer = Offer.builder()
			.user(user)
			.project(project)
			.time(LocalDateTime.now())
			.build();
		offerRepository.save(offer);
	}

	public List<OfferForm> showReceiveOffer(Long userId) {
		User user = userRepository.findById(userId).get();
		List<Offer> offers = offerRepository.findByUser(user);
		List<OfferForm> offerForms = new ArrayList<>();
		for (Offer o : offers) {
			OfferForm offerForm = OfferForm.toEntity(o);
			offerForms.add(offerForm);
		}
		return offerForms;
	}

	public List<OfferForm> showSendOffer(Long projectId) {
		Project project = projectRepository.findById(projectId).get();
		List<Offer> offers = offerRepository.findByProject(project);
		List<OfferForm> offerForms = new ArrayList<>();
		for (Offer o : offers) {
			OfferForm offerForm = OfferForm.toEntity(o);
			offerForms.add(offerForm);
		}
		return offerForms;
	}

	public void acceptOffer(Long userId, MatchingForm matchingForm) throws Exception {
		Project project = projectRepository.findById(matchingForm.getProjectId()).get();
		// 팀장인지 확인
		if(project.getCurrentPeople() < project.getTotalPeople()) {
			// 지원자를 팀에 등록
			project.setCurrentPeople(project.getCurrentPeople()+1);
			User user = userRepository.findById(userId).get();
			Team team = Team.builder()
				.role(TeamRole.MEMBER)
				.project(project)
				.user(user)
				.build();
			teamRepository.save(team);
		}
		else throw new Exception();
	}

	public void deleteOffer(MatchingForm matchingForm) {
		offerRepository.deleteById(matchingForm.getOfferId());
	}
}