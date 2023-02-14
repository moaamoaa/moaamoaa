package com.ssafy.moamoa.domain.dto;

import java.time.LocalDateTime;

import com.ssafy.moamoa.domain.entity.Apply;
import com.ssafy.moamoa.domain.entity.Offer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OfferForm {

	private Long offerId;
	private Long projectId;
	private Long userId;
	private LocalDateTime time;

	private String title;
	private String projectContents;
	private String projectImg;

	private String nickname;
	private String profileContext;
	private String profileImg;


	public static OfferForm toEntity(Offer offer) {
		return OfferForm.builder()
			.offerId(offer.getId())
			.userId(offer.getUser().getId())
			.projectId(offer.getProject().getId())
			.time(offer.getTime())
			.build();
	}
}
