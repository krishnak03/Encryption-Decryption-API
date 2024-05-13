package com.codingworld.encryptdecryptrsa;

import com.fasterxml.jackson.databind.ser.Serializers.Base;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class EncryptDecryptService {

  PrivateKey privateKey;
  public static Map<String, Object> map = new HashMap<>();

  private byte[] decode(String data){
    return Base64.getDecoder().decode(data);
  }

  public void initFromStrings(String privateKeyString){
    try{
//            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(publicKeyString));
      PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(privateKeyString));

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");

//            publicKey = keyFactory.generatePublic(keySpecPublic);
      privateKey = keyFactory.generatePrivate(keySpecPrivate);

//            System.out.println("New public key is: "+ publicKey);
      System.out.println("New private key is: "+ privateKey);

//            map.put("publicKey", publicKey);
      map.put("privateKey", privateKey);
    }catch (Exception ignored){}
  }

  public String decryptMessage(String encryptedText) {

    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
      PrivateKey privateKey = (PrivateKey) map.get("privateKey");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
      return new String(decrypt);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}
