package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue
    @Column(name="review_no")
    private Long id;

    @Lob
    @Column(name="review_context")
    private String context;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="review_time")
    private Date time;

    @ManyToOne()
    @JoinColumn(name ="review_receive")

    private User receiveUser;

    @ManyToOne()
    @JoinColumn(name ="review_send")

    private User sendUser;

}
