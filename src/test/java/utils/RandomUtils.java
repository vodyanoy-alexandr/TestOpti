package utils;


import java.util.Random;

public class RandomUtils {
    private static final String LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String NUMBERS = "0123456789";

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder nameBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(LETTERS.length());
            char randomChar = LETTERS.charAt(randomIndex);
            nameBuilder.append(randomChar);
        }

        return nameBuilder.toString();
    }

    public static int generateRandomInteger(int length) {
        Random random = new Random();
        StringBuilder numberBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(NUMBERS.length());
            char randomChar = NUMBERS.charAt(randomIndex);
            numberBuilder.append(randomChar);
        }

        return Integer.parseInt(numberBuilder.toString());
    }
}