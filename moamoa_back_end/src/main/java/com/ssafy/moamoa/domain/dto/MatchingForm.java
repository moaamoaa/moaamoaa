package com.ssafy.moamoa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchingForm {
	private Long userId;
	private Long projectId;
	private Long applyId;
	private Long offerId;
}
