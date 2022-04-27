package unannn.inside;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import unannn.inside.domain.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void 데이터베이스테스트() {
        User user = new User("unannn", "0000", "000");
        em.persist(user);
    }
}
