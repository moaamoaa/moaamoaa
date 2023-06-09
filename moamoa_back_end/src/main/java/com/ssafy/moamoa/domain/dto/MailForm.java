package com.ssafy.moamoa.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailForm {
	private String mailFrom;
	private String mailTo;
	private String mailCc;
	private String mailBcc;
	private String mailSubject;
	private String mailContent;
	private String contentType;

	public MailForm() {
		contentType = "text/plain";
	}

}
