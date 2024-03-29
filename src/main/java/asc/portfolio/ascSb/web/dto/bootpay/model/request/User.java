package asc.portfolio.ascSb.web.dto.bootpay.model.request;


public class User {
    public String id; // 개발사에서 관리하는 회원 고유 id
    public String username; //구매자 이름
    public String email; // 구매자 email
    public String phone; //01012341234
    public int gender; //0:여자, 1:남자
    public String area; // 서울|인천|대구|광주|부산|울산|경기|강원|충청북도|충북|충청남도|충남|전라북도|전북|전라남도|전남|경상북도|경북|경상남도|경남|제주|세종|대전 중 택 1
    public String birth; // 생일 901004
}
