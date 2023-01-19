package com.ssafy.moamoa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "profile_no")
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @NotNull
    @Column(name = "profile_nickname")
    private String nickname;

    @NotNull
    @ColumnDefault("'ALL'")
    @Enumerated(EnumType.STRING)
    private ProfileSearchStatus searchState = ProfileSearchStatus.ALL;

    @Column(name = "profile_img")
    private String img;

    @Lob
    @Column(name = "profile_context", columnDefinition = "TEXT")
    private String context;

    public Profile() {
    }

    //==set==//
    public void setUser(User newUser) {
        this.user = newUser;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}