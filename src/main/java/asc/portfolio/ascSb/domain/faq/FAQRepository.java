package asc.portfolio.ascSb.domain.faq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ, Long>, FAQCustomRepository {

}
