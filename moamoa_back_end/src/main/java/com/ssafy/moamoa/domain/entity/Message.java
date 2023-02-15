package com.ssafy.moamoa.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	private Profile sender;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_receiver")
	private Profile receiver;

	@NotNull
	@Column(name = "message_text", columnDefinition = "TEXT")
	private String text;

	@NotNull
	@Column(name = "message_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime time;

}
