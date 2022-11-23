package asc.portfolio.ascSb.domain.cafe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long>, CafeCustomRepository {

  Optional<Cafe> findByCafeName(String cafeName);
}
