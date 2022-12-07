package asc.portfolio.ascSb.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

  Optional<User> findByLoginId(String loginId);

  User findByNameContains(String name);


}
