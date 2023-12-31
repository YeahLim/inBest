package com.jrjr.inbest.user.dto;

import com.jrjr.inbest.login.constant.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UserDto {

	private Long seq;

	private String email;

	private String name;

	private String nickname;

	private String birth;

	private String birthyear;

	private String birthday;

	private Integer gender;

	private String profileImgSearchName;

	private String profileImgOriginalName;

	private String provider;

	private Role role;

}