
package com.codingworld.encryptdecryptrsa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptDecryptRSAController {

  @Autowired
  EncryptDecryptService encryptDecryptService;

  @PostMapping("/encrpyt")
  public String encryptMessage(@RequestBody String plainString) {
    return encryptDecryptService.encryptMessage(plainString);
  }
}
