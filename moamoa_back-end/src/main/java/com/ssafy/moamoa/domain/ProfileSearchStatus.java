package com.ssafy.moamoa.domain;

import lombok.Getter;

import javax.persistence.Entity;


@Getter
public enum ProfileSearchStatus {
    ALL, PROJECT, STUDY,NONE
}
