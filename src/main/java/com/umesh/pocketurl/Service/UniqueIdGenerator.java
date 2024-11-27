package com.umesh.pocketurl.Service;

import org.springframework.stereotype.Component;


import java.util.Random;

@Component
public class UniqueIdGenerator {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String generateUniqueId() {
        Random random = new Random();

    
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            letters.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

 
        String numbers = String.format("%03d", random.nextInt(1000));

        return letters.toString() + numbers;
    }
}
