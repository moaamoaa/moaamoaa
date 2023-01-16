package com.ssafy.moamoa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name="message_sender")
    private User user;

    @Setter
    @Lob
    @Column(name="message_text" ,length=1000)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="message_date")
    private Date date;




}
