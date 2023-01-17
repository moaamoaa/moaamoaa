package com.ssafy.moamoa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Message {
	@Id
	@GeneratedValue
	@Column(name = "message_no")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chatroom_no")
	private Chatroom chatroom;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_sender")
	private User user;

	@NotNull
	@Column(name = "message_text", columnDefinition = "TEXT")
	private String text;

	@NotNull
	@Column(name = "message_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime date;

}
