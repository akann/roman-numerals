package com.github.akann;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * RomanNumeral converts numbers to Roman Numerals and visa versa
 *
 * @author  Akan Nkweini
 */

public class RomanNumeral {

    private static final Map<String, Integer> data = new HashMap<>();

    static {
        data.put("I",  1);
        data.put("IV", 4);
        data.put("V",  5);
        data.put("IX", 9);
        data.put("X",  10);
        data.put("XL", 40);
        data.put("L",  50);
        data.put("XC", 90);
        data.put("C",  100);
        data.put("CD", 400);
        data.put("D",  500);
        data.put("CM", 900);
        data.put("M",  1000);
    }

    public static void main(String[] args) {
        System.out.println();

        for (String arg: args) {
            arg = arg.toUpperCase();
            if (arg.matches("^[0-9]+$"))
                System.out.printf("Converted number [%s] to [%s]\n", arg, RomanNumeral.convert(Integer.valueOf(arg)));
            else
                System.out.printf("Converted letters [%s] to [%s]\n", arg, RomanNumeral.convert(arg));
        }

        System.out.println();

    }

    public static int convert(String letters) {
        String[] chars = letters.split("");

        int prev;
        int sum = 0;
        int curr = 0;

        for (int i = chars.length; i > 0; ) {
            prev = curr;
            i--;
            curr = getDecimal(chars[i]);

            if (prev > curr) {
                sum -= curr;
            } else {
                sum += curr;
            }
        }

        return sum;
    }

    public static String convert(int number) {
        return convert(number, new StringBuilder());
    }

    private static String convert(int number, StringBuilder stringBuilder) {
        String letters = getLetters(number);

        if (letters == null) {
            int highestDecimal = findHighestDecimal(number);

            stringBuilder.append(getLetters(highestDecimal));

            int remainder = number - highestDecimal;

            if (remainder > 0) {
                convert(remainder, stringBuilder);
            }
        } else {
            stringBuilder.append(letters);
        }

        return stringBuilder.toString();
    }

    private static int findHighestDecimal(int number) {
        return data.values().stream()
                .filter(x -> x < number)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Could not find number: " + number));

    }

    private static int getDecimal(String letter) {
        return data.get(letter.toUpperCase());
    }

    private static String getLetters(int number) {
        return data.entrySet()
                .stream()
                .filter(e -> e.getValue() == number)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null)
                ;

    }


}
