package com.github.akann;

/**
 * RomanNumeral converts numbers to Roman Numerals and visa versa
 *
 * @author  Akan Nkweini
 */
public class RomanNumeralsExtended extends RomanNumerals {

    public RomanNumeralsExtended () {
        data.put("i",  1_000);
        data.put("iv", 4_000);
        data.put("v",  5_000);
        data.put("ix", 9_000);
        data.put("x",  10_000);
        data.put("xl", 40_000);
        data.put("l",  50_000);
        data.put("xc", 90_000);
        data.put("c",  100_000);
        data.put("cd", 400_000);
        data.put("d",  500_000);
        data.put("cm", 900_000);
        data.put("m",  1000_000);
    }
}
