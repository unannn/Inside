package unannn.inside.web.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import unannn.inside.config.SecurityConfig;
import unannn.inside.config.oauth.PrincipalOauth2UserService;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.JoinDto;
import unannn.inside.web.dto.LoginDto;
import unannn.inside.web.service.UserService;

import javax.persistence.EntityManager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;


    @BeforeEach
    void 계정_생성() {
        String username = "asdfasdf";
        String password = "asdfasdf";

        JoinDto joinUserDto = JoinDto.builder()
                .username(username)
                .password(password)
                .verifyPassword(password)
                .name("이윤환")
                .email("woooia1@gmail.com")
                .build();

        userService.join(joinUserDto);
    }

    @Test
    public void loginPageTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(model().attributeExists("loginDto"));
    }

    @Test
    public void loginOk() throws Exception {

        String username = "asdfasdf";
        String password = "asdfasdf";

        mockMvc.perform(formLogin("/login")
                        .user(username)
                        .password(password))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));

    }

}