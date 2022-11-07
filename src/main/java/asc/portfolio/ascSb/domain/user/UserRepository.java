package asc.portfolio.ascSb.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  public User findByLoginId(String loginId);
}
