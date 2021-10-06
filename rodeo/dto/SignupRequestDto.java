package com.sparta.rodeo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {


//    @NotNull
//    @NotBlank
//    @Size(min = 3, max = 12, message = "닉네임은 3자 이상 12자 이하로 입력해주세요.")
//    @Pattern(regexp = "^[a-zA-Z0-9]*$" , message = "올바른 아이디를 입력해주세요.")
    private String username;

//    @NotNull
//    @NotBlank
//    @Size(min = 4, max = 12, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
//    @Pattern(regexp = "^[a-zA-Z0-9]*$" , message = "특수문자, 아이디 문자열 제외")
    private String password;

//    @NotNull
//    @NotBlank
//    @Size(min = 4, max = 12, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
//    @Pattern(regexp = "^[a-zA-Z0-9]*$" , message = "특수문자, 아이디 문자열 제외")
    private String passwordCheck;

    private boolean admin = false;

    private String adminToken = "";
}