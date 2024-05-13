package com.codingworld.encryptdecryptrsa;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.springframework.stereotype.Service;

@Service
public class EncryptDecryptService {

  public static Map<String, Object> map = new HashMap<>();

  private String encode(byte[] data){
    return Base64.getEncoder().encodeToString(data);
  }

  public void createKeys() {
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(4096);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      System.err.println("ENCODED Public key\n "+ encode(publicKey.getEncoded()));
      System.err.println("ENCODED Private key\n "+ encode(privateKey.getEncoded()));
      map.put("publicKey", publicKey);
//      map.put("privateKey", privateKey);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String encryptMessage(String plainText) {

    try {
      createKeys();
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
      PublicKey publicKey = (PublicKey) map.get("publicKey");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] encrypt = cipher.doFinal(plainText.getBytes());
      return new String(Base64.getEncoder().encodeToString(encrypt));
    } catch (Exception e) {

    }
    return "";
  }
}
