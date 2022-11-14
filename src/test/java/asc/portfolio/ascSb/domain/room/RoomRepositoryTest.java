package asc.portfolio.ascSb.domain.room;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomRepositoryTest {

    private final TestEntityManager testEntityManager;

    public RoomRepositoryTest(TestEntityManager testEntityManager) {
        this.testEntityManager = testEntityManager;
    }

    @DisplayName("Room의 전체 테이블 조회")
    @Test
    void querydsl_showRoom() {

        EntityManager entityManager = testEntityManager.getEntityManager();

        JPAQuery<Room> query = new JPAQuery<>(entityManager);
        QRoom qRoom = new QRoom("p");

        List<Room> roomList = query
                .from(qRoom)
                .orderBy(qRoom.id.desc())
                .fetch();

        assertThat(roomList).hasSize(3);

    }
}
