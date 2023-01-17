package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class Chatroom {

	@Id
	@GeneratedValue
	@Column(name = "chatroom_no")
	private Long id;

	@Column(name = "chatroom_title", length = 20)
	private String title;

	@OneToOne
	@JoinColumn(name = "chatroom_user_one")
	private User userOne;

	@OneToOne
	@JoinColumn(name = "chatroom_user_two")
	private User userTwo;

}
