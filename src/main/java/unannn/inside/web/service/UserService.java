package unannn.inside.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.JoinDto;
import unannn.inside.web.dto.UserDto;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserDto createUserFormAttribute(User user) {
        User loginUser = em.merge(user);  //준영속 상태의 엔티티를 다시 영속상태로 변경
        return new UserDto(loginUser);
    }

    @Transactional
    public void join(JoinDto joinDto) {
        User user = User.builder()
                .username(joinDto.getUsername())
                .name(joinDto.getName())
                .encodedPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .email(joinDto.getEmail())
                .build();

        userRepository.save(user);
    }
}
