package com.sparta.rodeo.service;

import com.sparta.rodeo.dto.SignupRequestDto;
import com.sparta.rodeo.models.User;
import com.sparta.rodeo.models.UserRoleEnum;
import com.sparta.rodeo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @Test
    @Nested
    @DisplayName("닉네임 최소 3자 이상")
    void registerUserIdCheck() {


        String username = "ac";
        String password = "wotjd";
        String passwordCheck = "wotjd";
        String email = "";
        boolean admin = false;
        String adminToken = "";


        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );

        String result = userService.registerUser(requestDto);

        assertEquals("아이디를 3자 이상 입력하세요",result);
    }

    @Test
    @DisplayName("닉네임 특수문자")
    void registerUserIdCheck2() {
        String username = "$%^";
        String password = "wotjd";
        String passwordCheck = "wotjd";
        String email = "";
        boolean admin = false;
        String adminToken = "";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );

        String result = userService.registerUser(requestDto);

        assertEquals("알파벳 대소문자와 숫자로만 입력하세요",result);

    }

    @Test
    @Nested
    @DisplayName("비밀번호 4자 이상")
    void passwordCheck() {
        String username = "1234";
        String password = "wot";
        String passwordCheck = "wot";
        String email = "";
        boolean admin = false;
        String adminToken = "";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );

        String result = userService.registerUser(requestDto);

        assertEquals("비밀번호를 4자 이상 입력하세요",result);
    }

    @Test
    @DisplayName("닉네임과 같은 문자열")
    void passwordSameId() {
        String username = "wotjd";
        String password = "wotjd";
        String passwordCheck = "wotjd";
        String email = "";
        boolean admin = false;
        String adminToken = "";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );

        String result = userService.registerUser(requestDto);

        assertEquals("비밀번호에 아이디를 포함할 수 없습니다.",result);
    }

    @Test
    @Nested
    @DisplayName("비밀번호 일치")
    void passwordSamePasswordCheck() {
        String username = "123";
        String password = "wotj";
        String passwordCheck = "wotjd";
        String email = "";
        boolean admin = false;
        String adminToken = "";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );

        String result = userService.registerUser(requestDto);

        assertEquals("비밀번호가 일치하지 않습니다",result);
    }

    @Test
    @DisplayName("비밀번호 일치")
    void passwordSamePasswordCheck2() {
        String username = "123";
        String password = "wotjd";
        String passwordCheck = "wotj";
        String email = "";
        boolean admin = false;
        String adminToken = "";

        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );

        String result = userService.registerUser(requestDto);

        assertEquals("비밀번호가 일치하지 않습니다",result);
    }


    //이부분 save 후에 중복이 안되는 건지 알려주세요
//    @Test
//    @Nested
//    @DisplayName("중복된 아이디")
//    void duplicateId() {
//        String username = "123";
//        String password = "wotjd";
//        String passwordCheck = "wotjd";
//        String email = "";
//        boolean admin = false;
//        String adminToken = "";
//        UserRoleEnum role = UserRoleEnum.USER;
//
//        User user = new User(username, password, role);
//        userRepository.save(user);
//
//        SignupRequestDto requestDto = new SignupRequestDto(username, password, passwordCheck,email ,admin,adminToken );
//
//        String result = userService.registerUser(requestDto);
//
//        assertEquals("중복된 id입니다.",result);
//    }



}