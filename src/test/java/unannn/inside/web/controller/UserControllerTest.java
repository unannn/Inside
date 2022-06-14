package unannn.inside.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import unannn.inside.web.dto.LoginDto;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    PrincipalOauth2UserService principalOauth2UserService;

    @MockBean
    UserRepository userRepository;

    @Test
    @WithMockUser
    public void loginPageTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(model().attributeExists("loginDto"));
    }

    @Test
    public void loginOk() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "asdf").param("password", "asdf"))
                .andExpect(status().isOk());
    }
}