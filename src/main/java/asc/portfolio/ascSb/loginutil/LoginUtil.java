package asc.portfolio.ascSb.loginutil;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {
    // 암호화를 위해 id와 pw 두개 다 사용
    public String encryptPassword(String id, String pw) throws Exception{
        if(pw == null){
            return "";
        }
        byte[] hashValue = null;
        // MD5, SHA를 이용한 알고리즘을 사용하기 위한 MessageDigest 클래스
        // 암호화에는 'SHA-512'를 사용
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        md.update(id.getBytes()); // 객체내의 digest값을 갱신
        hashValue = md.digest(pw.getBytes()); // digest() => 최종 값 호출
        return new String(Base64.encodeBase64(hashValue));
    }
}
