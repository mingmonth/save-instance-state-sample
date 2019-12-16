package yskim.sample.saveinstancestatesample;

import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSATest {
    static private String PRIVATE_KEY = "MIICXQIBAAKBgQCh8mGWErgNI+FPYsNmLbZXM17mRjAOJR3Ocebw1ZqjVsiZAkgq\n" +
            "ZfyB+D/4bcrDFo4RAJPe3OPgDYMWtIn2nuqN6FdonSBelwgq40x3DcCpUK6m3OCS\n" +
            "UiIHhXYhk2li6vzX7boygPvgT/AI4qMolcTWtI6SQwEkZ+11Fe+7829vqQIDAQAB\n" +
            "AoGARYBePSffcVQ6t/Us4scvgBD941mv+gIX3qencyhY60ytXm1KR1fpnGXMHnQQ\n" +
            "FtWxNr7Gt1iZO2jRH4dvxjXBd2K0+eXosBtvwyZJpyX19dTeD7xG7fgXtmm4LZ+u\n" +
            "c3+uKb0b9NsgVS+Ye+cYDXzpmhOBkWwJCvFMeVgqcolS+QECQQDQSAt2I9yCUt+7\n" +
            "kcSNUUaElnYgz0ZSiyPZMtFMbrQgYUIjOk/Q32BSzlFE5d2WrpHCSRKzhaIxOhKZ\n" +
            "onYPEgDZAkEAxwzBVoLaHQKi3FJlB6SPpNRy1F0TuJ2ewizIg74poSSATIg0d73W\n" +
            "XdCpCezMsJBoZbR6d471qLdm4pKa2eujUQJAc28aexWRBuGrgiddyfy7moxRWAnI\n" +
            "rcLT0XLqsPPB/RgqMaAF0HucxQf33rmPQA3sVsNtC/YKWEIZQPDbgaq2sQJBALmc\n" +
            "M00wh6M1qhg43OzbjvbtBIbypEkq8beveusekEMrflffSs29v/+La5VNszGR90ef\n" +
            "m/OQ45KIMvBkNPGwwYECQQCPZxiMp0O8upcA6pTHSV2u02rDvVSmV5ZEVPBZzvnP\n" +
            "S7fvycTXO/Fba7mbGUSuNTDyazx7SBj9tvEiVs2Gaofn";
    static private String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCh8mGWErgNI+FPYsNmLbZXM17m\n" +
            "RjAOJR3Ocebw1ZqjVsiZAkgqZfyB+D/4bcrDFo4RAJPe3OPgDYMWtIn2nuqN6Fdo\n" +
            "nSBelwgq40x3DcCpUK6m3OCSUiIHhXYhk2li6vzX7boygPvgT/AI4qMolcTWtI6S\n" +
            "QwEkZ+11Fe+7829vqQIDAQAB";
    //"kIIo6TF6V0pv6aFSPVUadsTdmW64BML5CQxAoKte1KYdQBp459oVgDs8WuoY+P+7xtRRxYFE6ji08QRje9FEkBlUrVLIUMMtxWHzdrBG7x6g7uJ56FmGfkjt5cu17g13Jippt7rsHDx+3+rZTGrf1TqKcoXUaNM4yojj45Ur/rg="

    public static String encrypt(String input) throws Exception {
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(Base64.decode(PUBLIC_KEY, Base64.DEFAULT));
        Key encryptionKey = keyFactory.generatePublic(pubSpec);
        Cipher rsa = Cipher.getInstance("RSA/None/PKCS1Padding");
        rsa.init(Cipher.ENCRYPT_MODE, encryptionKey);
        return Base64.encodeToString(rsa.doFinal(input.getBytes("utf-8")), Base64.DEFAULT);
    }

    public static String decrypt(String input) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(Base64.decode(PRIVATE_KEY, Base64.DEFAULT));
        Key decryptionKey = keyFactory.generatePrivate(privSpec);
        Cipher rsa = Cipher.getInstance("RSA/None/PKCS1Padding");
        rsa.init(Cipher.DECRYPT_MODE, decryptionKey);
        return new String(rsa.doFinal(Base64.decode(input, Base64.DEFAULT)), "utf-8");
    }
}
