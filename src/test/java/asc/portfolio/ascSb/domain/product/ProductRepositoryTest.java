package asc.portfolio.ascSb.domain.product;

import asc.portfolio.ascSb.domain.cafe.CafeRepository;
import asc.portfolio.ascSb.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    ProductRepository productRepository;

}
