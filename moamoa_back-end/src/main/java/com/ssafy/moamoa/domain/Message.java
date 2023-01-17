package com.ssafy.moamoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Message {
	@Id
	@GeneratedValue
	@Column(name = "message_no")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "chatroom_no")
	private Chatroom chatId;

	@ManyToOne()
	@JoinColumn(name = "message_sender")
	private User user;

	@Setter
	@Lob
	@Column(name = "message_text", length = 1000)
	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "message_date")
	private Date date;

}
