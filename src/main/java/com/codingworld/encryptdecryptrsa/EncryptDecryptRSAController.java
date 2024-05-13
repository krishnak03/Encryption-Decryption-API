
package com.codingworld.encryptdecryptrsa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptDecryptRSAController {

  @Autowired
  final EncryptDecryptService encryptDecryptService;

  public EncryptDecryptRSAController(EncryptDecryptService encryptDecryptService) {
    this.encryptDecryptService = encryptDecryptService;
  }

  @PostMapping("/StringToKey")
  public void convertToKey(@RequestBody String privateKeyString){
    encryptDecryptService.initFromStrings(privateKeyString);
  }

  @PostMapping("/decrypt")
  public String decryptMessage(@RequestBody String encryptString) {
    return encryptDecryptService.decryptMessage(encryptString);
  }
}
