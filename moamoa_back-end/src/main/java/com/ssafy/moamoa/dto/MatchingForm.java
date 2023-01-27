package com.ssafy.moamoa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchingForm {
	private Long userId;
	private Long projectId;
	private Long applyId;
	private Long offerId;
}
