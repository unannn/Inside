package unannn.inside.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.recruitment.RecruitmentDto;
import unannn.inside.web.dto.user.JoinDto;
import unannn.inside.web.dto.user.UserDto;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserDto createUserFormAttribute(User user) {
        User loginUser = userRepository.save(user);  //준영속 상태의 엔티티를 다시 영속상태로 변경
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

    @Transactional
    public List<RecruitmentDto> getUserRecruitments(User user) {
        user = userRepository.save(user);
        List<Recruitment> recruitments = user.getRecruitments();
        if(recruitments.isEmpty()){
            return new ArrayList<RecruitmentDto>();
        }

        return user.getRecruitments().stream()
                .map(RecruitmentDto::toDto)
                .collect(Collectors.toList());
    }

    /*
     * 새로운 모집공고 생성, 업데이트된 모집공고 리스트 반환
     * */
    @Transactional
    public List<RecruitmentDto> addRecruitment(User user) {
        LocalDateTime defaultTime = LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0);

        Recruitment newRecruitment = Recruitment.builder()
                .startTime(defaultTime)
                .endTime(defaultTime)
                .title("새 모집 공고 " + (user.getRecruitments().size() + 1)) //(현재 생성한 모집공고 개수 + 1) 번째의 모집공고라는 의미
                .description("")
                .build();

        user.register(newRecruitment);

        return user.getRecruitments().stream()
                .map(RecruitmentDto::toDto)
                .collect(Collectors.toList());
    }
}
