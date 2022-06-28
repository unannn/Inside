package unannn.inside.web.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.user.JoinDto;
import unannn.inside.web.service.UserService;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser>{

    private final UserService userService;
    private final UserRepository userRepository;

    public WithMockCustomUserSecurityContextFactory(UserService userservice, UserRepository userRepository) {
        this.userService = userservice;
        this.userRepository = userRepository;
    }

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        PrincipalDetails principal = new PrincipalDetails(joinUser("user","password") ,null);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }

    @Transactional
    User joinUser(String username, String password) {

        JoinDto joinUserDto = JoinDto.builder()
                .username(username)
                .password(password)
                .verifyPassword(password)
                .name("이윤환")
                .email("woooia1@gmail.com")
                .build();

        userService.join(joinUserDto);

        User user = userRepository.findByUsername("user");
//        user.register(Recruitment.builder()
//                        .title("새 모집공고 1")
//                        .description("")
//                        .endTime(null)
//                        .startTime(null)
//                .build());
        return user;
    };

}
