package com.ssafy.moamoa.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chatroom {

    @Id
    @GeneratedValue
    @Column(name = "chatroom_no")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_user_one")
    private Profile userOne;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_user_two")
    private Profile userTwo;

    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();

}
