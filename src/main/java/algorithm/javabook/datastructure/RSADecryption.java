package algorithm.javabook.datastructure;

import java.math.BigInteger;

public class RSADecryption {
    public static void main(String[] args) {
        // 주어진 값들
        BigInteger n = new BigInteger("3174654383");
        BigInteger e = new BigInteger("65537");
        BigInteger C = new BigInteger("2487688703");

        // n을 인수분해하여 p와 q를 찾는 과정
        BigInteger p = BigInteger.ZERO;
        BigInteger q = BigInteger.ZERO;
        // 2부터 시작 n의 제곱근까지 증가하면서, n을 i로 나눌 수 있는지 확인 (나누면 p, q 있음)
        BigInteger i = new BigInteger("2");
        while (i.compareTo(n.sqrt()) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                p = i;
                q = n.divide(i);
                break;
            }
            i = i.add(BigInteger.ONE);
        }
        // 오일러 피 함수: (p-1)(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // 개인 키 d 계산: ed = 1 mod phi
        BigInteger d = e.modInverse(phi);
        // 암호문 C를 평문 M으로 변환: C^d mod n
        BigInteger M = C.modPow(d, n);
        // 바이트 배열로 변환 후, 평문을 텍스트 형식으로 얻음
        String plaintext = new String(M.toByteArray());

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Private Key d: " + d);
    }


}
