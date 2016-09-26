package com.github.akann;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * RomanNumeral converts numbers to Roman Numerals and visa versa
 *
 * @author  Akan Nkweini
 */

public class RomanNumerals {

    protected final Map<String, Integer> data = new LinkedHashMap<>();

    public RomanNumerals() {
        data.put("I", 1);
        data.put("IV", 4);
        data.put("V", 5);
        data.put("IX", 9);
        data.put("X", 10);
        data.put("XL", 40);
        data.put("L", 50);
        data.put("XC", 90);
        data.put("C", 100);
        data.put("CD", 400);
        data.put("D", 500);
        data.put("CM", 900);
        data.put("M", 1000);
    }

    public static void main(String[] args) {
        System.out.println();

        RomanNumerals romanNumerals = new RomanNumeralsExtended();
        for (String arg: args) {
            if (arg.matches("^[0-9]+$"))
                System.out.printf("Converted number [%s] to [%s]\n", arg, romanNumerals.convert(Integer.valueOf(arg)));
            else
                System.out.printf("Converted letters [%s] to [%s]\n", arg, romanNumerals.convert(arg));
        }

        System.out.println();

    }

    public int convert(final String letters) {
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

    public String convert(final int number) {
        return convert(number, "");
    }

    private String convert(final int number, final String accumulator) {
        Optional<String> letters = getLetters(number);
        String roman = accumulator;

        if (letters.isPresent()) {
            roman += letters.get();
        } else {

            int highestDecimal = findHighestDecimal(number);

            //No need to check if present here.
            //If not present findHighestDecimal above will throw RuntimeException
            roman += getLetters(highestDecimal).get();

            int remainder = number - highestDecimal;

            if (remainder > 0) {
                return convert(remainder, roman);
            }
        }

        return roman;
    }

    private int findHighestDecimal(int number) {
        return data.values().stream()
                .filter(x -> x < number)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Invalid number: " + number));

    }

    private int getDecimal(String letter) {
        if ( ! data.containsKey(letter) )
            throw new RuntimeException("Invalid letter: " + letter);

        return data.get(letter);
    }

    private Optional<String> getLetters(int number) {
        //data is instance of LinkedHashMap maintaining order
        //This means 1000 will always return M and not i
        return data.entrySet()
                .stream()
                .filter(e -> e.getValue() == number)
                .map(Map.Entry::getKey)
                .findFirst()
                ;

    }


}
