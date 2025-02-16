package com.crm.tehnomer.services.userService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Длина пароля должна быть не менее 8 символов.");
        }

        List<Character> password = new ArrayList<>();

        // Добавляем по одному символу из каждой группы (чтобы пароль был надежным)
        password.add(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        password.add(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        password.add(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.add(SPECIAL_CHARS.charAt(RANDOM.nextInt(SPECIAL_CHARS.length())));

        // Добавляем оставшиеся символы случайным образом
        for (int i = 4; i < length; i++) {
            password.add(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        // Перемешиваем символы для случайности
        Collections.shuffle(password, RANDOM);

        // Преобразуем список символов в строку
        StringBuilder passwordStr = new StringBuilder();
        for (char ch : password) {
            passwordStr.append(ch);
        }

        return passwordStr.toString();
    }
}
