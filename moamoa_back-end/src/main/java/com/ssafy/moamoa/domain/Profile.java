package com.ssafy.moamoa.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
public class Profile {
    @Id
    @Column(name="profile_no")
    private Long id;


    @OneToOne
    @JoinColumn(name="user_no")
    private User user;

    @Setter
    @Column(name="profile_nickname",length = 100)
    private String nickname;

    @Setter
    @Column(name="profile_search_state")
    @Enumerated(EnumType.STRING)
    private ProfileSearchStatus searchState;

    @Setter
    @Lob
    @Column(name="profile_img", columnDefinition = "BLOB")
    private byte[] img;

    @Setter
    @Lob
    @Column(name="profile_context" ,length=1000)
    private String context;



}
