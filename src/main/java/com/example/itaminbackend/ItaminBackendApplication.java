package com.example.itaminbackend;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItaminBackendApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ItaminBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword("tkfkd1617!"); //2번 설정의 암호화 키를 입력

        String enc1 = pbeEnc.encrypt("jJf55Mrx98Mt2FRkK9ug"); //암호화 할 내용
        String enc2= pbeEnc.encrypt("whzkdH66Fa"); //암호화 할 내용
        System.out.println("enc = " + enc1); //암호화 한 내용을 출력
        System.out.println("enc = " + enc2); //암호화 한 내용을 출력

        //테스트용 복호화
        String des1 = pbeEnc.decrypt(enc1);
        System.out.println("des = " + des1);

        String des2 = pbeEnc.decrypt(enc2);
        System.out.println("des = " + des2);

    }

}
