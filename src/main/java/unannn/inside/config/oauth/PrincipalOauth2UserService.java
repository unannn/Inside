package unannn.inside.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    //구글로 부터 받은 userRequest 데이터에 대한 후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        /*
            구글 로그인 버튼 클릭 -> 구글로그인 창 -> 로그인 완료하면 코드 리턴(OAtuh-Client라이브러리가 받음) ->
            코드를 통해Access Token 을 요청

             UserRequest 정보 -> 회원 프로필을 받아야함(loadUser함수를 통해)
         */

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId;
        String password = UUID.randomUUID().toString();
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            userEntity = User.builder()
                    .username(username)
                    .encodedPassword(password)
                    .email(email)
                    .phoneNumber(null)
                    .build();
        }
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
