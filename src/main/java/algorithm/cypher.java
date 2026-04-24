package algorithm;


import java.util.*;

public class cypher {
    public static void main(String[] args) {
        String text = "APS ZU BMS THAAMT KB SOP CHAAPJ MQ LPUWHKX. K UHJ SM JMZ SMLHJ VJ QXKPBLU -- UM PCPB SOMZDO TP\n" +
                "QHEP SOP LKQQKEZASKPU MQ SMLHJ HBL SMVMXXMT, K USKAA OHCP H LXPHV. KS KU H LXPHV LPPWAJ XMMSPL\n" +
                "KB SOP HVPXKEHB LXPHV. K OHCP H LXPHV SOHS MBP LHJ SOKU BHSKMB TKAA XKUP ZW HBL AKCP MZS SOP SXZP\n" +
                "VPHBKBD MQ KSU EXPPL: \"TP OMAL SOPUP SXZSOU SM IP UPAQ-PCKLPBS, SOHS HAA VPB HXP EXPHSPL PGZHA.\"\n" +
                "K OHCP H LXPHV SOHS MBP LHJ MB SOP XPL OKAAU MQ DPMXDKH SOP UMBU MQ QMXVPX UAHCPU HBL SOP UMBU\n" +
                "MQ QMXVPX UAHCP MTBPXU TKAA IP HIAP SM UKS LMTB SMDPSOPX HS SOP SHIAP MQ IXMSOPXOMML. K OHCP H\n" +
                "LXPHV SOHS MBP LHJ PCPB SOP USHSP MQ VKUUKUUKWWK, H USHSP UTPASPXKBD TKSO SOP OPHS MQ KBFZUSKEP,\n" +
                "UTPASPXKBD TKSO SOP OPHS MQ MWWXPUUKMB, TKAA IP SXHBUQMXVPL KBSM HB MHUKU MQ QXPPLMV HBL\n" +
                "FZUSKEP. K OHCP H LXPHV SOHS VJ QMZX AKSSAP EOKALXPB TKAA MBP LHJ AKCP KB H BHSKMB TOPXP SOPJ TKAA\n" +
                "BMS IP FZLDPL IJ SOP EMAMX MQ SOPKX URKB IZS IJ SOP EMBSPBS MQ SOPKX EOHXHESPX. K OHCP H LXPHV\n" +
                "SMLHJ. K OHCP H LXPHV SOHS MBP LHJ LMTB KB HAHIHVH, TKSO KSU CKEKMZU XHEKUSU, TKSO KSU DMCPXBMX\n" +
                "OHCKBD OKU AKWU LXKWWKBD TKSO SOP TMXLU MQ KBSPXWMUKSKMB HBL BZAAKQKEHSKMB -- MBP LHJ XKDOS\n" +
                "SOPXP KB HAHIHVH AKSSAP IAHER IMJU HBL IAHER DKXAU TKAA IP HIAP SM FMKB OHBLU TKSO AKSSAP TOKSP\n" +
                "IMJU HBL TOKSP DKXAU HU UKUSPXU HBL IXMSOPXU. K OHCP H LXPHV SMLHJ. K OHCP H LXPHV SOHS MBP LHJ\n" +
                "PCPXJ CHAAPJ UOHAA IP PNHASPL, HBL PCPXJ OKAA HBL VMZBSHKB UOHAA IP VHLP AMT, SOP XMZDO WAHEPU\n" +
                "TKAA IP VHLP WAHKB, HBL SOP EXMMRPL WAHEPU TKAA IP VHLP USXHKDOS, HBL SOP DAMXJ MQ SOP AMXL\n" +
                "UOHAA IP XPCPHAPL HBL HAA QAPUO UOHAA UPP KS SMDPSOPX.";
        calculateFrequency(text);
        List<Character> alphabetFrequency = Arrays.asList('E', 'A', 'T', 'I', 'O', 'L', 'H', 'R', 'N', 'S', 'D', 'W', 'M', 'F', 'Y', 'V', 'B', 'C', 'G', 'U', 'P', 'J', 'K', 'Q', 'X', 'Z');

        String decryptedText = decryptUsingFrequency(text.toLowerCase(), alphabetFrequency);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String decryptUsingFrequency(String encryptedText, List<Character> alphabetFrequency) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : encryptedText.toCharArray()) {
            // 알파벳만 빈도수 계산
            if (Character.isLetter(c)) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        // 빈도수에 따라 내림차순 정렬
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<Character, Character> decryptMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            decryptMap.put(list.get(i).getKey(), alphabetFrequency.get(i));
        }

        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                decryptedText.append(decryptMap.get(c));
            } else {
                decryptedText.append(c); // 알파벳이 아닌 경우 원본 유지
            }
        }

        return decryptedText.toString();
    }

    public static void calculateFrequency(String text) {
        // 알파벳 빈도수를 저장할 HashMap 생성
        Map<Character, Integer> frequency = new HashMap<>();

        // 입력된 문자열을 순회하며 알파벳의 빈도수 계산
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            }
        }

        // 결과를 값에 따라 내림차순으로 정렬하여 출력
        frequency.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}

