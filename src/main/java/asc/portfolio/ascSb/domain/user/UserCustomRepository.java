package asc.portfolio.ascSb.domain.user;

public interface UserCustomRepository {

  //TODO findByLoginId 구현.
  public User findByLoginID(String loginId);
}
