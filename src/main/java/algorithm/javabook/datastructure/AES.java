package algorithm.javabook.datastructure;

import javax.crypto.Cipher; //암호화 복호화 기능 클래스
import javax.crypto.spec.IvParameterSpec; //CBC 모드 암호화하기전 사용 IV
import javax.crypto.spec.SecretKeySpec; //AES암호화키
import java.util.Base64;

public class AES {
    public static void main(String[] args) throws Exception {
        //Base64 인코딩
        String base64Key = "8iE3bf1se6N76HGPP8S0Xw==";
        String base64Iv = "cHml3oX848/0uBwDJtChOA==";
        String base64Ciphertext = "QDr9NZNG9Bgc3TTnfRuqjjzf/" +
                "kVSYwbP7F9mR4GQZ/IneIh7HTc/" +
                "xnwzEeVBcH3pPlIbLFySKZruedJc9X87CGNDJ1f2Dat8BR3Ypbei5Q42xc306/" +
                "AkSuGsjfqbX9/ELxmdKn7MyvY/Jbc0v0AJHV6odgNzygKRRrFJcUIF/50=";
        //Base64에서 디코딩하여 바이트 배열로 반환
        byte[] key = Base64.getDecoder().decode(base64Key);
        byte[] iv = Base64.getDecoder().decode(base64Iv);
        byte[] ciphertext = Base64.getDecoder().decode(base64Ciphertext);
        //Cipher 객체 초기화
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); //AES 알고리즘, CBC 모드, PKC 패딩 방식으로 사용
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES"); //바이트 배열 key를 사용해 AES 알고리즘 비밀키 생성
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); //초기화 백터 IV 설정

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        //복호화
        String plaintext = new String(cipher.doFinal(ciphertext));
        //결과과
        System.out.println("Decrypted text: " + plaintext);
    }
}