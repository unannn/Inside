package unannn.inside.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.config.oauth.provider.GoogleUserInfo;
import unannn.inside.config.oauth.provider.NaverUserInfo;
import unannn.inside.config.oauth.provider.OAuth2UserInfo;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;

import java.util.Map;
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

        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = null;
        if (provider.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
        }else{
            throw new OAuth2AuthenticationException("요청 Provider 미지원");
        }

        User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
        if (userEntity == null) {
            userEntity = User.builder()
                    .username(oAuth2UserInfo.getUsername())
                    .encodedPassword(UUID.randomUUID().toString())
                    .email(oAuth2UserInfo.getEmail())
                    .name(oAuth2UserInfo.getName())
                    .phoneNumber(null)
                    .build();

            //회원가입
            userRepository.save(userEntity);
        }
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
