package unannn.inside.web.controller;

import com.nimbusds.common.contenttype.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import unannn.inside.web.dto.JoinDto;
import unannn.inside.web.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.thymeleaf.spring5.util.FieldUtils.globalErrors;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void 로그인_페이지() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(model().attributeExists("loginDto"));
    }

    @Test
    public void 로그인_성공() throws Exception {

        String username = "asdfasdf";
        String password = "asdfasdf";

        joinUser(username,password);

        mockMvc.perform(formLogin("/login")
                        .user(username)
                        .password(password))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void 로그인_실패_비밀번호() throws Exception {

        String username = "asdfasdf";
        String password = "asdfasdf";

        joinUser(username,password);


        mockMvc.perform(formLogin("/login")
                        .user(username)
                        .password("틀린비밀번호"))
                .andDo(print())
                .andExpect(unauthenticated())
                .andExpect(forwardedUrl("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인_실패_입력X() throws Exception {

        String username = "asdfasdf";
        String password = "asdfasdf";

        joinUser(username,password);

        mockMvc.perform(formLogin("/login")
                        .user("")
                        .password(""))
                .andDo(print())
                .andExpect(unauthenticated())
                .andExpect(forwardedUrl("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_페이지() throws Exception {
        mockMvc.perform(get("/join"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("joinDto"))
                .andExpect(view().name("joinFrom"));
    }

    @Test
    public void 회원가입_성공() throws Exception {
        mockMvc.perform(post("/join").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .param("username", "asdfasdf")
                        .param("password", "asdfasdf")
                        .param("verifyPassword", "asdfasdf")
                        .param("name", "이윤환")
                        .param("email", "woooia1@gmail.com"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void 회원가입_실패_비밀번호불일치() throws Exception {
        mockMvc.perform(post("/join").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .param("username", "asdfasdf")
                        .param("password", "asdfasdf")
                        .param("verifyPassword", "다른비밀번호")
                        .param("name", "이윤환")
                        .param("email", "woooia1@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("joinForm"))
                .andExpect(model().attributeExists("joinDto"));
//                .andDo(r -> r.getModelAndView().getModel().);

    }

    @Test
    public void 회원가입_실패_이름미입력() throws Exception {
        mockMvc.perform(post("/join").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .param("username", "asdfasdf")
                        .param("password", "asdfasdf")
                        .param("verifyPassword", "asdfasdf")
                        .param("name", "")
                        .param("email", "woooia1@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("joinForm"))
                .andExpect(model().attributeExists("joinDto"))
                .andExpect(model().attributeHasFieldErrors("joinDto", "name"));
    }

    void joinUser(String username, String password) {

        JoinDto joinUserDto = JoinDto.builder()
                .username(username)
                .password(password)
                .verifyPassword(password)
                .name("이윤환")
                .email("woooia1@gmail.com")
                .build();

        userService.join(joinUserDto);
    }
}