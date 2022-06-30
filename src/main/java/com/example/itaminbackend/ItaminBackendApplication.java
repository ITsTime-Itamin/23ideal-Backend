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
}
